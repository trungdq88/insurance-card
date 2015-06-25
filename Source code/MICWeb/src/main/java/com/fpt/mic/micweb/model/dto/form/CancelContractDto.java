package com.fpt.mic.micweb.model.dto.form;

import java.sql.Timestamp;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
/**
 * Created by Kha on 23/06/2015.
 */
public class CancelContractDto {
    @NotEmpty(message = "Mã khách khàng không được để trống")
    private String contractCode;
    @NotNull(message = "Ngày hủy hợp đồng không được trống")
    private Timestamp cancelDate;
    @NotEmpty(message = "Lý do hủy hợp đồng không được để trống")
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