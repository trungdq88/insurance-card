package com.fpt.mic.micweb.model.business;
import com.fpt.mic.micweb.model.dao.CardDao;
import com.fpt.mic.micweb.model.dao.CustomerDao;
import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dao.PaymentDao;
import com.fpt.mic.micweb.model.entity.CardEntity;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.PaymentEntity;
import com.fpt.mic.micweb.utils.Constants;
import sun.util.calendar.BaseCalendar;
import sun.util.calendar.LocalGregorianCalendar;
import java.awt.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.lang.Thread;

/**
 * Created by PhucNguyen on 05/06/2015.
 */
public class CustomerBusniess {
    //get all contract belong to customer
    public List<ContractEntity> getAllContractByCustomer(String customerCode) {
        ContractDao contractDa0 = new ContractDao();
        List<ContractEntity> listContract = contractDa0.getContractByCustomerCode(customerCode);
        return listContract;
    }

    // get contract detail
    public ContractEntity getContractDetail(String code) {
        ContractDao contractDa0 = new ContractDao();
        return contractDa0.read(code);
    }

    /**
     * cancel contract
     *
     * @param code
     * @param cancelReason
     * @return contract entity
     */
    public ContractEntity cancelContract(String code, String cancelReason) {
        ContractDao contractDa0 = new ContractDao();
        ContractEntity contract = contractDa0.read(code);
        //get date now
        java.util.Date date = new java.util.Date();
        contract.setCancelDate(new Timestamp(date.getTime()));
        contract.setCancelReason(cancelReason);
        contract.setStatus(Constants.ContractStatus.REQUEST_CANCEL);
        contractDa0.update(contract);
        return contract;
    }

    /**
     * renew contract
     *
     * @param contract
     * @param payment
     * @return contract
     */
    public boolean renewContract(ContractEntity contract, PaymentEntity payment) {
        boolean result = false;
        ContractDao contractDao = new ContractDao();
        PaymentDao paymentDao = new PaymentDao();
        if (contractDao.update(contract) != null && paymentDao.update(payment) != null) {
            result = true;
        }
        return result;

    }

    /**
     * reject request cancel contract
     *
     * @param contractCode
     * @return contract
     */
    public ContractEntity rejectCancelContract(String contractCode) {
        CardDao cardDao = new CardDao();
        ContractDao contractDao = new ContractDao();
        CardEntity card = cardDao.getCardByContract(contractCode);
        ContractEntity contract = contractDao.read(contractCode);
        java.util.Date date = new java.util.Date();
        // no card
        if(card == null){
            contract.setStatus(Constants.ContractStatus.NO_CARD);
        }
        else if ((new Timestamp(date.getTime()).before(contract.getExpiredDate()))){
            contract.setStatus(Constants.ContractStatus.READY);
        }
        else if ((new Timestamp(date.getTime()).after(contract.getExpiredDate()))){
            contract.setStatus(Constants.ContractStatus.EXPIRED);
        }
        contract.setCancelReason(null);
        contract.setCancelNote(null);



        if (contractDao.update(contract) != null) {
            return contract;
        } else {
            return null;
        }
    }

}
