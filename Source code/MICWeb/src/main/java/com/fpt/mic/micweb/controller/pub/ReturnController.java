package com.fpt.mic.micweb.controller.pub;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.dto.PayPal;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by TriPQMSE60746 on 06/07/2015.
 */
@WebServlet(name = "ReturnController", urlPatterns = {"/public/return"})
public class ReturnController  extends BasicController {

    public ResponseObject getReturn(R r){
        HttpSession session = r.equest.getSession(true);

        if (isSet(r.equest.getParameter("PayerID")))
            session.setAttribute("payer_id", r.equest.getParameter("PayerID"));
        String token = "";
        if (isSet(r.equest.getParameter("token"))){
            session.setAttribute("TOKEN", r.equest.getParameter("token"));
            token = r.equest.getParameter("token");
        }else{
            token = (String) session.getAttribute("TOKEN");
        }

        // Check to see if the Request object contains a variable named 'token'
        PayPal pp = new PayPal();
        Map<String, String> result = new HashMap<String, String>();
        // If the Request object contains the variable 'token' then it means that the user is coming from PayPal site.
        if (isSet(token))
        {
                /*
                * Calls the GetExpressCheckoutDetails API call
                */
            Map<String,String> results = pp.getDetails(token );
            String strAck = results.get("ACK").toString();
            if(strAck !=null && (strAck.equalsIgnoreCase("SUCCESS") || strAck.equalsIgnoreCase("SUCCESSWITHWARNING") ))
            {
                session.setAttribute("payer_id", results.get("PAYERID"));
                result.putAll(results);
                    /*
                    * The information that is returned by the GetExpressCheckoutDetails call should be integrated by the partner into his Order Review
                    * page
                    */
                String email 				= results.get("EMAIL"); // ' Email address of payer.
                String payerId 			= results.get("PAYERID"); // ' Unique PayPal customer account identification number.
                String payerStatus		= results.get("PAYERSTATUS"); // ' Status of payer. Character length and limitations: 10 single-byte alphabetic characters.
                String firstName			= results.get("FIRSTNAME"); // ' Payer's first name.
                String lastName			= results.get("LASTNAME"); // ' Payer's last name.

                String addressStatus 		= results.get("ADDRESSSTATUS"); // ' Status of street address on file with PayPal
                String totalAmt   		= results.get("PAYMENTREQUEST_0_AMT"); // ' Total Amount to be paid by buyer
                String currencyCode       = results.get("CURRENCYCODE"); // 'Currency being used

            }
            else
            {
                //Display a user friendly Error on the page using any of the following error information returned by PayPal
                String errorCode = results.get("L_ERRORCODE0").toString();
                String errorShortMsg = results.get("L_SHORTMESSAGE0").toString();
                String errorLongMsg = results.get("L_LONGMESSAGE0").toString();
                String errorSeverityCode = results.get("L_SEVERITYCODE0").toString();

                String errorString = "SetExpressCheckout API call failed. "+

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
        String page="return.jsp";
        if (isSet(r.equest.getParameter("page")) && r.equest.getParameter("page").equals("return")){
            // FIXME - The method 'request.getServerName()' must be sanitized before being used.
            HashMap results = pp.confirmPayment (checkoutDetails,r.equest.getServerName() );
            r.equest.setAttribute("payment_method","");
            String strAck = results.get("ACK").toString().toUpperCase();
            if(strAck !=null && (strAck.equalsIgnoreCase("Success") || strAck.equalsIgnoreCase("SuccessWithWarning"))){
                result.putAll(results);
                result.putAll(checkoutDetails);
                r.equest.setAttribute("ack", strAck);
                session.invalidate();
            }else{
                //Display a user friendly Error on the page using any of the following error information returned by PayPal
                String errorCode = results.get("L_ERRORCODE0").toString();
                String errorShortMsg = results.get("L_SHORTMESSAGE0").toString();
                String errorLongMsg = results.get("L_LONGMESSAGE0").toString();
                String errorSeverityCode = results.get("L_SEVERITYCODE0").toString();
                String errorString = "SetExpressCheckout API call failed. "+
                        "<br>Detailed Error Message: " + errorLongMsg +
                        "<br>Short Error Message: " + errorShortMsg +
                        "<br>Error Code: " + errorCode +
                        "<br>Error Severity Code: " + errorSeverityCode;
                r.equest.setAttribute("error", errorString);

                return new JspPage("public/error.jsp");
                // return;
            }
            page="public/return.jsp";
        }else{
            page="public/review.jsp";
        }
        r.equest.setAttribute("result", result);

        return new JspPage(page);
    }
    private Map<String,String> setRequestParams(HttpServletRequest request){
        Map<String,String> requestMap = new HashMap<String,String>();
        for (String key : request.getParameterMap().keySet()) {

            requestMap.put(key, request.getParameterMap().get(key)[0]);
        }

        return requestMap;

    }
    private boolean isSet(Object value){
        return (value !=null && value.toString().length()!=0);
    }
}