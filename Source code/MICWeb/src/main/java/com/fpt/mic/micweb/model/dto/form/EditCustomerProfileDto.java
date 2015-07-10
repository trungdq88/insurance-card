package com.fpt.mic.micweb.model.dto.form;
import com.fpt.mic.micweb.model.dao.CustomerDao;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
/**
 * Created by PhucNguyen on 10/07/2015.
 */
public class EditCustomerProfileDto {

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
    @NotEmpty(message = "Số chứng minh không được để trống")
    @Pattern(regexp = "[0-9]+", message = "Số chứng minh không hợp lệ")
    @Size(min = 8, max = 15, message = "Số chứng minh phải từ {min} đến {max} ký tự")
    private String personalID;

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

    public EditCustomerProfileDto(String address, String email, String phone, String personalID) {
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.personalID = personalID;
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
