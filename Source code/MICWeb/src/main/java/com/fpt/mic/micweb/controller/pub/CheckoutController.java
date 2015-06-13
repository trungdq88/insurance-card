package com.fpt.mic.micweb.controller.pub;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.dto.PayPal;
import com.tunyk.currencyconverter.BankUaCom;
import com.tunyk.currencyconverter.api.Currency;
import com.tunyk.currencyconverter.api.CurrencyConverter;
import com.tunyk.currencyconverter.api.CurrencyConverterException;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by TriPQMSE60746 on 06/10/2015.
 */
@WebServlet(name = "CheckoutController", urlPatterns = {"/public/checkout"})
public class CheckoutController extends BasicController{
    /**
     * Created by TriPQMSE60746 on 06/07/2015.
     * Reference: Paypal Express Checkout API from https://demo.paypal.com/us/home.
     *
     * Input: request must have these parameters:
     * <input type="hidden" name="L_PAYMENTREQUEST_0_NAME0" value="${ddlContractype}">
     <input type="hidden" name="L_PAYMENTREQUEST_0_DESC0" value="${ddlContractype}">
     <input type="hidden" name="L_PAYMENTREQUEST_0_QTY0" value="1">
     <input type="hidden" name="PAYMENTREQUEST_0_ITEMAMT" value="${txtFee/20000}">
     <input type="hidden" name="PAYMENTREQUEST_0_TAXAMT" value="0">
     <input type="hidden" name="PAYMENTREQUEST_0_AMT" value="${txtFee/20000}">
     <input type="hidden" name="currencyCodeType" value="USD">
     <input type="hidden" name="paymentType" value="Sale">
     <input type="hidden" name="L_PAYMENTREQUEST_0_AMT"
     value="${PAYMENTREQUEST_0_AMT}"/>
     */
    public ResponseObject getCheckout(R r) {
        // payment
        HttpSession session = r.equest.getSession();
        PayPal paypal = new PayPal();
        String url =  "public/error.jsp";
        /*
        '------------------------------------
        ' The returnURL is the location where buyers return to when a
        ' payment has been successfully authorized.
        '------------------------------------
        */

        String returnURL = r.equest.getScheme() + "://" + r.equest.getServerName() + ":" + r.equest.getServerPort() + r.equest.getContextPath() + "/public/return?action=return&page=return";

    /*
    '------------------------------------
    ' The cancelURL is the location buyers are sent to when they hit the
    ' cancel button during authorization of payment during the PayPal flow
    '------------------------------------
    */
        String cancelURL = r.equest.getScheme() + "://" + r.equest.getServerName() + ":" + r.equest.getServerPort() + r.equest.getContextPath() + "/public/return?action=cancel";
        Map<String, String> checkoutDetails = new HashMap<String, String>();
        checkoutDetails = setRequestParams(r);
        r.equest.setAttribute("result",checkoutDetails);
        //Redirect to check out page for check out mark
        if (isSet(r.equest.getParameter("checkout"))) {
            session.setAttribute("checkoutDetails", checkoutDetails);

            if (isSet(r.equest.getParameter("checkout")) || isSet(session.getAttribute("checkout"))) {
                session.setAttribute("checkout", StringEscapeUtils.escapeHtml4(r.equest.getParameter("checkout")));
            }

            //Assign the Return and Cancel to the Session object for ExpressCheckout Mark
            session.setAttribute("EXPRESS_MARK", "ECMark");

            r.equest.setAttribute("PAYMENTREQUEST_0_AMT", StringEscapeUtils.escapeHtml4(r.equest.getParameter("PAYMENTREQUEST_0_AMT")));
            //redirect to check out page
            url = "public/checkout.jsp";
        } else {
            Map<String, String> nvp = null;
            if (isSet(session.getAttribute("EXPRESS_MARK")) && session.getAttribute("EXPRESS_MARK").equals("ECMark")) {
                checkoutDetails.putAll((Map<String, String>) session.getAttribute("checkoutDetails"));
                checkoutDetails.putAll(setRequestParams(r));
                Float paymentAmount = Float.parseFloat(checkoutDetails.get("PAYMENTREQUEST_0_AMT"));
                try {
                    CurrencyConverter currencyConverter = new BankUaCom(Currency.fromString("VND"),Currency.USD);
                    paymentAmount = currencyConverter.convertCurrency(paymentAmount,Currency.fromString("VND"),Currency.USD);
                } catch (CurrencyConverterException e) {
                    e.printStackTrace();
                }
                //paymentAmount =(Double.parseDouble(checkoutDetails.get("PAYMENTREQUEST_0_AMT")))/20000;
                checkoutDetails.put("PAYMENTREQUEST_0_ITEMAMT", paymentAmount.toString());
                checkoutDetails.put("PAYMENTREQUEST_0_AMT", paymentAmount.toString());
                checkoutDetails.put("L_PAYMENTREQUEST_0_AMT", paymentAmount.toString());
                returnURL = r.equest.getScheme() + "://" + r.equest.getServerName() + ":" + r.equest.getServerPort() + r.equest.getContextPath() + "/public/lightboxreturn?action=view";
                nvp = paypal.callMarkExpressCheckout(checkoutDetails, returnURL, cancelURL);
                session.setAttribute("checkoutDetails", checkoutDetails);
            } else {
                session.invalidate();
                session = r.equest.getSession();
                nvp = paypal.callShortcutExpressCheckout(checkoutDetails, returnURL, cancelURL);
                session.setAttribute("checkoutDetails", checkoutDetails);
            }

            String strAck = nvp.get("ACK").toString().toUpperCase();
            if (strAck != null && (strAck.equals("SUCCESS") || strAck.equals("SUCCESSWITHWARNING"))) {
                session.setAttribute("TOKEN", nvp.get("TOKEN").toString());
                //Redirect to paypal.com
                paypal.redirectURL(r.esponse, nvp.get("TOKEN").toString(), (isSet(session.getAttribute("EXPRESS_MARK")) && session.getAttribute("EXPRESS_MARK").equals("ECMark") || (paypal.getUserActionFlag().equalsIgnoreCase("true"))));
            } else {
                // Display a user friendly Error on the page using any of the following error information returned by PayPal
                String ErrorCode = nvp.get("L_ERRORCODE0").toString();
                String ErrorShortMsg = nvp.get("L_SHORTMESSAGE0").toString();
                String ErrorLongMsg = nvp.get("L_LONGMESSAGE0").toString();
                String ErrorSeverityCode = nvp.get("L_SEVERITYCODE0").toString();

                String errorString = "SetExpressCheckout API call failed. " +
                        "<br>Detailed Error Message: " + ErrorLongMsg +
                        "<br>Short Error Message: " + ErrorShortMsg +
                        "<br>Error Code: " + ErrorCode +
                        "<br>Error Severity Code: " + ErrorSeverityCode;
                r.equest.setAttribute("error", errorString);
                session.invalidate();
            }
        }
        return new JspPage(url);
    }

    private Map<String,String> setRequestParams(R r){
        Map<String,String> requestMap = new HashMap<String,String>();
        for (String key : r.equest.getParameterMap().keySet()) {
            requestMap.put(key, StringEscapeUtils.escapeHtml4(r.equest.getParameterMap().get(key)[0]));
        }
        return requestMap;

    }
    private boolean isSet(Object value){
        return (value !=null && value.toString().length()!=0);
    }
}
