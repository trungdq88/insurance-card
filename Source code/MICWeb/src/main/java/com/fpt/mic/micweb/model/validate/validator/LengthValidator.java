package com.fpt.mic.micweb.model.validate.validator;

import com.fpt.mic.micweb.model.validate.common.ValidateLang;
import com.fpt.mic.micweb.model.validate.common.Validator;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/19/15.
 */
public class LengthValidator extends Validator {
    int minLength;
    int maxLength;

    public LengthValidator() {
    }

    public LengthValidator(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public boolean isValid() {
        return subject.length() > minLength && subject.length() < maxLength;
    }

    @Override
    public String getMessage() {
        return String.format(ValidateLang.LENGTH_VALIDATOR, fieldName, minLength, maxLength);
    }
}
