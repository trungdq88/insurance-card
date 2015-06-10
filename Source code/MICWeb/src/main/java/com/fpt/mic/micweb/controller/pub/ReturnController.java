package com.fpt.mic.micweb.controller.pub;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ForwardTo;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.RegisterBusiness;
import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dto.PayPal;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.PaymentEntity;
import com.fpt.mic.micweb.utils.DateUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by TriPQMSE60746 on 06/07/2015.
 * Reference: Paypal Express Checkout API from https://demo.paypal.com/us/home.
 */
@WebServlet(name = "ReturnController", urlPatterns = {"/public/return"})
public class ReturnController extends BasicController {


    public ResponseObject getReturn(R r) {
        return postReturn(r);

    }

    public ResponseObject postReturn(R r) {
        HttpSession session = r.equest.getSession(true);

        if (isSet(r.equest.getParameter("PayerID")))
            session.setAttribute("payer_id", r.equest.getParameter("PayerID"));
        String token = "";
        if (isSet(r.equest.getParameter("token"))) {
            session.setAttribute("TOKEN", r.equest.getParameter("token"));
            token = r.equest.getParameter("token");
        } else {
            token = (String) session.getAttribute("TOKEN");
        }

        // Check to see if the Request object contains a variable named 'token'
        PayPal pp = new PayPal();
        Map<String, String> result = new HashMap<String, String>();
        // If the Request object contains the variable 'token' then it means that the user is coming from PayPal site.
        if (isSet(token)) {
                /*
                * Calls the GetExpressCheckoutDetails API call
                */
            Map<String, String> results = pp.getDetails(token);
            String strAck = results.get("ACK").toString();
            if (strAck != null && (strAck.equalsIgnoreCase("SUCCESS") || strAck.equalsIgnoreCase("SUCCESSWITHWARNING"))) {
                session.setAttribute("payer_id", results.get("PAYERID"));
                result.putAll(results);
            } else {
                //Display a user friendly Error on the page using any of the following error information returned by PayPal
                String errorCode = results.get("L_ERRORCODE0").toString();
                String errorShortMsg = results.get("L_SHORTMESSAGE0").toString();
                String errorLongMsg = results.get("L_LONGMESSAGE0").toString();
                String errorSeverityCode = results.get("L_SEVERITYCODE0").toString();

                String errorString = "SetExpressCheckout API call failed. " +

                        "<br>Detailed Error Message: " + errorLongMsg +
                        "<br>Short Error Message: " + errorShortMsg +
                        "<br>Error Code: " + errorCode +
                        "<br>Error Severity Code: " + errorSeverityCode;
                r.equest.setAttribute("error", errorString);
                session.invalidate();
                return new JspPage("public/error.jsp");
            }
        }

        Map<String, String> checkoutDetails = new HashMap<String, String>();
        checkoutDetails.putAll((Map<String, String>) session.getAttribute("checkoutDetails"));
        checkoutDetails.putAll(setRequestParams(r.equest));
        checkoutDetails.put("TOKEN", token);
        checkoutDetails.put("payer_id", (String) session.getAttribute("payer_id"));

            /*
            * Calls the DoExpressCheckoutPayment API call
            */
        String page =(String) session.getAttribute("SUCCESS_URL");
        if (isSet(r.equest.getParameter("page")) && r.equest.getParameter("page").equals("return")) {
            HashMap results = pp.confirmPayment(checkoutDetails, r.equest.getServerName());
            r.equest.setAttribute("payment_method", "");
            String strAck = results.get("ACK").toString().toUpperCase();
            // Thanh toan thanh cong, cap nhat payment, cap nhat contract status, ngay het han
            if (strAck != null && (strAck.equalsIgnoreCase("Success") || strAck.equalsIgnoreCase("SuccessWithWarning"))) {
                result.putAll(results);
                result.putAll(checkoutDetails);
                session.setAttribute("ACK",strAck);
                session.setAttribute("RESULT", result);

                return new RedirectTo(page);
                //
//                result.putAll(results);
//                result.putAll(checkoutDetails);
//                r.equest.setAttribute("ack", strAck);
//                r.equest.setAttribute("result", result);
                // insert new time expired for contract
//
//                ContractEntity contractEntity = new ContractEntity();
//                ContractDao contractDao = new ContractDao();
//                PaymentEntity paymentEntity = new PaymentEntity();
//
//                // get contract just added by contract_code
//                String code =(String) session.getAttribute("CONTRACT_CODE");
//                contractEntity = contractDao.read(code);
//                DateUtils date = new DateUtils();
//
//                // set start date
//                // Timestamp startDate = DateUtils.stringToTime(checkoutDetails.get("txtStartDate"));
//                Timestamp currentDate = new Timestamp(new Date().getTime());
//                contractEntity.setStartDate(currentDate);
//                // set expired date = start_date + 1 year
//                contractEntity.setExpiredDate(DateUtils.addOneYear(contractEntity.getStartDate()));
//                contractEntity.setStatus("Ready");
//                contractDao.update(contractEntity);
//
//                paymentEntity.setPaidDate(new Timestamp(new Date().getTime()));
//                paymentEntity.setPaymentMethod("PayPal payment");
//                paymentEntity.setContent("Create new contract");
//                paymentEntity.setAmount(Float.parseFloat(results.get("PAYMENTINFO_0_AMT").toString()));
//                paymentEntity.setPaypalTransId(results.get("PAYMENTINFO_0_TRANSACTIONID").toString());
//                paymentEntity.setContractCode(contractEntity.getContractCode());
//
//                RegisterBusiness registerBusiness = new RegisterBusiness();
//                registerBusiness.updateContractPayment(contractEntity,paymentEntity);

            } else {
                //Display a user friendly Error on the page using any of the following error information returned by PayPal
                String errorCode = results.get("L_ERRORCODE0").toString();
                String errorShortMsg = results.get("L_SHORTMESSAGE0").toString();
                String errorLongMsg = results.get("L_LONGMESSAGE0").toString();
                String errorSeverityCode = results.get("L_SEVERITYCODE0").toString();
                String errorString = "SetExpressCheckout API call failed. " +
                        "<br>Detailed Error Message: " + errorLongMsg +
                        "<br>Short Error Message: " + errorShortMsg +
                        "<br>Error Code: " + errorCode +
                        "<br>Error Severity Code: " + errorSeverityCode;
                r.equest.setAttribute("error", errorString);
                page = "public/error.jsp";
            }
        }

        return new JspPage(page);
    }

    public ResponseObject getCancel(R r) {
        return new JspPage("public/cancel.jsp");
    }

    private Map<String, String> setRequestParams(HttpServletRequest request) {
        Map<String, String> requestMap = new HashMap<String, String>();
        for (String key : request.getParameterMap().keySet()) {

            requestMap.put(key, request.getParameterMap().get(key)[0]);
        }

        return requestMap;

    }

    private boolean isSet(Object value) {
        return (value != null && value.toString().length() != 0);
    }
}