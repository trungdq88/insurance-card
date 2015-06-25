package com.fpt.mic.micweb.model.dto.form;

import com.fpt.mic.micweb.model.dao.ContractDao;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by TriPQM on 06/23/2015.
 */
public class PublicRegisterFormDto {
    @NotEmpty(message = "Họ tên không được để trống")
    @Pattern(regexp = "\\S[^0-9!@#$%^&*()+=~`]+",message = "Họ tên không hợp lệ")
    @Size(min = 3, max = 80, message = "Họ tên phải từ {min} đến {max} ký tự")
    private String name;
    @NotEmpty(message = "Email không được để trống")
    @Pattern(regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}$",message = "Email không hợp lệ")
    private String email;
    @NotEmpty(message = "Địa chỉ không được để trống")
    @Size(min = 3, max = 250, message = "Địa chỉ phải từ {min} đến {max} ký tự")
    private String address;
    @NotEmpty(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "[0-9]+",message = "Số điện thoại không hợp lệ")
    @Size(min = 8, max = 15, message = "Số điện thoại phải từ {min} đến {max} ký tự")
    private String phone;
    @NotNull(message = "Ngày bắt đầu không được để trống")
    private Timestamp startDate;
    private String personalId;
    @NotNull(message = "Quyền lợi bảo hiểm không được để trống")
    private Integer contractType;
    @NotNull(message = "Phí bảo hiểm không được để trống")
    @Range(min = 0, message = "Phí bảo hiểm phải lớn hơn 0")
    private Float contractFee;
    @NotEmpty(message = "Biển số xe không được để trống")
    @Size(min = 4, max = 15, message = "Biển số xe phải có từ {min} đến {max} ký tự")
    private String plate;
    @NotEmpty(message = "Nhãn hiệu xe không được để trống")
    @Size(min = 2, max = 20, message = "Nhãn hiệu xe phải có từ {min} đến {max} ký tự")
    private String brand;
    @NotEmpty(message = "Số khung không được để trống")
    @Size(min = 2, max = 20, message = "Số khung phải có từ {min} đến {max} ký tự")
    private String chassis;
    @NotEmpty(message = "Số máy không được để trống")
    @Size(min = 2, max = 20, message = "Số máy phải có từ {min} đến {max} ký tự")
    private String engine;
    @NotEmpty(message = "Dung tích không được để trống")
    @Size(min = 2, max = 20, message = "Dung tích phải có từ {min} đến {max} ký tự")
    private String capacity;
    private String type;
    private String model;
    private String color;
    @Range(min = 1900, max = 2200, message = "Năm sản xuất phải có giá trị từ {min} đến {max}")
    private Integer yearOfMan;
    @Range(min = 1, max = 1000, message = "Tự trọng phải có giá trị từ {min} đến {max}")
    private Integer weight;
    @Range(min = 1, max = 100, message = "Số chỗ ngồi phải có giá trị từ {min} đến {max}")
    private Integer seatCapacity;

    @AssertTrue(message = "Ngày bắt đầu phải kể từ ngày hôm nay trở đi")
    private boolean isValidStartDate() {
        if (startDate != null) {
            Timestamp currentDate = new Timestamp(new Date().getTime());
            currentDate.setHours(0);
            currentDate.setMinutes(0);
            currentDate.setSeconds(0);
            currentDate.setNanos(0);
            return !startDate.before(currentDate);
        }
        return false;
    }
    @AssertTrue(message = "Số CMND/Hộ chiếu không hợp lệ")
    public boolean isValidPersonalId(){
        if ( personalId == null || personalId.isEmpty() ) {
            return true;
        } else {
            if(personalId.length() > 7 && personalId.length() < 16) {
                java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("[0-9]+");
                return pattern.matcher(personalId).matches();
            }
            return false;
        }
    }

    @AssertTrue(message = "Dung tích xe phải từ 2 đến 20 ký tự")
    public boolean isValidCapacity(){
        if ( capacity == null || capacity.isEmpty() ) {
            return true;
        } else {
            return capacity.length() > 1 && capacity.length() < 21;
        }
    }
    @AssertTrue(message = "Loại xe phải từ 2 đến 20 ký tự")
    public boolean isValidType(){
        if ( type == null || type.isEmpty() ) {
            return true;
        } else {
            return type.length() > 1 && type.length() < 21;
        }
    }
    @AssertTrue(message = "Số loại phải từ 2 đến 20 ký tự")
    public boolean isValidModel(){
        if ( model == null || model.isEmpty() ) {
            return true;
        } else {
            return model.length() > 1 && model.length() < 21;
        }
    }
    @AssertTrue(message = "Màu xe phải từ 2 đến 20 ký tự")
    public boolean isValidColor(){
        if ( color == null || color.isEmpty() ) {
            return true;
        } else {
            return color.length() > 1 && color.length() < 21;
        }
    }

    @AssertTrue(message = "Đang có hợp đồng hiệu lực với xe có biển số này")
    private boolean isValidPlate() {
        ContractDao contractDao = new ContractDao();
        return !contractDao.isExistByPlate(plate);
    }
    /*@AssertTrue(message = "Đang có hợp đồng hiệu lực với xe có số khung này")
    private boolean isValidChassis() {
        ContractDao contractDao = new ContractDao();
        return !contractDao.isExistByChassis(chassis);
    }
    @AssertTrue(message = "Đang có hợp đồng hiệu lực với xe có số máy này")
    private boolean isValidEngine() {
        ContractDao contractDao = new ContractDao();
        return !contractDao.isExistByEngine(engine);
    }*/

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
