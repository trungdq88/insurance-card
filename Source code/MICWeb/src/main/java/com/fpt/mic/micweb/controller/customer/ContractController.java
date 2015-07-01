package com.fpt.mic.micweb.controller.customer;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.Paginator;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.model.business.ContractBusiness;
import com.fpt.mic.micweb.model.business.CustomerBusiness;
import com.fpt.mic.micweb.model.dto.CheckoutRequestDto;
import com.fpt.mic.micweb.model.dto.UserDto;
import com.fpt.mic.micweb.model.dto.form.CancelContractDto;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.utils.Constants;
import com.fpt.mic.micweb.utils.DateUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by PhucNguyen on 06/05/15.
 */
@WebServlet(name = "CustomerContractController", urlPatterns = "/customer/contract")
public class ContractController extends AuthController {

    /**
     * Paginator for contract
     */
    Paginator contractPaginator = new Paginator();

    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_CUSTOMER);
    }

    public ResponseObject getNewContract(R r) {
        return new JspPage("customer/contract-new.jsp");
    }

    public ResponseObject getView(R r) {
        final CustomerBusiness customerBusiness = new CustomerBusiness();
        final String customerCode = ((CustomerEntity) getLoggedInUser()).getCustomerCode();

        contractPaginator.setGetItemsCallback(new Paginator.IGetItems() {
            @Override
            public List getItems(int offset, int count) {
                return customerBusiness.getAllContractByCustomer(customerCode, offset, count);
            }
        });
        contractPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
            @Override
            public Long getItemSize() {
                return customerBusiness.getAllContractByCustomerCount(customerCode);
            }
        });

        r.equest.setAttribute("contractPaginator", contractPaginator);
        return new JspPage("customer/contract.jsp");
    }

    public ResponseObject getSearch(R r) {
        String keyword = r.equest.getParameter("keyword");
        if (keyword == null) {
            keyword = "";
        }
        keyword = keyword.trim();

        final String customerCode = ((CustomerEntity) getLoggedInUser()).getCustomerCode();

        final CustomerBusiness customerBusiness = new CustomerBusiness();

        final String finalKeyword = keyword;
        contractPaginator.setGetItemsCallback(new Paginator.IGetItems() {
            @Override
            public List getItems(int offset, int count) {
                return customerBusiness.searchCustomerContractByCode(customerCode, finalKeyword, offset, count);
            }
        });
        contractPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
            @Override
            public Long getItemSize() {
                return customerBusiness.searchCustomerContractByCodeCount(customerCode, finalKeyword);
            }
        });

        r.equest.setAttribute("contractPaginator", contractPaginator);
        return new JspPage("customer/contract.jsp");
    }

    public ResponseObject getContractDetail(R r) {
        CustomerBusiness customerBusiness = new CustomerBusiness();
        String customerCode = ((CustomerEntity) getLoggedInUser()).getCustomerCode();
        String code = r.equest.getParameter("code");
        ContractEntity contract = customerBusiness.getContractDetail(code);
        if (contract == null || contract.getCustomerCode().compareToIgnoreCase(customerCode) != 0) {
            return new RedirectTo("/error/404");
        } else {
            // Save last_modified value for concurrency check
            r.equest.getSession(true).setAttribute(
                    Constants.Session.CONCURRENCY + contract.getContractCode(),
                    contract.getLastModified());

            r.equest.setAttribute("contract", contract);
            return new JspPage("customer/contract-detail.jsp");
        }
    }

    /* Handle canncel contract */
    public ResponseObject postCancelContract(R r) {
        CancelContractDto cancelDto = (CancelContractDto) r.ead.entity(CancelContractDto.class, "cancel");
        cancelDto.setCancelDate(DateUtils.currentDateWithoutTime());

        // Get concurrency data
        Timestamp lastModified = (Timestamp) r.equest.getSession(true).getAttribute(
                Constants.Session.CONCURRENCY + cancelDto.getContractCode());
        cancelDto.setLastModified(lastModified);

        if (cancelDto.getContractCode() == null) {
            return new RedirectTo("/error/404");
        } else {
            CustomerBusiness customerBusiness = new CustomerBusiness();
            List errors = r.ead.validate(cancelDto);
            if (errors.size() > 0) {
                r.equest.setAttribute("validateErrors", errors);
                r.equest.setAttribute("submitted", cancelDto);
                r.equest.setAttribute("contract", customerBusiness.getContractDetail(cancelDto.getContractCode()));
                return new JspPage("customer/contract-detail.jsp");
            } else {
                String mesg = "Yêu Cầu Hủy Thất Bại";
                ContractEntity contract = customerBusiness.cancelContract(cancelDto);
                if (contract != null) {
                    r.equest.setAttribute("contract", contract);
                    return new RedirectTo("contract?action=ContractDetail&code=" + cancelDto.getContractCode());
                } else {
                    r.equest.setAttribute("result", mesg);
                    r.equest.setAttribute("contractCode", cancelDto.getContractCode());
                    return new JspPage("customer/message.jsp");
                }
            }
        }
    }

    /*Payment Contract*/
    public ResponseObject postPayContract(R r) {
        //get parameter
        String contractCode = r.equest.getParameter("txtContractCode");
        HttpSession session = r.equest.getSession();
        session.setAttribute("contractCode", contractCode);
        session.setAttribute("SUCCESS_URL", r.equest.getParameter("successUrl"));
        session.setAttribute("cancel_message", "Bạn đã hủy thanh toán. Xin vui lòng thực hiện lại hoặc đến thanh toán trực tiếp");
        session.setAttribute("redirectLink", "/customer/contract?action=ContractDetail&code=" + contractCode);

        CheckoutRequestDto checkoutRequest = new CheckoutRequestDto();
        checkoutRequest.setPaymentrequest_name(r.equest.getParameter("L_PAYMENTREQUEST_0_NAME0"));
        checkoutRequest.setPaymentrequest_desc(r.equest.getParameter("L_PAYMENTREQUEST_0_DESC0"));
        checkoutRequest.setPaymentrequest_qty(r.equest.getParameter("L_PAYMENTREQUEST_0_QTY0"));
        checkoutRequest.setPaymentrequest_itemamt(r.equest.getParameter("PAYMENTREQUEST_0_ITEMAMT"));
        checkoutRequest.setPaymentrequest_taxamt(r.equest.getParameter("PAYMENTREQUEST_0_TAXAMT"));
        checkoutRequest.setPaymentrequest_amt(r.equest.getParameter("PAYMENTREQUEST_0_AMT"));
        checkoutRequest.setCurrencycodetype(r.equest.getParameter("currencyCodeType"));
        checkoutRequest.setPaymenttype(r.equest.getParameter("paymentType"));
        checkoutRequest.setPaymentrequest_amt_l(r.equest.getParameter("PAYMENTREQUEST_0_AMT"));
        return new RedirectTo("/public/checkout?action=checkout&checkout=true&" + checkoutRequest.getQueryString());

    }

    /**
     * Renew contract, called when user click "Gia han hop dong" button
     */
    public ResponseObject postRenewContract(R r) {
        //get parameter
        String contractCode = r.equest.getParameter("txtContractCode");

        // Validate: check if the remaining days is greater than 2 months
        ContractBusiness contractBusiness = new ContractBusiness();
        if (!contractBusiness.isRenewable(contractCode)) {
            return showCustomError(r, contractCode, contractBusiness, "Không thể gia hạn hợp đồng còn giá trị trên 2 tháng");
        }

        Timestamp newExpiredDate = contractBusiness.getNewExpiredDate(contractCode);
        if (newExpiredDate == null) {
            return showCustomError(r, contractCode, contractBusiness, "Hợp đồng không tồn tại hoặc trạng thái không hợp lệ");
        }

        HttpSession session = r.equest.getSession();

        session.setAttribute("contractCode", contractCode);
        session.setAttribute("newExpiredDate", newExpiredDate);
        session.setAttribute("SUCCESS_URL", r.equest.getParameter("successUrl"));
        session.setAttribute("cancel_message", "Bạn đã hủy thanh toán. Xin vui lòng thực hiện lại hoặc đến thanh toán trực tiếp");
        session.setAttribute("redirectLink", "/customer/contract?action=ContractDetail&code=" + contractCode);


        CheckoutRequestDto checkoutRequest = new CheckoutRequestDto();
        checkoutRequest.setPaymentrequest_name(r.equest.getParameter("L_PAYMENTREQUEST_0_NAME0"));
        checkoutRequest.setPaymentrequest_desc(r.equest.getParameter("L_PAYMENTREQUEST_0_DESC0"));

        checkoutRequest.setPaymentrequest_qty(r.equest.getParameter("L_PAYMENTREQUEST_0_QTY0"));
        checkoutRequest.setPaymentrequest_itemamt(r.equest.getParameter("PAYMENTREQUEST_0_ITEMAMT"));
        checkoutRequest.setPaymentrequest_taxamt(r.equest.getParameter("PAYMENTREQUEST_0_TAXAMT"));
        checkoutRequest.setPaymentrequest_amt(r.equest.getParameter("PAYMENTREQUEST_0_AMT"));
        checkoutRequest.setCurrencycodetype(r.equest.getParameter("currencyCodeType"));
        checkoutRequest.setPaymenttype(r.equest.getParameter("paymentType"));
        checkoutRequest.setPaymentrequest_amt_l(r.equest.getParameter("PAYMENTREQUEST_0_AMT"));


        return new RedirectTo("/public/checkout?action=checkout&checkout=true&" + checkoutRequest.getQueryString());
    }

    private ResponseObject showCustomError(R r, String contractCode, ContractBusiness contractBusiness, String errorMessage) {
        r.equest.setAttribute("validateErrors",
                Collections.singletonList(errorMessage));
        r.equest.setAttribute("contract", contractBusiness.getContract(contractCode));
        return new JspPage("customer/contract-detail.jsp");
    }

    public ResponseObject getActivePayContract(R r) {
        String url = "public/return.jsp";
        HttpSession session = r.equest.getSession(true);
        if (session.getAttribute("RESULT") == null
                || session.getAttribute("contractCode") == null
                || session.getAttribute("amountVND") == null
                || session.getAttribute("ACK") == null) {
            return new RedirectTo("/error/404");
        } else {
            Map<String, String> results = new HashMap<String, String>();
            results.putAll((Map<String, String>) session.getAttribute("RESULT"));

            String contractCode = (String) session.getAttribute("contractCode");

            r.equest.setAttribute("amountVND", session.getAttribute("amountVND"));
            r.equest.setAttribute("redirectLink", "/customer/contract?action=ContractDetail&code=" + contractCode);
            r.equest.setAttribute("result", results);
            r.equest.setAttribute("ack", session.getAttribute("ACK"));
            //renew contract by customer
            CustomerBusiness customerBusiness = new CustomerBusiness();

            // Get concurrency data
            Timestamp lastModified = (Timestamp) r.equest.getSession(true).getAttribute(
                    Constants.Session.CONCURRENCY + contractCode);

            // Concurrency check
            if (customerBusiness.isContractChanged(contractCode, lastModified)) {
                r.equest.setAttribute("message", "Thông tin hợp đồng đã bị thay đổi bởi một " +
                        "người khác, thanh toán đã bị huỷ. <br/>" +
                        "Vui lòng lưu lại mã giao dịch để đối chiếu trong trường hợp hoàn lại tiền");
                // TODO: refund?
            } else {
                // Concurrency check success
                boolean result = customerBusiness.paymentContract(contractCode,
                        results.get("PAYMENTINFO_0_TRANSACTIONID"));

                if (result) {
                    r.equest.setAttribute("message", "Thanh toán cho hợp đồng" + contractCode + " thành công.");
                } else {
                    r.equest.setAttribute("message", "Thanh toán thất bại.");
                }
            }

            session.removeAttribute("RESULT");
            session.removeAttribute("contractCode");
            session.removeAttribute("amountVND");
            session.removeAttribute("ACK");

            return new JspPage(url);
        }
    }

    public ResponseObject getActiveRenewContract(R r) {
        String url = "public/return.jsp";
        HttpSession session = r.equest.getSession(true);
        if (session.getAttribute("RESULT") == null
                || session.getAttribute("contractCode") == null
                || session.getAttribute("newExpiredDate") == null
                || session.getAttribute("amountVND") == null
                || session.getAttribute("ACK") == null) {
            return new RedirectTo("/error/404");
        } else {
            Map<String, String> results = new HashMap<String, String>();
            results.putAll((Map<String, String>) session.getAttribute("RESULT"));

            String contractCode = (String) session.getAttribute("contractCode");
            Timestamp newExpiredDate = (Timestamp) session.getAttribute("newExpiredDate");
            r.equest.setAttribute("amountVND", session.getAttribute("amountVND"));
            r.equest.setAttribute("redirectLink", "/customer/contract?action=ContractDetail&code=" + contractCode);
            r.equest.setAttribute("result", results);
            r.equest.setAttribute("ack", session.getAttribute("ACK"));


            // Get concurrency data
            Timestamp lastModified = (Timestamp) r.equest.getSession(true).getAttribute(
                    Constants.Session.CONCURRENCY + contractCode);

            CustomerBusiness customerBusiness = new CustomerBusiness();
            // Concurrency check
            if (customerBusiness.isContractChanged(contractCode, lastModified)) {
                r.equest.setAttribute("message", "Thông tin hợp đồng đã bị thay đổi bởi một " +
                        "người khác, thanh toán đã bị huỷ. <br/>" +
                        "Vui lòng lưu lại mã giao dịch để đối chiếu trong trường hợp hoàn lại tiền");
                // TODO: refund?
            } else {
                //renew contract by customer
                boolean result = customerBusiness.renewContract(contractCode, newExpiredDate,
                        results.get("PAYMENTINFO_0_TRANSACTIONID"));

                if (result) {
                    r.equest.setAttribute("message", "Gia hạn thành công.");
                } else {
                    r.equest.setAttribute("message", "Gia hạn thất bại.");
                }
            }

            session.removeAttribute("RESULT");
            session.removeAttribute("contractCode");
            session.removeAttribute("newExpiredDate");
            session.removeAttribute("amountVND");
            session.removeAttribute("ACK");

            return new JspPage(url);
        }
    }

    public ResponseObject postRejectRequestCancel(R r) {
        CustomerBusiness business = new CustomerBusiness();
        String contractCode = r.equest.getParameter("contractcode");
        if (business.getContractDetail(contractCode) == null) {
            return new RedirectTo("/error/404");
        } else {
            String mesg;

            // Get concurrency data
            Timestamp lastModified = (Timestamp) r.equest.getSession(true).getAttribute(
                    Constants.Session.CONCURRENCY + contractCode);

            if (business.isContractChanged(contractCode, lastModified)) {
                mesg = "Thông tin hợp đồng đã bị sửa đổi trước đó bởi một người khác, vui lòng thực hiện lại thao tác";
            } else {
                ContractEntity contract = business.rejectCancelContract(contractCode);

                mesg = "Không thể gở bỏ yêu cầu hủy hợp đồng";
                if (contract != null) {
                    r.equest.setAttribute("contract", contract);
                    return new RedirectTo("contract?action=ContractDetail&code=" + contractCode);
                }
            }
            r.equest.setAttribute("result", mesg);
            r.equest.setAttribute("contractCode", contractCode);
            return new JspPage("customer/message.jsp");
        }
    }
}


