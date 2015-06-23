package com.fpt.mic.micweb.model.dto.form;

import java.sql.Timestamp;

/**
 * Created by Kha on 23/06/2015.
 */
public class RenewContractDto {
    private String contractCode;
    private Timestamp expiredDate;
    private float contractFee;
    private Timestamp paidDate;
    private Float amount;

    public RenewContractDto() {
    }

    public RenewContractDto(String contractCode, Timestamp expiredDate, float contractFee,
                            Timestamp paidDate, Float amount) {
        this.contractCode = contractCode;
        this.expiredDate = expiredDate;
        this.contractFee = contractFee;
        this.paidDate = paidDate;
        this.amount = amount;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public Timestamp getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Timestamp expiredDate) {
        this.expiredDate = expiredDate;
    }

    public float getContractFee() {
        return contractFee;
    }

    public void setContractFee(float contractFee) {
        this.contractFee = contractFee;
    }

    public Timestamp getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Timestamp paidDate) {
        this.paidDate = paidDate;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}