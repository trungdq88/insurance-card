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

    public RegisterInformationDto() {
    }

    public RegisterInformationDto(ContractEntity contractEntity, CustomerEntity customerEntity) {
        this.contractEntity = contractEntity;
        this.customerEntity = customerEntity;
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
}
