package com.fpt.mic.micweb.controller.customer;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.dto.UserDto;

import javax.servlet.annotation.WebServlet;
import java.util.Collections;
import java.util.List;

/**
 * Created by dinhquangtrung on 5/24/15.
 */
@WebServlet(name = "CustomerCardController", urlPatterns = "/customer/card")
public class CardController extends AuthController {
    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_CUSTOMER);
    }
    public ResponseObject getView(R r) {
        return new JspPage("/customer/card.jsp");
    }
    public ResponseObject getNewCard(R r) {
        return new JspPage("/customer/new-card-request.jsp");
    }
}
