package com.fpt.mic.micweb.controller.api;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JsonString;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.ApiBusiness;
import com.fpt.mic.micweb.model.dto.ContractSearchResultDto;
import com.fpt.mic.micweb.model.entity.CardInstanceEntity;

import javax.servlet.annotation.WebServlet;
import java.util.Date;
import java.util.List;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/6/15.
 */
@WebServlet(name = "ApiController", urlPatterns = "/api")
public class ApiController extends BasicController {

    ApiBusiness apiBusiness = new ApiBusiness();


    public ResponseObject getGetTime(R r) {
        return new JsonString(new Date());
    }
    public ResponseObject getCheckConnection(R r) {
        return new JsonString(true);
    }

    /**
     * Search contract API
     * /api?action=contracts&keyword=something
     *
     * keyword could be contract code or customer name
     * if no keyword is provided, return list of contract order by Pending status first
     * @param r
     * @return
     */
    public ResponseObject getContracts(R r) {
        // Get keyword
        String keyword = r.equest.getParameter("keyword");

        // Call business for search
        List<ContractSearchResultDto> contractSearchResultDtos =
                apiBusiness.searchContracts(keyword);

        // Return json result
        return new JsonString(contractSearchResultDtos);
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
        CardInstanceEntity result = apiBusiness.updateCardID(contractCode, cardID);

        // Return json result
        return new JsonString(result);
    }

    public ResponseObject getCheckCard(R r) {
        String cardID = r.equest.getParameter("cardID");
        String deviceID = r.equest.getParameter("deviceID");

        if (cardID == null || cardID.isEmpty()) {
            return new JsonString(null);
        }
        if (deviceID == null || deviceID.isEmpty()) {
            return new JsonString(null);
        }

        // Call to business
        CardInstanceEntity card = apiBusiness.checkCard(deviceID, cardID);

        // Return json result
        return new JsonString(card);
    }

    public ResponseObject getUpdatePunishment(R r) {
        String contractCode = r.equest.getParameter("contractCode");
        String title = r.equest.getParameter("title");
        String photo = r.equest.getParameter("photo");
        String deviceID = r.equest.getParameter("deviceID");


        if (contractCode == null || contractCode.isEmpty()) {
            return new JsonString(false);
        }
        if (title == null || title.isEmpty()) {
            return new JsonString(false);
        }
        if (photo == null || photo.isEmpty()) {
            return new JsonString(false);
        }
        if (deviceID == null || deviceID.isEmpty()) {
            return new JsonString(null);
        }

        // Call to business
        Boolean result = apiBusiness.updatePunishment(deviceID, contractCode, title, photo);

        return new JsonString(result);

    }
}
