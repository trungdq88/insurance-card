package com.fpt.mic.micweb.controller.customer;

import com.fpt.mic.micweb.framework.responses.ForwardTo;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.model.business.CustomerBusniess;
import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dto.CheckoutRequest;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.utils.DateUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
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
        List<ContractEntity> listContract = customerBusiness.getAllContract();
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
        String result = "Yêu Cầu Hủy Thất Bại";
        String contractcode = r.equest.getParameter("contractcode");
        String reasoncancel = r.equest.getParameter("txtReason");
        if (reasoncancel != null && reasoncancel != "") {
            if (customerBusiness.CancelContract(contractcode, reasoncancel, 1) == true) {
                result = "Yêu Cầu Hủy Hợp Đồng Thành Công ";
            }

        }
        r.equest.setAttribute("result", result);
        return new JspPage("customer/message.jsp");
    }
    /* Renew contract */
    public ResponseObject postRenewContract(R r){
        //get parameter

        String contractCode = r.equest.getParameter("txtContractCode");
        String newStartDate = r.equest.getParameter("txtNewStartDate");
        Timestamp startDate = DateUtils.stringToTime(newStartDate);
        String newExpiredDate = r.equest.getParameter("txtNewExpiredDate");
        Timestamp expiredDate = DateUtils.stringToTime(newExpiredDate);

        HttpSession session = r.equest.getSession();
        session.setAttribute("contractCode",contractCode);
        session.setAttribute("newStartDate",startDate);
        session.setAttribute("newExpiredDate",expiredDate);
        session.setAttribute("SUCCESS_URL",r.equest.getParameter("successUrl"));

        CheckoutRequest checkoutRequest = new CheckoutRequest();
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

    public ResponseObject getActiveRenewContract (R r){
        String url = "public/return.jsp";
        HttpSession session = r.equest.getSession(true);
        Map<String, String> results = new HashMap<String, String>();
        results.putAll((Map<String, String>) session.getAttribute("RESULT"));

        r.equest.setAttribute("result",results);
        r.equest.setAttribute("ack",(String) session.getAttribute("ACK"));
        //renew contract by customer

        String contractCode = (String) session.getAttribute("contractCode");
        Timestamp newStartDate = (Timestamp) session.getAttribute("newStartDate");
        Timestamp newExpiredDate = (Timestamp) session.getAttribute("newExpiredDate");
        ContractDao contractDao = new ContractDao();
        ContractEntity contract = contractDao.read(contractCode);
        contract.setStartDate(newStartDate);
        contract.setExpiredDate(newExpiredDate);
        contract.setStatus("Ready");
        contractDao.update(contract);
        session.invalidate();
        r.equest.setAttribute("message","Gia han thanh cong.");
        return new JspPage(url);
    }

}


