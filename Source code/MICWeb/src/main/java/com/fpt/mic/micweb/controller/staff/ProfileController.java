package com.fpt.mic.micweb.controller.staff;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.StaffBusiness;
import com.fpt.mic.micweb.model.dto.UserDto;
import com.fpt.mic.micweb.model.dto.form.EditStaffProfileDto;
import com.fpt.mic.micweb.model.dto.form.StaffChangePasswordDto;
import com.fpt.mic.micweb.model.entity.StaffEntity;

import javax.servlet.annotation.WebServlet;
import java.util.Collections;
import java.util.List;

/**
 * Created by TriPQM on 07/15/2015.
 */
@WebServlet(name = "ProfileController", urlPatterns = {"/staff/profile"})
public class ProfileController extends AuthController {

    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_STAFF);
    }
    public ResponseObject getView(R r){
        r.equest.setAttribute("staff",(StaffEntity) getLoggedInUser());
        return new JspPage("staff/profile.jsp");
    }

    public ResponseObject getViewEditProfile(R r) {
        r.equest.setAttribute("submitted",(StaffEntity) getLoggedInUser());
        return new JspPage("staff/profile-edit.jsp");
    }

    public ResponseObject postEditProfile(R r) {
        EditStaffProfileDto editStaffProfileDto = (EditStaffProfileDto) r.ead.entity(EditStaffProfileDto.class,"staff");
        List errors = r.ead.validate(editStaffProfileDto);

        // Nếu có lỗi khi validate
        if (errors.size() > 0) {
            // Gửi lỗi về trang JSP
            r.equest.setAttribute("validateErrors", errors);
            // Gửi dữ liệu mà người dùng đã nhập về trang JSP, gán vào biến submitted
            r.equest.setAttribute("submitted", editStaffProfileDto);
            return new JspPage("staff/profile-edit.jsp");
        }
        StaffBusiness staffBusiness= new StaffBusiness();
        if (staffBusiness.updateStaffProfile(editStaffProfileDto, ((StaffEntity) getLoggedInUser()).getStaffCode())){
            return new RedirectTo("/staff/profile?action=view&info=success");
        }
        else {
            return new RedirectTo("/staff/profile?action=view&info=fail");
        }

    }

    public ResponseObject postChangePassword(R r){
        StaffChangePasswordDto dto = (StaffChangePasswordDto) r.ead.entity(StaffChangePasswordDto.class,"newPass");
        dto.setStaffCode(((StaffEntity)getLoggedInUser()).getStaffCode());
        List errors = r.ead.validate(dto);

        // Nếu có lỗi khi validate
        if (errors.size() > 0) {
            // Gửi lỗi về trang JSP
            r.equest.setAttribute("validateErrors", errors);
            // Gửi dữ liệu mà người dùng đã nhập về trang JSP, gán vào biến submitted

            return getView(r);
        }
        StaffBusiness staffBusiness = new StaffBusiness();
        if (staffBusiness.changePassword(dto)){
            return new RedirectTo("/staff/profile?action=view&info=changePasswordSuccess");
        } else {
            return new RedirectTo("/staff/profile?action=view&info=changePasswordFail");
        }

    }
}
