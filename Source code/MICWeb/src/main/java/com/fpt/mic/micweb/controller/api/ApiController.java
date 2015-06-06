package com.fpt.mic.micweb.controller.api;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JsonString;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.ApiBusiness;
import com.fpt.mic.micweb.model.dto.ContractSearchResult;

import javax.servlet.annotation.WebServlet;
import java.util.List;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/6/15.
 */
@WebServlet(name = "ApiController", urlPatterns = "/api")
public class ApiController extends BasicController {
    /**
     * Search contract API
     * /api?action=contracts&keyword=something
     *
     * keyword could be contract code or customer name
     * @param r
     * @return
     */
    public ResponseObject getContracts(R r) {
        // Get keyword
        String keyword = r.equest.getParameter("keyword");
        if (keyword == null || keyword.isEmpty()) keyword = "";

        // Call business for search
        ApiBusiness apiBusiness = new ApiBusiness();
        List<ContractSearchResult> contractSearchResults =
                apiBusiness.searchContracts(keyword);

        // Return json result
        return new JsonString(contractSearchResults);
    }
}
