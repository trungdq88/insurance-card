package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.PaymentDao;
import com.fpt.mic.micweb.model.dto.form.CreateNewCardPaymentDto;
import com.fpt.mic.micweb.model.entity.PaymentEntity;
import com.fpt.mic.micweb.utils.ConfigUtils;
import com.fpt.mic.micweb.utils.Constants;

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
    // createNewCardRequestPayment by staff
    public PaymentEntity createNewCardRequestPayment(CreateNewCardPaymentDto createNewCardPaymentDto, String staffCode){
        PaymentDao paymentDao = new PaymentDao();
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setContractCode(createNewCardPaymentDto.getContractCode());
        paymentEntity.setPaymentMethod("Trực tiếp");
        ConfigUtils configUtils = new ConfigUtils();
        // kiem tra neu isDeliveryRequested
        if(createNewCardPaymentDto.getDelivery() == 1) {
            paymentEntity.setContent("Đăng ký thẻ mới "+createNewCardPaymentDto.getContractCode() +" + giao thẻ trực tiếp");
            paymentEntity.setAmount(configUtils.getNewCardFee() + configUtils.getDeliveryFee());
        }
        else {
            paymentEntity.setContent("Đăng ký thẻ mới "+createNewCardPaymentDto.getContractCode());
            paymentEntity.setAmount(configUtils.getNewCardFee());
        }
        paymentEntity.setPaidDate(createNewCardPaymentDto.getPaidDate());
        paymentEntity.setReceiver(staffCode);
        return paymentDao.create(paymentEntity);

    }
}
