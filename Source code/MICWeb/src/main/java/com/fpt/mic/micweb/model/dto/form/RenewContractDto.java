package com.fpt.mic.micweb.model.dto.form;

import com.fpt.mic.micweb.model.dao.ContractDao;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by Kha on 23/06/2015.
 */
public class RenewContractDto {
    @NotEmpty(message = "Mã hợp đồng không được để trống")
    // Mã hợp đồng không tồn tại: @see {@link isNotExisted}
    private String contractCode;
    @NotNull(message = "Thời điểm có hiệu lực không được để trống")
    private Timestamp startDate;
    @NotNull(message = "Thời điểm hết hiệu lực không được để trống")
    private Timestamp expiredDate;
    @NotNull(message = "Phí bảo hiểm không được để trống")
    private float contractFee;
    @NotNull(message = "Ngày nộp phí không được để trống")
    private Timestamp paidDate;
    @NotNull(message = "Phí bảo hiểm không được để trống")
    private Float amount;

    @AssertTrue(message = "Mã hợp đồng không tồn tại")
    private boolean isNotExisted() {
        ContractDao contractDao = new ContractDao();
        return contractCode != null && contractDao.read(contractCode) != null;
    }

    @AssertTrue(message = "Thời điểm có hiệu lực phải sau thời điểm hết hiệu lực")
    private boolean isValidDate() {
        if (startDate != null & expiredDate != null) {
            return expiredDate.after(startDate);
        } else {
            return false;
        }
    }

    @AssertTrue(message = "Thời hạn hợp đồng tối đa là 1 năm")
    private boolean isValidTerm() {
        if (startDate != null & expiredDate != null) {
            long contractTerm = expiredDate.getTime() - startDate.getTime();

            final int MILLIS_IN_SECOND = 1000;
            final int SECONDS_IN_MINUTE = 60;
            final int MINUTES_IN_HOUR = 60;
            final int HOURS_IN_DAY = 24;
            final int DAYS_IN_YEAR = 366;
            final long MILLISECONDS_IN_YEAR =
                    (long)MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR
                            * HOURS_IN_DAY * DAYS_IN_YEAR;
            if (contractTerm <= MILLISECONDS_IN_YEAR) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    public RenewContractDto() {
    }

    public RenewContractDto(String contractCode, Timestamp startDate, Timestamp expiredDate, float contractFee,
                            Timestamp paidDate, Float amount) {
        this.contractCode = contractCode;
        this.startDate = startDate;
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

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
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