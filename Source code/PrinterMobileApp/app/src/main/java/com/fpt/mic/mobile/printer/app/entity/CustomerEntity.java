package com.fpt.mic.mobile.printer.app.entity;

import java.util.Collection;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/5/15.
 */
public class CustomerEntity {
    public String customerCode;
    public String name;
    public String address;
    public String email;
    public String phone;
    public String personalId;
    public String password;
    public Collection<Object> micContractsByCustomerCode;
    public Collection<Object> micNewCardRequestsByCustomerCode;

}
