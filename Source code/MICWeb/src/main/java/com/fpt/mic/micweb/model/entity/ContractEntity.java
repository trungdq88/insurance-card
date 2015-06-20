package com.fpt.mic.micweb.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/5/15.
 */
@Entity
@Table(name = "mic_contract", schema = "", catalog = "mic_data")
public class ContractEntity {
    public static final String STATUS_READY = "Ready";
    private String contractCode;
    private Timestamp startDate;
    private Timestamp expiredDate;
    private String status;
    private float contractFee;
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
    private Timestamp cancelDate;
    private String cancelReason;
    private String cancelNote;
    private String staffCode;
    private int contractTypeId;
    private String customerCode;
    private Collection<AccidentEntity> micAccidentsByContractCode;
    private Collection<CardEntity> micCardsByContractCode;
    private Collection<CompensationEntity> micCompensationsByContractCode;
    private ContractTypeEntity micContractTypeByContractTypeId;
    private CustomerEntity micCustomerByCustomerCode;
    private StaffEntity micStaffByStaffCode;
    private Collection<PaymentEntity> micPaymentsByContractCode;
    private Collection<PunishmentEntity> micPunishmentsByContractCode;

    @Id
    @Column(name = "contract_code")
    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    @Basic
    @Column(name = "start_date")
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "expired_date")
    public Timestamp getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Timestamp expiredDate) {
        this.expiredDate = expiredDate;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "contract_fee")
    public float getContractFee() {
        return contractFee;
    }

    public void setContractFee(float contractFee) {
        this.contractFee = contractFee;
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
    @Column(name = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Basic
    @Column(name = "model_code")
    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    @Basic
    @Column(name = "vehicle_type")
    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Basic
    @Column(name = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Basic
    @Column(name = "engine")
    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    @Basic
    @Column(name = "chassis")
    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    @Basic
    @Column(name = "capacity")
    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    @Basic
    @Column(name = "year_of_manufacture")
    public Integer getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(Integer yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    @Basic
    @Column(name = "weight")
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "seat_capacity")
    public Integer getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(Integer seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    @Basic
    @Column(name = "cancel_date")
    public Timestamp getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Timestamp cancelDate) {
        this.cancelDate = cancelDate;
    }

    @Basic
    @Column(name = "cancel_reason")
    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    @Basic
    @Column(name = "cancel_note")
    public String getCancelNote() {
        return cancelNote;
    }

    public void setCancelNote(String cancelNote) {
        this.cancelNote = cancelNote;
    }

    @Basic
    @Column(name = "staff_code")
    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    @Basic
    @Column(name = "contract_type_id")
    public int getContractTypeId() {
        return contractTypeId;
    }

    public void setContractTypeId(int contractTypeId) {
        this.contractTypeId = contractTypeId;
    }

    @Basic
    @Column(name = "customer_code")
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
        result = 31 * result + (cancelDate != null ? cancelDate.hashCode() : 0);
        result = 31 * result + (cancelReason != null ? cancelReason.hashCode() : 0);
        result = 31 * result + (cancelNote != null ? cancelNote.hashCode() : 0);
        result = 31 * result + (staffCode != null ? staffCode.hashCode() : 0);
        result = 31 * result + contractTypeId;
        result = 31 * result + (customerCode != null ? customerCode.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "micContractByContractCode")
    public Collection<AccidentEntity> getMicAccidentsByContractCode() {
        return micAccidentsByContractCode;
    }

    public void setMicAccidentsByContractCode(Collection<AccidentEntity> micAccidentsByContractCode) {
        this.micAccidentsByContractCode = micAccidentsByContractCode;
    }

    @OneToMany(mappedBy = "micContractByContractCode")
    public Collection<CardEntity> getMicCardsByContractCode() {
        return micCardsByContractCode;
    }

    public void setMicCardsByContractCode(Collection<CardEntity> micCardsByContractCode) {
        this.micCardsByContractCode = micCardsByContractCode;
    }

    @OneToMany(mappedBy = "micContractByContractCode")
    public Collection<CompensationEntity> getMicCompensationsByContractCode() {
        return micCompensationsByContractCode;
    }

    public void setMicCompensationsByContractCode(Collection<CompensationEntity> micCompensationsByContractCode) {
        this.micCompensationsByContractCode = micCompensationsByContractCode;
    }

    @ManyToOne
    @JoinColumn(name = "contract_type_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public ContractTypeEntity getMicContractTypeByContractTypeId() {
        return micContractTypeByContractTypeId;
    }

    public void setMicContractTypeByContractTypeId(ContractTypeEntity micContractTypeByContractTypeId) {
        this.micContractTypeByContractTypeId = micContractTypeByContractTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "customer_code", referencedColumnName = "customer_code", nullable = false, insertable = false, updatable = false)
    public CustomerEntity getMicCustomerByCustomerCode() {
        return micCustomerByCustomerCode;
    }

    public void setMicCustomerByCustomerCode(CustomerEntity micCustomerByCustomerCode) {
        this.micCustomerByCustomerCode = micCustomerByCustomerCode;
    }

    @ManyToOne
    @JoinColumn(name = "staff_code", referencedColumnName = "staff_code", nullable = true, insertable = false, updatable = false)
    public StaffEntity getMicStaffByStaffCode() {
        return micStaffByStaffCode;
    }

    public void setMicStaffByStaffCode(StaffEntity micStaffByStaffCode) {
        this.micStaffByStaffCode = micStaffByStaffCode;
    }

    @OneToMany(mappedBy = "micContractByContractCode")
    public Collection<PaymentEntity> getMicPaymentsByContractCode() {
        return micPaymentsByContractCode;
    }

    public void setMicPaymentsByContractCode(Collection<PaymentEntity> micPaymentsByContractCode) {
        this.micPaymentsByContractCode = micPaymentsByContractCode;
    }

    @OneToMany(mappedBy = "micContractByContractCode")
    public Collection<PunishmentEntity> getMicPunishmentsByContractCode() {
        return micPunishmentsByContractCode;
    }

    public void setMicPunishmentsByContractCode(Collection<PunishmentEntity> micPunishmentsByContractCode) {
        this.micPunishmentsByContractCode = micPunishmentsByContractCode;
    }
}
