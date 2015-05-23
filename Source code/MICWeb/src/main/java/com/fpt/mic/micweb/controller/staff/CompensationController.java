package com.fpt.mic.micweb.controller.staff;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.ResponseObject;

import javax.servlet.annotation.WebServlet;

/**
 * Created by dinhquangtrung on 5/23/15.
 */
@WebServlet(name = "CompensationController", urlPatterns = {"/staff/compensation"})
public class CompensationController extends BasicController {
    public ResponseObject getView(R r) {
        return new JspPage("staff/compensations.jsp");
    }
    public ResponseObject getDetail(R r) {
        return new JspPage("staff/detail-compensation.jsp");
    }
}
