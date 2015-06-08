package com.fpt.mic.micweb.controller.pub;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.RegisterBusiness;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.model.entity.PaymentEntity;
import com.fpt.mic.micweb.model.dto.PayPal;
import com.fpt.mic.micweb.utils.DateUtils;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dinhquangtrung on 5/24/15.
 */
@WebServlet(name = "RegisterController", urlPatterns = "/public/register")
public class RegisterController extends BasicController {

    public ResponseObject postRegister(R r) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(r.equest.getParameter("txtName"));
        customerEntity.setEmail(r.equest.getParameter("txtEmail"));
        customerEntity.setAddress(r.equest.getParameter("txtAddress"));
        customerEntity.setPersonalId(r.equest.getParameter("txtPersonalId"));
        customerEntity.setPhone(r.equest.getParameter("txtPhone"));
        r.equest.setAttribute("txtStartDate", r.equest.getParameter("txtStartDate"));
        r.equest.setAttribute("ddlContractType", r.equest.getParameter("ddlContractType"));
        r.equest.setAttribute("txtFee", r.equest.getParameter("txtFee"));
        // r.equest.getParameter()
        System.out.println(customerEntity.getAddress() + " " + customerEntity.getEmail() + " " + customerEntity.getName());
        r.equest.setAttribute("customerEntity", customerEntity);
        return new JspPage("public/register.jsp");
    }

    public ResponseObject postCreateContract(R r) {
        String url = "public/error.jsp";
        // Get information...
        CustomerEntity customerEntity = new CustomerEntity();
        ContractEntity contractEntity = new ContractEntity();

        customerEntity.setPhone(r.equest.getParameter("txtPhone"));
        customerEntity.setAddress(r.equest.getParameter("txtAddress"));
        customerEntity.setEmail(r.equest.getParameter("txtEmail"));
        customerEntity.setName(r.equest.getParameter("txtName"));
        customerEntity.setPersonalId(r.equest.getParameter("txtPersonalId"));

        contractEntity.setPlate(r.equest.getParameter("txtPlate"));
        contractEntity.setBrand(r.equest.getParameter("txtBrand"));
        contractEntity.setModelCode(r.equest.getParameter("txtModel"));
        contractEntity.setVehicleType(r.equest.getParameter("txtType"));
        contractEntity.setColor(r.equest.getParameter("txtColor"));
        contractEntity.setEngine(r.equest.getParameter("txtEngine"));
        contractEntity.setChassis(r.equest.getParameter("txtChassis"));
        contractEntity.setCapacity(r.equest.getParameter("txtCapacity"));
        contractEntity.setYearOfManufacture(Integer.parseInt(r.equest.getParameter("txtYearOfMan")));
        contractEntity.setWeight(Integer.parseInt(r.equest.getParameter("txtWeight")));
        contractEntity.setSeatCapacity(Integer.parseInt(r.equest.getParameter("txtSeatCapacity")));
        contractEntity.setContractFee(Float.parseFloat(r.equest.getParameter("txtFee")));

        // kiem tra payment success?, neu thanh cong thi contract status la Ready, fail thi Pending
        contractEntity.setStatus("Pending");
        int contractTypeId = Integer.parseInt(r.equest.getParameter("ddlContractType"));
        contractEntity.setContractTypeId(contractTypeId);
        // lay ngay nhap vao - add later
        Timestamp startDate = DateUtils.stringToTime(r.equest.getParameter("txtStartDate"));
        contractEntity.setStartDate(startDate);
        contractEntity.setExpiredDate(startDate);

        // Call to business object
        RegisterBusiness registerBusiness = new RegisterBusiness();
        String result = registerBusiness.registerNewContract(customerEntity, contractEntity);

        if (result != null) {
            // Return Success JSP Page
            // payment
            HttpSession session = r.equest.getSession();
            session.setAttribute("CONTRACT_CODE",result);
            System.out.println("Contract code:" + result);
            PayPal paypal = new PayPal();
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

        }

        r.equest.setAttribute("error", "Something wrong");
        return new JspPage(url);
    }

    public ResponseObject postCheckout(R r) {
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

