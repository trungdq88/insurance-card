package com.fpt.mic.micweb.model.dto.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/21/15.
 */
public class RegisterFormDto {
    @NotEmpty(message = "Tên tài khoản không được bỏ trống!")
    String username;
    @NotEmpty(message = "Mật khẩu không được bỏ trống!")
    @Size(min = 6, max = 32, message = "Mật khẩu phải có từ 6 đến 32 ký tự")
    String password;
    @NotEmpty(message = "Xác nhận mật khẩu không được bỏ trống!")
    String confirmPassword;
    Boolean remember;

    @AssertTrue(message="Mật khẩu xác nhận không giống")
    private boolean isValid() {
        return confirmPassword != null && !confirmPassword.isEmpty() && this.confirmPassword.equals(this.password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Boolean getRemember() {
        return remember;
    }

    public void setRemember(Boolean remember) {
        this.remember = remember;
    }
}
