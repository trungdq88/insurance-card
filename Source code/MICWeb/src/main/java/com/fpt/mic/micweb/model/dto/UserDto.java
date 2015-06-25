package com.fpt.mic.micweb.model.dto;

import com.fpt.mic.micweb.model.dao.CustomerDao;
import com.fpt.mic.micweb.model.dao.StaffDao;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/25/15.
 *
 * This class is the DTO for session to save the logged in user
 * User can be staff or customer
 */
public class UserDto {
    public static String ROLE_CUSTOMER = "customer";
    public static String ROLE_STAFF = "staff";
    private String id;
    private String role;

    public UserDto(String id, String role) {
        this.id = id;
        this.role = role;
    }

    public Object getUserEntity() {
        if (role.equals(ROLE_CUSTOMER)) {
            CustomerDao dao = new CustomerDao();
            return dao.read(id);
        } else if (role.equals(ROLE_STAFF)) {
            StaffDao dao = new StaffDao();
            return dao.read(id);
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
