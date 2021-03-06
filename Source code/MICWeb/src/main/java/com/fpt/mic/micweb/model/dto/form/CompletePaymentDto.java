package com.fpt.mic.micweb.model.dto.form;

import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.utils.ConfigUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

/**
 * Created by Kha on 27/06/2015.
 */
public class CompletePaymentDto {
    @NotEmpty(message = "Mã hợp đồng không được để trống")
    @Pattern(regexp = "^HD([0-9A-Z]{4,8})$", message = "Mã hợp đồng không hợp lệ")
    // Mã hợp đồng không tồn tại: @see {@link isNotExisted}
    private String contractCode;
    @NotNull(message = "Ngày nộp phí không được để trống")
    private Timestamp paidDate;
    @NotNull(message = "Số tiền không được để trống")
    @Range(min = 0, max = 1000000000, message = "Số tiền phải từ 0 tới 1.000.000.000")
    private float amount;
    private Timestamp lastModified;

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    @AssertTrue(message = "Thông tin hợp đồng đã bị sửa đổi trước đó bởi" +
            " một người khác, vui lòng thực hiện lại thao tác. " +
            "<div class='more-detail'></div>")
    private boolean isContractNotChanged() {
        if (this.lastModified != null && this.contractCode != null) {
            ContractDao contractDao = new ContractDao();
            ContractEntity contractEntity = contractDao.read(contractCode);
            return contractEntity.getLastModified().equals(lastModified);
        } else {
            return false;
        }
    }

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
