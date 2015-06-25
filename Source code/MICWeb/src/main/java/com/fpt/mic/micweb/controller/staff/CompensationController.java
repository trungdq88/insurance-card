package com.fpt.mic.micweb.controller.staff;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.dto.UserDto;

import javax.servlet.annotation.WebServlet;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by dinhquangtrung on 5/23/15.
 */
@WebServlet(name = "CompensationController", urlPatterns = {"/staff/compensation"})
public class CompensationController extends AuthController {
    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_STAFF);
    }
    public ResponseObject getView(R r) {
        return new JspPage("staff/compensations.jsp");
    }
    public ResponseObject getDetail(R r) {
        return new JspPage("staff/compensation-detail.jsp");
    }
}
