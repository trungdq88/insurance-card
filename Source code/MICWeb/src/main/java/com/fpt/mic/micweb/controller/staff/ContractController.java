package com.fpt.mic.micweb.controller.staff;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ResponseObject;

import javax.servlet.annotation.WebServlet;

/**
 * Created by dinhquangtrung on 5/23/15.
 */
@WebServlet(name = "ContractController", urlPatterns = {"/staff/contract"})
public class ContractController extends BasicController {
    public ResponseObject getView(R r) {
        return new JspPage("staff/contracts.jsp");
    }
    public ResponseObject getDetail(R r) {
        return new JspPage("staff/detail-contract.jsp");
    }
    public ResponseObject getCreate(R r) {
        return new JspPage("staff/create-contract.jsp");
    }
    public ResponseObject getPreview(R r) {
        return new JspPage("staff/create-contract-preview.jsp");
    }
    public ResponseObject getSuccess(R r) {
        return new JspPage("staff/create-contract-success.jsp");
    }
}
