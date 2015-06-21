package com.fpt.mic.micweb.controller.test;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JsonString;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.model.validate.common.ValidateList;
import com.fpt.mic.micweb.model.validate.validator.DuplicateValidator;
import com.fpt.mic.micweb.model.validate.validator.LengthValidator;
import com.fpt.mic.micweb.model.validate.validator.RequireValidator;

import javax.servlet.annotation.WebServlet;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/19/15.
 */
@WebServlet(name = "TestValidate", urlPatterns =  {"/testvalidate"})
public class TestValidate extends BasicController {
    public ResponseObject getView(R r) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        ContractEntity contractEntity = new ContractEntity();
        contractEntity.setPlate("123");

        Set<ConstraintViolation<ContractEntity>> validate = validator.validate(contractEntity);
        List<String> message = new ArrayList<String>();
        for (ConstraintViolation<ContractEntity> violator : validate) {
            message.add(violator.getMessage());
        }
        return new JsonString(validate.size() + " : " + message);

    }

    public ResponseObject getMyValidate(R r) {
        String myName = "Đinh Quang Trung";

        ValidateList validateList = new ValidateList("Tên", myName);
        validateList
                .add(new RequireValidator())
                .add(new LengthValidator(10, 20))
                .add(new DuplicateValidator<CustomerEntity, String>(CustomerEntity.class, "getName"));

        return new JsonString(validateList.isValid() + " : " + validateList.getMessages());
    }
}
