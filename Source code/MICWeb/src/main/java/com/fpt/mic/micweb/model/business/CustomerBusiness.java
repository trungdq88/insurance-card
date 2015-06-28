package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.CardDao;
import com.fpt.mic.micweb.model.dao.CustomerDao;
import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dao.PaymentDao;
import com.fpt.mic.micweb.model.dto.form.CancelContractDto;
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
public class CustomerBusiness {
    //get all contract belong to customer
    public List<ContractEntity> getAllContractByCustomer(String customerCode) {
        ContractDao contractDao = new ContractDao();
        List<ContractEntity> listContract = contractDao.getContractByCustomerCode(customerCode);
        return listContract;
    }

    // get contract detail
    public ContractEntity getContractDetail(String code) {
        ContractDao contractDao = new ContractDao();
        ContractEntity contract = contractDao.read(code);
        if (contract != null) {
            return contract;
        } else {
            return null;
        }
    }

    /**
     * cancel contract
     *
     * @return contract entity
     */
    public ContractEntity cancelContract(CancelContractDto cancelDto) {
        ContractDao contractDao = new ContractDao();
        ContractEntity contract = contractDao.read(cancelDto.getContractCode());

        contract.setCancelDate(cancelDto.getCancelDate());
        contract.setCancelReason(cancelDto.getCancelReason());
        contract.setStatus(Constants.ContractStatus.REQUEST_CANCEL);
        contractDao.update(contract);
        return contract;
    }

    /**
     * renew contract
     *
     * @param contractCode,        nexExpried
     * @param paymentTransactionId
     * @return contract
     */
    public boolean renewContract(String contractCode, Timestamp neweEpired, String paymentTransactionId) {
        //init
        boolean result = false;
        ContractDao contractDao = new ContractDao();
        PaymentDao paymentDao = new PaymentDao();
        CardDao cardDao = new CardDao();
        ContractEntity contract = contractDao.read(contractCode);
        PaymentEntity payment = new PaymentEntity();
        CardEntity card = cardDao.getCardByContract(contractCode);
        java.util.Date date = new java.util.Date();
        /////////////////////////
        if (contract != null) {
            //update contract
            contract.setExpiredDate(neweEpired);
            if(card == null){
                contract.setStatus(Constants.ContractStatus.NO_CARD);
            }else {
                contract.setStatus(Constants.ContractStatus.READY);
            }
            //update payment
            payment.setPaidDate(new Timestamp(date.getTime()));
            payment.setPaymentMethod("PayPal payment");
            payment.setContent("Gia hạn hợp đồng");
            payment.setAmount(contract.getMicContractTypeByContractTypeId().getPricePerYear());
            payment.setPaypalTransId(paymentTransactionId);
            payment.setContractCode(contract.getContractCode());
            if (contractDao.update(contract) != null && paymentDao.create(payment) != null) {
                result = true;
            }
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
        if (card == null) {
            contract.setStatus(Constants.ContractStatus.NO_CARD);
        } else if ((new Timestamp(date.getTime()).before(contract.getExpiredDate()))) {
            contract.setStatus(Constants.ContractStatus.READY);
        } else if ((new Timestamp(date.getTime()).after(contract.getExpiredDate()))) {
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
    /**
     * payment for contract
     *
     * @param contractCode , paymentTransactionId
     * @return bool result
     */
    public boolean paymentContract(String contractCode, String paymentTransactionId){
        //init
        boolean result = false;
        java.util.Date date = new java.util.Date();
        ContractDao contractDao = new ContractDao();
        PaymentDao paymentDao = new PaymentDao();
        ContractEntity contract = contractDao.read(contractCode);
        PaymentEntity payment = new PaymentEntity();
        if(contract != null){
            contract.setStatus(Constants.ContractStatus.NO_CARD);
            payment.setPaidDate(new Timestamp(date.getTime()));
            payment.setPaymentMethod("PayPal payment");
            payment.setContent("Đăng ký hợp đồng mới " + contract.getContractCode() );
            payment.setAmount(contract.getMicContractTypeByContractTypeId().getPricePerYear());
            payment.setPaypalTransId(paymentTransactionId);
            payment.setContractCode(contract.getContractCode());
            if (contractDao.update(contract) != null && paymentDao.create(payment) != null) {
                result = true;
            }
        }
        return result;
    }

    public List searchCustomerContractByCode(String customerCode, String keyword) {
        ContractDao contractDao = new ContractDao();
        return contractDao.getCustomerContractByCode(customerCode, keyword);
    }
}
