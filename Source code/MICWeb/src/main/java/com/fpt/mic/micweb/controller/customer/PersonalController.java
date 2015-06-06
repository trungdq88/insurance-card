package com.fpt.mic.micweb.controller.customer;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ResponseObject;

import javax.servlet.annotation.WebServlet;

/**
 * Created by dinhquangtrung on 5/24/15.
 */
@WebServlet(name = "PersonalController", urlPatterns = "/customer")
public class PersonalController extends BasicController {
    public ResponseObject getView(R r) {
        return new JspPage("customer/personal-information.jsp");
    }
}
