package com.fpt.mic.micweb.model.dto.form;

import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.utils.DateUtils;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Kha on 23/06/2015.
 */
public class RenewContractDto {

    private static final int MILLIS_IN_SECOND = 1000;
    private static final int SECONDS_IN_MINUTE = 60;
    private static final int MINUTES_IN_HOUR = 60;
    private static final int HOURS_IN_DAY = 24;
    private static final int DAYS_IN_YEAR = 366;

    @NotEmpty(message = "Mã hợp đồng không được để trống")
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

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

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

    @AssertTrue(message = "Thời điểm có hiệu lực phải sau thời điểm hết hiệu lực")
    private boolean isValidExpiredDate() {
        Timestamp startDate = getStartDate();
        if (startDate != null & expiredDate != null) {
            return expiredDate.after(startDate);
        } else {
            return false;
        }
    }

    @AssertTrue(message = "Thời hạn hợp đồng tối đa là 1 năm")
    private boolean isValidTerm() {
        Timestamp startDate = getStartDate();
        if (startDate != null & expiredDate != null) {
            long contractTerm = expiredDate.getTime() - startDate.getTime();

            final long MILLISECONDS_IN_YEAR =
                    (long) MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR
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

    @AssertTrue(message = "Không thể gia hạn hợp đồng còn giá trị trên 2 tháng")
    private boolean isValidCurrentTerm() {
        ContractDao contractDao = new ContractDao();
        ContractEntity contractEntity = contractDao.read(contractCode);
        if (contractEntity != null) {
            long currentTerm = contractEntity.getExpiredDate().getTime() - System.currentTimeMillis();

            final long MILLISECONDS_IN_TWO_MONTHS =
                    (long) MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR
                            * HOURS_IN_DAY * 60;
            if (currentTerm < MILLISECONDS_IN_TWO_MONTHS) {
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
}