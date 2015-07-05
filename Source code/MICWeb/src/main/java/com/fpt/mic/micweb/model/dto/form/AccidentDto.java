package com.fpt.mic.micweb.model.dto.form;

import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.utils.DateUtils;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Created by Kha on 05/07/2015.
 */
public class AccidentDto {
    private Integer id;
    @NotEmpty(message = "Mã hợp đồng không được để trống")
    @Pattern(regexp = "^HD([0-9A-Z]{4,8})$", message = "Mã hợp đồng không hợp lệ")
    // Mã hợp đồng không tồn tại: @see {@link isNotExisted}
    private String contractCode;
    @NotNull(message = "Ngày gởi thông báo không được để trống")
    private Timestamp createdDate;
    @NotEmpty(message = "Nội dung thông báo không được để trống")
    @NotNull(message = "Nội dung thông báo không được để trống")
    @Size(min = 1, max = 250, message = "Nội dung thông báo phải từ {min} đến {max} ký tự")
    private String title;
    @NotEmpty(message = "Đường dẫn tới văn bản đính kèm không được để trống")
    @Size(min = 1, max = 255, message = "Đường dẫn tới văn bản đính kèm phải từ {min} đến {max} ký tự")
    private String attachment;
    private Timestamp lastModified;

    @AssertTrue(message = "Mã hợp đồng không tồn tại")
    private boolean isNotExisted() {
        ContractDao contractDao = new ContractDao();
        return contractCode != null && contractDao.read(contractCode) != null;
    }

    @AssertTrue(message = "Ngày gởi yêu cầu không được sau ngày hiện tại")
    private boolean isValidCreatedDate() {
        if (createdDate != null) {
            return !createdDate.after(DateUtils.currentDateWithoutTime());
        }
        return false;
    }

    public AccidentDto() {
    }

    public AccidentDto(Integer id, String contractCode, Timestamp createdDate, String title, String attachment,
                       Timestamp lastModified) {
        this.id = id;
        this.contractCode = contractCode;
        this.createdDate = createdDate;
        this.title = title;
        this.attachment = attachment;
        this.lastModified = lastModified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }
}