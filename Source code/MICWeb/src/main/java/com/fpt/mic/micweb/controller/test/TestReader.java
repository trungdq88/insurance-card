package com.fpt.mic.micweb.controller.test;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.dto.form.RegisterFormDto;

import javax.servlet.annotation.WebServlet;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/21/15.
 */
@WebServlet(name = "TestReader", urlPatterns = {"/testreader"})
public class TestReader extends BasicController {
    public ResponseObject getView(R r) {
         return new JspPage("/test/reader/view.jsp");
    }
    public ResponseObject postRegister(R r) {
        // Read value from JSP page
        RegisterFormDto dto = (RegisterFormDto) r.ead.entity(RegisterFormDto.class, "register");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<RegisterFormDto>> validate = validator.validate(dto);
        StringBuilder message = new StringBuilder();
        for (ConstraintViolation<RegisterFormDto> violator : validate) {
            message.append(violator.getMessage()).append("<br/>");
        }

        r.equest.setAttribute("errors", message);
        if (validate.size() == 0) {
            r.equest.setAttribute("success", true);
        }

        return new JspPage("/test/reader/view.jsp");
    }
}
