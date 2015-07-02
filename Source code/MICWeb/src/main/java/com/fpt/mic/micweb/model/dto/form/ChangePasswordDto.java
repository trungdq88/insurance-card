package com.fpt.mic.micweb.model.dto.form;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fpt.mic.micweb.model.dao.CustomerDao;
import com.fpt.mic.micweb.utils.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by PhucNguyen on 01/07/2015.
 */
public class ChangePasswordDto {
    @NotEmpty(message = "Không tìm thấy mã khách hàng")
    private String customerCode;

    @NotEmpty(message = "Mật khẩu hiện tại không được để trống")
    private String currentPassword;

    @NotEmpty(message = "Mật khẩu mới không được để trống")
    @Size(min = 6, max = 32, message = "Mật khẩu phải từ 6 đến 32 ký tự")
    private String newPassword;

    @NotEmpty(message = "Xác nhận mật khẩu không được để trống")
    @Size(min = 6, max = 32, message = "Mật khẩu phải từ 6 đến 32 ký tự")
    private String confirmPassword;

    @AssertTrue(message = "Mã khách hàng không tồn tại")
    private boolean isExistCutomer() {
        CustomerDao contractDao = new CustomerDao();
        return customerCode != null && contractDao.read(customerCode) != null;
    }
    @AssertTrue(message = "Sai mật khẩu hiện tại")
    private boolean isMatchCurrentPassword() {
        CustomerDao customerDao = new CustomerDao();
        String encryptedPassword = StringUtils.getMD5Hash(currentPassword);
        return customerDao.read(customerCode).getPassword().equals(encryptedPassword);
    }
    @AssertTrue(message = "Xác nhận mật khẩu không khớp với mật khẩu mới")
    private boolean isMatchPassword() {
        return newPassword.equals(confirmPassword);
    }
    public ChangePasswordDto() {
    }


    public String getCustomerCode() {

        return customerCode;
    }

    public ChangePasswordDto(String customerCode, String currentPassword, String newPassword, String confirmPassword) {

        this.customerCode = customerCode;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public String getCurrentPassword() {

        return currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
