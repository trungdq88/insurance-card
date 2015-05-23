package com.fpt.mic.micweb.controller.test;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.ResponseObject;

import javax.servlet.annotation.WebServlet;

/**
 * Created by dinhquangtrung on 5/22/15.
 */
@WebServlet(name = "TestController", urlPatterns = {"/testcontroller"})
public class TestController extends BasicController {

    /**
     * If no action was provided, this action will be used to process the requests
     * Example: a GET request to
     *      http://domain.com/testcontroller
     * will call the getView method.
     */
    @Override
    protected String getDefaultAction() {
        return "view";
    }

    /**
     * How to process a GET request.
     * - Method name starts with "get", next letter must be in uppercase.
     * - When access by URL, the URL should be
     *      http://domain.com/testcontroller?action=test&other=information&put=here;
     * @param r R object : this object contains HttpServletRequest and HttpResponseResponse
     *          Usage: `r.equest` to get request object
     *                 `r.esponse` to get response object
     * @return ResponseObject
     */
    public ResponseObject getTest(R r) {
        return new JspPage("test/get.jsp");
    }

    /**
     * How to process a POST request
     * - Method name starts with "post", next letter must be in uppercase.
     * - When send POST request, there must be a parameter named "action", example:
     *      <form action="testcontroller">
     *          <input type="hidden" name="action" value="test"/>
     *          <input type="submit" value="Submit form"/>
     *      </form>
     * @param r R object : this object contains HttpServletRequest and HttpResponseResponse
     *          Usage: `r.equest` to get request object
     *                 `r.esponse` to get response object
     * @return ResponseObject
     */
    public ResponseObject postTest(R r) {
        return new JspPage("test/post.jsp");
    }

    public ResponseObject getView(R r) {
        return new JspPage("test/view.jsp");
    }

}
