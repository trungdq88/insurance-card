package com.fpt.mic.micweb.controller.common;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.model.dto.UserDto;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/25/15.
 *
 * Authenticate & authorize for controller scope
 */
public abstract class AuthController extends BasicController {
    private UserDto userDto;

    /**
     * Check if there is any user logged in
     * @return
     */
    public boolean isLoggedIn() {
        return userDto != null;
    }

    /**
     * Get user entity, could be CustomerEntity or StaffEntity
     * @return
     */
    public Object getUser() {
        return userDto.getUserEntity();
    }

    /**
     * Returns list of role name string whose have right to access the controller
     * @return
     */
    public List<String> getAllowedRoles() {
        return new ArrayList<String>();
    }

    @Override
    public int processRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get userDto from session, always
        HttpSession session = request.getSession(true); // Create session if not already exists
        userDto = (UserDto) session.getAttribute(Constants.Session.USER_DTO);

        // List of allowed roles
        List<String> allowedRoles = getAllowedRoles();
        if (allowedRoles.size() > 0) {
            // There is authentication
            if (userDto == null) {
                return permissionDenied(request, response);
            } else {
                if (!allowedRoles.contains(userDto.getRole())) {
                    return permissionDenied(request, response);
                }
                // else {
                //     Authorized
                // }
            }
        }
        // else {
        //     No need authentication
        // }

        return super.processRequests(request, response);
    }

    private int permissionDenied(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // No logged in user, return 403 page
        return super.processResponse(request, response, new RedirectTo("error/403"));
    }
}
