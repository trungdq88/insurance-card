package com.fpt.mic.micweb.model.dto.form;
import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dao.ContractTypeDao;
import com.fpt.mic.micweb.model.dao.CustomerDao;
import com.fpt.mic.micweb.model.entity.ContractTypeEntity;
import com.fpt.mic.micweb.model.entity.helper.BusinessRulesEntity;
import com.fpt.mic.micweb.utils.ConfigUtils;
import com.fpt.mic.micweb.utils.DateUtils;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.joda.time.LocalDate;

import javax.validation.constraints.*;
import java.sql.Timestamp;
/**
 * Created by TriPQM on 07/02/2015.
 */
public class CustomerCreateContractDto {
    @NotNull(message = "Ngày bắt đầu không được để trống")
    private Timestamp startDate;
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

    @AssertTrue(message = "Ngày bắt đầu hiệu lực hợp đồng không hợp lệ")
    private boolean isValidMaxStartDate() {
        if (startDate != null) {
            ConfigUtils configUtils = new ConfigUtils();
            LocalDate maxDate = configUtils.getStartDateMax();
            Timestamp maxStartDate = new Timestamp(maxDate.toDateTimeAtStartOfDay().getMillis());
            return !startDate.after(maxStartDate);
        }
        return false;
    }
    @AssertTrue(message = "Ngày bắt đầu phải kể từ ngày hôm nay trở đi")
    private boolean isValidStartDate() {
        if (startDate != null) {
            Timestamp currentDate = DateUtils.currentDateWithoutTime();
            return !startDate.before(currentDate);
        }
        return false;
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

    @AssertTrue(message = "Phí bảo hiểm không đúng")
    public boolean isValidContactFee(){
        try {
            ContractTypeDao contractTypeDao = new ContractTypeDao();
            ContractTypeEntity contractTypeEntity = contractTypeDao.read(contractType);
            if (contractFee == contractTypeEntity.getPricePerYear())
                return true;
        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }

    public CustomerCreateContractDto() {
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
