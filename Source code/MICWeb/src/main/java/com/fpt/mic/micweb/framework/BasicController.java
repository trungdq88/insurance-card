package com.fpt.mic.micweb.framework;

import com.fpt.mic.micweb.framework.responses.ForwardTo;
import com.fpt.mic.micweb.framework.responses.JsonString;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by dinhquangtrung on 5/23/15.
 */
public abstract class BasicController extends HttpServlet {

    protected String defaultAction = "view";

    protected String getDefaultAction() {
        return defaultAction;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequests(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequests(request, response);
    }

    /**
     * This method receive all GET and POST requests, get "action" parameter and invoke methods
     * @param request request
     * @param response response
     * @return int
     */
    public int processRequests(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Get request method and action
            String requestMethod = request.getMethod().toLowerCase();
            String action = request.getParameter("action");
            if (action == null || action.isEmpty()) {
                action = getDefaultAction();
            }

            // Invoke method from controller
            String method = requestMethod + StringUtils.uppercaseFirstLetter(action);
            // Find the method with signature: ResponseObject method(R r);
            Method invoker = this.getClass().getMethod(method, R.class);
            // Get response
            ResponseObject responseObject = (ResponseObject) invoker.invoke(this, new R(request, response));

            return processResponse(request, response, responseObject);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new ServletException(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new ServletException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    public int processResponse(HttpServletRequest request,
                                HttpServletResponse response,
                                ResponseObject responseObject)
            throws ServletException, IOException {
        // Process response
        if (responseObject instanceof JspPage) {
            // Dispatch the request to JSP page
            request.getRequestDispatcher(((JspPage) responseObject).getPath())
                    .forward(request, response);
        } else if (responseObject instanceof JsonString) {
            // Write JSON string
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(((JsonString) responseObject).getJsonString());
            writer.close();
        } else if (responseObject instanceof ForwardTo) {
            request.getRequestDispatcher(((ForwardTo) responseObject).getControllerUrl())
                    .forward(request, response);
        } else if (responseObject instanceof RedirectTo) {
            response.sendRedirect(((RedirectTo) responseObject).getUrl());
        } else {
            // TODO: process other kind of response
            // Do something else
            return -1;
        }

        return 0;
    }
}
