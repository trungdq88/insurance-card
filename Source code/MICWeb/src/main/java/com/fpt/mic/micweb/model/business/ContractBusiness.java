package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.ContractTypeDao;
import com.fpt.mic.micweb.model.entity.ContractTypeEntity;

import java.util.List;

/**
 * Created by TriPQMse60746 on 06/11/2015.
 */
public class ContractBusiness {
    public List<ContractTypeEntity> getAllContractType() {
        ContractTypeDao contractTypeDao = new ContractTypeDao();
        return contractTypeDao.getAllContractType();
    }
}
