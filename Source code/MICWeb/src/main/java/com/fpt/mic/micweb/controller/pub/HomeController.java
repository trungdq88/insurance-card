package com.fpt.mic.micweb.controller.pub;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.ResponseObject;

import javax.servlet.annotation.WebServlet;

/**
 *
 * @author dinhquangtrung
 */

@WebServlet(name = "HomeController", urlPatterns = {"/public/home"})
public class HomeController extends BasicController {
    public ResponseObject getView(R r) {
        return new JspPage("public/home.jsp");
    }

}
