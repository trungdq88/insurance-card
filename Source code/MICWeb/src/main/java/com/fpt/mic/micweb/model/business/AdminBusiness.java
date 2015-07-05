package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.StaffDao;
import com.fpt.mic.micweb.model.dao.helper.BusinessRulesDao;
import com.fpt.mic.micweb.model.dto.form.BusinessRulesDto;
import com.fpt.mic.micweb.model.dto.form.CreateStaffDto;
import com.fpt.mic.micweb.model.entity.StaffEntity;
import com.fpt.mic.micweb.model.entity.helper.BusinessRulesEntity;
import com.fpt.mic.micweb.utils.StringUtils;

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
    public BusinessRulesEntity getBusinessRules(){
        BusinessRulesDao businessRulesDao = new BusinessRulesDao();
        return businessRulesDao.read(1);
    }
    public void setBusinessRules(BusinessRulesDto businessRulesDto){
        BusinessRulesDao businessRulesDao = new BusinessRulesDao();
        BusinessRulesEntity businessRulesEntity = new BusinessRulesEntity();
        businessRulesEntity.setId(1);
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
        businessRulesDao.update(businessRulesEntity);
    }
}
