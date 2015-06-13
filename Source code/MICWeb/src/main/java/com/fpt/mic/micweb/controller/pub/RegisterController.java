package com.fpt.mic.micweb.controller.pub;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ForwardTo;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.ContractBusiness;
import com.fpt.mic.micweb.model.business.RegisterBusiness;
import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dto.CheckoutRequest;
import com.fpt.mic.micweb.model.dto.PayPal;
import com.fpt.mic.micweb.model.dto.RegisterInformation;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.ContractTypeEntity;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.model.entity.PaymentEntity;
import com.fpt.mic.micweb.utils.Constants;
import com.fpt.mic.micweb.utils.DateUtils;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

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

        ContractTypeEntity contractTypeEntity = new ContractTypeEntity();
        ContractBusiness contractBusiness = new ContractBusiness();
        List<ContractTypeEntity> list = new ArrayList<ContractTypeEntity>();
        list = contractBusiness.getAllContractType();
        HashMap<Integer,ContractTypeEntity> mapContractType = new HashMap<Integer, ContractTypeEntity>();
        for (int i = 0; i< list.size();i++) {
            mapContractType.put(list.get(i).getId(),list.get(i));
        }
        r.equest.setAttribute("mapContractType",mapContractType);
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

        try {
            contractEntity.setYearOfManufacture(Integer.parseInt(r.equest.getParameter("txtYearOfMan")));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            contractEntity.setWeight(Integer.parseInt(r.equest.getParameter("txtWeight")));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            contractEntity.setSeatCapacity(Integer.parseInt(r.equest.getParameter("txtSeatCapacity")));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        contractEntity.setContractFee(Float.parseFloat(r.equest.getParameter("txtFee")));

        // kiem tra payment success?, neu thanh cong thi contract status la Ready, fail thi Pending
        contractEntity.setStatus(Constants.ContractStatus.PENDING);
        int contractTypeId = Integer.parseInt(r.equest.getParameter("ddlContractType"));
        contractEntity.setContractTypeId(contractTypeId);
        // lay ngay nhap vao - add later
        Timestamp startDate = DateUtils.stringToTime(r.equest.getParameter("txtStartDate"));
        contractEntity.setStartDate(startDate);
        contractEntity.setExpiredDate(startDate);

        // Call to business object
        RegisterBusiness registerBusiness = new RegisterBusiness();
        RegisterInformation register = registerBusiness.registerNewContract(customerEntity, contractEntity);

        if (register != null) {
            HttpSession session = r.equest.getSession();
            session.setAttribute("CONTRACT_CODE", register.getContractEntity().getContractCode());
            session.setAttribute("SUCCESS_URL", "/public/register?action=activeContract");

            r.equest.setAttribute("register", register);
            return new JspPage("public/register-payment.jsp");

            // Create new checkout request
//            CheckoutRequest checkoutRequest = new CheckoutRequest();
//            checkoutRequest.setPaymentrequest_name(r.equest.getParameter("L_PAYMENTREQUEST_0_NAME0"));
//            checkoutRequest.setPaymentrequest_desc(r.equest.getParameter("L_PAYMENTREQUEST_0_DESC0"));
//            checkoutRequest.setPaymentrequest_qty(r.equest.getParameter("L_PAYMENTREQUEST_0_QTY0"));
//            checkoutRequest.setPaymentrequest_itemamt(r.equest.getParameter("PAYMENTREQUEST_0_ITEMAMT"));
//            checkoutRequest.setPaymentrequest_taxamt(r.equest.getParameter("PAYMENTREQUEST_0_TAXAMT"));
//            checkoutRequest.setPaymentrequest_amt(r.equest.getParameter("PAYMENTREQUEST_0_AMT"));
//            checkoutRequest.setCurrencycodetype(r.equest.getParameter("currencyCodeType"));
//            checkoutRequest.setPaymenttype(r.equest.getParameter("paymentType"));
//            checkoutRequest.setPaymentrequest_amt_l(r.equest.getParameter("PAYMENTREQUEST_0_AMT"));
//
//            return new RedirectTo("/public/checkout?action=checkout&checkout=true&" + checkoutRequest.getQueryString());
        }

        r.equest.setAttribute("error", "Something wrong");
        return new JspPage(url);
    }

    public ResponseObject getActiveContract(R r) {
        String url = "public/return.jsp";
        HttpSession session = r.equest.getSession(true);
        Map<String, String> results = new HashMap<String, String>();
        results.putAll((Map<String, String>) session.getAttribute("RESULT"));

        r.equest.setAttribute("result", results);
        r.equest.setAttribute("ack", (String) session.getAttribute("ACK"));
        HashMap<String,String> details = (HashMap<String,String> )session.getAttribute("checkoutDetails");
        r.equest.setAttribute("amountVND",(String) session.getAttribute("amountVND"));
        r.equest.setAttribute("redirectLink","home");
        ContractEntity contractEntity = new ContractEntity();
        ContractDao contractDao = new ContractDao();
        PaymentEntity paymentEntity = new PaymentEntity();

        // get contract just added by contract_code
        String code = (String) session.getAttribute("CONTRACT_CODE");
        contractEntity = contractDao.read(code);
        DateUtils date = new DateUtils();

        // set start date
        // Timestamp startDate = DateUtils.stringToTime(checkoutDetails.get("txtStartDate"));
        Timestamp currentDate = new Timestamp(new Date().getTime());
        contractEntity.setStartDate(currentDate);
        // set expired date = start_date + 1 year
        contractEntity.setExpiredDate(DateUtils.addOneYear(contractEntity.getStartDate()));
        contractEntity.setStatus(Constants.ContractStatus.NO_CARD);
        contractDao.update(contractEntity);

        paymentEntity.setPaidDate(new Timestamp(new Date().getTime()));
        paymentEntity.setPaymentMethod("PayPal payment");
        paymentEntity.setContent("Đăng ký hợp đồng mới");
        paymentEntity.setAmount(Float.parseFloat(results.get("PAYMENTINFO_0_AMT").toString()));
        paymentEntity.setPaypalTransId(results.get("PAYMENTINFO_0_TRANSACTIONID").toString());
        paymentEntity.setContractCode(contractEntity.getContractCode());


        RegisterBusiness registerBusiness = new RegisterBusiness();
        registerBusiness.updateContractPayment(contractEntity, paymentEntity);
        session.invalidate();
        return new JspPage(url);
    }

}

