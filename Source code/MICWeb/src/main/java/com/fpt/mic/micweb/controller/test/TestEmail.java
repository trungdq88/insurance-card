package com.fpt.mic.micweb.controller.test;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JsonString;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.utils.EmailUtils;
import com.fpt.mic.micweb.utils.StringUtils;

import javax.servlet.annotation.WebServlet;
import java.io.InputStream;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/25/15.
 */
@WebServlet(name = "TestEmail", urlPatterns = {"/testemail"})
public class TestEmail extends BasicController {
    public ResponseObject getView(R r) {
        InputStream resourceAsStream = getServletContext().getResourceAsStream("/WEB-INF/templates/password-email.html");
        String content = StringUtils.getString(resourceAsStream);
        boolean b = EmailUtils.sendMail("trungdq88@gmail.com", content);
        return new JsonString(b);
    }
}
