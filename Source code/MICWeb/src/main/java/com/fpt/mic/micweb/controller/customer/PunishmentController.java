package com.fpt.mic.micweb.controller.customer;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.CustomerBusiness;
import com.fpt.mic.micweb.model.business.PunishmentBusiness;
import com.fpt.mic.micweb.model.dto.UserDto;
import com.fpt.mic.micweb.model.dto.form.CreatePunishmentDto;
import com.fpt.mic.micweb.model.entity.CompensationEntity;
import com.fpt.mic.micweb.model.entity.PunishmentEntity;
import com.fpt.mic.micweb.utils.DateUtils;

import javax.servlet.annotation.WebServlet;
import java.util.Collections;
import java.util.List;

/**
 * Created by dinhquangtrung on 5/24/15.
 */
@WebServlet(name = "PunishmentController", urlPatterns = "/customer/punishment")
public class PunishmentController extends AuthController {
    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_CUSTOMER);
    }

    public ResponseObject getView(R r) {
        return new JspPage("/customer/punishment.jsp");
    }

    public ResponseObject postCreate(R r) {
        String msg = "";
        CustomerBusiness customerBusiness = new CustomerBusiness();
        PunishmentBusiness punishmentBusiness = new PunishmentBusiness();
        CreatePunishmentDto dto = (CreatePunishmentDto) r.ead.entity(CreatePunishmentDto.class, "punishment");
        List errors = r.ead.validate(dto);

        // If there is validation errors
        if (errors.size() > 0) {
            // Send error messages to JSP page
            r.equest.setAttribute("validateErrors", errors);
            // Send submitted data to JSP page
            r.equest.setAttribute("submitted", dto);
            // Re-call the create page
            r.equest.setAttribute("contract", customerBusiness.getContractDetail(dto.getContractCode()));
            return new JspPage("customer/contract-detail.jsp");
        }
        // If the code reached this line that means there is no validation errors
        PunishmentEntity result = punishmentBusiness.createPunishment(dto);
        if (result != null) {
            msg ="Thêm vi phạm mới thành công";
            r.equest.setAttribute("contractCode", dto.getContractCode());
            r.equest.setAttribute("info", msg);
            return new JspPage("/customer/message.jsp");
        } else {
            msg = "Tạo vi phạm mới thất bại, thử lại hay gởi email về hydrangea8604@gmail.com";
            r.equest.setAttribute("result", msg);
            r.equest.setAttribute("contractCode", dto.getContractCode());
            return new JspPage("customer/message.jsp");
        }
    }
}
