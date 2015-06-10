package com.fpt.mic.micweb.controller.pub;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ForwardTo;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.RegisterBusiness;
import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dto.CheckoutRequest;
import com.fpt.mic.micweb.model.dto.PayPal;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.utils.DateUtils;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dinhquangtrung on 5/24/15.
 */
@WebServlet(name = "RegisterController", urlPatterns = "/public/register")
public class RegisterController extends BasicController {

    public ResponseObject getView(R r) {
        return new RedirectTo("/"); // Go to homepage
    }

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
            HttpSession session = r.equest.getSession();
            session.setAttribute("CONTRACT_CODE",result);
            //return new CheckoutController().postCheckout(r);

            // Create new checkout request
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

        r.equest.setAttribute("error", "Something wrong");
        return new JspPage(url);
    }

}

