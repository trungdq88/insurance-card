package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.PaymentDao;
import com.fpt.mic.micweb.model.entity.PaymentEntity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by TriPQM on 07/03/2015.
 */
public class PaymentBusiness {
    public PaymentEntity updateNewCardRequestPayment(String contractCode,String  paymentMethod,String  paymentContent,Float amount,String  paypalTransId){
        PaymentDao paymentDao = new PaymentDao();
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setContractCode(contractCode);
        paymentEntity.setPaymentMethod(paymentMethod);
        paymentEntity.setAmount(amount);
        paymentEntity.setContent(paymentContent);
        paymentEntity.setPaypalTransId(paypalTransId);
        paymentEntity.setPaidDate(new Timestamp(new Date().getTime()));
        return paymentDao.create(paymentEntity);
    }
}
