package com.fpt.mic.micweb.model.validate.validator;

import com.fpt.mic.micweb.model.validate.common.ValidateLang;
import com.fpt.mic.micweb.model.validate.common.Validator;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/19/15.
 */
public class RequireValidator extends Validator {
    @Override
    public boolean isValid() {
        return subject != null && !subject.isEmpty();
    }

    @Override
    public String getMessage() {
        return String.format(ValidateLang.REQUIRE_VALIDATOR, fieldName);
    }
}
