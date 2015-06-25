package com.fpt.mic.micweb.model.dto.form;

import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dao.CustomerDao;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Created by Kha on 18/06/2015.
 */
public class CreateContractDto {
    @NotEmpty(message = "Mã khách hàng không được để trống")
    @Pattern(regexp = "^KH([0-9A-Z]{4,8})$", message = "Mã khách hàng không hợp lệ")
    // Mã khách hàng không tồn tại: @see {@link isNotExisted}
    private String customerCode;
    @NotNull(message = "Loại hợp đồng không được để trống")
    private Integer contractTypeId;
    @NotNull(message = "Thời điểm có hiệu lực không được để trống")
    private Timestamp startDate;
    @NotNull(message = "Thời điểm hết hiệu lực không được để trống")
    private Timestamp expiredDate;
    @NotNull(message = "Phí bảo hiểm không được để trống")
    private Float contractFee;
    @NotEmpty(message = "Biển số xe không được để trống")
    @Size(min = 4, max = 15, message = "Biển số xe phải có từ {min} đến {max} ký tự")
    private String plate;
    @NotEmpty(message = "Nhãn hiệu xe không được để trống")
    @Size(min = 2, max = 20, message = "Nhãn hiệu xe phải có từ {min} đến {max} ký tự")
    private String brand;
    private String modelCode;
    private String vehicleType;
    private String color;
    @NotEmpty(message = "Số máy không được để trống")
    @Size(min = 2, max = 20, message = "Số máy phải có từ {min} đến {max} ký tự")
    private String engine;
    @NotEmpty(message = "Số khung không được để trống")
    @Size(min = 2, max = 20, message = "Số khung phải có từ {min} đến {max} ký tự")
    private String chassis;
    @NotEmpty(message = "Dung tích không được để trống")
    @Size(min = 2, max = 20, message = "Dung tích phải có từ {min} đến {max} ký tự")
    private String capacity;
    @Range(min = 1900, max = 2200, message = "Năm sản xuất phải có giá trị từ {min} đến {max}")
    private Integer yearOfManufacture;
    @Range(min = 1, max = 1000, message = "Tự trọng phải có giá trị từ {min} đến {max}")
    private Integer weight;
    @Range(min = 1, max = 1000, message = "Số người được phép chở phải có giá trị từ {min} đến {max}")
    private Integer seatCapacity;
    @NotNull(message = "Ngày nộp phí không được để trống")
    private Timestamp paidDate;
    @NotNull(message = "Phí bảo hiểm không được để trống")
    private Float amount;

    @AssertTrue(message = "Mã khách hàng không tồn tại")
    private boolean isNotExisted() {
        CustomerDao customerDao = new CustomerDao();
        return customerCode != null && customerDao.read(customerCode) != null;
    }

    @AssertTrue(message = "Thời điểm có hiệu lực phải sau thời điểm hết hiệu lực")
    private boolean isValidDate() {
        if (startDate != null & expiredDate != null) {
            return expiredDate.after(startDate);
        } else {
            return false;
        }
    }

    @AssertTrue(message = "Thời hạn hợp đồng tối đa là 1 năm")
    private boolean isValidTerm() {
        if (startDate != null & expiredDate != null) {
            long contractTerm = expiredDate.getTime() - startDate.getTime();

            final int MILLIS_IN_SECOND = 1000;
            final int SECONDS_IN_MINUTE = 60;
            final int MINUTES_IN_HOUR = 60;
            final int HOURS_IN_DAY = 24;
            final int DAYS_IN_YEAR = 366;
            final long MILLISECONDS_IN_YEAR =
                    (long)MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR
                            * HOURS_IN_DAY * DAYS_IN_YEAR;
            if (contractTerm <= MILLISECONDS_IN_YEAR) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @AssertTrue(message = "Đang có hợp đồng hiệu lực với xe có biển số này")
    private boolean isValidPlate() {
        ContractDao contractDao = new ContractDao();
        return !contractDao.isExistByPlate(plate);
    }

    public CreateContractDto() {
    }

    public CreateContractDto(String customerCode, Integer contractTypeId, Timestamp startDate, Timestamp expiredDate,
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
