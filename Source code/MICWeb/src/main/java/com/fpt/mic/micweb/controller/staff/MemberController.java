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
@WebServlet(name = "MemberController", urlPatterns = {"/staff/member"})
public class MemberController extends AuthController {
    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_STAFF);
    }
    public ResponseObject getView(R r) {
        return new JspPage("staff/members.jsp");
    }
    public ResponseObject getCreate(R r) {
        return new JspPage("staff/create-member.jsp");
    }
    public ResponseObject getSuccess(R r) {
        return new JspPage("staff/create-member-success.jsp");
    }

}
