package com.fpt.mic.micweb.model.dto.form;

import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.entity.ContractEntity;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by TriPQM on 06/30/2015.
 */
public class ConcurrencyDto {
    private String contractCode;
    private Timestamp lastModified;

    @AssertTrue(message = "Thông tin hợp đồng đã bị thay đổi bởi một " +
            "người khác, thanh toán đã bị huỷ. <br/>" +
            "Vui lòng lưu lại mã giao dịch để đối chiếu trong trường hợp hoàn lại tiền")
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
    private boolean isContractExists() {
        if (contractCode != null) {
            ContractDao contractDao = new ContractDao();
            return contractDao.read(contractCode) != null;
        }
        return false;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

}
