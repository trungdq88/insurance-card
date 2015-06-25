package com.fpt.mic.micweb.controller.customer;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.dto.UserDto;

import javax.servlet.annotation.WebServlet;
import java.util.Collections;
import java.util.List;

/**
 * Created by dinhquangtrung on 5/24/15.
 */
@WebServlet(name = "CustomerCompensationController", urlPatterns = "/customer/compensation")
public class CompensationController extends AuthController {
    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_CUSTOMER);
    }
    public ResponseObject getView(R r) {
        return new JspPage("/customer/compensation.jsp");
    }
    public ResponseObject getDetail(R r) {
        return new JspPage("customer/compensation-detail.jsp");
    }
    public ResponseObject getCreate(R r) {
        return new JspPage("customer/create-compensation.jsp");
    }
}
