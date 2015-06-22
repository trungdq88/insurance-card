package com.fpt.mic.micweb.model.dto.form;

import java.sql.Timestamp;

/**
 * Created by Kha on 18/06/2015.
 */
public class CreateContractDTO {
    private String customerCode;
    private Integer contractTypeId;
    private Timestamp startDate;
    private Timestamp expiredDate;
    private Float contractFee;
    private String plate;
    private String brand;
    private String modelCode;
    private String vehicleType;
    private String color;
    private String engine;
    private String chassis;
    private String capacity;
    private Integer yearOfManufacture;
    private Integer weight;
    private Integer seatCapacity;
    private Timestamp paidDate;
    private Float amount;

    public CreateContractDTO() {
    }

    public CreateContractDTO(String customerCode, Integer contractTypeId, Timestamp startDate, Timestamp expiredDate,
                             Float contractFee, String plate, String brand, String modelCode, String vehicleType,
                             String color, String engine, String chassis, String capacity, Integer yearOfManufacture,
                             Integer weight, Integer seatCapacity, Timestamp paidDate, Float amount) {
        this.customerCode = customerCode;
        this.contractTypeId = contractTypeId;
        this.startDate = startDate;
        this.expiredDate = expiredDate;
        this.contractFee = contractFee;
        this.plate = plate;
        this.brand = brand;
        this.modelCode = modelCode;
        this.vehicleType = vehicleType;
        this.color = color;
        this.engine = engine;
        this.chassis = chassis;
        this.capacity = capacity;
        this.yearOfManufacture = yearOfManufacture;
        this.weight = weight;
        this.seatCapacity = seatCapacity;
        this.paidDate = paidDate;
        this.amount = amount;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Integer getContractTypeId() {
        return contractTypeId;
    }

    public void setContractTypeId(Integer contractTypeId) {
        this.contractTypeId = contractTypeId;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Timestamp expiredDate) {
        this.expiredDate = expiredDate;
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

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public Integer getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(Integer yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
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

    public Timestamp getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Timestamp paidDate) {
        this.paidDate = paidDate;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
