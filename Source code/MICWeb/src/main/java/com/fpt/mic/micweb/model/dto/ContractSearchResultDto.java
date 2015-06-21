package com.fpt.mic.micweb.model.dto;

import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.CustomerEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/6/15.
 */
@Entity
public class ContractSearchResultDto {
    @OneToOne
    private ContractEntity contractEntity;
    @OneToOne
    private CustomerEntity customerEntity;

    public ContractSearchResultDto(ContractEntity contractEntity, CustomerEntity customerEntity) {
        this.contractEntity = contractEntity;
        this.customerEntity = customerEntity;
    }

    @Id
    private Integer id;

    public ContractSearchResultDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
