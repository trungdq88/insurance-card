package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.CardDao;
import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.utils.Constants;
import com.fpt.mic.micweb.utils.DateUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by TriPQM on 07/01/2015.
 */
public class SchedulerBusiness {
    public void updateContracts() {
        ContractDao contractDao = new ContractDao();
        List<ContractEntity> contractEntityList = contractDao.getListContract();

        for (ContractEntity contractEntity : contractEntityList) {
            boolean isNotChanged = contractEntity.getLastModified().equals(contractDao.read(contractEntity.getContractCode()).getLastModified());
            do {
                if (isNotChanged) {
                    // check if contract is expired
                    if(!checkIfContractExpired(contractEntity)){
                        // if not, check if contract is nearly expired
                        if(!checkIfContractNearlyExpired(contractEntity)){
                            // if not, check if contract if exceeded payment due date
                            if(!checkIfContractExceedPaymentDueDate(contractEntity)){
                                // check if contract is started.
                                checkIfPendingContractStart(contractEntity);
                            }
                        }
                    }
                } else {
                    contractEntity = contractDao.read(contractEntity.getContractCode());
                    isNotChanged = contractEntity.getLastModified().equals(contractDao.read(contractEntity.getContractCode()).getLastModified());
                }
            } while (!isNotChanged);
        }
    }
    public boolean checkIfContractNearlyExpired(ContractEntity contractEntity) {
        ContractDao contractDao = new ContractDao();
        Timestamp currentDate = DateUtils.currentDateWithoutTime();
        // checking READY, NO_CARD and REQUEST_CANCEL contracts
        if (contractEntity.getStatus().equals(Constants.ContractStatus.READY)
                || contractEntity.getStatus().equals(Constants.ContractStatus.NO_CARD)
                || contractEntity.getStatus().equals(Constants.ContractStatus.REQUEST_CANCEL)){
            // check if contract nearly exceeded expired (3)
            if (DateUtils.dateBetween(currentDate, contractEntity.getExpiredDate()) < Constants.DueDate.NEARLY_EXCEED_EXPIRED) {
                // TODO send notification to customer
                System.out.println("Nearly expired: "+contractEntity.getContractCode());
                return true;
            }
        }
        return false;
    }
    public boolean checkIfContractExpired(ContractEntity contractEntity) {
        ContractDao contractDao = new ContractDao();
        Timestamp currentDate = DateUtils.currentDateWithoutTime();
        // checking READY, NO_CARD and REQUEST_CANCEL contracts
        if (contractEntity.getStatus().equals(Constants.ContractStatus.READY)
                || contractEntity.getStatus().equals(Constants.ContractStatus.NO_CARD)
                || contractEntity.getStatus().equals(Constants.ContractStatus.REQUEST_CANCEL)) {
            // check if contract expired (2)
            if (contractEntity.getExpiredDate().before(currentDate)) {
                contractEntity.setStatus(Constants.ContractStatus.EXPIRED);
                contractEntity.setLastModified(new Timestamp(new Date().getTime()));
                contractDao.update(contractEntity);
                // TODO send notification to customer
                return true;
            }
        }
            return false;
    }

    public boolean checkIfContractExceedPaymentDueDate(ContractEntity contractEntity) {
        ContractDao contractDao = new ContractDao();
        Timestamp currentDate = DateUtils.currentDateWithoutTime();
        // check if Pending contract exceeded payment due date
        if (contractEntity.getStatus().equals(Constants.ContractStatus.PENDING)) {
            if (contractEntity.getStartDate().equals(contractEntity.getExpiredDate())) {
                if (DateUtils.dateBetween(contractEntity.getCreatedDate(), currentDate) > Constants.DueDate.PAYMENT_DUE_DATE) {
                    contractEntity.setStatus(Constants.ContractStatus.CANCELLED);
                    contractEntity.setCancelDate(currentDate);
                    contractEntity.setCancelReason("Quá ngày thanh toán hợp đồng");
                    contractEntity.setLastModified(new Timestamp(new Date().getTime()));
                    contractDao.update(contractEntity);
                    // TODO notify
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkIfPendingContractStart(ContractEntity contractEntity) {
        ContractDao contractDao = new ContractDao();
        Timestamp currentDate = DateUtils.currentDateWithoutTime();
        // check if completed payment pending contract exceeded startDate
        if (contractEntity.getStatus().equals(Constants.ContractStatus.PENDING)) {
            if (!(contractEntity.getStartDate().after(currentDate))) {
                CardDao cardDao = new CardDao();
                if (null == cardDao.getCardByContract(contractEntity.getContractCode())) {
                    contractEntity.setStatus(Constants.ContractStatus.NO_CARD);
                    contractEntity.setLastModified(new Timestamp(new Date().getTime()));
                    contractDao.update(contractEntity);
                } else {
                    contractEntity.setStatus(Constants.ContractStatus.READY);
                    contractEntity.setLastModified(new Timestamp(new Date().getTime()));
                    contractDao.update(contractEntity);
                }
                return true;
            }
        }
        return false;
    }
}
