package com.fpt.mic.micweb.model.dto.form;

import java.sql.Timestamp;

/**
 * Created by Kha on 23/06/2015.
 */
public class CancelContractDto {
    private String contractCode;
    private Timestamp cancelDate;
    private String cancelReason;
    private String cancelNote;

    public CancelContractDto() {
    }

    public CancelContractDto(String contractCode, Timestamp cancelDate, String cancelReason, String cancelNote) {
        this.contractCode = contractCode;
        this.cancelDate = cancelDate;
        this.cancelReason = cancelReason;
        this.cancelNote = cancelNote;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public Timestamp getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Timestamp cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getCancelNote() {
        return cancelNote;
    }

    public void setCancelNote(String cancelNote) {
        this.cancelNote = cancelNote;
    }
}