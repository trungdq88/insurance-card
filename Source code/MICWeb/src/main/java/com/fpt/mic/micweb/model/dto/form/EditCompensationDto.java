package com.fpt.mic.micweb.model.dto.form;

import com.fpt.mic.micweb.model.dao.CompensationDao;
import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.utils.DateUtils;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Created by Kha on 04/07/2015.
 */
public class EditCompensationDto {
    @NotEmpty(message = "Mã yêu cầu bồi thường không được để trống")
    @Pattern(regexp = "^BT([0-9A-Z]{4,8})$", message = "Mã yêu cầu bồi thường không hợp lệ")
    // Mã yêu cầu bồi thường không tồn tại: @see {@link isCompensationCodeNotExisted}
    private String compensationCode;
    @NotEmpty(message = "Mã hợp đồng không được để trống")
    @Pattern(regexp = "^HD([0-9A-Z]{4,8})$", message = "Mã hợp đồng không hợp lệ")
    // Mã hợp đồng không tồn tại: @see {@link isContractCodeNotExisted}
    private String contractCode;
    @NotNull(message = "Ngày gởi yêu cầu không được để trống")
    private Timestamp createdDate;
    @NotEmpty(message = "Họ tên lái xe không được để trống")
    @Pattern(regexp = "^([^0-9`~!@#$%^&*,.<>;':/|{}()=_+-]+)$", message = "Họ tên lái xe không hợp lệ")
    @Size(min = 3, max = 80, message = "Họ tên lái xe phải từ {min} đến {max} ký tự")
    private String driverName;
    @NotEmpty(message = "Giấy phép lái xe không được để trống")
    @Size(min = 10, max = 15, message = "Giấy phép lái xe phải từ {min} đến {max} ký tự")
    private String licenseNumber;
    @NotEmpty(message = "Hạng GPLX không được để trống")
    @Size(min = 1, max = 15, message = "Hạng GPLX phải từ {min} đến {max} ký tự")
    private String licenseType;
    @NotEmpty(message = "Địa chỉ liên hệ không được để trống")
    @Size(min = 3, max = 250, message = "Địa chỉ liên hệ phải từ {min} đến {max} ký tự")
    private String driverAddress;
    @NotEmpty(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "[0-9]+", message = "Số điện thoại không hợp lệ")
    @Size(min = 8, max = 15, message = "Số điện thoại phải từ {min} đến {max} ký tự")
    private String driverPhone;
    @NotEmpty(message = "Biển số xe gây tai nạn không được để trống")
    @Size(min = 4, max = 15, message = "Biển số xe gây tai nạn phải có từ {min} đến {max} ký tự")
    private String plate;
    @NotEmpty(message = "Trọng tải/số chỗ ngồi không được để trống")
    @Size(min = 1, max = 20, message = "Trọng tải/số chỗ ngồi phải có từ {min} đến {max} ký tự")
    private String vehicleCapacity;
    @NotNull(message = "Ngày xảy ra tai nạn không được để trống")
    private Timestamp accidentDate;
    @NotEmpty(message = "Nơi xảy ra tai nạn không được để trống")
    @Size(min = 3, max = 250, message = "Nơi xảy ra tai nạn phải từ {min} đến {max} ký tự")
    private String accidentPlace;
    @NotEmpty(message = "Cơ quan công an giải quyết tai nạn không được để trống")
    @Size(min = 3, max = 250, message = "Cơ quan công an giải quyết tai nạn phải từ {min} đến {max} ký tự")
    private String controlDepartment;
    @NotEmpty(message = "Diễn biến và nguyên nhân tai nạn không được để trống")
    @Size(min = 1, max = 2000, message = "Diễn biến và nguyên nhân tai nạn phải từ {min} đến {max} ký tự")
    private String description;
    @NotEmpty(message = "Tình hình thiệt hại về người không được để trống")
    @Size(min = 1, max = 2000, message = "Tình hình thiệt hại về người phải từ {min} đến {max} ký tự")
    private String humanDamage;
    @NotEmpty(message = "Tình hình thiệt hại về tài sản không được để trống")
    @Size(min = 1, max = 2000, message = "Tình hình thiệt hại về tài sản phải từ {min} đến {max} ký tự")
    private String assetDamage;
    @NotEmpty(message = "Người làm chứng không được để trống")
    @Pattern(regexp = "^([^0-9`~!@#$%^&*,.<>;':/|{}()=_+-]+)$", message = "Người làm chứng không hợp lệ")
    @Size(min = 3, max = 80, message = "Người làm chứng phải từ {min} đến {max} ký tự")
    private String observer;
    @NotEmpty(message = "Địa chỉ người làm chứng không được để trống")
    @Size(min = 3, max = 250, message = "Địa chỉ người làm chứng phải từ {min} đến {max} ký tự")
    private String observerAddress;
    private String compensationNote;
    private String attachment;
    private Timestamp resolveDate;
    private String decision;
    private String resolveNote;
    private Timestamp lastModified;

    @AssertTrue(message = "Mã yêu cầu bồi thường không tồn tại")
    private boolean isCompensationCodeNotExisted() {
        CompensationDao compensationDao = new CompensationDao();
        return compensationCode != null && compensationDao.read(compensationCode) != null;
    }

    @AssertTrue(message = "Mã hợp đồng không tồn tại")
    private boolean isContractCodeNotExisted() {
        ContractDao contractDao = new ContractDao();
        return contractCode != null && contractDao.read(contractCode) != null;
    }

    @AssertTrue(message = "Ngày gởi yêu cầu không được sau ngày hiện tại")
    private boolean isValidCreatedDate() {
        if (createdDate != null) {
            return !createdDate.after(DateUtils.currentDateWithoutTime());
        }
        return false;
    }

    @AssertTrue(message = "Thời điểm xảy ra tai nạn không được sau ngày gởi yêu cầu")
    private boolean isValidAccidentDate() {
        if (accidentDate != null) {
            return !accidentDate.after(createdDate);
        }
        return false;
    }

    @AssertTrue(message = "Yêu cầu bồi thường và đề xuất khác phải có độ dài từ 1 đến 2000 ký tự")
    private boolean isCompensationNoteValid() {
        if (compensationNote == null || compensationNote.isEmpty()) {
            return true; // Vẫn chấp nhận null hoặc rỗng
        } else {
            // Nếu có giá trị thì phải có độ dài đúng requirement
            return compensationNote.length() >= 1 && compensationNote.length() <= 2000;
        }
    }

    @AssertTrue(message = "Đường dẫn tới văn bản đính kèm phải có độ dài từ 1 đến 255 ký tự")
    private boolean isAttachmentValid() {
        if (attachment == null || attachment.isEmpty()) {
            return true; // Vẫn chấp nhận null hoặc rỗng
        } else {
            // Nếu có giá trị thì phải có độ dài đúng requirement
            return attachment.length() >= 1 && attachment.length() <= 255;
        }
    }

    @AssertTrue(message = "Quyết định của công ty phải có độ dài từ 3 đến 250 ký tự")
    private boolean isDecisionValid() {
        if (decision == null || decision.isEmpty()) {
            return true; // Vẫn chấp nhận null hoặc rỗng
        } else {
            // Nếu có giá trị thì phải có độ dài đúng requirement
            return decision.length() >= 2 && decision.length() <= 2000;
        }
    }

    @AssertTrue(message = "Ghi chú của công ty phải có độ dài từ 1 đến 2000 ký tự")
    private boolean isResolveNoteValid() {
        if (resolveNote == null || resolveNote.isEmpty()) {
            return true; // Vẫn chấp nhận null hoặc rỗng
        } else {
            // Nếu có giá trị thì phải có độ dài đúng requirement
            return resolveNote.length() >= 1 && resolveNote.length() <= 2000;
        }
    }

    public EditCompensationDto() {
    }

    public EditCompensationDto(String compensationCode, String contractCode, Timestamp createdDate, String driverName,
                               String licenseNumber, String licenseType, String driverAddress, String driverPhone,
                               String plate, String vehicleCapacity, Timestamp accidentDate, String accidentPlace,
                               String controlDepartment, String description, String humanDamage, String assetDamage,
                               String observer, String observerAddress, String compensationNote, String attachment,
                               Timestamp resolveDate, String decision, String resolveNote, Timestamp lastModified) {
        this.compensationCode = compensationCode;
        this.contractCode = contractCode;
        this.createdDate = createdDate;
        this.driverName = driverName;
        this.licenseNumber = licenseNumber;
        this.licenseType = licenseType;
        this.driverAddress = driverAddress;
        this.driverPhone = driverPhone;
        this.plate = plate;
        this.vehicleCapacity = vehicleCapacity;
        this.accidentDate = accidentDate;
        this.accidentPlace = accidentPlace;
        this.controlDepartment = controlDepartment;
        this.description = description;
        this.humanDamage = humanDamage;
        this.assetDamage = assetDamage;
        this.observer = observer;
        this.observerAddress = observerAddress;
        this.compensationNote = compensationNote;
        this.attachment = attachment;
        this.resolveDate = resolveDate;
        this.decision = decision;
        this.resolveNote = resolveNote;
        this.lastModified = lastModified;
    }

    public Timestamp getCreatedDate() {
        CompensationDao compensationDao = new CompensationDao();
        return compensationDao.read(compensationCode).getCreatedDate();
    }

    public String getCompensationCode() {
        return compensationCode;
    }

    public void setCompensationCode(String compensationCode) {
        this.compensationCode = compensationCode;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public String getDriverAddress() {
        return driverAddress;
    }

    public void setDriverAddress(String driverAddress) {
        this.driverAddress = driverAddress;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getVehicleCapacity() {
        return vehicleCapacity;
    }

    public void setVehicleCapacity(String vehicleCapacity) {
        this.vehicleCapacity = vehicleCapacity;
    }

    public Timestamp getAccidentDate() {
        return accidentDate;
    }

    public void setAccidentDate(Timestamp accidentDate) {
        this.accidentDate = accidentDate;
    }

    public String getAccidentPlace() {
        return accidentPlace;
    }

    public void setAccidentPlace(String accidentPlace) {
        this.accidentPlace = accidentPlace;
    }

    public String getControlDepartment() {
        return controlDepartment;
    }

    public void setControlDepartment(String controlDepartment) {
        this.controlDepartment = controlDepartment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHumanDamage() {
        return humanDamage;
    }

    public void setHumanDamage(String humanDamage) {
        this.humanDamage = humanDamage;
    }

    public String getAssetDamage() {
        return assetDamage;
    }

    public void setAssetDamage(String assetDamage) {
        this.assetDamage = assetDamage;
    }

    public String getObserver() {
        return observer;
    }

    public void setObserver(String observer) {
        this.observer = observer;
    }

    public String getObserverAddress() {
        return observerAddress;
    }

    public void setObserverAddress(String observerAddress) {
        this.observerAddress = observerAddress;
    }

    public String getCompensationNote() {
        return compensationNote;
    }

    public void setCompensationNote(String compensationNote) {
        this.compensationNote = compensationNote;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Timestamp getResolveDate() {
        return resolveDate;
    }

    public void setResolveDate(Timestamp resolveDate) {
        this.resolveDate = resolveDate;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getResolveNote() {
        return resolveNote;
    }

    public void setResolveNote(String resolveNote) {
        this.resolveNote = resolveNote;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }
}