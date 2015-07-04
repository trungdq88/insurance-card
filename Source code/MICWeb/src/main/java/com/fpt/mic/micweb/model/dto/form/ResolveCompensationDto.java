package com.fpt.mic.micweb.model.dto.form;

import com.fpt.mic.micweb.model.dao.CompensationDao;
import com.fpt.mic.micweb.utils.DateUtils;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Created by Kha on 04/07/2015.
 */
public class ResolveCompensationDto {
    @NotEmpty(message = "Mã yêu cầu bồi thường không được để trống")
    @Pattern(regexp = "^BT([0-9A-Z]{4,8})$", message = "Mã yêu cầu bồi thường không hợp lệ")
    // Mã yêu cầu bồi thường không tồn tại: @see {@link isNotExisted}
    private String compensationCode;
    @NotNull(message = "Ngày giải quyết yêu cầu không được để trống")
    private Timestamp resolveDate;
    @NotEmpty(message = "Quyết định của công ty không được để trống")
    @Size(min = 1, max = 250, message = "Quyết định của công ty phải từ {min} đến {max} ký tự")
    private String decision;
    private String resolveNote;
    private Timestamp lastModified;

    @AssertTrue(message = "Mã yêu cầu bồi thường không tồn tại")
    private boolean isNotExisted() {
        CompensationDao compensationDao = new CompensationDao();
        return compensationCode != null && compensationDao.read(compensationCode) != null;
    }

    @AssertTrue(message = "Ngày giải quyết yêu cầu không được trước ngày gởi yêu cầu")
    private boolean isValidCreatedDate() {
        if (resolveDate != null) {
            return !resolveDate.before(getCreatedDate());
        }
        return false;
    }

    @AssertTrue(message = "Ngày giải quyết yêu cầu không được sau ngày hiện tại")
    private boolean isValidResolveDate() {
        if (resolveDate != null) {
            return !resolveDate.after(DateUtils.currentDateWithoutTime());
        }
        return false;
    }

    @AssertTrue(message = "Ghi chú của công ty phải có độ dài từ 1 đến 2000 ký tự")
    private boolean isResolveNoteValid() {
        if (resolveNote == null || resolveNote.isEmpty()) {
            return true; // Vẫn chấp nhận null hoặc rỗng
        } else {
            // Nếu có giá trị thì phải có độ dài đúng requirement
            return resolveNote.length() >= 1 && resolveNote.length() <= 2000;
        }
    }

    public ResolveCompensationDto() {
    }

    public ResolveCompensationDto(String compensationCode, Timestamp resolveDate, String decision, String resolveNote) {
        this.compensationCode = compensationCode;
        this.resolveDate = resolveDate;
        this.decision = decision;
        this.resolveNote = resolveNote;
    }

    public Timestamp getCreatedDate() {
        CompensationDao compensationDao = new CompensationDao();
        return compensationDao.read(compensationCode).getCreatedDate();
    }

    public String getCompensationCode() {
        return compensationCode;
    }

    public void setCompensationCode(String compensationCode) {
        this.compensationCode = compensationCode;
    }

    public Timestamp getResolveDate() {
        return resolveDate;
    }

    public void setResolveDate(Timestamp resolveDate) {
        this.resolveDate = resolveDate;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getResolveNote() {
        return resolveNote;
    }

    public void setResolveNote(String resolveNote) {
        this.resolveNote = resolveNote;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }
}