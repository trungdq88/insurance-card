package com.fpt.mic.mobile.printer.app.entity;

import java.util.Collection;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/5/15.
 */
public class StaffEntity {
    public String staffCode;
    public String password;
    public String name;
    public String email;
    public String phone;
    public Collection<ContractEntity> micContractsByStaffCode;

}
