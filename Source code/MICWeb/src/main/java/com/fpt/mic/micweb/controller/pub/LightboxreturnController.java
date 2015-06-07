package com.fpt.mic.micweb.controller.pub;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.ResponseObject;

import javax.servlet.annotation.WebServlet;

/**
 * Created by TriPQMSE60746 on 06/07/2015.
 */
@WebServlet(name = "LightboxreturnController", urlPatterns = "/public/lightboxreturn")
public class LightboxreturnController extends BasicController {
    public ResponseObject getView (R r) {
        return new JspPage("public/lightboxreturn.jsp");
    }
}
