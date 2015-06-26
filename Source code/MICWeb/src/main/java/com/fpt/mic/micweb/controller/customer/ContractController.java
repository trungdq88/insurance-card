package com.fpt.mic.micweb.controller.customer;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
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
    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_CUSTOMER);
    }

    public ResponseObject getView(R r) {
        CustomerBusiness customerBusiness = new CustomerBusiness();
        String customerCode = ((CustomerEntity) getLoggedInUser()).getCustomerCode();
        List<ContractEntity> listContract = customerBusiness.getAllContractByCustomer(customerCode);
        r.equest.setAttribute("listContract", listContract);
        return new JspPage("customer/contract.jsp");
    }

    public ResponseObject getContractDetail(R r) {
        CustomerBusiness customerBusiness = new CustomerBusiness();
        String code = r.equest.getParameter("code");
        ContractEntity contract = customerBusiness.getContractDetail(code);
        if (contract == null) {
            return new RedirectTo("/error/404");
        } else {
            r.equest.setAttribute("contract", contract);
            return new JspPage("customer/contract-detail.jsp");
        }
    }

    /* Handle canncel contract */
    public ResponseObject postCancelContract(R r) {
        CancelContractDto cancelDto = (CancelContractDto) r.ead.entity(CancelContractDto.class, "cancel");
        CustomerBusiness customerBusiness = new CustomerBusiness();
        java.util.Date date = new java.util.Date();
        cancelDto.setCancelDate(new Timestamp(date.getTime()));
        if(cancelDto.getContractCode() == null){
            return new RedirectTo("/error/404");
        }
        else {
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

    /* Renew contract */
    public ResponseObject postRenewContract(R r) {
        //get parameter
        Date date = new Date();
        String contractCode = r.equest.getParameter("txtContractCode");
        CustomerBusiness busniess = new CustomerBusiness();
        ContractEntity contract = busniess.getContractDetail(contractCode);
        Timestamp expiredDate = contract.getExpiredDate();
        if (contract.getStatus().equalsIgnoreCase(Constants.ContractStatus.READY)) {
            expiredDate = DateUtils.addOneYear(expiredDate);
        } else if (contract.getStatus().equalsIgnoreCase(Constants.ContractStatus.EXPIRED)) {
            expiredDate = DateUtils.addOneYear(new Timestamp(date.getTime()));
        }
        HttpSession session = r.equest.getSession();

        session.setAttribute("contractCode", contractCode);
        session.setAttribute("newExpiredDate", expiredDate);
        session.setAttribute("SUCCESS_URL", r.equest.getParameter("successUrl"));
        session.setAttribute("cancel_message","Bạn đã hủy thanh toán. Xin vui lòng thực hiện lại hoặc đến thanh toán trực tiếp");
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

    public ResponseObject getNewContract(R r) {
        return new JspPage("customer/contract-new.jsp");
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
            r.equest.setAttribute("amountVND", (String) session.getAttribute("amountVND"));
            r.equest.setAttribute("redirectLink", "/customer/contract?action=ContractDetail&code=" + contractCode);
            r.equest.setAttribute("result", results);
            r.equest.setAttribute("ack", (String) session.getAttribute("ACK"));
            //renew contract by customer
            CustomerBusiness customerBusiness = new CustomerBusiness();
            boolean result = customerBusiness.renewContract(contractCode, newExpiredDate,
                    results.get("PAYMENTINFO_0_TRANSACTIONID"));

            if (result) {
                r.equest.setAttribute("message", "Gia hạn thành công.");
            } else {
                r.equest.setAttribute("message", "Gia hạn thất bại.");
            }
            session.invalidate();

            return new JspPage(url);
        }
    }

    public ResponseObject postRejectRequestCancel(R r) {
        CustomerBusiness business = new CustomerBusiness();
        String contractCode = r.equest.getParameter("contractcode");
        if (business.getContractDetail(contractCode) == null) {
            return new RedirectTo("/error/404");
        } else {
            ContractEntity contract = business.rejectCancelContract(contractCode);
            String mesg = "Không thể gở bỏ yêu cầu hủy hợp đồng";
            if (contract != null) {
                r.equest.setAttribute("contract", contract);
                return new RedirectTo("contract?action=ContractDetail&code=" + contractCode);
            } else {
                r.equest.setAttribute("result", mesg);
                r.equest.setAttribute("contractCode", contractCode);
                return new JspPage("customer/message.jsp");
            }
        }
    }

}


