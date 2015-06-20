package com.fpt.mic.micweb.controller.test;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JsonString;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.model.validate.common.ValidateList;
import com.fpt.mic.micweb.model.validate.validator.DuplicateValidator;
import com.fpt.mic.micweb.model.validate.validator.LengthValidator;
import com.fpt.mic.micweb.model.validate.validator.RequireValidator;

import javax.servlet.annotation.WebServlet;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/19/15.
 */
@WebServlet(name = "TestValidate", urlPatterns =  {"/testvalidate"})
public class TestValidate extends BasicController {
    public ResponseObject getView(R r) {
        String myName = "Đinh Quang Trung";

        ValidateList validateList = new ValidateList("Tên", myName);
        validateList
                .add(new RequireValidator())
                .add(new LengthValidator(10, 20))
                .add(new DuplicateValidator<CustomerEntity, String>(CustomerEntity.class, "getName"));

        return new JsonString(validateList.isValid() + " : " + validateList.getMessages());
    }
}
