package com.fpt.mic.micweb.controller.pub;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.ContractBusiness;
import com.fpt.mic.micweb.model.entity.ContractTypeEntity;
import com.fpt.mic.micweb.utils.ConfigUtils;

import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dinhquangtrung
 */

@WebServlet(name = "HomeController", urlPatterns = {"/public/home", "/public", "/home"})
public class HomeController extends BasicController {
    public ResponseObject getView(R r) {
        ContractBusiness contractBusiness = new ContractBusiness();
        List<ContractTypeEntity> list = contractBusiness.getAllActiveContractType();
        r.equest.setAttribute("listContractType", list);
        ConfigUtils configUtils = new ConfigUtils();
        r.equest.setAttribute("contractDefaultTerm",configUtils.getContractDefaultTerm());
        return new JspPage("public/home.jsp");
    }

    public ResponseObject postRegister(R r) {
        return getView(r);
    }

}
