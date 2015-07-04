package com.fpt.mic.micweb.model.dto.form;

import com.fpt.mic.micweb.model.dao.StaffDao;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by TriPQM on 07/03/2015.
 */
public class CreateStaffDto {
    @NotEmpty(message = "Mã nhân viên không được để trống")
    @Pattern(regexp = "^([^0-9`~!@#$%^&*,.<>;':/|{}()=_+-]+)$", message = "Mã nhân viên không hợp lệ")
    @Size(min = 4, max = 10, message = "Mã nhân viên phải từ {min} đến {max} ký tự")
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
    @NotEmpty(message = "Mật khẩu không được để trống")
    @Size(min = 3, max = 80, message = "Mật khẩu phải từ {min} đến {max} ký tự")
    private String password;
    @AssertTrue(message = "Email này đã được sử dụng")
    private boolean isExistedByEmail() {
        StaffDao staffDao= new StaffDao();
        return !staffDao.isExistByEmail(email);
    }
    @AssertTrue(message = "Mã nhân viên đã tồn tại")
    private boolean isExistedByCode() {
        StaffDao staffDao= new StaffDao();
        return !staffDao.isExistByCode(staffCode);
    }

    public CreateStaffDto() {
    }

    public CreateStaffDto(String staffCode, String name, String email, String phone, String password) {
        this.staffCode = staffCode;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
