package com.fpt.mic.micweb.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/5/15.
 */
@Entity
@Table(name = "mic_customer", schema = "", catalog = "mic_data")
public class CustomerEntity {
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
    private Collection<NewCardRequestEntity> micNewCardRequestsByCustomerCode;

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
        if (customerCode != null ? !customerCode.equals(that.customerCode) : that.customerCode != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (personalId != null ? !personalId.equals(that.personalId) : that.personalId != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        return !(lastModified != null ? !lastModified.equals(that.lastModified) : that.lastModified != null);

    }

    @Override
    public int hashCode() {
        int result = customerCode != null ? customerCode.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (personalId != null ? personalId.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + isDefaultPassword;
        result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
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
    public Collection<NewCardRequestEntity> getMicNewCardRequestsByCustomerCode() {
        return micNewCardRequestsByCustomerCode;
    }

    public void setMicNewCardRequestsByCustomerCode(Collection<NewCardRequestEntity> micNewCardRequestsByCustomerCode) {
        this.micNewCardRequestsByCustomerCode = micNewCardRequestsByCustomerCode;
    }
}
