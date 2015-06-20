package com.fpt.mic.micweb.model.validate.test;

import com.fpt.mic.micweb.model.validate.common.ValidateList;
import com.fpt.mic.micweb.model.validate.validator.LengthValidator;
import com.fpt.mic.micweb.model.validate.validator.RequireValidator;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/19/15.
 */
public class ValidateTest {
    public static void main(String[] args) {
        String myName = "Đinh Quang Trung";

        ValidateList validateList = new ValidateList("Tên", myName);
        validateList
                .add(new RequireValidator())
                .add(new LengthValidator(5, 10));
                // Check Controller TestValidate to see the test for DuplicateValidator
                //.add(new DuplicateValidator<CustomerEntity, String>(CustomerEntity.class, "name"));


        System.out.println(validateList.isValid());
        System.out.println(validateList.getMessages());
    }
}
