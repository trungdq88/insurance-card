package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.CardDao;
import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dao.PaymentDao;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.PaymentEntity;
import com.fpt.mic.micweb.utils.Constants;
import com.fpt.mic.micweb.utils.DateUtils;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by TriPQM on 07/01/2015.
 */
public class SchedulerBusiness {
    public void updateContracts() {
        ContractDao contractDao = new ContractDao();
        List<ContractEntity> contractEntityList = contractDao.getListContract();
        Timestamp currentDate = DateUtils.currentDateWithoutTime();
        for (ContractEntity contractEntity : contractEntityList) {
            // check if contract exceeded renew due date
            if (contractEntity.getStatus().equals(Constants.ContractStatus.EXPIRED)) {
                if (DateUtils.dateBetween(contractEntity.getExpiredDate(),currentDate) > Constants.DueDate.RENEW_DUE_DATE){
                    contractEntity.setStatus(Constants.ContractStatus.CANCELLED);
                    System.out.println("contract cancelled " + contractEntity.getContractCode());
                    contractDao.update(contractEntity);
                }
            }
            // checking READY, NO_CARD and REQUEST_CANCEL contracts
            if (contractEntity.getStatus().equals(Constants.ContractStatus.READY)
                    || contractEntity.getStatus().equals(Constants.ContractStatus.NO_CARD)
                    || contractEntity.getStatus().equals(Constants.ContractStatus.REQUEST_CANCEL)) {
                // check if contract expired
                if (contractEntity.getExpiredDate().before(currentDate)) {
                    contractEntity.setStatus(Constants.ContractStatus.EXPIRED);
                    contractDao.update(contractEntity);
                    System.out.println("contract expired " + contractEntity.getContractCode());
                    // TODO send notification to customer
                }
                // check if contract nearly exceeded expired
                if (DateUtils.dateBetween(currentDate, contractEntity.getExpiredDate()) < 15 ) {
                    // TODO send notification to customer
                }
            }
            // checking PENDING contract
            if (contractEntity.getStatus().equals(Constants.ContractStatus.PENDING)) {
                // check if Pending contract exceeded payment due date
                if ( contractEntity.getStartDate().equals(contractEntity.getExpiredDate())) {
                    // TODO after create registeredDate
                }
                // else check if completed payment pending contract exceeded startDate
                else {
                    if (contractEntity.getStartDate().equals(currentDate) || contractEntity.getStartDate().before(currentDate)) {
                        CardDao cardDao = new CardDao();
                        if ( null == cardDao.getCardByContract(contractEntity.getContractCode())) {
                            contractEntity.setStatus(Constants.ContractStatus.NO_CARD);
                            contractDao.update(contractEntity);
                            System.out.println("Contract no card " +contractEntity.getContractCode());
                        }
                        else {
                            contractEntity.setStatus(Constants.ContractStatus.READY);
                            contractDao.update(contractEntity);
                            System.out.println("Contract ready "+contractEntity.getContractCode());
                        }
                    }
                }
            }
        }
    }
}
