package com.fpt.mic.micweb.model.dto.form;

import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.utils.ConfigUtils;
import com.fpt.mic.micweb.utils.Constants;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Created by Kha on 07/07/2015.
 */
public class CreatePaymentDto {
    @NotEmpty(message = "Mã hợp đồng không được để trống")
    @Pattern(regexp = "^HD([0-9A-Z]{4,8})$", message = "Mã hợp đồng không hợp lệ")
    // Mã hợp đồng không tồn tại: @see {@link isNotExisted}
    private String contractCode;
    @NotNull(message = "Ngày nộp phí không được để trống")
    private Timestamp paidDate;
    @NotEmpty(message = "Dịch vụ không được để trống")
    @Size(min = 1, max = 250, message = "Dịch vụ phải từ {min} đến {max} ký tự")
    private String content;
    @NotNull(message = "Số tiền không được để trống")
    @Range(min = 0, max = 1000000000, message = "Số tiền phải từ 0 tới 1.000.000.000")
    private Float amount;

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

    @AssertTrue(message = "Hợp đồng đã bị hủy không thể thêm thông tin thanh toán")
    private boolean isValidStatus() {
        ContractDao contractDao = new ContractDao();
        ContractEntity contractEntity = contractDao.read(contractCode);
        if (contractEntity != null) {
            return !contractEntity.getStatus().equalsIgnoreCase(Constants.ContractStatus.CANCELLED);
        } else {
            return true;
        }
    }

    public CreatePaymentDto() {
    }

    public CreatePaymentDto(String contractCode, Timestamp paidDate, String content, Float amount) {
        this.contractCode = contractCode;
        this.paidDate = paidDate;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}