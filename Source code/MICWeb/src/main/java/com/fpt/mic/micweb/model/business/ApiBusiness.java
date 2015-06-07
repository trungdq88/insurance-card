package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.CardDao;
import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dto.ContractSearchResult;
import com.fpt.mic.micweb.model.entity.CardEntity;
import com.fpt.mic.micweb.model.entity.ContractEntity;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
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

    public boolean updateCardID(String contractCode, String cardID) {
        CardDao cardDao = new CardDao();
        ContractDao contractDao = new ContractDao();

        // Validate contract code, card ID


        // Insert to database
        CardEntity cardEntity = new CardEntity();
        cardEntity.setContractCode(contractCode);
        cardEntity.setCardId(cardID);
        cardEntity.setActivatedDate(new Timestamp(new Date().getTime()));

        CardEntity result = cardDao.create(cardEntity);

        if (result != null) {
            // Change contract status
            ContractEntity contract = contractDao.read(contractCode);
            contract.setStatus(ContractEntity.STATUS_READY);
            contractDao.update(contract);
        }

        return result != null;
    }
}
