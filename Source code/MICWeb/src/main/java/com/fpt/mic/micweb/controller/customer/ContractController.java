package com.fpt.mic.micweb.controller.customer;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.Paginator;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.model.business.*;
import com.fpt.mic.micweb.model.dto.CheckoutRequestDto;
import com.fpt.mic.micweb.model.dto.RegisterInformationDto;
import com.fpt.mic.micweb.model.dto.UserDto;
import com.fpt.mic.micweb.model.dto.form.CancelContractDto;
import com.fpt.mic.micweb.model.dto.form.ConcurrencyDto;
import com.fpt.mic.micweb.model.dto.form.CustomerCreateContractDto;
import com.fpt.mic.micweb.model.entity.*;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.utils.ConfigUtils;
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
    Paginator compensationPaginator = new Paginator("compensation");
    Paginator punishmentPaginator = new Paginator("punishment");
    Paginator accidentPaginator = new Paginator("accident");

    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_CUSTOMER);
    }

    public ResponseObject getNewContract(R r) {
        ContractBusiness contractBusiness = new ContractBusiness();
        ConfigUtils configUtils = new ConfigUtils();
        List<ContractTypeEntity> list = contractBusiness.getAllActiveContractType();
        HashMap<Integer, ContractTypeEntity> mapContractType = new HashMap<Integer, ContractTypeEntity>();
        for (int i = 0; i < list.size(); i++) {
            mapContractType.put(list.get(i).getId(), list.get(i));
        }
        r.equest.setAttribute("configUtils", configUtils);
        r.equest.setAttribute("mapContractType", mapContractType);
        return new JspPage("customer/contract-new.jsp");
    }

    public ResponseObject getReviewNewContract(R r) {
        CustomerCreateContractDto customerCreateContractDto = (CustomerCreateContractDto) r.ead.entity(CustomerCreateContractDto.class, "contract");
        ConfigUtils configUtils = new ConfigUtils();
        // Gọi hàm validate ở đây
        List errors = r.ead.validate(customerCreateContractDto);

        // Nếu có lỗi khi validate
        if (errors.size() > 0) {
            // Gửi lỗi về trang JSP
            r.equest.setAttribute("validateErrors", errors);
            // Gửi dữ liệu mà người dùng đã nhập về trang JSP, gán vào biến submitted
            r.equest.setAttribute("submitted", customerCreateContractDto);
            r.equest.setAttribute("startDate", r.equest.getParameter("contract:startDate"));
            return getNewContract(r);
        }
        r.equest.setAttribute("startDate", r.equest.getParameter("contract:startDate"));
        r.equest.setAttribute("submitted", customerCreateContractDto);
        r.equest.setAttribute("configUtils", configUtils);
        ContractBusiness contractBusiness = new ContractBusiness();
        r.equest.setAttribute("contractTypeName", contractBusiness.getContractType(customerCreateContractDto.getContractType()).getName());
        return new JspPage("customer/contract-review.jsp");
    }

    public ResponseObject getCreateContract(R r) {
        String url = "public/error.jsp";
        CustomerCreateContractDto customerCreateContractDto = (CustomerCreateContractDto) r.ead.entity(CustomerCreateContractDto.class, "contract");
        // Gọi hàm validate ở đây
        List errors = r.ead.validate(customerCreateContractDto);

        // Nếu có lỗi khi validate
        if (errors.size() > 0) {
            // Gửi lỗi về trang JSP
            r.equest.setAttribute("validateErrors", errors);
            // Gửi dữ liệu mà người dùng đã nhập về trang JSP, gán vào biến submitted
            r.equest.setAttribute("submitted", customerCreateContractDto);
            r.equest.setAttribute("startDate", r.equest.getParameter("contract:startDate"));
            return getNewContract(r);
        }
        String customerCode = ((CustomerEntity) getLoggedInUser()).getCustomerCode();

        // Call to business object
        RegisterBusiness registerBusiness = new RegisterBusiness();
        RegisterInformationDto register =
                registerBusiness.customerCreateContract(customerCreateContractDto, customerCode);

        if (register.getContractEntity() != null) {
            HttpSession session = r.equest.getSession();

            // Save last_modified value for concurrency check
            Timestamp lastModified = register.getContractEntity().getLastModified();
            session.setAttribute(
                    Constants.Session.CONCURRENCY + register.getContractEntity().getContractCode(),
                    lastModified);
            System.out.println("getCreateContract" + lastModified);

            session.setAttribute("CONTRACT_CODE", register.getContractEntity().getContractCode());
            session.setAttribute("SUCCESS_URL", "/customer/contract?action=activeCreateContract");
            session.setAttribute("cancel_message", "Bạn đã hủy thanh toán. Xin vui lòng thực hiện lại hoặc đến thanh toán trực tiếp");
            session.setAttribute("redirectLink", "/customer/contract");

            r.equest.setAttribute("register", register);
            return new JspPage("public/register-payment.jsp");
        }

        r.equest.setAttribute("error", "Không thể tạo hợp đồng. Xin thử lại");
        return new JspPage(url);
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

    public ResponseObject getDetail(R r) {
        ConfigUtils configUtils = new ConfigUtils();

        final CustomerBusiness customerBusiness = new CustomerBusiness();
        CardBusiness cardBusiness = new CardBusiness();
        final CompensationBusiness compensationBusiness = new CompensationBusiness();
        final PunishmentBusiness punishmentBusiness = new PunishmentBusiness();

        String customerCode = ((CustomerEntity) getLoggedInUser()).getCustomerCode();
        final String code = r.equest.getParameter("code");
        ContractEntity contract = customerBusiness.getContractDetail(code);
        if (contract == null || contract.getCustomerCode().compareToIgnoreCase(customerCode) != 0) {
            return new RedirectTo("/error/404");
        } else {
            // Save last_modified value for concurrency check
            r.equest.getSession(true).setAttribute(
                    Constants.Session.CONCURRENCY + contract.getContractCode(),
                    contract.getLastModified());

            // compensation region
            compensationPaginator.setGetItemsCallback(new Paginator.IGetItems() {
                @Override
                public List getItems(int offset, int count) {
                    return compensationBusiness.getAllCompensationByContractCode(code, offset, count);
                }
            });
            compensationPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
                @Override
                public Long getItemSize() {
                    return compensationBusiness.getAllCompensationByContractCodeCount(code);
                }
            });
            //
            //punishment region
            punishmentPaginator.setGetItemsCallback(new Paginator.IGetItems() {
                @Override
                public List getItems(int offset, int count) {
                    return punishmentBusiness.getAllPunishmentByContractCode(code, offset, count);
                }
            });
            punishmentPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
                @Override
                public Long getItemSize() {
                    return punishmentBusiness.getAllPunishmentByContractCodeCount(code);
                }
            });

            //accident region
            accidentPaginator.setGetItemsCallback(new Paginator.IGetItems() {
                @Override
                public List getItems(int offset, int count) {
                    return customerBusiness.getAllAccidentByContractCode(code, offset, count);
                }
            });
            accidentPaginator.setGetItemSizeCallback(new Paginator.IGetItemSize() {
                @Override
                public Long getItemSize() {
                    return customerBusiness.getAllAccidentByContractCodeCount(code);
                }
            });

            r.equest.setAttribute("contract", contract);
            r.equest.setAttribute("isPayment", customerBusiness.isPayment(code));
            r.equest.setAttribute("configUtils", configUtils);
            r.equest.setAttribute("newCardRequested", cardBusiness.getUnresolveNewCardRequest(code));
            r.equest.setAttribute("card", cardBusiness.getCardByContract(code));
            r.equest.setAttribute("accidentPaginator", accidentPaginator);
            r.equest.setAttribute("compensationPaginator", compensationPaginator);
            r.equest.setAttribute("punishmentPaginator", punishmentPaginator);
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
                    return new RedirectTo("contract?action=detail&code=" + cancelDto.getContractCode());
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
        session.setAttribute("CONTRACT_CODE", contractCode);
        session.setAttribute("SUCCESS_URL", r.equest.getParameter("successUrl"));
        session.setAttribute("cancel_message", "Bạn đã hủy thanh toán. Xin vui lòng thực hiện lại hoặc đến thanh toán trực tiếp");
        session.setAttribute("redirectLink", "/customer/contract?action=detail&code=" + contractCode);

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
        ConfigUtils configUtils = new ConfigUtils();

        // Validate: check if the remaining days is greater than 2 months
        ContractBusiness contractBusiness = new ContractBusiness();
        if (!contractBusiness.isRenewable(contractCode)) {
            return showCustomError(r, contractCode, contractBusiness, "Không thể gia hạn hợp đồng còn giá trị trên "
                    + configUtils.getContractRenewLimit() + " ngày");
        }

        Timestamp newExpiredDate = contractBusiness.getNewExpiredDate(contractCode);
        if (newExpiredDate == null) {
            return showCustomError(r, contractCode, contractBusiness, "Hợp đồng không tồn tại hoặc trạng thái không hợp lệ");
        }

        // Set need_renew_payment variable
        contractBusiness.setContractNeedRenewPayment(contractCode, true);

        HttpSession session = r.equest.getSession();

        session.setAttribute("CONTRACT_CODE", contractCode);
        session.setAttribute("newExpiredDate", newExpiredDate);
        session.setAttribute("SUCCESS_URL", r.equest.getParameter("successUrl"));
        session.setAttribute("cancel_message", "Bạn đã hủy thanh toán. Xin vui lòng thực hiện lại hoặc đến thanh toán trực tiếp");
        session.setAttribute("redirectLink", "/customer/contract?action=detail&code=" + contractCode);


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
                || session.getAttribute("CONTRACT_CODE") == null
                || session.getAttribute("amountVND") == null
                || session.getAttribute("ACK") == null) {
            return new RedirectTo("/error/404");
        } else {
            Map<String, String> results = new HashMap<String, String>();
            results.putAll((Map<String, String>) session.getAttribute("RESULT"));

            String contractCode = (String) session.getAttribute("CONTRACT_CODE");
            System.out.println("getActivePayContract:" + contractCode);

            r.equest.setAttribute("amountVND", session.getAttribute("amountVND"));
            r.equest.setAttribute("redirectLink", "/customer/contract?action=detail&code=" + contractCode);
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
            session.removeAttribute("CONTRACT_CODE");
            session.removeAttribute("amountVND");
            session.removeAttribute("ACK");

            return new JspPage(url);
        }
    }

    public ResponseObject getActiveRenewContract(R r) {
        String url = "public/return.jsp";
        HttpSession session = r.equest.getSession(true);
        if (session.getAttribute("RESULT") == null
                || session.getAttribute("CONTRACT_CODE") == null
                || session.getAttribute("newExpiredDate") == null
                || session.getAttribute("amountVND") == null
                || session.getAttribute("ACK") == null) {
            return new RedirectTo("/error/404");
        } else {
            Map<String, String> results = new HashMap<String, String>();
            results.putAll((Map<String, String>) session.getAttribute("RESULT"));

            String contractCode = (String) session.getAttribute("CONTRACT_CODE");
            System.out.println("getActiveRenewContract:" + contractCode);

            Timestamp newExpiredDate = (Timestamp) session.getAttribute("newExpiredDate");
            r.equest.setAttribute("amountVND", session.getAttribute("amountVND"));
            r.equest.setAttribute("redirectLink", "/customer/contract?action=detail&code=" + contractCode);
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
            session.removeAttribute("CONTRACT_CODE");
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
                    return new RedirectTo("contract?action=detail&code=" + contractCode);
                }
            }
            r.equest.setAttribute("result", mesg);
            r.equest.setAttribute("contractCode", contractCode);
            return new JspPage("customer/message.jsp");
        }
    }

    public ResponseObject getActiveCreateContract(R r) {
        String errorUrl = "/error/404";
        HttpSession session = r.equest.getSession(false);
        if (session != null) {
            String returnUrl = "public/return.jsp";
            ConcurrencyDto concurrencyDto = new ConcurrencyDto();

            String contractCode = (String) session.getAttribute("CONTRACT_CODE");

            Map<String, String> results = new HashMap<String, String>();
            results.putAll((Map<String, String>) session.getAttribute("RESULT"));

            r.equest.setAttribute("result", results);
            r.equest.setAttribute("ack", (String) session.getAttribute("ACK"));
            Float amount = Float.parseFloat((String) session.getAttribute("amountVND"));
            r.equest.setAttribute("amountVND", amount);
            r.equest.setAttribute("redirectLink", "/customer/contract?action=detail&code=" + contractCode);


            String paypalTransId = results.get("PAYMENTINFO_0_TRANSACTIONID");
            String paymentMethod = "PayPal";
            String paymentContent = (String) session.getAttribute("descVN");

            RegisterBusiness registerBusiness = new RegisterBusiness();
            String result = registerBusiness.updateContractPayment(contractCode, paymentMethod, paymentContent, amount, paypalTransId);
            if (result == null) {
                session.removeAttribute("RESULT");
                session.removeAttribute("CONTRACT_CODE");
                session.removeAttribute("amountVND");
                session.removeAttribute("ACK");
                session.removeAttribute("SUCCESS_URL");
                session.removeAttribute("EXPRESS_MARK");
                session.removeAttribute("payer_id");
                session.removeAttribute("checkoutDetails");
                session.removeAttribute("checkout");
                session.removeAttribute("TOKEN");
                r.equest.setAttribute("message", "Thanh toán thành công");
                return new JspPage(returnUrl);
            } else {
                session.removeAttribute("cancel_message");
                r.equest.setAttribute("cancel_message", result);
                return new JspPage("/public/cancel.jsp");
            }
        }
        //r.equest.setAttribute("error","Phiên giao dịch đã hết thời gian");
        return new RedirectTo(errorUrl);
    }
}


