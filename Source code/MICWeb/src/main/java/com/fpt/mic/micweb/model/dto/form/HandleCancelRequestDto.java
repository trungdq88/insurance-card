package com.fpt.mic.micweb.model.dto.form;

import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

/**
 * Created by Kha on 11/07/2015.
 */
public class HandleCancelRequestDto {
    @NotEmpty(message = "Mã hợp đồng không được để trống")
    @Pattern(regexp = "^HD([0-9A-Z]{4,8})$", message = "Mã hợp đồng không hợp lệ")
    // Mã hợp đồng không tồn tại: @see {@link isNotExisted}
    private String contractCode;
    @NotEmpty(message = "Quyết định của công ty không được để trống")
    private String decision;
    private String cancelNote;
    private Timestamp lastModified;


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

    @AssertTrue(message = "Ghi chú hủy phải có độ dài từ 1 đến 2000 ký tự")
    private boolean isCancelNoteValid() {
        if (cancelNote == null || cancelNote.isEmpty()) {
            return true; // Vẫn chấp nhận null hoặc rỗng
        } else {
            // Nếu có giá trị thì phải có độ dài đúng requirement
            return cancelNote.length() >= 1 && cancelNote.length() <= 2000;
        }
    }

    public HandleCancelRequestDto() {
    }

    public HandleCancelRequestDto(String contractCode, String decision, String cancelNote, Timestamp lastModified) {
        this.contractCode = contractCode;
        this.decision = decision;
        this.cancelNote = cancelNote;
        this.lastModified = lastModified;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getCancelNote() {
        return cancelNote;
    }

    public void setCancelNote(String cancelNote) {
        this.cancelNote = cancelNote;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }
}