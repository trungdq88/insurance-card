package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.CardInstanceDao;
import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dto.NotificationBuilder;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.helper.NotificationEntity;
import com.fpt.mic.micweb.utils.ConfigUtils;
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

            NotificationBusiness bus = new NotificationBusiness();

            int type = 0;
            ConfigUtils configUtils = new ConfigUtils();

            if (DateUtils.dateBetween(currentDate, contractEntity.getExpiredDate()) <= configUtils.getNearlyExceedExpiredOne()) {
                type = NotificationEntity.Type.CONTRACT_NEARLY_EXPIRED_1;
            }
            if (DateUtils.dateBetween(currentDate, contractEntity.getExpiredDate()) <= configUtils.getNearlyExceedExpiredTwo()) {
                type = NotificationEntity.Type.CONTRACT_NEARLY_EXPIRED_2;
            }
            if (DateUtils.dateBetween(currentDate, contractEntity.getExpiredDate()) <= configUtils.getNearlyExceedExpiredThree()) {
                type = NotificationEntity.Type.CONTRACT_NEARLY_EXPIRED_3;
            }
            if (type > 0) {
                bus.send(NotificationBuilder.contractNearlyExpired(contractEntity, type),
                        contractEntity.getMicCustomerByCustomerCode().getEmail());
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

                // Send notification to customer
                NotificationBusiness bus = new NotificationBusiness();
                bus.send(NotificationBuilder.contractExpired(contractEntity),
                        contractEntity.getMicCustomerByCustomerCode().getEmail());

                return true;
            }
        }
            return false;
    }

    public boolean checkIfContractExceedPaymentDueDate(ContractEntity contractEntity) {
        ContractDao contractDao = new ContractDao();
        Timestamp currentDate = DateUtils.currentDateWithoutTime();
        ConfigUtils configUtils = new ConfigUtils();
        // check if Pending contract exceeded payment due date
        if (contractEntity.getStatus().equals(Constants.ContractStatus.PENDING)) {
            if (contractEntity.getStartDate().equals(contractEntity.getExpiredDate())) {
                if (DateUtils.dateBetween(contractEntity.getCreatedDate(), currentDate) > configUtils.getPaymentDueDate()) {
                    contractEntity.setStatus(Constants.ContractStatus.CANCELLED);
                    contractEntity.setCancelDate(currentDate);
                    contractEntity.setCancelReason("Quá ngày thanh toán hợp đồng");
                    contractEntity.setLastModified(new Timestamp(new Date().getTime()));
                    contractDao.update(contractEntity);

                    // Send notification to customer
                    NotificationBusiness bus = new NotificationBusiness();
                    bus.send(NotificationBuilder.contractCancelledNoPayment(contractEntity),
                            contractEntity.getMicCustomerByCustomerCode().getEmail());
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
            if (!(contractEntity.getStartDate().after(currentDate)) // If the date has come
                    && !contractEntity.getStartDate().equals(contractEntity.getExpiredDate()) // And the contract is paid
                    ) {
                CardInstanceDao cardInstanceDao = new CardInstanceDao();
                if (null == cardInstanceDao.getActiveCardInstanceByContract(contractEntity.getContractCode())) {
                    contractEntity.setStatus(Constants.ContractStatus.NO_CARD);
                    contractEntity.setLastModified(new Timestamp(new Date().getTime()));
                    contractDao.update(contractEntity);
                } else {
                    contractEntity.setStatus(Constants.ContractStatus.READY);
                    contractEntity.setLastModified(new Timestamp(new Date().getTime()));
                    contractDao.update(contractEntity);
                }

                // Send notification to customer
                NotificationBusiness bus = new NotificationBusiness();
                bus.send(NotificationBuilder.contractStartDateCome(contractEntity));
                return true;
            }
        }
        return false;
    }
}
