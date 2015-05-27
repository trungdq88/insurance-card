package com.fpt.mic.micweb.framework;

import com.fpt.mic.micweb.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
     */
    public void processRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException {
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

            // Process response
            if (responseObject instanceof JspPage) {
                request.getRequestDispatcher(((JspPage) responseObject).getPath())
                        .forward(request, response);
            } else {
                // TODO: process other kind of response
                // Do something else
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new ServletException(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new ServletException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new ServletException(e);
        } catch (ServletException e) {
            e.printStackTrace();
            throw new ServletException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}