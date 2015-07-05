package com.fpt.mic.micweb.controller.staff;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.PunishmentBusiness;
import com.fpt.mic.micweb.model.dto.UserDto;
import com.fpt.mic.micweb.model.dto.form.PunishmentDto;
import com.fpt.mic.micweb.model.entity.PunishmentEntity;
import com.fpt.mic.micweb.utils.Constants;

import javax.servlet.annotation.WebServlet;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

/**
 * Created by Kha on 05/07/2015.
 */
@WebServlet(name = "StaffPunishmentController", urlPatterns = {"/staff/punishment"})
public class PunishmentController extends AuthController {
    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_STAFF);
    }

    private static String msg = "";

    public ResponseObject getCreate(R r) {
        return new JspPage("/staff/create-punishment.jsp");
    }

    public ResponseObject getEdit(R r) {
        Integer punishmentId = 0;
        String id = r.equest.getParameter("id");
        if (id != null) {
            try {
                punishmentId = Integer.parseInt(id);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }

        // Receive punishmentId from failed validation
        // @see {@link postEdit}
        if (punishmentId == 0) {
            punishmentId = (Integer) r.equest.getAttribute("punishmentId");
        }

        // Get compensation detail information
        PunishmentBusiness punishmentBusiness = new PunishmentBusiness();
        PunishmentEntity punishmentEntity = punishmentBusiness.getPunishmentDetail(punishmentId);

        // If punishment is not exists, show 404 page
        if (punishmentEntity == null) {
            return new RedirectTo("/error/404");
        }

        // Save last_modified value for concurrency check
        r.equest.getSession(true).setAttribute(
                Constants.Session.CONCURRENCY + punishmentId, punishmentEntity.getLastModified());

        r.equest.setAttribute("PUNISHMENT", punishmentEntity);
        return new JspPage("/staff/edit-punishment.jsp");
    }

    public ResponseObject postCreate(R r) {
        PunishmentDto dto = (PunishmentDto) r.ead.entity(PunishmentDto.class, "punishment");
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
        PunishmentBusiness punishmentBusiness = new PunishmentBusiness();
        PunishmentEntity result = punishmentBusiness.createNewPunishment(dto);

        if (result != null) {
            msg = "Thêm vi phạm luật ATGT thành công";
        } else {
            msg = "Thêm vi phạm luật ATGT thất bại, vui lòng thử lại hoặc liên hệ IT";
        }
        r.equest.setAttribute("CODE", dto.getContractCode());
        r.equest.setAttribute("MESSAGE", msg);
        return new JspPage("/staff/message.jsp");
    }

    public ResponseObject postEdit(R r) {
        PunishmentDto dto = (PunishmentDto) r.ead.entity(PunishmentDto.class, "edit");

        // Get concurrency data
        Timestamp lastModified = (Timestamp) r.equest.getSession(true).getAttribute(
                Constants.Session.CONCURRENCY + dto.getId());
        dto.setLastModified(lastModified);

        List errors = r.ead.validate(dto);

        // If there is validation errors
        if (errors.size() > 0) {
            // Send error messages to JSP page
            r.equest.setAttribute("validateErrors", errors);
            // This is a form in a popup, we don't need to display data again since
            // the popup will not automatically open when the page is reloaded
            // r.equest.setAttribute("submitted", dto);
            // Re-call the edit punishment page
            r.equest.setAttribute("punishmentId", dto.getId());
            return getEdit(r);
        }
        // If the code reached this line that means there is no validation errors

        // Call to business object
        PunishmentBusiness punishmentBusiness = new PunishmentBusiness();
        boolean result = punishmentBusiness.editPunishment(dto);

        if (result) {
            msg = "Chỉnh sửa vi phạm luật ATGT thành công";
        } else {
            msg = "Chỉnh sửa vi phạm luật ATGT thất bại, vui lòng thử lại hoặc liên hệ IT";
        }
        r.equest.setAttribute("CODE", dto.getContractCode());
        r.equest.setAttribute("MESSAGE", msg);
        return new JspPage("/staff/message.jsp");
    }
}
