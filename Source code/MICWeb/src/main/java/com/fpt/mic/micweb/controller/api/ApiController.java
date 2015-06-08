package com.fpt.mic.micweb.controller.api;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JsonString;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.ApiBusiness;
import com.fpt.mic.micweb.model.dto.ContractSearchResult;
import com.fpt.mic.micweb.model.entity.CardEntity;

import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.List;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/6/15.
 */
@WebServlet(name = "ApiController", urlPatterns = "/api")
public class ApiController extends BasicController {

    ApiBusiness apiBusiness = new ApiBusiness();

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

        // If no keyword provided, return empty array
        if (keyword == null || keyword.isEmpty()) {
            return new JsonString(new ArrayList<ContractSearchResult>());
        }

        // Call business for search
        List<ContractSearchResult> contractSearchResults =
                apiBusiness.searchContracts(keyword);

        // Return json result
        return new JsonString(contractSearchResults);
    }

    /**
     * Update card ID to a contract.
     * This API is called from Printer Mobile App
     * @param r
     * @return
     */
    public ResponseObject getUpdateCardID(R r) {
        String contractCode = r.equest.getParameter("contractCode");
        String cardID = r.equest.getParameter("cardID");

        if (contractCode == null || contractCode.isEmpty()) {
            return new JsonString(null);
        }
        if (cardID == null || cardID.isEmpty()) {
            return new JsonString(null);
        }

        // Call to business
        CardEntity result = apiBusiness.updateCardID(contractCode, cardID);

        // Return json result
        return new JsonString(result);
    }
}
