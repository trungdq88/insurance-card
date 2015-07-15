package com.fpt.mic.micweb.model.dto.form;

/**
 * Created by TriPQM on 07/15/2015.
 */

import com.fpt.mic.micweb.model.dao.StaffDao;
import com.fpt.mic.micweb.model.entity.StaffEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


/**
 * Created by TriPQM on 07/03/2015.
 */
public class EditStaffProfileDto {
    private String staffCode;
    @NotEmpty(message = "Họ tên không được để trống")
    @Pattern(regexp = "^([^0-9`~!@#$%^&*,.<>;':/|{}()=_+-]+)$", message = "Họ tên không hợp lệ")
    @Size(min = 3, max = 80, message = "Họ tên phải từ {min} đến {max} ký tự")
    private String name;
    @NotEmpty(message = "Email không được để trống")
    @Pattern(regexp = "^([a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3})$", message = "Email không hợp lệ")
    @Size(min = 3, max = 250, message = "Email phải từ {min} đến {max} ký tự")
    private String email;
    @NotEmpty(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "[0-9]+", message = "Số điện thoại không hợp lệ")
    @Size(min = 8, max = 15, message = "Số điện thoại phải từ {min} đến {max} ký tự")
    private String phone;

    @AssertTrue(message = "Email này đã được sử dụng")
    private boolean isExistedByEmail() {
        StaffDao staffDao= new StaffDao();
        StaffEntity staffEntity = staffDao.read(staffCode);
        if (staffEntity.getEmail().equals(email)){
            return true;
        }
        return !staffDao.isExistByEmail(email);
    }

    public EditStaffProfileDto() {
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

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }
}

