package com.fpt.mic.micweb.model.dto.form;

import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.utils.ConfigUtils;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

/**
 * Created by TriPQM on 07/08/2015.
 */
public class CreateNewCardPaymentDto {
    @NotEmpty(message = "Mã hợp đồng không được để trống")
    @Pattern(regexp = "^HD([0-9A-Z]{4,8})$", message = "Mã hợp đồng không hợp lệ")
    private String contractCode;
    private int delivery;
    @NotNull(message = "Ngày nộp phí không được để trống")
    private Timestamp paidDate;

    @AssertTrue(message = "Mã hợp đồng không tồn tại")
    private boolean isNotExisted() {
        ContractDao contractDao = new ContractDao();
        return contractCode != null && contractDao.read(contractCode) != null;
    }

    @AssertTrue(message = "Ngày nộp phí không được trước thời gian quy định")
    private boolean isValidPaidDateMin() {
        if (paidDate != null) {
            ConfigUtils configUtils = new ConfigUtils();
            Timestamp paidDateMin = new Timestamp(configUtils.getPaidDateMin().toDateTimeAtStartOfDay().getMillis());
            return !paidDate.before(paidDateMin);
        }
        return false;
    }

    @AssertTrue(message = "Ngày nộp phí không được sau thời gian quy định")
    private boolean isValidPaidDateMax() {
        if (paidDate != null) {
            ConfigUtils configUtils = new ConfigUtils();
            Timestamp paidDateMax = new Timestamp(configUtils.getPaidDateMax().toDateTimeAtStartOfDay().getMillis());
            return !paidDate.after(paidDateMax);
        }
        return false;
    }

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
