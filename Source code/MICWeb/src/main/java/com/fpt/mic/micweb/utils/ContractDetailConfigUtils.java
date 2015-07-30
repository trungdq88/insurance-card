package com.fpt.mic.micweb.utils;

import com.fpt.mic.micweb.model.entity.ContractEntity;
import org.joda.time.Days;
import org.joda.time.LocalDate;

/**
 * Created by Kha on 30/07/2015.
 */
public class ContractDetailConfigUtils {
    private ContractEntity contractEntity;
    private String displayRemain;
    private boolean renewable;
    private boolean cancelable;

    public ContractDetailConfigUtils() {
    }

    public ContractDetailConfigUtils(ContractEntity contractEntity) {
        this.contractEntity = contractEntity;
    }

    public int getRemainDays() {
        return Days.daysBetween(new LocalDate(), new LocalDate(contractEntity.getExpiredDate())).getDays();
    }

    public String getDisplayRemain() {
        return displayRemain = getRemainDays() + " ngày";
    }

    public boolean isRenewable() {
        ConfigUtils configUtils = new ConfigUtils();
        if (contractEntity.getStatus().equalsIgnoreCase(Constants.ContractStatus.NO_CARD) ||
                contractEntity.getStatus().equalsIgnoreCase(Constants.ContractStatus.READY)) {
            if (getRemainDays() <= configUtils.getContractRenewLimit()) {
                return true;
            }
        }
        if (contractEntity.getStatus().equalsIgnoreCase(Constants.ContractStatus.EXPIRED)) {
            return true;
        }
        return false;
    }

    public boolean isCancelable() {
        if (contractEntity.getStatus().equalsIgnoreCase(Constants.ContractStatus.PENDING) ||
                contractEntity.getStatus().equalsIgnoreCase(Constants.ContractStatus.NO_CARD) ||
                contractEntity.getStatus().equalsIgnoreCase(Constants.ContractStatus.READY)) {
            return true;
        }
        return false;
    }
}