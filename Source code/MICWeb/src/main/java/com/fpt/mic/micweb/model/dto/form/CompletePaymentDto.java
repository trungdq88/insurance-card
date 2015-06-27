package com.fpt.mic.micweb.model.dto.form;

import com.fpt.mic.micweb.model.dao.ContractDao;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by Kha on 27/06/2015.
 */
public class CompletePaymentDto {
    @NotEmpty(message = "Mã hợp đồng không được để trống")
    // Mã hợp đồng không tồn tại: @see {@link isNotExisted}
    private String contractCode;
    @NotNull(message = "Ngày nộp phí không được để trống")
    private Timestamp paidDate;
    @NotNull(message = "Phí bảo hiểm không được để trống")
    private float amount;

    @AssertTrue(message = "Mã hợp đồng không tồn tại")
    private boolean isNotExisted() {
        ContractDao contractDao = new ContractDao();
        return contractCode != null && contractDao.read(contractCode) != null;
    }

    public CompletePaymentDto() {
    }

    public CompletePaymentDto(String contractCode, Timestamp paidDate, float amount) {
        this.contractCode = contractCode;
        this.paidDate = paidDate;
        this.amount = amount;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public Timestamp getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Timestamp paidDate) {
        this.paidDate = paidDate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
