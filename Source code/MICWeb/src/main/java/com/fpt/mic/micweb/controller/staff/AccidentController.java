package com.fpt.mic.micweb.controller.staff;

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
 * Created by Kha on 05/07/2015.
 */
@WebServlet(name = "StaffAccidentController", urlPatterns = {"/staff/accident"})
public class AccidentController extends AuthController {
    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_STAFF);
    }

    private static String msg = "";
    private static boolean isSuccess;

    public ResponseObject getCreate(R r) {
        return new JspPage("/staff/create-accident.jsp");
    }

    public ResponseObject getEdit(R r) {
        Integer accidentId = 0;
        String id = r.equest.getParameter("id");
        if (id != null) {
            try {
                accidentId = Integer.parseInt(id);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }

        // Receive accidentId from failed validation
        // @see {@link postEdit}
        if (accidentId == 0) {
            accidentId = (Integer) r.equest.getAttribute("accidentId");
        }

        // Get compensation detail information
        AccidentBusiness accidentBusiness = new AccidentBusiness();
        AccidentEntity accidentEntity = accidentBusiness.getAccidentDetail(accidentId);

        // If contract is not exists, show 404 page
        if (accidentEntity == null) {
            return new RedirectTo("/error/404");
        }

        // Save last_modified value for concurrency check
        r.equest.getSession(true).setAttribute(
                Constants.Session.CONCURRENCY + accidentId, accidentEntity.getLastModified());

        r.equest.setAttribute("ACCIDENT", accidentEntity);
        return new JspPage("/staff/edit-accident.jsp");
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
            isSuccess = true;
            msg = "Gởi thông báo tai nạn mới thành công";
        } else {
            isSuccess = false;
            msg = "Gởi thông báo tai nạn thất bại, vui lòng thử lại hoặc liên hệ IT";
        }
        r.equest.setAttribute("CODE", dto.getContractCode());
        r.equest.setAttribute("MESSAGE", msg);
        r.equest.setAttribute("SUCCESS", isSuccess);
        return new JspPage("/staff/message.jsp");
    }

    public ResponseObject postEdit(R r) {
        AccidentDto dto = (AccidentDto) r.ead.entity(AccidentDto.class, "edit");

        // Get concurrency data
        Timestamp lastModified = (Timestamp) r.equest.getSession(true).getAttribute(
                Constants.Session.CONCURRENCY + dto.getId());
        dto.setLastModified(lastModified);

        List errors = r.ead.validate(dto);

        // If there is validation errors
        if (errors.size() > 0) {
            // Send error messages to JSP page
            r.equest.setAttribute("validateErrors", errors);
            // Send submitted data to JSP page
            r.equest.setAttribute("submitted", dto);
            // Re-call the contract detail page
            r.equest.setAttribute("accidentId", dto.getId());
            return getEdit(r);
        }
        // If the code reached this line that means there is no validation errors

        // Call to business object
        AccidentBusiness accidentBusiness = new AccidentBusiness();
        boolean result = accidentBusiness.editAccident(dto);

        if (result) {
            isSuccess = true;
            msg = "Chỉnh sửa thông báo tai nạn thành công";
        } else {
            isSuccess = false;
            msg = "Chỉnh sửa thông báo tai nạn thất bại, vui lòng thử lại hoặc liên hệ IT";
        }
        r.equest.setAttribute("CODE", dto.getContractCode());
        r.equest.setAttribute("MESSAGE", msg);
        r.equest.setAttribute("SUCCESS", isSuccess);
        return new JspPage("/staff/message.jsp");
    }
}
