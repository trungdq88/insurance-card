package com.fpt.mic.micweb.controller.pub;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.ResponseObject;

import javax.servlet.annotation.WebServlet;

/**
 * Created by dinhquangtrung on 5/24/15.
 */
@WebServlet(name = "RegisterController", urlPatterns = "/public/register")
public class RegisterController extends BasicController {

    public ResponseObject postRegister(R r) {
        return new JspPage("public/register.jsp");
    }

}
