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
    public Long getAllCompensationByContractCodeCount(String code) {
        CompensationDao compensationDao = new CompensationDao();
        return compensationDao.getAllCompensationByContractCodeCount(code);
    }
    public List<CompensationEntity> getCompensationByContractCode(String code) {
        CompensationDao compensationDao = new CompensationDao();
        return compensationDao.getCompensationByContractCode(code);
    }
    public List getAllCompensationByContractCode(String code, int offset, int count) {
        CompensationDao compensationDao = new CompensationDao();
        return compensationDao.getCompensationByContractCode(code, offset, count);
    }
}
