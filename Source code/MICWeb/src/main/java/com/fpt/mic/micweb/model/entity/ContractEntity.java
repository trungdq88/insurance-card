package com.fpt.mic.micweb.model.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/5/15.
 */
@Entity
@javax.persistence.Table(name = "mic_contract", schema = "", catalog = "mic_data")
public class ContractEntity {
    private String contractCode;

    @Id
    @javax.persistence.Column(name = "contract_code")
    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    private Timestamp startDate;

    @Basic
    @javax.persistence.Column(name = "start_date")
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    private Timestamp expiredDate;

    @Basic
    @javax.persistence.Column(name = "expired_date")
    public Timestamp getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Timestamp expiredDate) {
        this.expiredDate = expiredDate;
    }

    private String status;

    @Basic
    @javax.persistence.Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private float contractFee;

    @Basic
    @javax.persistence.Column(name = "contract_fee")
    public float getContractFee() {
        return contractFee;
    }

    public void setContractFee(float contractFee) {
        this.contractFee = contractFee;
    }

    private String plate;

    @Basic
    @javax.persistence.Column(name = "plate")
    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    private String brand;

    @Basic
    @javax.persistence.Column(name = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    private String modelCode;

    @Basic
    @javax.persistence.Column(name = "model_code")
    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    private String vehicleType;

    @Basic
    @javax.persistence.Column(name = "vehicle_type")
    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    private String color;

    @Basic
    @javax.persistence.Column(name = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private String engine;

    @Basic
    @javax.persistence.Column(name = "engine")
    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    private String chassis;

    @Basic
    @javax.persistence.Column(name = "chassis")
    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    private String capacity;

    @Basic
    @javax.persistence.Column(name = "capacity")
    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    private int yearOfManufacture;

    @Basic
    @javax.persistence.Column(name = "year_of_manufacture")
    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    private int weight;

    @Basic
    @javax.persistence.Column(name = "weight")
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    private int seatCapacity;

    @Basic
    @javax.persistence.Column(name = "seat_capacity")
    public int getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    private String certImage;

    @Basic
    @javax.persistence.Column(name = "cert_image")
    public String getCertImage() {
        return certImage;
    }

    public void setCertImage(String certImage) {
        this.certImage = certImage;
    }

    private Timestamp cancelDate;

    @Basic
    @javax.persistence.Column(name = "cancel_date")
    public Timestamp getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Timestamp cancelDate) {
        this.cancelDate = cancelDate;
    }

    private String cancelReason;

    @Basic
    @javax.persistence.Column(name = "cancel_reason")
    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    private String cancelNote;

    @Basic
    @javax.persistence.Column(name = "cancel_note")
    public String getCancelNote() {
        return cancelNote;
    }

    public void setCancelNote(String cancelNote) {
        this.cancelNote = cancelNote;
    }

    private String staffCode;

    @Basic
    @javax.persistence.Column(name = "staff_code")
    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    private int contractTypeId;

    @Basic
    @javax.persistence.Column(name = "contract_type_id")
    public int getContractTypeId() {
        return contractTypeId;
    }

    public void setContractTypeId(int contractTypeId) {
        this.contractTypeId = contractTypeId;
    }

    private String customerCode;

    @Basic
    @javax.persistence.Column(name = "customer_code")
    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContractEntity that = (ContractEntity) o;

        if (Float.compare(that.contractFee, contractFee) != 0) return false;
        if (yearOfManufacture != that.yearOfManufacture) return false;
        if (weight != that.weight) return false;
        if (seatCapacity != that.seatCapacity) return false;
        if (contractTypeId != that.contractTypeId) return false;
        if (contractCode != null ? !contractCode.equals(that.contractCode) : that.contractCode != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (expiredDate != null ? !expiredDate.equals(that.expiredDate) : that.expiredDate != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (plate != null ? !plate.equals(that.plate) : that.plate != null) return false;
        if (brand != null ? !brand.equals(that.brand) : that.brand != null) return false;
        if (modelCode != null ? !modelCode.equals(that.modelCode) : that.modelCode != null) return false;
        if (vehicleType != null ? !vehicleType.equals(that.vehicleType) : that.vehicleType != null) return false;
        if (color != null ? !color.equals(that.color) : that.color != null) return false;
        if (engine != null ? !engine.equals(that.engine) : that.engine != null) return false;
        if (chassis != null ? !chassis.equals(that.chassis) : that.chassis != null) return false;
        if (capacity != null ? !capacity.equals(that.capacity) : that.capacity != null) return false;
        if (certImage != null ? !certImage.equals(that.certImage) : that.certImage != null) return false;
        if (cancelDate != null ? !cancelDate.equals(that.cancelDate) : that.cancelDate != null) return false;
        if (cancelReason != null ? !cancelReason.equals(that.cancelReason) : that.cancelReason != null) return false;
        if (cancelNote != null ? !cancelNote.equals(that.cancelNote) : that.cancelNote != null) return false;
        if (staffCode != null ? !staffCode.equals(that.staffCode) : that.staffCode != null) return false;
        if (customerCode != null ? !customerCode.equals(that.customerCode) : that.customerCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = contractCode != null ? contractCode.hashCode() : 0;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (expiredDate != null ? expiredDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (contractFee != +0.0f ? Float.floatToIntBits(contractFee) : 0);
        result = 31 * result + (plate != null ? plate.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (modelCode != null ? modelCode.hashCode() : 0);
        result = 31 * result + (vehicleType != null ? vehicleType.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (engine != null ? engine.hashCode() : 0);
        result = 31 * result + (chassis != null ? chassis.hashCode() : 0);
        result = 31 * result + (capacity != null ? capacity.hashCode() : 0);
        result = 31 * result + yearOfManufacture;
        result = 31 * result + weight;
        result = 31 * result + seatCapacity;
        result = 31 * result + (certImage != null ? certImage.hashCode() : 0);
        result = 31 * result + (cancelDate != null ? cancelDate.hashCode() : 0);
        result = 31 * result + (cancelReason != null ? cancelReason.hashCode() : 0);
        result = 31 * result + (cancelNote != null ? cancelNote.hashCode() : 0);
        result = 31 * result + (staffCode != null ? staffCode.hashCode() : 0);
        result = 31 * result + contractTypeId;
        result = 31 * result + (customerCode != null ? customerCode.hashCode() : 0);
        return result;
    }
}
