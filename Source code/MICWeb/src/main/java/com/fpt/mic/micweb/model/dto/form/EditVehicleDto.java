package com.fpt.mic.micweb.model.dto.form;

import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Created by Kha on 16/07/2015.
 */
public class EditVehicleDto {
    @NotEmpty(message = "Mã hợp đồng không được để trống")
    @Pattern(regexp = "^HD([0-9A-Z]{4,8})$", message = "Mã hợp đồng không hợp lệ")
    // Mã hợp đồng không tồn tại: @see {@link isContractCodeNotExisted}
    private String contractCode;
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
    @Range(min = 1, max = 100, message = "Số người được phép chở phải có giá trị từ {min} đến {max}")
    private Integer seatCapacity;
    private Timestamp lastModified;

    @AssertTrue(message = "Mã hợp đồng không tồn tại")
    private boolean isContractCodeNotExisted() {
        ContractDao contractDao = new ContractDao();
        return contractCode != null && contractDao.read(contractCode) != null;
    }

    @AssertTrue(message = "Đang có hợp đồng hiệu lực với xe có biển số này")
    private boolean isValidPlate() {
        ContractDao contractDao = new ContractDao();
        if (plate.equals(contractDao.read(contractCode).getPlate())) {
            return true;
        } else {
            return !contractDao.isExistByPlate(plate);
        }
    }

    @AssertTrue(message = "Số loại phải có độ dài từ 2 đến 20 ký tự")
    private boolean isModelCodeValid() {
        if (modelCode == null || modelCode.isEmpty()) {
            return true; // Vẫn chấp nhận null hoặc rỗng
        } else {
            // Nếu có giá trị thì phải có độ dài đúng requirement
            return modelCode.length() >= 2 && modelCode.length() <= 20;
        }
    }

    @AssertTrue(message = "Loại xe phải có độ dài từ 2 đến 20 ký tự")
    private boolean isVehicleTypeValid() {
        if (vehicleType == null || vehicleType.isEmpty()) {
            return true; // Vẫn chấp nhận null hoặc rỗng
        } else {
            // Nếu có giá trị thì phải có độ dài đúng requirement
            return vehicleType.length() >= 2 && vehicleType.length() <= 20;
        }
    }

    @AssertTrue(message = "Màu sắc phải có độ dài từ 2 đến 20 ký tự")
    private boolean isColorValid() {
        if (color == null || color.isEmpty()) {
            return true; // Vẫn chấp nhận null hoặc rỗng
        } else {
            // Nếu có giá trị thì phải có độ dài đúng requirement
            return color.length() >= 2 && color.length() <= 20;
        }
    }

    @AssertTrue(message = "Thông tin hợp đồng đã bị sửa đổi trước đó bởi một người khác, vui lòng thực hiện lại thao tác")
    private boolean isContractNotChanged() {
        if (this.lastModified != null && this.contractCode != null) {
            ContractDao contractDao = new ContractDao();
            ContractEntity contractEntity = contractDao.read(contractCode);
            return contractEntity.getLastModified().equals(lastModified);
        } else {
            return false;
        }
    }

    public EditVehicleDto() {
    }

    public EditVehicleDto(String contractCode, String plate, String brand, String modelCode, String vehicleType,
                          String color, String engine, String chassis, String capacity, Integer yearOfManufacture,
                          Integer weight, Integer seatCapacity, Timestamp lastModified) {
        this.contractCode = contractCode;
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
        this.lastModified = lastModified;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
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

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }
}