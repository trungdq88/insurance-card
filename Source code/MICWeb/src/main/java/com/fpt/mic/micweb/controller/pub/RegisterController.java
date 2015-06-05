package com.fpt.mic.micweb.controller.pub;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.ResponseObject;
import com.fpt.mic.micweb.model.business.RegisterBusiness;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.model.entity.PaymentEntity;

import javax.servlet.annotation.WebServlet;

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
        r.equest.setAttribute("txtStartDate",r.equest.getParameter("txtStartDate"));
        r.equest.setAttribute("ddlContractType", r.equest.getParameter("ddlContractType"));
        r.equest.setAttribute("txtFee", r.equest.getParameter("txtFee"));
        // r.equest.getParameter()
        System.out.println(customerEntity.getAddress() + " " + customerEntity.getEmail() + " " + customerEntity.getName());
        r.equest.setAttribute("customerEntity",customerEntity);
        return new JspPage("public/register.jsp");
    }

    public ResponseObject postCreateContract(R r) {
        // Get information...
        CustomerEntity customerEntity = new CustomerEntity();
        ContractEntity contractEntity = new ContractEntity();
        PaymentEntity paymentEntity = null;

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


        // Call to business object
        RegisterBusiness registerBusiness = new RegisterBusiness();
        boolean result = registerBusiness.registerNewContract(customerEntity, contractEntity, paymentEntity);

        if (result) {
            // Return Success JSP Page
            return new JspPage("public/register-success.jsp");
        } else {
            r.equest.setAttribute("error", "Something wrong");
            return new JspPage("error/msg.jsp");
        }
    }

}
