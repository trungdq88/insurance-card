package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.StaffDao;
import com.fpt.mic.micweb.model.dao.helper.BusinessRulesDao;
import com.fpt.mic.micweb.model.dto.form.BusinessRulesDto;
import com.fpt.mic.micweb.model.dto.form.CreateStaffDto;
import com.fpt.mic.micweb.model.entity.StaffEntity;
import com.fpt.mic.micweb.model.entity.helper.BusinessRulesEntity;
import com.fpt.mic.micweb.utils.StringUtils;

import java.util.List;

/**
 * Created by TriPQM on 07/03/2015.
 */
public class AdminBusiness {
    public StaffEntity createStaff(CreateStaffDto createStaffDto) {
        StaffDao staffDao = new StaffDao();
        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setStaffCode(staffDao.getIncrementId());
        staffEntity.setName(createStaffDto.getName());
        staffEntity.setEmail(createStaffDto.getEmail());
        staffEntity.setPassword(StringUtils.getMD5Hash(createStaffDto.getPassword()));
        staffEntity.setPhone(createStaffDto.getPhone());
        return staffDao.create(staffEntity);
    }
    public boolean deleteStaff(String staffCode) {
        StaffDao staffDao = new StaffDao();
        try {
            staffDao.delete(staffDao.read(staffCode));
            return true;
        } catch (Exception e){
            return false;
        }
    }
    // get last active business rules
    public BusinessRulesEntity getLastActiveBusinessRule(){
        BusinessRulesDao businessRulesDao = new BusinessRulesDao();
        return businessRulesDao.getLastActiveBusinessRule();
    }

    public BusinessRulesEntity getBusinessRules(int configId){
        BusinessRulesDao businessRulesDao = new BusinessRulesDao();
        return businessRulesDao.read(configId);
    }
    // create new business config rules
    public void createBusinessRules(BusinessRulesDto businessRulesDto){
        BusinessRulesDao businessRulesDao = new BusinessRulesDao();
        BusinessRulesEntity businessRulesEntity = new BusinessRulesEntity();
        businessRulesEntity.setStartDateBefore(businessRulesDto.getStartDateBefore());
        businessRulesEntity.setStartDateAfter(businessRulesDto.getStartDateAfter());
        businessRulesEntity.setContractDefaultTerm(businessRulesDto.getContractDefaultTerm());
        businessRulesEntity.setContractMinTerm(businessRulesDto.getContractMinTerm());
        businessRulesEntity.setPaidDaterBefore(businessRulesDto.getPaidDaterBefore());
        businessRulesEntity.setPaidDateAfter(businessRulesDto.getPaidDateAfter());
        businessRulesEntity.setCancelDateBefore(businessRulesDto.getCancelDateBefore());
        businessRulesEntity.setCancelDateAfter(businessRulesDto.getCancelDateAfter());
        businessRulesEntity.setPaymentDueDate(businessRulesDto.getPaymentDueDate());
        businessRulesEntity.setNearlyExceedExpiredOne(businessRulesDto.getNearlyExceedExpiredOne());
        businessRulesEntity.setNearlyExceedExpiredTwo(businessRulesDto.getNearlyExceedExpiredTwo());
        businessRulesEntity.setNearlyExceedExpiredThree(businessRulesDto.getNearlyExceedExpiredThree());
        businessRulesEntity.setNewCardRequestFee(businessRulesDto.getNewCardRequestFee());
        businessRulesEntity.setDeliveryFee(businessRulesDto.getDeliveryFee());
        businessRulesDao.create(businessRulesEntity);
    }

    public List getOnePageBusinessRules(int offset, int count) {
        BusinessRulesDao businessRulesDao = new BusinessRulesDao();
        return businessRulesDao.getOnePageBusinessRules(offset, count);
    }

    public Long getAllBusinessRulesCount() {
        BusinessRulesDao businessRulesDao = new BusinessRulesDao();
        return businessRulesDao.getAllBusinessRulesCount();
    }
}
