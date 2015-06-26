package com.fpt.mic.micweb.model.dto;

import com.fpt.mic.micweb.model.entity.CustomerEntity;

/**
 * Created by Kha on 26/06/2015.
 */
public class CreateCustomerInfoDto {
    private CustomerEntity customerEntity;
    private boolean emailSuccess;

    public CreateCustomerInfoDto() {
    }

    public CreateCustomerInfoDto(CustomerEntity customerEntity, boolean emailSuccess) {
        this.customerEntity = customerEntity;
        this.emailSuccess = emailSuccess;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public boolean isEmailSuccess() {
        return emailSuccess;
    }

    public void setEmailSuccess(boolean emailSuccess) {
        this.emailSuccess = emailSuccess;
    }
}
