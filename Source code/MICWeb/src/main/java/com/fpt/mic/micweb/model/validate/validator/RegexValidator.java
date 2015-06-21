package com.fpt.mic.micweb.model.validate.validator;

import com.fpt.mic.micweb.model.validate.common.ValidateLang;
import com.fpt.mic.micweb.model.validate.common.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/20/15.
 */
public class RegexValidator extends Validator {
    String regex;

    public RegexValidator(String regex) {
        this.regex = regex;
    }

    @Override
    public boolean isValid() {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(subject);
        return matcher.groupCount() > 0;
    }

    @Override
    public String getMessage() {
        return String.format(ValidateLang.REGEX_VALIDATOR, fieldName);
    }
}
