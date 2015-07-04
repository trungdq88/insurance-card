package com.fpt.mic.micweb.model.dto.form;

import com.fpt.mic.micweb.model.business.LoginBusiness;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;

/**
 * Created by TriPQM on 07/03/2015.
 */
public class NewCardRequestDto {
    private String customerCode;
    @NotEmpty(message = "Mật khẩu không được để trống")
    private String password;
    @NotEmpty(message = "Mã hợp đồng không được để trống")
    private String contractCode;
    @NotEmpty(message = "Ghi chú không được để trống")
    @Length(min = 3, max = 2000,message = "Ghi chú phải từ {min} đến {max} ký tự")
    private String note;
    @NotEmpty(message = "Phương thức thanh toán không được để trống")
    private String payment;
    @AssertTrue(message = "Mật khẩu không chính xác")
    public boolean isValidPassword(){
        if(customerCode == null || password == null){
            return false;
        }
        LoginBusiness loginBusiness = new LoginBusiness();
        return loginBusiness.checkPassword(customerCode,password);
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public NewCardRequestDto() {
    }

    public NewCardRequestDto(String customerCode, String password, String contractCode, String note, String payment) {
        this.customerCode = customerCode;
        this.password = password;
        this.contractCode = contractCode;
        this.note = note;
        this.payment = payment;
    }

}
