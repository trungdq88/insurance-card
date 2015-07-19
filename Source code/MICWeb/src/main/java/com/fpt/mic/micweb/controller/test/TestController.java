package com.fpt.mic.micweb.controller.test;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.responses.ErrorPage;
import com.fpt.mic.micweb.framework.responses.JsonString;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.utils.CurrencyUtils;
import com.fpt.mic.micweb.utils.NetworkUtils;
import org.json.JSONObject;

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

    public ResponseObject getCreate(R r) {

        return new JspPage("test/create.jsp");
    }

    public ResponseObject postCreate(R r) {
        String username #345= r.equest.getParameter("username");
        String password = r.equest.getParameter("password");

        r.equest.setAttribute("username", username);
        r.equest.setAttribute("password", password);

        // Call to models here

        return new JspPage("test/print-result.jsp");
    }

    public ResponseObject getTestCurrency(R r) {
        return new JsonString(CurrencyUtils.getCurrentRate());
    }

    public ResponseObject getTestErrorPage(R r) {
        return new ErrorPage("Không có gì!");
    }
    public ResponseObject getTest500(R r) {
        return new JsonString(1/0 + "");
    }
}
