package com.fpt.mic.micweb.model.dto.form;


import com.fpt.mic.micweb.model.dao.CustomerDao;
import com.fpt.mic.micweb.utils.ConfigUtils;
import com.fpt.mic.micweb.utils.DateUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by TriPQM on 06/23/2015.
 */
public class PublicHomeFormDto {
    @NotEmpty(message = "Họ tên không được để trống")
    @Pattern(regexp = "\\S[^0-9!@#$%^&*()+=~`]+", message = "Họ tên không hợp lệ")
    @Size(min = 3, max = 80, message = "Họ tên phải từ {min} đến {max} ký tự")
    private String name;
    @NotEmpty(message = "Email không được để trống")
    @Pattern(regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}$", message = "Email không hợp lệ")
    private String email;
    @NotEmpty(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "[0-9]+", message = "Số điện thoại không hợp lệ")
    @Size(min = 8, max = 15, message = "Số điện thoại phải từ {min} đến {max} ký tự")
    private String phone;
    //    @Pattern(regexp = "[0-9]+",message = "Số CMND/Hộ chiếu không hợp lệ")
//    @Size(min = 8, max = 15, message = "Số CMND/Hộ chiếu phải từ {min} đến {max} ký tự")
    private String personalId;
    @NotEmpty(message = "Địa chỉ không được để trống")
    @Size(min = 3, max = 250, message = "Địa chỉ phải từ {min} đến {max} ký tự")
    private String address;
    @NotNull(message = "Ngày bắt đầu không được để trống")
    private Timestamp startDate;
    @NotNull(message = "Quyền lợi bảo hiểm không được để trống")
    private Integer contractType;
    @NotNull(message = "Phí bảo hiểm không được để trống")
    @Range(min = 0, message = "Phí bảo hiểm phải lớn hơn {min}")
    private Float contractFee;

    private Integer captchaFirstNumber;
    private Integer captchaSecondNumber;
    private Integer captchaTotalNumber;

    @AssertTrue(message = "Ngày bắt đầu phải kể từ ngày hôm nay trở đi")
    private boolean isValidStartDate() {
        if (startDate != null) {
            Timestamp currentDate = DateUtils.currentDateWithoutTime();
            return !startDate.before(currentDate);
        }
        return false;
    }

    @AssertTrue(message = "Ngày bắt đầu không được sau thời gian quy định")
    private boolean isValidStartDateMax() {
        if (startDate != null) {
            ConfigUtils configUtils = new ConfigUtils();
            Timestamp startDateMax = new Timestamp(configUtils.getStartDateMax().toDateTimeAtStartOfDay().getMillis());
            return !startDate.after(startDateMax);
        }
        return false;
    }

    @AssertTrue(message = "Số CMND/Hộ chiếu không hợp lệ")
    public boolean isValidPersonalId() {
        if (personalId == null || personalId.isEmpty()) {
            return true;
        } else {
            if (personalId.length() > 7 && personalId.length() < 16) {
                java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("[0-9]+");
                return pattern.matcher(personalId).matches();
            }
            return false;
        }
    }

    @AssertTrue(message = "Email đã được đăng ký bởi người dùng khác")
    public boolean isValidEmail() {
        CustomerDao customerDao = new CustomerDao();
        return !customerDao.isExistByEmail(email);
    }

    @AssertTrue(message = "Trả lời câu hỏi bảo mật không chính xác")
    public boolean isCaptchaValid() {
        return captchaFirstNumber != null
                && captchaSecondNumber != null
                && captchaTotalNumber != null
                && captchaFirstNumber + captchaSecondNumber == captchaTotalNumber;
    }

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

    public Integer getCaptchaFirstNumber() {
        return captchaFirstNumber;
    }

    public void setCaptchaFirstNumber(Integer captchaFirstNumber) {
        this.captchaFirstNumber = captchaFirstNumber;
    }

    public Integer getCaptchaSecondNumber() {
        return captchaSecondNumber;
    }

    public void setCaptchaSecondNumber(Integer captchaSecondNumber) {
        this.captchaSecondNumber = captchaSecondNumber;
    }

    public Integer getCaptchaTotalNumber() {
        return captchaTotalNumber;
    }

    public void setCaptchaTotalNumber(Integer captchaTotalNumber) {
        this.captchaTotalNumber = captchaTotalNumber;
    }

    public PublicHomeFormDto() {
    }

    public PublicHomeFormDto(String name, String email, String phone, String personalId, String address,
                             Timestamp startDate, Integer contractType, Float contractFee) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.personalId = personalId;
        this.address = address;
        this.startDate = startDate;
        this.contractType = contractType;
        this.contractFee = contractFee;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
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
}
