package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.CustomerDao;
import com.fpt.mic.micweb.model.dao.StaffDao;
import com.fpt.mic.micweb.model.dto.UserDto;
import com.fpt.mic.micweb.model.dto.form.LoginDto;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.model.entity.StaffEntity;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/25/15.
 */
public class LoginBusiness {
    /**
     * Check login for a user
     * @param dto
     * @return Object: null if check fail, Entity object if success
     */
    public Object checkLogin(LoginDto dto) {
        if (dto.getRole().equals(UserDto.ROLE_CUSTOMER)) {
            CustomerDao customerDao = new CustomerDao();
            // Try to find customer by email
            CustomerEntity customer = customerDao.getCustomerByEmail(dto.getEmailorcode());
            if (customer == null) {
                // Try to find customer by code
                customer = customerDao.read(dto.getEmailorcode());
            }

            // If no customer found, return false
            if (customer == null) {
                return null;
            }

            // If customer found, check if password is correct
            // TODO: do the encryption here
            if (customer.getPassword().equals(dto.getPassword())) {
                return customer;
            } else {
                return null;
            }
        } else if (dto.getRole().equals(UserDto.ROLE_STAFF)) {
            StaffDao staffDao = new StaffDao();
            // Try to find staff by email
            StaffEntity staff = staffDao.getStaffByEmail(dto.getEmailorcode());
            if (staff == null) {
                // Try to find staff by code
                staff = staffDao.read(dto.getEmailorcode());
            }

            // If no staff found, return false
            if (staff == null) {
                return null;
            }

            // If staff found, check if password is correct
            // TODO: do the encryption here
            if (staff.getPassword().equals(dto.getPassword())) {
                return staff;
            } else {
                return null;
            }
        } else {
            // Role is incorrect
            return null;
        }
    }
}
