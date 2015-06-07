package com.fpt.mic.micweb.model.dao.common;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/6/15.
 */

import com.fpt.mic.micweb.model.dao.helper.IncrementsDao;
import com.fpt.mic.micweb.model.entity.helper.IncrementsEntity;
import com.fpt.mic.micweb.utils.NumberUtils;

import java.io.Serializable;

/**
 * This class is used to manage entities that have increment code (ex: contract_code, staff_code...)
 */
public abstract class IncrementDao<T, PK extends Serializable> extends GenericDaoJpaImpl<T, PK> {

    protected abstract String getCodePrefix();

    /**
     * Generate new unique ID for new entity
     * @return
     */
    public String getIncrementId() {
        int nextId;
        IncrementsDao incrementsDao = new IncrementsDao();
        IncrementsEntity increment = incrementsDao.read(getCodePrefix());
        if (increment == null) {
            nextId = 1;
            increment = new IncrementsEntity();
            increment.setIncrement(nextId);
            increment.setTableName(getCodePrefix());
            incrementsDao.create(increment);
        } else {
            nextId = increment.getIncrement() + 1;
            increment.setIncrement(nextId);
            incrementsDao.update(increment);
        }

        String id = NumberUtils.base10to36(nextId);

        // Add leading zeros;
        while (id.length() < 4) id = "0" + id;

        return getCodePrefix() + id;
    }
}
