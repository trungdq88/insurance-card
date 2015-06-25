package com.fpt.mic.micweb.controller.customer;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ResponseObject;

import javax.servlet.annotation.WebServlet;

/**
 * Created by dinhquangtrung on 5/24/15.
 */
@WebServlet(name = "PunishmentController", urlPatterns = "/customer/punishment")
public class PunishmentController extends AuthController {
    public ResponseObject getView(R r) {
        return new JspPage("/customer/punishment.jsp");
    }
}
