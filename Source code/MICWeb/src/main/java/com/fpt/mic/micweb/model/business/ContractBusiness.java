package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dao.ContractTypeDao;
import com.fpt.mic.micweb.model.dto.ContractSearchResultDto;
import com.fpt.mic.micweb.model.entity.ContractEntity;
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

    public List searchContract(String keyword) {
        ContractDao contractDao = new ContractDao();
        return contractDao.getContractByCodeOrCustomerName(keyword);
    }
}
