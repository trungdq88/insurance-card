package com.fpt.mic.micweb.model.dto.form;
import com.fpt.mic.micweb.model.entity.PunishmentEntity;
import com.fpt.mic.micweb.model.dao.PunishmentDao;
import com.fpt.mic.micweb.utils.DateUtils;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Created by PhucNguyen on 04/07/2015.
 */
public class CreatePunishmentDto {
    @NotNull(message = "Ngày vi phạm")
    private Timestamp createDate;
    @NotEmpty(message = "Nội dung vi phạm không được để trống")
    private String title;
    @NotEmpty(message = "Văn bản vi phạm không được để trống")
    private String attachment;
    @NotEmpty (message = "Mã hợp đồng không được trống")
    private String contractCode;

    public CreatePunishmentDto() {
    }

    public CreatePunishmentDto(Timestamp createDate, String title, String contractCode, String attachment) {
        this.createDate = createDate;
        this.title = title;
        this.contractCode = contractCode;
        this.attachment = attachment;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public Timestamp getCreateDate() {

        return createDate;
    }

    public String getTitle() {
        return title;
    }

    public String getAttachment() {
        return attachment;
    }

    public String getContractCode() {
        return contractCode;
    }
}

