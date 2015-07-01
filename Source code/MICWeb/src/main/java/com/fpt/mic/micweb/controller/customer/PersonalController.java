package com.fpt.mic.micweb.controller.customer;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.CustomerBusiness;
import com.fpt.mic.micweb.model.dto.UserDto;
import com.fpt.mic.micweb.model.entity.CustomerEntity;

import javax.servlet.annotation.WebServlet;
import java.util.Collections;
import java.util.List;

/**
 * Created by dinhquangtrung on 5/24/15.
 */
@WebServlet(name = "PersonalController", urlPatterns = "/customer")
public class PersonalController extends AuthController {
    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_CUSTOMER);
    }

    public ResponseObject getView(R r) {
        final CustomerBusiness customerBusiness = new CustomerBusiness();
        final String customerCode = ((CustomerEntity) getLoggedInUser()).getCustomerCode();
        CustomerEntity customerEntity = customerBusiness.getCustomer(customerCode);
        if (customerEntity == null) {
            return new RedirectTo("/error/404");
        }
        r.equest.setAttribute("customer", customerEntity);
        return new JspPage("customer/personal-information.jsp");

    }
}
