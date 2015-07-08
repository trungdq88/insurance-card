package com.fpt.mic.mobile.printer.app.entity;

import java.sql.Timestamp;
import java.util.Collection;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/5/15.
 */
public class ContractEntity {
    public String contractCode;
    public Timestamp startDate;
    public Timestamp expiredDate;
    public String status;
    public float contractFee;
    public String plate;
    public String brand;
    public String modelCode;
    public String vehicleType;
    public String color;
    public String engine;
    public String chassis;
    public String capacity;
    public int yearOfManufacture;
    public int weight;
    public int seatCapacity;
    public String certImage;
    public Timestamp cancelDate;
    public String cancelReason;
    public String cancelNote;
    public String staffCode;
    public int contractTypeId;
    public String customerCode;
    public Timestamp lastModified;
    public Timestamp createdDate;
    public Integer needRenewPayment;
    public Collection<Object> micAccidentsByContractCode;
    public Collection<Object> micCardInstancesByContractCode;
    public Collection<Object> micCompensationsByContractCode;
    public ContractTypeEntity micContractTypeByContractTypeId;
    public CustomerEntity micCustomerByCustomerCode;
    public StaffEntity micStaffByStaffCode;
    public Collection<Object> micPaymentsByContractCode;
    public Collection<Object> micPunishmentsByContractCode;
}
