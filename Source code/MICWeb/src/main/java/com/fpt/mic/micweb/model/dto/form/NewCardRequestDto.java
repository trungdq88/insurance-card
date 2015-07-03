package com.fpt.mic.micweb.model.dto.form;

/**
 * Created by TriPQM on 07/03/2015.
 */
public class NewCardRequestDto {
    private String customerCode;
    private String password;
    private String contractCode;
    private String note;
    private String payment;

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
