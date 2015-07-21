package com.fpt.mic.micweb.model.entity;

import com.fpt.mic.micweb.model.entity.helper.IUserEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/5/15.
 */
@Entity
@Table(name = "mic_customer", schema = "", catalog = "mic_data")
public class CustomerEntity implements IUserEntity {
    private String customerCode;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String personalId;
    private String password;
    private int isDefaultPassword;
    private Timestamp lastModified;
    private Collection<ContractEntity> micContractsByCustomerCode;
    private Collection<CardInstanceEntity> micCardInstancesByCustomerCode;

    @Id
    @Column(name = "customer_code")
    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "personal_id")
    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "is_default_password")
    public int getIsDefaultPassword() {
        return isDefaultPassword;
    }

    public void setIsDefaultPassword(int isDefaultPassword) {
        this.isDefaultPassword = isDefaultPassword;
    }

    @Basic
    @Column(name = "last_modified")
    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerEntity that = (CustomerEntity) o;

        if (isDefaultPassword != that.isDefaultPassword) return false;
        if (!customerCode.equals(that.customerCode)) return false;
        if (!name.equals(that.name)) return false;
        if (!address.equals(that.address)) return false;
        if (!email.equals(that.email)) return false;
        if (!phone.equals(that.phone)) return false;
        if (personalId != null ? !personalId.equals(that.personalId) : that.personalId != null) return false;
        if (!password.equals(that.password)) return false;
        return lastModified.equals(that.lastModified);

    }

    @Override
    public int hashCode() {
        int result = customerCode.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + (personalId != null ? personalId.hashCode() : 0);
        result = 31 * result + password.hashCode();
        result = 31 * result + isDefaultPassword;
        result = 31 * result + lastModified.hashCode();
        return result;
    }

    @OneToMany(mappedBy = "micCustomerByCustomerCode")
    public Collection<ContractEntity> getMicContractsByCustomerCode() {
        return micContractsByCustomerCode;
    }

    public void setMicContractsByCustomerCode(Collection<ContractEntity> micContractsByCustomerCode) {
        this.micContractsByCustomerCode = micContractsByCustomerCode;
    }

    @OneToMany(mappedBy = "micCustomerByCustomerCode")
    public Collection<CardInstanceEntity> getMicCardInstancesByCustomerCode() {
        return micCardInstancesByCustomerCode;
    }

    public void setMicCardInstancesByCustomerCode(Collection<CardInstanceEntity> micCardsByCustomerCode) {
        this.micCardInstancesByCustomerCode = micCardsByCustomerCode;
    }

    @Override
    public String calcUserCode() {
        return customerCode;
    }

    @Override
    public String calcRole() {
        return "customer";
    }
}
