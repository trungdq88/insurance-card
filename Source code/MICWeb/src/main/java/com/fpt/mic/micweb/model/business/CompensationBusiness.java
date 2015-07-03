package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.CompensationDao;
import com.fpt.mic.micweb.model.entity.CompensationEntity;

import java.util.List;

/**
 * Created by Kha on 03/07/2015.
 */
public class CompensationBusiness {

    public List<CompensationEntity> getAllCompensation(int offset, int count) {
        CompensationDao compensationDao = new CompensationDao();
        return compensationDao.getAllCompensation(offset, count);
    }

    public Long getAllCompensationCount() {
        CompensationDao compensationDao = new CompensationDao();
        return compensationDao.getAllCompensationCount();
    }

    public List<CompensationEntity> getCompensationByContractCode(String code) {
        CompensationDao compensationDao = new CompensationDao();
        return compensationDao.getCompensationByContractCode(code);
    }
}
