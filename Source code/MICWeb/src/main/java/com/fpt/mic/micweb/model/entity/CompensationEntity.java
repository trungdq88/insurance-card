package com.fpt.mic.micweb.model.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by dinhquangtrung on 6/2/15.
 */
@Entity
@javax.persistence.Table(name = "mic_compensation", schema = "", catalog = "mic_data")
public class CompensationEntity {
    private String conpensationCode;

    @Id
    @javax.persistence.Column(name = "conpensation_code")
    public String getConpensationCode() {
        return conpensationCode;
    }

    public void setConpensationCode(String conpensationCode) {
        this.conpensationCode = conpensationCode;
    }

    private String driverName;

    @Basic
    @javax.persistence.Column(name = "driver_name")
    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    private String licenseNumber;

    @Basic
    @javax.persistence.Column(name = "license_number")
    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    private String licenseType;

    @Basic
    @javax.persistence.Column(name = "license_type")
    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    private String driverPhone;

    @Basic
    @javax.persistence.Column(name = "driver_phone")
    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    private String vehicleCapacity;

    @Basic
    @javax.persistence.Column(name = "vehicle_capacity")
    public String getVehicleCapacity() {
        return vehicleCapacity;
    }

    public void setVehicleCapacity(String vehicleCapacity) {
        this.vehicleCapacity = vehicleCapacity;
    }

    private String driverAddress;

    @Basic
    @javax.persistence.Column(name = "driver_address")
    public String getDriverAddress() {
        return driverAddress;
    }

    public void setDriverAddress(String driverAddress) {
        this.driverAddress = driverAddress;
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

    private int date;

    @Basic
    @javax.persistence.Column(name = "date")
    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    private String place;

    @Basic
    @javax.persistence.Column(name = "place")
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    private String controlDepartment;

    @Basic
    @javax.persistence.Column(name = "control_department")
    public String getControlDepartment() {
        return controlDepartment;
    }

    public void setControlDepartment(String controlDepartment) {
        this.controlDepartment = controlDepartment;
    }

    private String description;

    @Basic
    @javax.persistence.Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String humanDamage;

    @Basic
    @javax.persistence.Column(name = "human_damage")
    public String getHumanDamage() {
        return humanDamage;
    }

    public void setHumanDamage(String humanDamage) {
        this.humanDamage = humanDamage;
    }

    private String assetDamage;

    @Basic
    @javax.persistence.Column(name = "asset_damage")
    public String getAssetDamage() {
        return assetDamage;
    }

    public void setAssetDamage(String assetDamage) {
        this.assetDamage = assetDamage;
    }

    private String observer;

    @Basic
    @javax.persistence.Column(name = "observer")
    public String getObserver() {
        return observer;
    }

    public void setObserver(String observer) {
        this.observer = observer;
    }

    private String compensationNote;

    @Basic
    @javax.persistence.Column(name = "compensation_note")
    public String getCompensationNote() {
        return compensationNote;
    }

    public void setCompensationNote(String compensationNote) {
        this.compensationNote = compensationNote;
    }

    private String attachment;

    @Basic
    @javax.persistence.Column(name = "attachment")
    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    private int createdDate;

    @Basic
    @javax.persistence.Column(name = "created_date")
    public int getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(int createdDate) {
        this.createdDate = createdDate;
    }

    private Integer resolveDate;

    @Basic
    @javax.persistence.Column(name = "resolve_date")
    public Integer getResolveDate() {
        return resolveDate;
    }

    public void setResolveDate(Integer resolveDate) {
        this.resolveDate = resolveDate;
    }

    private String desicion;

    @Basic
    @javax.persistence.Column(name = "desicion")
    public String getDesicion() {
        return desicion;
    }

    public void setDesicion(String desicion) {
        this.desicion = desicion;
    }

    private String resolveNote;

    @Basic
    @javax.persistence.Column(name = "resolve_note")
    public String getResolveNote() {
        return resolveNote;
    }

    public void setResolveNote(String resolveNote) {
        this.resolveNote = resolveNote;
    }

    private String contractCode;

    @Basic
    @javax.persistence.Column(name = "contract_code")
    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompensationEntity that = (CompensationEntity) o;

        if (date != that.date) return false;
        if (createdDate != that.createdDate) return false;
        if (conpensationCode != null ? !conpensationCode.equals(that.conpensationCode) : that.conpensationCode != null)
            return false;
        if (driverName != null ? !driverName.equals(that.driverName) : that.driverName != null) return false;
        if (licenseNumber != null ? !licenseNumber.equals(that.licenseNumber) : that.licenseNumber != null)
            return false;
        if (licenseType != null ? !licenseType.equals(that.licenseType) : that.licenseType != null) return false;
        if (driverPhone != null ? !driverPhone.equals(that.driverPhone) : that.driverPhone != null) return false;
        if (vehicleCapacity != null ? !vehicleCapacity.equals(that.vehicleCapacity) : that.vehicleCapacity != null)
            return false;
        if (driverAddress != null ? !driverAddress.equals(that.driverAddress) : that.driverAddress != null)
            return false;
        if (plate != null ? !plate.equals(that.plate) : that.plate != null) return false;
        if (place != null ? !place.equals(that.place) : that.place != null) return false;
        if (controlDepartment != null ? !controlDepartment.equals(that.controlDepartment) : that.controlDepartment != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (humanDamage != null ? !humanDamage.equals(that.humanDamage) : that.humanDamage != null) return false;
        if (assetDamage != null ? !assetDamage.equals(that.assetDamage) : that.assetDamage != null) return false;
        if (observer != null ? !observer.equals(that.observer) : that.observer != null) return false;
        if (compensationNote != null ? !compensationNote.equals(that.compensationNote) : that.compensationNote != null)
            return false;
        if (attachment != null ? !attachment.equals(that.attachment) : that.attachment != null) return false;
        if (resolveDate != null ? !resolveDate.equals(that.resolveDate) : that.resolveDate != null) return false;
        if (desicion != null ? !desicion.equals(that.desicion) : that.desicion != null) return false;
        if (resolveNote != null ? !resolveNote.equals(that.resolveNote) : that.resolveNote != null) return false;
        if (contractCode != null ? !contractCode.equals(that.contractCode) : that.contractCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = conpensationCode != null ? conpensationCode.hashCode() : 0;
        result = 31 * result + (driverName != null ? driverName.hashCode() : 0);
        result = 31 * result + (licenseNumber != null ? licenseNumber.hashCode() : 0);
        result = 31 * result + (licenseType != null ? licenseType.hashCode() : 0);
        result = 31 * result + (driverPhone != null ? driverPhone.hashCode() : 0);
        result = 31 * result + (vehicleCapacity != null ? vehicleCapacity.hashCode() : 0);
        result = 31 * result + (driverAddress != null ? driverAddress.hashCode() : 0);
        result = 31 * result + (plate != null ? plate.hashCode() : 0);
        result = 31 * result + date;
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (controlDepartment != null ? controlDepartment.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (humanDamage != null ? humanDamage.hashCode() : 0);
        result = 31 * result + (assetDamage != null ? assetDamage.hashCode() : 0);
        result = 31 * result + (observer != null ? observer.hashCode() : 0);
        result = 31 * result + (compensationNote != null ? compensationNote.hashCode() : 0);
        result = 31 * result + (attachment != null ? attachment.hashCode() : 0);
        result = 31 * result + createdDate;
        result = 31 * result + (resolveDate != null ? resolveDate.hashCode() : 0);
        result = 31 * result + (desicion != null ? desicion.hashCode() : 0);
        result = 31 * result + (resolveNote != null ? resolveNote.hashCode() : 0);
        result = 31 * result + (contractCode != null ? contractCode.hashCode() : 0);
        return result;
    }
}
