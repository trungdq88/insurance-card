package com.fpt.mic.micweb.controller.staff;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ResponseObject;

import javax.servlet.annotation.WebServlet;

/**
 * Created by dinhquangtrung on 5/23/15.
 */
@WebServlet(name = "DashboardController", urlPatterns = {"/staff"})
public class DashboardController extends BasicController {

    public ResponseObject getView(R r) {
        return new JspPage("staff/dashboard.jsp");
    }
}
