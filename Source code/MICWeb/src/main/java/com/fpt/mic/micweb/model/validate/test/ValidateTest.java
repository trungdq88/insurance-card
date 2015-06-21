package com.fpt.mic.micweb.model.validate.test;

import com.fpt.mic.micweb.model.validate.common.ValidateList;
import com.fpt.mic.micweb.model.validate.validator.LengthValidator;
import com.fpt.mic.micweb.model.validate.validator.RegexValidator;
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

        ValidateList validateList2 = new ValidateList("Tên", "KH0123");
        validateList
                .add(new RegexValidator("^KH([0-9A-Z]{4})$"));

        System.out.println(validateList.isValid());
        System.out.println(validateList.getMessages());
        System.out.println(validateList2.isValid());
        System.out.println(validateList2.getMessages());
    }
}
