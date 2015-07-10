package com.fpt.mic.micweb.model.dto.form;

import com.fpt.mic.micweb.model.dao.CustomerDao;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Created by PhucNguyen on 10/07/2015.
 */
public class EditCustomerProfileDto {
    private String customerCode;
    @NotEmpty(message = "Địa chỉ không được để trống")
    @Size(min = 3, max = 250, message = "Địa chỉ phải từ {min} đến {max} ký tự")
    private String address;
    @NotEmpty(message = "Email không được để trống")
    @Pattern(regexp = "^([a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3})$", message = "Email không hợp lệ")
    @Size(min = 3, max = 250, message = "Email phải từ {min} đến {max} ký tự")
    private String email;
    @NotEmpty(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "[0-9]+", message = "Số điện thoại không hợp lệ")
    @Size(min = 8, max = 15, message = "Số điện thoại phải từ {min} đến {max} ký tự")
    private String phone;
    private Timestamp lastModified;

    private String personalID;

    @AssertTrue(message = "Email này đã được sử dụng")
    private boolean isExistedByEmail() {
        CustomerDao customerDao = new CustomerDao();
        return !customerDao.isExistByEmailCustomer(email ,customerCode);
    }
    @AssertTrue(message = "Thông tin khách hàng đã bị sửa đổi trước đó bởi một người khác, vui lòng thực hiện lại thao tác")
    private boolean isCustomerNotChanged() {
        if (this.lastModified != null && this.customerCode != null) {
            CustomerDao customerDao = new CustomerDao();
            CustomerEntity customerEntity = customerDao.read(customerCode);
            return customerEntity.getLastModified().equals(lastModified);
        } else {
            return false;
        }
    }
    @AssertTrue(message = "Số CMND/Hộ chiếu phải có độ dài từ 8 tới 15 ký tự")
    private boolean isPersonalIDValid() {
        if (personalID == null || personalID.isEmpty()) {
            return true; // Nullable
        } else {
            return personalID.length() >= 8 && personalID.length() <= 15;
        }
    }

    public EditCustomerProfileDto() {
    }



    public EditCustomerProfileDto(String address, String email, String phone, String personalID, String customerCode,Timestamp lastModified) {
        this.customerCode = customerCode;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.personalID = personalID;
        this.lastModified = lastModified;

    }
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerCode() {

        return customerCode;
    }
    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    public Timestamp getLastModified() {

        return lastModified;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getPersonalID() {
        return personalID;
    }

    public void setPersonalID(String personalID) {
        this.personalID = personalID;
    }
}
