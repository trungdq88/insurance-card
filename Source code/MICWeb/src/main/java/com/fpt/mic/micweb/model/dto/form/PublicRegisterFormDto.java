package com.fpt.mic.micweb.model.dto.form;

import java.sql.Timestamp;

/**
 * Created by TriPQM on 06/23/2015.
 */
public class PublicRegisterFormDto {
    private String name;
    private String email;
    private String address;
    private String phone;
    private Timestamp startDate;
    private String personalId;
    private Integer contractType;
    private Float contractFee;
    private String plate;
    private String brand;
    private String chassis;
    private String engine;
    private String capacity;
    private String type;
    private String model;
    private String color;
    private Integer yearOfMan;
    private Integer weight;
    private Integer seatCapacity;

    public PublicRegisterFormDto() {
    }

    public PublicRegisterFormDto(String name, String email, String address, String phone, Timestamp startDate, String personalId, Integer contractType,
                                 Float contractFee, String plate, String brand, String chassis, String engine, String capacity, String type,
                                 String model, String color, Integer yearOfMan, Integer weight, Integer seatCapacity) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.startDate = startDate;
        this.personalId = personalId;
        this.contractType = contractType;
        this.contractFee = contractFee;
        this.plate = plate;
        this.brand = brand;
        this.chassis = chassis;
        this.engine = engine;
        this.capacity = capacity;
        this.type = type;
        this.model = model;
        this.color = color;
        this.yearOfMan = yearOfMan;
        this.weight = weight;
        this.seatCapacity = seatCapacity;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
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

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getYearOfMan() {
        return yearOfMan;
    }

    public void setYearOfMan(Integer yearOfMan) {
        this.yearOfMan = yearOfMan;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(Integer seatCapacity) {
        this.seatCapacity = seatCapacity;
    }
}
