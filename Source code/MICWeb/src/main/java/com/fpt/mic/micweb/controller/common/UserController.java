package com.fpt.mic.micweb.controller.common;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.dto.UserDto;
import com.fpt.mic.micweb.model.dto.form.LoginDto;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.model.entity.StaffEntity;
import com.fpt.mic.micweb.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/25/15.
 */
@WebServlet(name = "UserController", urlPatterns = {"/user"})
public class UserController extends BasicController {
    public ResponseObject getView(R r) {
        return getLogin(r);
    }

    public ResponseObject getLogin(R r) {
        return new JspPage("common/login.jsp");
    }

    public ResponseObject getLogout(R r) {
        HttpSession session = r.equest.getSession(true);
        session.setAttribute(Constants.Session.USER_DTO, null);
        return new RedirectTo("/");
    }

    public ResponseObject postLogin(R r) {
        LoginDto loginDto = (LoginDto) r.ead.entity(LoginDto.class, "login");

        List errors = r.ead.validate(loginDto);
        if (errors.size() > 0) {
                // Send error messages to JSP page
                r.equest.setAttribute("validateErrors", errors);
                // Send submitted data to JSP page
                r.equest.setAttribute("submitted", loginDto);
                // Re-call the create page
                return getView(r);
        }

        ////// Login success:

        // Get the user
        UserDto userDto = null;
        if (loginDto.getRole().equals(UserDto.ROLE_CUSTOMER)) {
            CustomerEntity user = (CustomerEntity) loginDto.getUserEntity();
            userDto = new UserDto(user.getCustomerCode(), UserDto.ROLE_CUSTOMER);
        } else if (loginDto.getRole().equals(UserDto.ROLE_STAFF)) {
            StaffEntity user = (StaffEntity) loginDto.getUserEntity();
            userDto = new UserDto(user.getStaffCode(), UserDto.ROLE_STAFF);
        }

        // Set the session
        HttpSession session = r.equest.getSession(true);
        session.setAttribute(Constants.Session.USER_DTO, userDto);

        if (loginDto.getRedirect() != null && !loginDto.getRedirect().isEmpty()) {
            try {
                return new RedirectTo(URLDecoder.decode(loginDto.getRedirect(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        // This will continue to run if the redirect URL has encoding problem
        if (loginDto.getRole().equals(UserDto.ROLE_CUSTOMER)) {
            return new RedirectTo("/customer");
        } else if (loginDto.getRole().equals(UserDto.ROLE_STAFF)) {
            return new RedirectTo("/staff");
        } else {
            return null;
        }
    }
}
