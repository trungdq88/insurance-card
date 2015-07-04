package com.fpt.mic.micweb.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/5/15.
 */
@Entity
@Table(name = "mic_compensation", schema = "", catalog = "mic_data")
public class CompensationEntity {
    private String compensationCode;
    private String driverName;
    private String licenseNumber;
    private String licenseType;
    private String driverPhone;
    private String vehicleCapacity;
    private String driverAddress;
    private String plate;
    private Timestamp accidentDate;
    private String accidentPlace;
    private String controlDepartment;
    private String description;
    private String humanDamage;
    private String assetDamage;
    private String observer;
    private String observerAddress;
    private String compensationNote;
    private String attachment;
    private Timestamp createdDate;
    private Timestamp resolveDate;
    private String decision;
    private String resolveNote;
    private String contractCode;
    private Timestamp lastModified;

    private ContractEntity micContractByContractCode;



    @Id
    @Column(name = "compensation_code")
    public String getCompensationCode() {
        return compensationCode;
    }

    public void setCompensationCode(String compensationCode) {
        this.compensationCode = compensationCode;
    }

    @Basic
    @Column(name = "driver_name")
    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Basic
    @Column(name = "license_number")
    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Basic
    @Column(name = "license_type")
    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    @Basic
    @Column(name = "driver_phone")
    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    @Basic
    @Column(name = "vehicle_capacity")
    public String getVehicleCapacity() {
        return vehicleCapacity;
    }

    public void setVehicleCapacity(String vehicleCapacity) {
        this.vehicleCapacity = vehicleCapacity;
    }

    @Basic
    @Column(name = "driver_address")
    public String getDriverAddress() {
        return driverAddress;
    }

    public void setDriverAddress(String driverAddress) {
        this.driverAddress = driverAddress;
    }

    @Basic
    @Column(name = "plate")
    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    @Basic
    @Column(name = "accident_date")
    public Timestamp getAccidentDate() {
        return accidentDate;
    }

    public void setAccidentDate(Timestamp accidentDate) {
        this.accidentDate = accidentDate;
    }

    @Basic
    @Column(name = "accident_place")
    public String getAccidentPlace() {
        return accidentPlace;
    }

    public void setAccidentPlace(String accidentPlace) {
        this.accidentPlace = accidentPlace;
    }

    @Basic
    @Column(name = "control_department")
    public String getControlDepartment() {
        return controlDepartment;
    }

    public void setControlDepartment(String controlDepartment) {
        this.controlDepartment = controlDepartment;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "human_damage")
    public String getHumanDamage() {
        return humanDamage;
    }

    public void setHumanDamage(String humanDamage) {
        this.humanDamage = humanDamage;
    }

    @Basic
    @Column(name = "asset_damage")
    public String getAssetDamage() {
        return assetDamage;
    }

    public void setAssetDamage(String assetDamage) {
        this.assetDamage = assetDamage;
    }

    @Basic
    @Column(name = "observer")
    public String getObserver() {
        return observer;
    }

    public void setObserver(String observer) {
        this.observer = observer;
    }
    @Basic
    @Column(name = "observer_address")
    public String getObserverAddress() {
        return observerAddress;
    }

    public void setObserverAddress(String observerAddress) {
        this.observerAddress = observerAddress;
    }

    @Basic
    @Column(name = "compensation_note")
    public String getCompensationNote() {
        return compensationNote;
    }

    public void setCompensationNote(String compensationNote) {
        this.compensationNote = compensationNote;
    }

    @Basic
    @Column(name = "attachment")
    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "resolve_date")
    public Timestamp getResolveDate() {
        return resolveDate;
    }

    public void setResolveDate(Timestamp resolveDate) {
        this.resolveDate = resolveDate;
    }

    @Basic
    @Column(name = "decision")
    public String getDecision() {
        return decision;
    }

    public void setDecision(String desicion) {
        this.decision = desicion;
    }

    @Basic
    @Column(name = "resolve_note")
    public String getResolveNote() {
        return resolveNote;
    }

    public void setResolveNote(String resolveNote) {
        this.resolveNote = resolveNote;
    }
    @Basic
    @Column(name = "contract_code")
    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
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

        CompensationEntity that = (CompensationEntity) o;

        if (compensationCode != null ? !compensationCode.equals(that.compensationCode) : that.compensationCode != null)
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
        if (accidentDate != null ? !accidentDate.equals(that.accidentDate) : that.accidentDate != null) return false;
        if (accidentPlace != null ? !accidentPlace.equals(that.accidentPlace) : that.accidentPlace != null)
            return false;
        if (controlDepartment != null ? !controlDepartment.equals(that.controlDepartment) : that.controlDepartment != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (humanDamage != null ? !humanDamage.equals(that.humanDamage) : that.humanDamage != null) return false;
        if (assetDamage != null ? !assetDamage.equals(that.assetDamage) : that.assetDamage != null) return false;
        if (observer != null ? !observer.equals(that.observer) : that.observer != null) return false;
        if (observerAddress != null ? !observerAddress.equals(that.observerAddress) : that.observerAddress != null) return false;
        if (compensationNote != null ? !compensationNote.equals(that.compensationNote) : that.compensationNote != null)
            return false;
        if (attachment != null ? !attachment.equals(that.attachment) : that.attachment != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (resolveDate != null ? !resolveDate.equals(that.resolveDate) : that.resolveDate != null) return false;
        if (decision != null ? !decision.equals(that.decision) : that.decision != null) return false;
        if (resolveNote != null ? !resolveNote.equals(that.resolveNote) : that.resolveNote != null) return false;
        if (contractCode != null ? !contractCode.equals(that.contractCode) : that.contractCode != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = compensationCode != null ? compensationCode.hashCode() : 0;
        result = 31 * result + (driverName != null ? driverName.hashCode() : 0);
        result = 31 * result + (licenseNumber != null ? licenseNumber.hashCode() : 0);
        result = 31 * result + (licenseType != null ? licenseType.hashCode() : 0);
        result = 31 * result + (driverPhone != null ? driverPhone.hashCode() : 0);
        result = 31 * result + (vehicleCapacity != null ? vehicleCapacity.hashCode() : 0);
        result = 31 * result + (driverAddress != null ? driverAddress.hashCode() : 0);
        result = 31 * result + (plate != null ? plate.hashCode() : 0);
        result = 31 * result + (accidentDate != null ? accidentDate.hashCode() : 0);
        result = 31 * result + (accidentPlace != null ? accidentPlace.hashCode() : 0);
        result = 31 * result + (controlDepartment != null ? controlDepartment.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (humanDamage != null ? humanDamage.hashCode() : 0);
        result = 31 * result + (assetDamage != null ? assetDamage.hashCode() : 0);
        result = 31 * result + (observer != null ? observer.hashCode() : 0);
        result = 31 * result + (observerAddress != null ? observerAddress.hashCode() : 0);
        result = 31 * result + (compensationNote != null ? compensationNote.hashCode() : 0);
        result = 31 * result + (attachment != null ? attachment.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (resolveDate != null ? resolveDate.hashCode() : 0);
        result = 31 * result + (decision != null ? decision.hashCode() : 0);
        result = 31 * result + (resolveNote != null ? resolveNote.hashCode() : 0);
        result = 31 * result + (contractCode != null ? contractCode.hashCode() : 0);
        return result;
    }


    @ManyToOne
    @JoinColumn(name = "contract_code", referencedColumnName = "contract_code", nullable = false, insertable = false, updatable = false)
    public ContractEntity getMicContractByContractCode() {
        return micContractByContractCode;
    }

    public void setMicContractByContractCode(ContractEntity micContractByContractCode) {
        this.micContractByContractCode = micContractByContractCode;
    }
}
