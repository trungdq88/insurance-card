package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dao.ContractTypeDao;
import com.fpt.mic.micweb.model.dto.form.ContractTypeDto;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.ContractTypeEntity;
import com.fpt.mic.micweb.utils.Constants;
import com.fpt.mic.micweb.utils.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by TriPQMse60746 on 06/11/2015.
 */
public class ContractBusiness {

    public List<ContractTypeEntity> getAllContractType() {
        ContractTypeDao contractTypeDao = new ContractTypeDao();
        return contractTypeDao.getAllContractType();
    }

    public List<ContractTypeEntity> getAllActiveContractType() {
        ContractTypeDao contractTypeDao = new ContractTypeDao();
        return contractTypeDao.getAllActiveContractType();
    }

    public List getOnePageContractTypes(int offset, int count) {
        ContractTypeDao contractTypeDao = new ContractTypeDao();
        return contractTypeDao.getOnePageContractTypes(offset, count);
    }

    public Long getAllContractTypeCount() {
        ContractTypeDao contractTypeDao = new ContractTypeDao();
        return contractTypeDao.getAllContractTypeCount();
    }

    public boolean addContractType(ContractTypeDto contractTypeDto){
        ContractTypeDao contractTypeDao = new ContractTypeDao();
        ContractTypeEntity contractTypeEntity = new ContractTypeEntity();
        contractTypeEntity.setName(contractTypeDto.getName());
        contractTypeEntity.setDescription(contractTypeDto.getDescription());
        contractTypeEntity.setPricePerYear(contractTypeDto.getPricePerYear());
        contractTypeEntity.setActive(1);
        try{
            contractTypeDao.create(contractTypeEntity);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteContractType(int contractTypeId){
        ContractTypeDao contractTypeDao = new ContractTypeDao();
        ContractTypeEntity contractTypeEntity = contractTypeDao.read(contractTypeId);
        if (contractTypeEntity != null){
            contractTypeDao.delete(contractTypeEntity);
            return true;
        }else {
            return false;
        }
    }

    public boolean deactivateContractType(int contractTypeId){
        ContractTypeDao contractTypeDao = new ContractTypeDao();
        ContractTypeEntity contractTypeEntity = contractTypeDao.read(contractTypeId);
        if (contractTypeEntity != null){
            contractTypeEntity.setActive(0);
            if (contractTypeDao.update(contractTypeEntity) != null){
                return true;
            }
        }
        return false;
    }

    public boolean activateContractType(int contractTypeId){
        ContractTypeDao contractTypeDao = new ContractTypeDao();
        ContractTypeEntity contractTypeEntity = contractTypeDao.read(contractTypeId);
        if (contractTypeEntity != null){
            contractTypeEntity.setActive(1);
            if (contractTypeDao.update(contractTypeEntity) != null){
                return true;
            }
        }
        return false;
    }

    public Long getAllRequestCancelContractCount(){
        ContractDao contractDao = new ContractDao();
        return contractDao.getAllRequestCancelContractCount();
    }

    public Long getAllActiveContractCount(){
        ContractDao contractDao = new ContractDao();
        return contractDao.getAllActiveContractCount();
    }

    public Long getCountOfContractByContractType(int contractTypeId){
        ContractDao contractDao = new ContractDao();
        return contractDao.getCountOfContractByContractType(contractTypeId);
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

    public boolean updateContractStatus(String contractCode,String status){
        ContractDao contractDao = new ContractDao();
        ContractEntity contractEntity = contractDao.read(contractCode);
        if (contractEntity != null){
            contractEntity.setStatus(status);
            contractDao.update(contractEntity);
            return true;
        }
        return false;
    }

    public boolean editContractType(int contractTypeId, ContractTypeDto dto){
        ContractTypeDao contractTypeDao = new ContractTypeDao();
        ContractTypeEntity contractTypeEntity = contractTypeDao.read(contractTypeId);
        contractTypeEntity.setName(dto.getName());
        contractTypeEntity.setPricePerYear(dto.getPricePerYear());
        contractTypeEntity.setDescription(dto.getDescription());
        if (contractTypeDao.update(contractTypeEntity) != null){
            return true;
        }
        return false;
    }
}
