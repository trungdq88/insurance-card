package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dto.ContractSearchResult;
import com.fpt.mic.micweb.model.entity.ContractEntity;

import java.util.List;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/6/15.
 */
public class ApiBusiness {
    /**
     * Search for contract
     * @param keyword could be contract_code or customer name
     * @return List {@link List<ContractEntity>}
     */
    public List<ContractSearchResult> searchContracts(String keyword) {
        ContractDao contractDao = new ContractDao();
        return contractDao.searchContracts(keyword);
    }
}
