package com.fpt.mic.micweb.model.dto.form;

import com.fpt.mic.micweb.model.dao.StaffDao;
import com.fpt.mic.micweb.utils.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

/**
 * Created by TriPQM on 07/16/2015.
 */
public class StaffChangePasswordDto {
    private String staffCode;
    @NotEmpty(message = "Mật khẩu hiện tại không được để trống")
    private String currentPassword;

    @NotEmpty(message = "Mật khẩu mới không được để trống")
    @Size(min = 6, max = 32, message = "Mật khẩu phải từ 6 đến 32 ký tự")
    private String newPassword;

    @NotEmpty(message = "Xác nhận mật khẩu không được để trống")
    @Size(min = 6, max = 32, message = "Mật khẩu phải từ 6 đến 32 ký tự")
    private String confirmPassword;

    @AssertTrue(message = "Sai mật khẩu hiện tại")
    private boolean isMatchCurrentPassword() {
        StaffDao staffDao= new StaffDao();
        String encryptedPassword = StringUtils.getMD5Hash(currentPassword);
        return staffDao.read(staffCode).getPassword().equals(encryptedPassword);
    }
    @AssertTrue(message = "Mật khẩu mới không được trùng với mật khẩu hiện tại")
    private boolean isDuplicateOldPass() {
        StaffDao staffDao = new StaffDao();
        String encryptedPassword = StringUtils.getMD5Hash(newPassword);
        return !staffDao.read(staffCode).getPassword().equals(encryptedPassword);
    }
    @AssertTrue(message = "Xác nhận mật khẩu không khớp với mật khẩu mới")
    private boolean isMatchPassword() {
        return newPassword.equals(confirmPassword);
    }

    public StaffChangePasswordDto() {
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
