package com.fpt.mic.micweb.controller.pub;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.CardBusiness;
import com.fpt.mic.micweb.model.business.ContractBusiness;
import com.fpt.mic.micweb.model.business.RegisterBusiness;
import com.fpt.mic.micweb.model.dto.form.ConcurrencyDto;
import com.fpt.mic.micweb.model.dto.form.NewCardRequestDto;
import com.fpt.mic.micweb.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by TriPQM on 06/07/2015.
 */
@WebServlet(name = "LightboxreturnController", urlPatterns = "/public/lightboxreturn")
public class LightboxreturnController extends BasicController {
    public ResponseObject getView (R r) {
        HttpSession session = r.equest.getSession(false);
        // if payment succeed, remember to remove CONTRACT_CODE
        if(session == null || null == session.getAttribute("CONTRACT_CODE")) {
            new RedirectTo("/error/404");
        }
        // check if payment for crete + renew contract
        NewCardRequestDto newCardRequestDto =(NewCardRequestDto) session.getAttribute("NEW_CARD_DTO");
        if( null == newCardRequestDto){

            // Check concurrency
            ConcurrencyDto concurrencyDto = new ConcurrencyDto();
            String contractCode = (String) session.getAttribute("CONTRACT_CODE");
            System.out.println("LightboxreturnController"+ contractCode);

            Timestamp startModifyTime =(Timestamp)
                    session.getAttribute(Constants.Session.CONCURRENCY + contractCode);

            concurrencyDto.setLastModified(startModifyTime);
            concurrencyDto.setContractCode(contractCode);
            List errors = r.ead.validate(concurrencyDto);

            if (errors.size() > 0) {

                StringBuilder message = new StringBuilder();
                for (Object error : errors) {
                    message.append((String) error).append("<br/>");
                }

                r.equest.setAttribute("error", message);
                return new JspPage("public/error.jsp");
            }
        // new card request payment check concurrency
        } else {

        }

//        RegisterBusiness registerBusiness = new RegisterBusiness();
//        ContractBusiness contractBusiness = new ContractBusiness();
//        if(registerBusiness.isPaidContract(contractCode) || registerBusiness.isExistByPlate(contractBusiness.getContract(contractCode).getPlate())){
//            r.equest.setAttribute("error","Đã có hợp đồng hiệu lực với biển số này<br>Thanh toán không thành công");
//            return new JspPage("public/error.jsp");
//        }

        String url = r.equest.getScheme() + "://" + r.equest.getServerName() + ":" + r.equest.getServerPort() + r.equest.getContextPath() + "/";
        url = url + "/public/return?action=return&page=return&";

        Map<String, String[]> parameters = r.equest.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            url += entry.getKey() + "=" + r.equest.getParameter(entry.getKey()) + "&";

        }
        r.equest.setAttribute("url",url);
        return new JspPage("public/lightboxreturn.jsp");
    }
}
