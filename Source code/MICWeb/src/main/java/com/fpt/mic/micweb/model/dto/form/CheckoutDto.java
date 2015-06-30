package com.fpt.mic.micweb.model.dto.form;

import javax.validation.constraints.AssertTrue;
import java.sql.Timestamp;

/**
 * Created by TriPQM on 06/30/2015.
 */
public class CheckoutDto {
    private Timestamp startModifyTime;
    private Timestamp contractLastModified;

    @AssertTrue(message = "Hợp đồng đã bị thay đổi. Xin vui lòng kiểm tra và thực hiện lại")
    private boolean isUpToDate() {
        return startModifyTime.equals(contractLastModified);
    }

    public Timestamp getStartModifyTime() {
        return startModifyTime;
    }
    public void setStartModifyTime(Timestamp startModifyTime) {
        this.startModifyTime = startModifyTime;
    }
    public CheckoutDto() {
    }
    public Timestamp getContractLastModified() {
        return contractLastModified;
    }
    public void setContractLastModified(Timestamp contractLastModified) {
        this.contractLastModified = contractLastModified;
    }
}
