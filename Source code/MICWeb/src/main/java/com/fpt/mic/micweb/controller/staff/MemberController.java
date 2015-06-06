package com.fpt.mic.micweb.controller.staff;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ResponseObject;

import javax.servlet.annotation.WebServlet;

/**
 * Created by dinhquangtrung on 5/23/15.
 */
@WebServlet(name = "MemberController", urlPatterns = {"/staff/member"})
public class MemberController extends BasicController {
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
