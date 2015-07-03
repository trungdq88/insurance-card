package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.StaffDao;
import com.fpt.mic.micweb.model.dto.form.CreateStaffDto;
import com.fpt.mic.micweb.model.entity.StaffEntity;

/**
 * Created by TriPQM on 07/03/2015.
 */
public class AdminBusiness {
    public StaffEntity createStaff(CreateStaffDto createStaffDto) {
        StaffDao staffDao = new StaffDao();
        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setStaffCode(createStaffDto.getStaffCode());
        staffEntity.setName(createStaffDto.getName());
        staffEntity.setEmail(createStaffDto.getEmail());
        staffEntity.setPassword(createStaffDto.getPassword());
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
}
