package com.fpt.mic.micweb.model.dto.form;

import java.sql.Timestamp;

/**
 * Created by UDEWQ on 06/23/2015.
 */
public class PublicHomeFormDto {
    private String name;
    private String email;
    private String phone;
    private String personalId;
    private String address;
    private Timestamp startDate;
    private Integer contractType;
    private Float contractFee;

    public PublicHomeFormDto() {
    }

    public PublicHomeFormDto(String name, String email, String phone, String personalId, String address,
                             Timestamp startDate, Integer contractType, Float contractFee) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.personalId = personalId;
        this.address = address;
        this.startDate = startDate;
        this.contractType = contractType;
        this.contractFee = contractFee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Integer getContractType() {
        return contractType;
    }

    public void setContractType(Integer contractType) {
        this.contractType = contractType;
    }

    public Float getContractFee() {
        return contractFee;
    }

    public void setContractFee(Float contractFee) {
        this.contractFee = contractFee;
    }
}
