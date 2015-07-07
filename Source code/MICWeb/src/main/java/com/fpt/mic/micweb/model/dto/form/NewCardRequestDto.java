package com.fpt.mic.micweb.model.dto.form;

import com.fpt.mic.micweb.model.business.CustomerBusiness;
import com.fpt.mic.micweb.model.business.LoginBusiness;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

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
    @NotNull(message = "Yêu cầu giao thẻ không có giá trị")
    private boolean deliveryRequested;
    @NotNull(message = "Yêu cầu hủy thẻ không có giá trị")
    private boolean deactiveCardRequested;


    @AssertTrue(message = "Mật khẩu không chính xác")
    public boolean isValidPassword(){
        if(customerCode == null || password == null){
            return false;
        }
        LoginBusiness loginBusiness = new LoginBusiness();
        return loginBusiness.checkPassword(customerCode,password);
    }

    @AssertTrue(message = "Mã hợp đồng đã bị sửa đổi")
    public boolean isValidContract(){
        CustomerBusiness customerBusiness = new CustomerBusiness();
        ContractEntity contract = customerBusiness.getContractDetail(contractCode);
        if (contract == null || contract.getCustomerCode().compareToIgnoreCase(customerCode) != 0) {
            return false;
        }
        return true;
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

    public boolean isDeliveryRequested() {
        return deliveryRequested;
    }

    public void setDeliveryRequested(boolean deliveryRequested) {
        this.deliveryRequested = deliveryRequested;
    }

    public boolean isDeactiveCardRequested() {
        return deactiveCardRequested;
    }

    public void setDeactiveCardRequested(boolean deactiveCardRequested) {
        this.deactiveCardRequested = deactiveCardRequested;
    }

    public NewCardRequestDto() {
    }

    public NewCardRequestDto(String customerCode, String password, String contractCode, String note, String payment, boolean deliveryRequested, boolean deactiveCardRequested) {
        this.customerCode = customerCode;
        this.password = password;
        this.contractCode = contractCode;
        this.note = note;
        this.payment = payment;
        this.deliveryRequested = deliveryRequested;
        this.deactiveCardRequested = deactiveCardRequested;
    }
}
