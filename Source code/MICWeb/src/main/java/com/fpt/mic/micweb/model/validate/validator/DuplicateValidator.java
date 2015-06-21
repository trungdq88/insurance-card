package com.fpt.mic.micweb.model.validate.validator;

import com.fpt.mic.micweb.model.dao.helper.ValidatorDao;
import com.fpt.mic.micweb.model.validate.common.ValidateLang;
import com.fpt.mic.micweb.model.validate.common.Validator;

import java.io.Serializable;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/19/15.
 */
public class DuplicateValidator<T, PK extends Serializable> extends Validator {

    /**
     * Method in entity class used to compare, for example: "getName"
     */
    String compareMethod;
    Class<T> type;

    public DuplicateValidator(Class<T> compareEntity, String compareMethod) {
        this.compareMethod = compareMethod;
        this.type = compareEntity;
    }

    @Override
    public boolean isValid() {
        ValidatorDao<T, PK> dao = new ValidatorDao<T, PK>(type);
        return !dao.isExists(compareMethod, subject);
    }

    @Override
    public String getMessage() {
        return String.format(ValidateLang.DUPLICATE_VALIDATOR, fieldName, subject);
    }
}
