package com.fpt.mic.micweb.model.dto.form;

import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.utils.ConfigUtils;
import com.fpt.mic.micweb.utils.Constants;
import com.fpt.mic.micweb.utils.DateUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

/**
 * Created by Kha on 23/06/2015.
 */
public class RenewContractDto {

    @NotEmpty(message = "Mã hợp đồng không được để trống")
    @Pattern(regexp = "^HD([0-9A-Z]{4,8})$", message = "Mã hợp đồng không hợp lệ")
    // Mã hợp đồng không tồn tại: @see {@link isNotExisted}
    private String contractCode;
    @NotNull(message = "Thời điểm hết hiệu lực không được để trống")
    private Timestamp expiredDate;
    @NotNull(message = "Phí bảo hiểm không được để trống")
    private float contractFee;
    @NotNull(message = "Ngày nộp phí không được để trống")
    private Timestamp paidDate;
    @NotNull(message = "Phí bảo hiểm không được để trống")
    private Float amount;
    @NotNull(message = "Yêu cầu thẻ mới không có giá trị")
    private boolean newCard;
    @NotNull(message = "Yêu cầu giao thẻ không có giá trị")
    private boolean deliveryNewCard;
    private Timestamp lastModified;

    @AssertTrue(message = "Thông tin hợp đồng đã bị sửa đổi trước đó bởi một người khác, vui lòng thực hiện lại thao tác")
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

    @AssertTrue(message = "Thời điểm hết hiệu lực không được trước thời gian quy định")
    private boolean isValidExpiredDateMin() {
        if (expiredDate != null) {
            Timestamp startDate = getStartDate();
            ConfigUtils configUtils = new ConfigUtils();
            LocalDate stDate = new LocalDate(startDate);
            LocalDate expDateMin = stDate.plusMonths(configUtils.getContractMinTerm());
            Timestamp expiredDateMin = new Timestamp(expDateMin.toDateTimeAtStartOfDay().getMillis());
            return !expiredDate.before(expiredDateMin);
        }
        return false;
    }

    @AssertTrue(message = "Thời điểm hết hiệu lực không được sau thời gian quy định")
    private boolean isValidExpiredDateMax() {
        if (expiredDate != null) {
            Timestamp startDate = getStartDate();
            ConfigUtils configUtils = new ConfigUtils();
            LocalDate stDate = new LocalDate(startDate);
            LocalDate expDateMax = stDate.plusMonths(configUtils.getContractDefaultTerm());
            Timestamp expiredDateMax = new Timestamp(expDateMax.toDateTimeAtStartOfDay().getMillis());
            return !expiredDate.after(expiredDateMax);
        }
        return false;
    }

    @AssertTrue(message = "Thời điểm có hiệu lực phải sau thời điểm hết hiệu lực")
    private boolean isValidExpiredDate() {
        if (expiredDate != null) {
            Timestamp startDate = getStartDate();
            return expiredDate.after(startDate);
        } else {
            return false;
        }
    }

    @AssertTrue(message = "Thời hạn hợp đồng không được dài hơn thời gian quy định")
    private boolean isValidDefaultTerm() {
        if (expiredDate != null) {
            Timestamp startDate = getStartDate();
            ConfigUtils configUtils = new ConfigUtils();
            LocalDate maxExpDate = new LocalDate(startDate).plusMonths(configUtils.getContractDefaultTerm());
            Timestamp maxExpiredDate = new Timestamp(maxExpDate.toDateTimeAtStartOfDay().getMillis());
            return !maxExpiredDate.before(expiredDate);
        } else {
            return false;
        }
    }

    @AssertTrue(message = "Thời hạn hợp đồng không được ngắn hơn thời gian quy định")
    private boolean isValidMinTerm() {
        if (expiredDate != null) {
            Timestamp startDate = getStartDate();
            ConfigUtils configUtils = new ConfigUtils();
            LocalDate minExpDate = new LocalDate(startDate).plusMonths(configUtils.getContractMinTerm());
            Timestamp minExpiredDate = new Timestamp(minExpDate.toDateTimeAtStartOfDay().getMillis());
            return !minExpiredDate.after(expiredDate);
        } else {
            return false;
        }
    }

    @AssertTrue(message = "Không thể gia hạn hợp đồng còn thời hạn dài hơn quy định")
    private boolean isValidCurrentTerm() {
        ContractDao contractDao = new ContractDao();
        ContractEntity contractEntity = contractDao.read(contractCode);
        if (contractEntity != null) {
            LocalDate expDate = new LocalDate(contractEntity.getExpiredDate());
            int currentTerm = Days.daysBetween(new LocalDate(), expDate).getDays();
            ConfigUtils configUtils = new ConfigUtils();
            return currentTerm <= configUtils.getContractRenewLimit();
        } else {
            return true;
        }
    }

    @AssertTrue(message = "Không thể gia hạn hợp đồng đang yêu cầu hủy hoặc đã hủy")
    private boolean isValidStatus() {
        ContractDao contractDao = new ContractDao();
        ContractEntity contractEntity = contractDao.read(contractCode);
        if (contractEntity != null) {
            String status = contractEntity.getStatus();
            return !status.equalsIgnoreCase(Constants.ContractStatus.REQUEST_CANCEL) ||
                    !status.equalsIgnoreCase(Constants.ContractStatus.CANCELLED);
        } else {
            return true;
        }
    }

    public RenewContractDto() {
    }

    public RenewContractDto(String contractCode, Timestamp expiredDate, float contractFee, Timestamp paidDate,
                            Float amount, boolean newCard, boolean deliveryNewCard, Timestamp lastModified) {
        this.contractCode = contractCode;
        this.expiredDate = expiredDate;
        this.contractFee = contractFee;
        this.paidDate = paidDate;
        this.amount = amount;
        this.newCard = newCard;
        this.deliveryNewCard = deliveryNewCard;
        this.lastModified = lastModified;
    }

    public Timestamp getStartDate() {
        ContractDao contractDao = new ContractDao();
        ContractEntity contractEntity = contractDao.read(contractCode);
        Timestamp startDate;
        if (contractEntity.getStatus().equals("Expired")) {
            startDate = DateUtils.currentDateWithoutTime();
        } else {
            startDate = contractEntity.getExpiredDate();
        }
        return startDate;
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

    public boolean isNewCard() {
        return newCard;
    }

    public void setNewCard(boolean newCard) {
        this.newCard = newCard;
    }

    public boolean isDeliveryNewCard() {
        return deliveryNewCard;
    }

    public void setDeliveryNewCard(boolean deliveryNewCard) {
        this.deliveryNewCard = deliveryNewCard;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }
}