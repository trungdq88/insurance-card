package com.fpt.mic.micweb.model.dao;

import com.fpt.mic.micweb.model.dao.common.GenericDaoJpaImpl;
import com.fpt.mic.micweb.model.dao.common.IncrementDao;
import com.fpt.mic.micweb.model.entity.CustomerEntity;

/**
 * Created by TriPQMSE60746 on 06/05/2015.
 */
public class CustomerDao extends IncrementDao<CustomerEntity, String> {

    @Override
    protected String getCodePrefix() {
        return "KH";
    }
}
