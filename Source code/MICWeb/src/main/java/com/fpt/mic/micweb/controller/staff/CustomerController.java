package com.fpt.mic.micweb.controller.staff;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ResponseObject;

import javax.servlet.annotation.WebServlet;

/**
 * Created by dinhquangtrung on 5/23/15.
 */
@WebServlet(name = "CustomerController", urlPatterns = {"/staff/customer"})
public class CustomerController extends BasicController {
    public ResponseObject getView(R r) {
        return new JspPage("staff/customers.jsp");
    }

    public ResponseObject getDetail(R r) {
        return new JspPage("staff/detail-customer.jsp");
    }

    public ResponseObject getCreate(R r) {
        return new JspPage("staff/create-customer.jsp");
    }

    public ResponseObject postCreate(R r) {
        return new JspPage("staff/create-customer-success.jsp");
    }
}
