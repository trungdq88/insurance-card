package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.CustomerDao;
import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dao.PaymentDao;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.PaymentEntity;
import sun.util.calendar.BaseCalendar;
import sun.util.calendar.LocalGregorianCalendar;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by PhucNguyen on 05/06/2015.
 */
public class CustomerBusniess {
    //get all contract belong to customer
    public List<ContractEntity> getAllContract() {
        ContractDao contractDa0 = new ContractDao();
        List<ContractEntity> listContract = contractDa0.getListContract();
        return listContract;
    }

    // get contract detail
    public ContractEntity getContractDetail(String code) {
        ContractDao contractDa0 = new ContractDao();
        return contractDa0.read(code);
    }

    /*cancel contract    */
    public ContractEntity CancelContract(String code, String cancelReason) {
        ContractDao contractDa0 = new ContractDao();
        ContractEntity contract = contractDa0.read(code);
        //get date now
        java.util.Date date = new java.util.Date();
        contract.setCancelDate(new Timestamp(date.getTime()));
        contract.setCancelReason(cancelReason);
        contract.setStatus("Request cancel");
        contractDa0.update(contract);
        return contract;
    }

    /*
    renew contract
     */
    public boolean RenewContract(ContractEntity contract, PaymentEntity payment) {
        boolean result = false;
        ContractDao contractDa0 = new ContractDao();
        PaymentDao paymentDao = new PaymentDao();
        if (contractDa0.update(contract) != null && paymentDao.update(payment) != null) {
            result = true;
        }
        return result;

    }

    /*
    reject request cancel contract
     */
    public ContractEntity RejectCancelContract(String contractCode) {
        ContractDao contractDa0 = new ContractDao();
        ContractEntity contract = contractDa0.read(contractCode);
        contract.setCancelReason(null);
        contract.setCancelNote(null);
        contract.setStatus("Ready");
        if (contractDa0.update(contract) != null) {
            return contract;
        } else {
            return null;
        }

    }

}
