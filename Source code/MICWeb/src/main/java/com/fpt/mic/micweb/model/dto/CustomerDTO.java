package com.fpt.mic.micweb.model.dto;

import java.io.Serializable;

/**
 * Created by Kha on 08/06/2015.
 */
public class CustomerDTO implements Serializable {
    private String customerCode;
    private String customerName;
    private String customerPhone;
    private String contractCode;
    private String cardID;

    public CustomerDTO() {
    }

    public CustomerDTO(String customerCode, String customerName, String cardID, String contractCode, String customerPhone) {
        this.customerCode = customerCode;
        this.customerName = customerName;
        this.cardID = cardID;
        this.contractCode = contractCode;
        this.customerPhone = customerPhone;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }
}
