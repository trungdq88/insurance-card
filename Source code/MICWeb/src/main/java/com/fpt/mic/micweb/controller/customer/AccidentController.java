package com.fpt.mic.micweb.controller.customer;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.AccidentBusiness;
import com.fpt.mic.micweb.model.dto.UserDto;
import com.fpt.mic.micweb.model.dto.form.AccidentDto;
import com.fpt.mic.micweb.model.entity.AccidentEntity;
import com.fpt.mic.micweb.utils.Constants;

import javax.servlet.annotation.WebServlet;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

/**
 * Created by PhucNguyen on 05/07/2015.
 */
@WebServlet(name = "AccidentController", urlPatterns = {"/customer/accident"})
public class AccidentController  extends AuthController{
    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_CUSTOMER);
    }

    private static String msg = "";

    public ResponseObject getCreate(R r) {
        return new JspPage("/customer/create-accident.jsp");
    }
    public ResponseObject postCreate(R r) {
        AccidentDto dto = (AccidentDto) r.ead.entity(AccidentDto.class, "accident");
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
        AccidentBusiness accidentBusiness = new AccidentBusiness();
        AccidentEntity result = accidentBusiness.createAccident(dto);

        if (result != null) {
            msg = "Gởi thông báo tai nạn mới thành công";
        } else {
            msg = "Gởi thông báo tai nạn thất bại, vui lòng thử lại hoặc liên hệ IT";
        }
        r.equest.setAttribute("contractCode", dto.getContractCode());
        r.equest.setAttribute("info", msg);
        return new JspPage("/customer/message.jsp");
    }
}
