package com.fpt.mic.micweb.controller.customer;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.ResponseObject;

import javax.servlet.annotation.WebServlet;

/**
 * Created by dinhquangtrung on 5/24/15.
 */
@WebServlet(name = "CustomerCardController", urlPatterns = "/customer/card")
public class CardController extends BasicController {
    public ResponseObject getView(R r) {
        return new JspPage("/customer/card.jsp");
    }
    public ResponseObject getNewCard(R r) {
        return new JspPage("/customer/new-card-request.jsp");
    }
}
