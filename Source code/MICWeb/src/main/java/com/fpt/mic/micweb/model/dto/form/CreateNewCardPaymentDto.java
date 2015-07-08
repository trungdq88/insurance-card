package com.fpt.mic.micweb.model.dto.form;

import java.sql.Timestamp;

/**
 * Created by TriPQM on 07/08/2015.
 */
public class CreateNewCardPaymentDto {
    private String contractCode;
    private int delivery;
    private Timestamp paidDate;

    public CreateNewCardPaymentDto(String contractCode, int delivery, Timestamp paidDate) {
        this.contractCode = contractCode;
        this.delivery = delivery;
        this.paidDate = paidDate;
    }

    public CreateNewCardPaymentDto() {
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public int getDelivery() {
        return delivery;
    }

    public void setDelivery(int delivery) {
        this.delivery = delivery;
    }

    public Timestamp getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Timestamp paidDate) {
        this.paidDate = paidDate;
    }
}
