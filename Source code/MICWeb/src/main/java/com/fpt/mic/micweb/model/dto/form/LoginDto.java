package com.fpt.mic.micweb.model.dto.form;

import com.fpt.mic.micweb.model.business.LoginBusiness;
import com.fpt.mic.micweb.model.dto.UserDto;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/25/15.
 */
public class LoginDto {
    @NotEmpty(message = "Email hoặc mã không được để trống")
    private String emailorcode;
    @NotEmpty(message = "Mật khẩu không được để trống")
    private String password;
    private String role;
    private String redirect;

    @AssertTrue(message = "Vai trò không hợp lệ. Liên hệ Admin để biết thêm chi tiết")
    private boolean isRoleValid() {
        return role.equals(UserDto.ROLE_CUSTOMER) || role.equals(UserDto.ROLE_STAFF);
    }

    @AssertTrue(message = "Mật khẩu không chính xác")
    private boolean isPasswordCorrect() {
        LoginBusiness loginBusiness = new LoginBusiness();
        return loginBusiness.checkLogin(this) != null;
    }

    public Object getUserEntity() {
        LoginBusiness loginBusiness = new LoginBusiness();
        return loginBusiness.checkLogin(this);
    }

    public String getEmailorcode() {
        return emailorcode;
    }

    public void setEmailorcode(String emailorcode) {
        this.emailorcode = emailorcode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
}
