package com.fpt.mic.micweb.controller.staff;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.StaffBusiness;
import com.fpt.mic.micweb.model.dto.CustomerDTO;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.CustomerEntity;

import javax.servlet.annotation.WebServlet;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by dinhquangtrung on 5/23/15.
 * Implemented by nguyenchikha on 6/7/15.
 */
@WebServlet(name = "CustomerController", urlPatterns = {"/staff/customer"})
public class CustomerController extends BasicController {

    public ResponseObject getView(R r) {
        StaffBusiness staffBus = new StaffBusiness();
        //List<CustomerDTO> listCustomer = staffBus.getAllCustomer();
        List<CustomerEntity> listCustomer = staffBus.getAllCustomer();
        r.equest.setAttribute("INFO", listCustomer);
        return new JspPage("staff/customers.jsp");
    }

    public ResponseObject getDetail(R r) {
        StaffBusiness staffBus = new StaffBusiness();
        String customerCode = r.equest.getParameter("code");
        System.out.println(customerCode);

        // Get customer detail
        CustomerEntity customerDetail = staffBus.getCustomerDetail(customerCode);
        r.equest.setAttribute("CUSTOMER", customerDetail);

        // Get customer's contracts
        List<ContractEntity> listCustomerContract = staffBus.getContractByCustomerCode(customerDetail.getCustomerCode());
        r.equest.setAttribute("CONTRACTS", listCustomerContract);

        // Dispatch to JSP page
        return new JspPage("staff/customer-detail.jsp");
    }

    public ResponseObject getCreate(R r) {
        return new JspPage("staff/create-customer.jsp");
    }

    public ResponseObject postCreate(R r) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(r.equest.getParameter("txtName"));
        customerEntity.setAddress(r.equest.getParameter("txtAddress"));
        customerEntity.setEmail(r.equest.getParameter("txtEmail"));
        customerEntity.setPhone(r.equest.getParameter("txtPhone"));
        customerEntity.setPersonalId(r.equest.getParameter("txtPersonalID"));
        // Business object
        StaffBusiness staffBus = new StaffBusiness();
        boolean result = staffBus.createCustomer(customerEntity);

        if (result) {
            // Get created customer information

            // Return Success JSP Page
            return new JspPage("staff/create-customer-success.jsp");
        } else {
            r.equest.setAttribute("error", "Something wrong");
            return new JspPage("staff/message.jsp");
        }
    }
}
