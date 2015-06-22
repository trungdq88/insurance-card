package com.fpt.mic.micweb.controller.customer;

import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.model.business.CustomerBusniess;
import com.fpt.mic.micweb.model.dto.CheckoutRequestDto;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.entity.PaymentEntity;
import com.fpt.mic.micweb.utils.Constants;
import com.fpt.mic.micweb.utils.DateUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PhucNguyen on 06/05/15.
 */
@WebServlet(name = "CustomerContractController", urlPatterns = "/customer/contract")
public class ContractController extends BasicController {

    public ResponseObject getView(R r) {
        CustomerBusniess customerBusiness = new CustomerBusniess();
        String customerCode = "KH0001";
        List<ContractEntity> listContract = customerBusiness.getAllContractByCustomer(customerCode);
        r.equest.setAttribute("listContract", listContract);
        return new JspPage("customer/contract.jsp");
    }

    public ResponseObject getContractDetail(R r) {
        CustomerBusniess customerBusiness = new CustomerBusniess();
        String code = r.equest.getParameter("code");
        ContractEntity contract = customerBusiness.getContractDetail(code);
        r.equest.setAttribute("contract", contract);
        return new JspPage("customer/contract-detail.jsp");
    }

    /* Handle canncel contract */
    public ResponseObject postCancelContract(R r) {
        CustomerBusniess customerBusiness = new CustomerBusniess();
        String mesg = "Yêu Cầu Hủy Thất Bại";
        String contractcode = r.equest.getParameter("contractcode");
        String reasoncancel = r.equest.getParameter("txtReason");
        ContractEntity contractOld = customerBusiness.getContractDetail(contractcode);
        String oldStatus = contractOld.getStatus();
        HttpSession session = r.equest.getSession();
        session.setAttribute("oldStatus", oldStatus);
        ContractEntity contract = customerBusiness.cancelContract(contractcode, reasoncancel);
        if (contract != null) {
            r.equest.setAttribute("contract", contract);
            return new RedirectTo("contract?action=ContractDetail&code=" + contractcode);
        } else {
            r.equest.setAttribute("result", mesg);
            r.equest.setAttribute("contractCode", contractcode);
            return new JspPage("customer/message.jsp");
        }
    }

    /* Renew contract */
    public ResponseObject postRenewContract(R r) {
        //get parameter
        Date date = new Date();
        String contractCode = r.equest.getParameter("txtContractCode");
        CustomerBusniess busniess = new CustomerBusniess();
        ContractEntity contract = busniess.getContractDetail(contractCode);
        Timestamp expiredDate = contract.getExpiredDate();
        if (contract.getStatus().equalsIgnoreCase(Constants.ContractStatus.READY)) {
            expiredDate = DateUtils.addOneYear(expiredDate);
        }
        else if (contract.getStatus().equalsIgnoreCase(Constants.ContractStatus.EXPIRED) ) {
            expiredDate = DateUtils.addOneYear(new Timestamp(date.getTime()));
        }
        HttpSession session = r.equest.getSession();

        session.setAttribute("contractCode", contractCode);
        session.setAttribute("newExpiredDate", expiredDate);
        session.setAttribute("SUCCESS_URL", r.equest.getParameter("successUrl"));

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
        Map<String, String> results = new HashMap<String, String>();
        results.putAll((Map<String, String>) session.getAttribute("RESULT"));

        String contractCode = (String) session.getAttribute("contractCode");
        Timestamp newExpiredDate = (Timestamp) session.getAttribute("newExpiredDate");

        r.equest.setAttribute("amountVND", (String) session.getAttribute("amountVND"));
        r.equest.setAttribute("redirectLink", "/customer/contract?action=ContractDetail&code=" + contractCode);

        r.equest.setAttribute("result", results);
        r.equest.setAttribute("ack", (String) session.getAttribute("ACK"));
        //renew contract by customer

        java.util.Date date = new java.util.Date();


        CustomerBusniess customerBusiness = new CustomerBusniess();

        ContractEntity contract = customerBusiness.getContractDetail(contractCode);
        contract.setExpiredDate(newExpiredDate);
        contract.setStatus(Constants.ContractStatus.READY);

        PaymentEntity payment = new PaymentEntity();
        payment.setPaidDate(new Timestamp(date.getTime()));
        payment.setPaymentMethod("PayPal payment");
        payment.setContent("Gia Hạn Hợp Đồng");
        payment.setAmount(contract.getContractFee());
        payment.setPaypalTransId(results.get("PAYMENTINFO_0_TRANSACTIONID").toString());
        payment.setContractCode(contract.getContractCode());
        boolean result = customerBusiness.renewContract(contract, payment);
        if (result == true) {
            r.equest.setAttribute("message", "Gia hạn thành công.");
        } else {
            r.equest.setAttribute("message", "Gia hạn thất bại.");
        }
        // insert information in payment table
        session.invalidate();

        return new JspPage(url);
    }

    public ResponseObject postRejectRequestCancel(R r) {
        CustomerBusniess business = new CustomerBusniess();
        String contractCode = r.equest.getParameter("contractcode");
        HttpSession session = r.equest.getSession(true);
        String oldStatus = (String)session.getAttribute("oldStatus");
        ContractEntity contract = business.rejectCancelContract(contractCode , oldStatus);
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


