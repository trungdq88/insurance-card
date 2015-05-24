package com.fpt.mic.micweb.controller.customer;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.ResponseObject;

import javax.servlet.annotation.WebServlet;

/**
 * Created by dinhquangtrung on 5/24/15.
 */
@WebServlet(name = "CompensationController", urlPatterns = "/customer/compensation")
public class CompensationController extends BasicController {
    public ResponseObject getView(R r) {
        return new JspPage("/customer/compensation.jsp");
    }
    public ResponseObject getDetail(R r) {
        return new JspPage("customer/detail-compensation.jsp");
    }

    public ResponseObject getCreate(R r) {
        return new JspPage("customer/create-compensation.jsp");
    }
}
