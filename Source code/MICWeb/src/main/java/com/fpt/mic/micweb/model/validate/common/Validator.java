package com.fpt.mic.micweb.model.validate.common;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/19/15.
 *
 * Class Validator
 * This is an abstract class for validation
 */
public abstract class Validator {
    protected String fieldName;
    protected String subject;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Check if the validation rule is valid
     */
    public abstract boolean isValid();

    /**
     * Return error message if the validation rile is invalid
     */
    public abstract String getMessage();
}
