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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/25/15.
 * <p/>
 * Authenticate & authorize for controller scope
 */
public abstract class AuthController extends BasicController {
    private UserDto userDto;

    /**
     * Check if there is any user logged in
     *
     * @return
     */
    public boolean isLoggedIn() {
        return userDto != null;
    }

    /**
     * Get user entity, could be CustomerEntity or StaffEntity
     *
     * @return
     */
    public Object getLoggedInUser() {
        return userDto.getUserEntity();
    }

    /**
     * Returns list of role name string whose have right to access the controller
     * null: everyone can access
     * list: list of role who can access
     *
     * @return
     */
    public abstract List<String> getAllowedRoles();

    @Override
    public int processRequests(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get userDto from session, always
        HttpSession session = request.getSession(true); // Create session if not already exists
        userDto = (UserDto) session.getAttribute(Constants.Session.USER_DTO);

        // List of allowed roles
        List<String> allowedRoles = getAllowedRoles();

        if (allowedRoles != null && allowedRoles.size() > 0) {
            // There is authentication
            if (userDto == null) {
                return permissionDenied(request, response);
            } else {
                if (!allowedRoles.contains(userDto.getRole())) {
                    return permissionDenied(request, response, true);
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

    private int permissionDenied(HttpServletRequest request, HttpServletResponse response,
                                 boolean authorizeFailed)
            throws ServletException, IOException {
        String redirect = getCurrentUrl(request);

        String authorizeMessage = "";
        if (authorizeFailed) {
            authorizeMessage = "&authorize=false";
        }

        // No logged in user, return login page
        return super.processResponse(request, response,
                new RedirectTo("/user?action=login&redirect="
                        + URLEncoder.encode(redirect, "UTF-8")
                        + authorizeMessage));
    }

    private String getCurrentUrl(HttpServletRequest request) {
        if (request.getQueryString() != null) {
            return request.getRequestURI() + "?" + request.getQueryString();
        } else {
            return request.getRequestURI();
        }
    }

    private int permissionDenied(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        return permissionDenied(request, response, false);
    }
}
