package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dao.ContractTypeDao;
import com.fpt.mic.micweb.model.dao.helper.NewCardRequestDao;
import com.fpt.mic.micweb.model.dto.ContractSearchResultDto;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.ContractTypeEntity;
import com.fpt.mic.micweb.utils.Constants;
import com.fpt.mic.micweb.utils.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by TriPQMse60746 on 06/11/2015.
 */
public class ContractBusiness {
    public List<ContractTypeEntity> getAllContractType() {
        ContractTypeDao contractTypeDao = new ContractTypeDao();
        return contractTypeDao.getAllContractType();
    }

    public Long searchContractCount(String keyword) {
        ContractDao contractDao = new ContractDao();
        return contractDao.getContractByCodeOrCustomerNameCount(keyword);
    }

    public List searchContract(String keyword, int offset, int count) {
        ContractDao contractDao = new ContractDao();
        return contractDao.getContractByCodeOrCustomerName(keyword, offset, count);
    }

    /**
     * If contract remaining days is greater than 60 days the contract will not renewable
     * @return
     */
    public boolean isRenewable(String contractCode) {
        ContractDao contractDao = new ContractDao();
        ContractEntity contract = contractDao.read(contractCode);

        if (contract == null) return false;

        DateTime currentDate = DateTime.now();
        DateTime expiredDate = new DateTime(contract.getExpiredDate().getTime());
        int remainingDays = Days.daysBetween(currentDate, expiredDate).getDays();
        return remainingDays <= 60;
    }

    /**
     * Get new expired date
     * If contract status is "Read" or "No card": extended 1 year from old expired date
     * If contract status is "Expired": extend 1 year from now
     * Other contract status should not be able to renew.
     * @param contractCode
     * @return
     */
    public Timestamp getNewExpiredDate(String contractCode) {
        ContractDao contractDao = new ContractDao();
        ContractEntity contract = contractDao.read(contractCode);

        if (contract == null) return null;

        Timestamp expiredDate = DateUtils.convertDateTimeToDate(contract.getExpiredDate());
        if (contract.getStatus().equalsIgnoreCase(Constants.ContractStatus.READY)
                ||contract.getStatus().equalsIgnoreCase(Constants.ContractStatus.NO_CARD)) {
            expiredDate = DateUtils.addOneYear(expiredDate);
        } else if (contract.getStatus().equalsIgnoreCase(Constants.ContractStatus.EXPIRED)) {
            expiredDate = DateUtils.addOneYear(DateUtils.currentDateWithoutTime());
        } else {
            return null;
        }

        return expiredDate;
    }

    /**
     * Get contract by code
     * @param contractCode
     * @return
     */
    public ContractEntity getContract(String contractCode) {
        ContractDao contractDao = new ContractDao();
        return contractDao.read(contractCode);
    }

    public ContractTypeEntity getContractType(int contractTypeId) {
        ContractTypeDao contractTypeDao = new ContractTypeDao();
        return contractTypeDao.read(contractTypeId);
    }

    /**
     * Set need_renew_payment variable to true when user renew and payment direct
     * @param contractCode
     */
    public void setContractNeedRenewPayment(String contractCode, boolean flag) {
        ContractDao contractDao = new ContractDao();
        ContractEntity contractEntity = contractDao.read(contractCode);
        contractEntity.setNeedRenewPayment(flag ? 1 : null);
        contractDao.update(contractEntity);
    }
}
