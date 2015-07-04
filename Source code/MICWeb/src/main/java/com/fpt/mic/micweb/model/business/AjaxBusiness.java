package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.CustomerDao;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.utils.StringUtils;


/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/29/15.
 */
public class AjaxBusiness {
    public boolean resendPassword(String customerCode, String loginUrl) {
        CustomerDao customerDao = new CustomerDao();
        CustomerEntity customerEntity = customerDao.read(customerCode);

        if (customerEntity == null) return false;

        RegisterBusiness registerBusiness = new RegisterBusiness();

        // Get the customer a new password
        String customerPassword = StringUtils.randomString();
        String encryptedPassword = StringUtils.getMD5Hash(customerPassword);

        // Update new password for customer
        customerEntity.setPassword(encryptedPassword);
        customerDao.update(customerEntity);

        // Send new password email
        return registerBusiness.sendPasswordEmail(loginUrl, customerPassword, customerEntity);
    }
}
