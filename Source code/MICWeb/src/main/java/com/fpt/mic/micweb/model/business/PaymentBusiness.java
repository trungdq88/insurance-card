package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.PaymentDao;
import com.fpt.mic.micweb.model.dto.form.CreateNewCardPaymentDto;
import com.fpt.mic.micweb.model.entity.PaymentEntity;
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

        if(createNewCardPaymentDto.getDelivery() == 1) {
            paymentEntity.setContent("Đăng ký thẻ mới "+createNewCardPaymentDto.getContractCode() +" + giao thẻ trực tiếp");
            paymentEntity.setAmount(Constants.PaymentFee.NEW_CARD_REQUEST_FEE + Constants.PaymentFee.DELIVERY_FEE);
        }
        else {
            paymentEntity.setContent("Đăng ký thẻ mới "+createNewCardPaymentDto.getContractCode());
            paymentEntity.setAmount(Constants.PaymentFee.NEW_CARD_REQUEST_FEE);
        }
        paymentEntity.setPaidDate(createNewCardPaymentDto.getPaidDate());
        paymentEntity.setReceiver(staffCode);
        return paymentDao.create(paymentEntity);

    }
}
