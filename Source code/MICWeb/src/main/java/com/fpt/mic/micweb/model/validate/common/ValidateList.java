package com.fpt.mic.micweb.model.validate.common;

import java.util.ArrayList;
import java.util.List;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/19/15.
 */
public class ValidateList {
    String fieldName;
    String subject;
    List<Validator> validators;

    public ValidateList(String fieldName, String subject) {
        this.fieldName = fieldName;
        this.subject = subject;
        this.validators = new ArrayList<Validator>();
    }

    public ValidateList(String fieldName, Integer subject) {
        this.fieldName = fieldName;
        this.subject = subject + "";
        this.validators = new ArrayList<Validator>();
    }

    /**
     * Add validator to validate list
     * @param validator
     * @return
     */
    public ValidateList add(Validator validator) {
        validator.setFieldName(fieldName);
        validator.setSubject(subject);
        this.validators.add(validator);
        return this;
    }

    /**
     * Get list of invalid messages
     * @return
     */
    public List<String> getMessages() {
        List<String> result = new ArrayList<String>();
        for (Validator validator : validators) {
            if (!validator.isValid()) {
                result.add(validator.getMessage());
            }
        }
        return result;
    }

    /**
     * Returns true if all the validator is valid
     * @return
     */
    public boolean isValid() {
        boolean valid = true;
        for (Validator validator : validators) {
            valid = valid && validator.isValid();
        }
        return valid;
    }
}
