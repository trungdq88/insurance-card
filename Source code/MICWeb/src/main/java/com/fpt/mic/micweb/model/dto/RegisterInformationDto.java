package com.fpt.mic.micweb.model.dto;

import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.CustomerEntity;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/11/15.
 */
public class RegisterInformationDto {
    private ContractEntity contractEntity;
    private CustomerEntity customerEntity;
    private boolean emailSuccess;

    public RegisterInformationDto() {
    }

    public RegisterInformationDto(ContractEntity contractEntity, CustomerEntity customerEntity, boolean emailSuccess) {
        this.contractEntity = contractEntity;
        this.customerEntity = customerEntity;
        this.emailSuccess = emailSuccess;
    }

    public ContractEntity getContractEntity() {
        return contractEntity;
    }

    public void setContractEntity(ContractEntity contractEntity) {
        this.contractEntity = contractEntity;
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
