package com.fpt.mic.micweb.controller.staff;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.ResponseObject;

import javax.servlet.annotation.WebServlet;

/**
 * Created by dinhquangtrung on 5/23/15.
 */
@WebServlet(name = "CustomersController", urlPatterns = {"/staff/customers"})
public class CustomersController extends BasicController {
    public ResponseObject getView(R r) {
        return new JspPage("staff/customers.jsp");
    }
}
