package com.fpt.mic.micweb.model.dto.form;

import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.utils.ConfigUtils;
import com.fpt.mic.micweb.utils.Constants;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Created by Kha on 23/06/2015.
 */
public class CancelContractDto {
    @NotEmpty(message = "Mã hợp đồng không được để trống")
    @Pattern(regexp = "^HD([0-9A-Z]{4,8})$", message = "Mã hợp đồng không hợp lệ")
    // Mã hợp đồng không tồn tại: @see {@link isNotExisted}
    private String contractCode;
    @NotNull(message = "Ngày hủy hợp đồng không được trống")
    private Timestamp cancelDate;
    @NotEmpty(message = "Lý do hủy hợp đồng không được để trống")
    @Size(min = 1, max = 250, message = "Lý do hủy hợp đồng phải có từ {min} đến {max} ký tự")
    private String cancelReason;
    private String cancelNote;
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

    @AssertTrue(message = "Ngày hủy hợp đồng không được trước thời gian quy định")
    private boolean isValidCancelDateMin() {
        if (cancelDate != null) {
            ConfigUtils configUtils = new ConfigUtils();
            Timestamp cancelDateMin = new Timestamp(configUtils.getCancelDateMin().toDateTimeAtStartOfDay().getMillis());
            return !cancelDate.before(cancelDateMin);
        }
        return false;
    }

    @AssertTrue(message = "Ngày hủy hợp đồng không được sau thời gian quy định")
    private boolean isValidCancelDateMax() {
        if (cancelDate != null) {
            ConfigUtils configUtils = new ConfigUtils();
            Timestamp cancelDateMax = new Timestamp(configUtils.getCancelDateMax().toDateTimeAtStartOfDay().getMillis());
            return !cancelDate.after(cancelDateMax);
        }
        return false;
    }
    
    @AssertTrue(message = "Lý do hủy hợp đồng không thể là khoảng trắng")
    private boolean isCancelReasonValid() {
        if (cancelReason.trim().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    @AssertTrue(message = "Ghi chú hủy phải có độ dài từ 1 đến 2000 ký tự")
    private boolean isCancelNoteValid() {
        if (cancelNote == null || cancelNote.isEmpty()) {
            return true; // Vẫn chấp nhận null hoặc rỗng
        } else {
            // Nếu có giá trị thì phải có độ dài đúng requirement
            return cancelNote.length() >= 1 && cancelNote.length() <= 2000;
        }
    }

    @AssertTrue(message = "Không thể hủy hợp đồng đã bị hủy")
    private boolean isValidStatus() {
        ContractDao contractDao = new ContractDao();
        ContractEntity contractEntity = contractDao.read(contractCode);
        if (contractEntity != null) {
            return !contractEntity.getStatus().equalsIgnoreCase(Constants.ContractStatus.CANCELLED);
        } else {
            return true;
        }
    }

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