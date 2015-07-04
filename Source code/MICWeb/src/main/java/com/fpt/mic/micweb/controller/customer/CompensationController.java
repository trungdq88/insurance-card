package com.fpt.mic.micweb.controller.customer;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.CompensationBusiness;
import com.fpt.mic.micweb.model.dto.UserDto;
import com.fpt.mic.micweb.model.dto.form.CreateCompensationDto;
import com.fpt.mic.micweb.model.entity.CompensationEntity;
import com.fpt.mic.micweb.utils.DateUtils;

import javax.servlet.annotation.WebServlet;
import java.util.Collections;
import java.util.List;

/**
 * Created by dinhquangtrung on 5/24/15.
 */
@WebServlet(name = "CustomerCompensationController", urlPatterns = "/customer/compensation")
public class CompensationController extends AuthController {
    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_CUSTOMER);
    }

    public ResponseObject getView(R r) {
        return new JspPage("/customer/compensation.jsp");
    }

    public ResponseObject getDetail(R r) {
        String compensationCode = r.equest.getParameter("code");

        // Get compensation detail information
        CompensationBusiness compensationBusiness = new CompensationBusiness();
        CompensationEntity compensationEntity = compensationBusiness.getCompensationDetail(compensationCode);

        r.equest.setAttribute("compensation", compensationEntity);
        return new JspPage("customer/detail-compensation.jsp");
    }

    public ResponseObject getCreate(R r) {
        String contractCode = r.equest.getParameter("contractCode");
        r.equest.setAttribute("contractCode", contractCode);
        return new JspPage("customer/create-compensation.jsp");
    }

    public ResponseObject postCreate(R r) {
        String msg = "";
        CreateCompensationDto dto = (CreateCompensationDto) r.ead.entity(CreateCompensationDto.class, "compensation");
        dto.setCreatedDate(DateUtils.currentDateWithoutTime());
        dto.setAccidentDate(DateUtils.currentDateWithoutTime());
        List errors = r.ead.validate(dto);

        // If there is validation errors
        if (errors.size() > 0) {
            // Send error messages to JSP page
            r.equest.setAttribute("validateErrors", errors);
            // Send submitted data to JSP page
            r.equest.setAttribute("submitted", dto);
            // Re-call the create page
            return getCreate(r);
        }
        // If the code reached this line that means there is no validation errors

        // Call to business object
        CompensationBusiness compensationBusiness = new CompensationBusiness();
        CompensationEntity result = compensationBusiness.createCompensation(dto);

        if (result != null) {
            r.equest.setAttribute("compensation", result);
            return new RedirectTo("compensation?action=Detail&code=" + result.getCompensationCode());
        } else {
            msg = "Tạo yêu cầu bồi thường thất bại, vui lòng thử lại hoặc gởi email tới địa chỉ  hydrangea8604@gmail.com";
            r.equest.setAttribute("result", msg);
            r.equest.setAttribute("contractCode", dto.getContractCode());
            return new JspPage("customer/message.jsp");
        }
    }
}
