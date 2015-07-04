package com.fpt.mic.micweb.model.dto;

import com.fpt.mic.micweb.model.entity.CompensationEntity;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.NewCardRequestEntity;
import com.fpt.mic.micweb.model.entity.helper.NotificationEntity;
import com.fpt.mic.micweb.utils.DateUtils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 7/3/15.
 */
public class NotificationBuilder {
    private static final String RECEIVER_ALL_STAFF = "^NV([0-9A-Z]{4,8})$";

    /**
     * Type: 1
     * Trigger: Customer creates new contract
     * Receiver: All staffs
     * Method: Web
     * Close notify trigger: Mark as read
     * @param contractEntity
     * @return
     */
    public static NotificationEntity customerCreateContract(ContractEntity contractEntity) {
        NotificationEntity entity = new NotificationEntity();
        String content = "Khách hàng %s đăng ký hợp đồng mới %s";
        entity.setContent(String.format(content,
                contractEntity.getMicCustomerByCustomerCode().getName(),
                contractEntity.getContractCode()));
        entity.setCreatedDate(new Timestamp(new Date().getTime()));
        entity.setType(NotificationEntity.Type.CUSTOMER_CREATE_CONTRACT);
        entity.setExtraData(contractEntity.getContractCode());
        entity.setReceiver(NotificationBuilder.RECEIVER_ALL_STAFF);
        entity.setMethod(NotificationEntity.Method.WEB);
        return entity;
    }

    /**
     * Type: 2
     * Trigger: Customer sends compensation request
     * Receiver: All staffs
     * Method: Web
     * Close notify trigger: Staff resolves compensation
     * @param compensationEntity
     * @return
     */
    public static NotificationEntity customerSendCompensation(CompensationEntity compensationEntity) {
        NotificationEntity entity = new NotificationEntity();
        String content = "Khách hàng %s gửi yêu cầu bồi thường cho hợp đồng %s";
        entity.setContent(String.format(content,
                compensationEntity.getMicContractByContractCode().getMicCustomerByCustomerCode().getName(),
                compensationEntity.getMicContractByContractCode().getContractCode()));
        entity.setCreatedDate(new Timestamp(new Date().getTime()));
        entity.setType(NotificationEntity.Type.CUSTOMER_SEND_COMPENSATION);
        entity.setExtraData(compensationEntity.getCompensationCode());
        entity.setReceiver(NotificationBuilder.RECEIVER_ALL_STAFF);
        entity.setMethod(NotificationEntity.Method.WEB);
        return entity;
    }

    /**
     * Type: 3
     * Trigger: Customer sends new card request
     * Receiver: All staffs
     * Method: Web
     * Close notify trigger: Staff prints new card
     * @param newCardRequestEntity
     * @return
     */
    public static NotificationEntity customerSendNewCardRequest(NewCardRequestEntity newCardRequestEntity) {
        NotificationEntity entity = new NotificationEntity();
        String content = "Khách hàng %s gửi yêu cầu cấp thẻ mới cho hợp đồng %s";
        entity.setContent(String.format(content,
                newCardRequestEntity.getMicCustomerByCustomerCode().getName(),
                newCardRequestEntity.getMicCardByOldCardId().getMicContractByContractCode().getContractCode()));
        entity.setCreatedDate(new Timestamp(new Date().getTime()));
        entity.setType(NotificationEntity.Type.CUSTOMER_SEND_NEW_CARD_REQUEST);
        entity.setExtraData(newCardRequestEntity.getId() + "");
        entity.setReceiver(NotificationBuilder.RECEIVER_ALL_STAFF);
        entity.setMethod(NotificationEntity.Method.WEB);
        return entity;
    }

    /**
     * Type: 4
     * Trigger: Contract is nearly expired (send 3 times)
     * Receiver: Customer
     * Method: Web, Email
     * Close notify trigger: Customer or Staff renew the contract
     * @param contractEntity
     * @return
     */
    public static NotificationEntity contractNearlyExpired(ContractEntity contractEntity, int type) {
        NotificationEntity entity = new NotificationEntity();
        String content = "Hợp đồng %s sẽ hết hạn trong %s ngày nữa";
        entity.setContent(String.format(content,
                contractEntity.getContractCode(),
                DateUtils.dateBetween(DateUtils.currentDateWithoutTime(),
                        contractEntity.getExpiredDate())));
        entity.setCreatedDate(new Timestamp(new Date().getTime()));
        entity.setType(type);
        entity.setExtraData(contractEntity.getContractCode());
        entity.setReceiver("^" + contractEntity.getCustomerCode() + "$");
        entity.setMethod(NotificationEntity.Method.WEB & NotificationEntity.Method.EMAIL);
        return entity;
    }

    /**
     * Type: 5
     * Trigger: Contract is expired
     * Receiver: Customer
     * Method: Web, Email
     * Close notify trigger: Customer or Staff renew the contract
     * @param contractEntity
     * @return
     */
    public static NotificationEntity contractExpired(ContractEntity contractEntity) {
        NotificationEntity entity = new NotificationEntity();
        String content = "Hợp đồng %s đã hết hạn, vui lòng gia hạn để tiếp tục sử dụng dịch vụ.";
        entity.setContent(String.format(content, contractEntity.getContractCode()));
        entity.setCreatedDate(new Timestamp(new Date().getTime()));
        entity.setType(NotificationEntity.Type.CONTRACT_EXPIRED);
        entity.setExtraData(contractEntity.getContractCode());
        entity.setReceiver("^" + contractEntity.getCustomerCode() + "$");
        entity.setMethod(NotificationEntity.Method.WEB & NotificationEntity.Method.EMAIL);
        return entity;
    }

    /**
     * Type: 6
     * Trigger: Customer sends request cancel
     * Receiver: All staffs
     * Method: Web
     * Close notify trigger: Staff resolves request cancel or Customer rejects the request cancel
     * @param contractEntity
     * @return
     */
    public static NotificationEntity customerSendRequestCancel(ContractEntity contractEntity) {
        NotificationEntity entity = new NotificationEntity();
        String content = "Khách hàng %s yêu cầu huỷ hợp đồng %s";
        entity.setContent(String.format(content,
                contractEntity.getMicCustomerByCustomerCode().getName(),
                contractEntity.getContractCode()));
        entity.setCreatedDate(new Timestamp(new Date().getTime()));
        entity.setType(NotificationEntity.Type.CUSTOMER_REQUEST_CANCEL);
        entity.setExtraData(contractEntity.getContractCode());
        entity.setReceiver(RECEIVER_ALL_STAFF);
        entity.setMethod(NotificationEntity.Method.WEB);
        return entity;
    }

    /**
     * Type: 7
     * Trigger: Contract is cancelled because payment due date exceed
     * Receiver: All staffs and the customer
     * Method: Web & Email
     * Close notify trigger: Mark as read
     * @param contractEntity
     * @return
     */
    public static NotificationEntity contractCancelledNoPayment(ContractEntity contractEntity) {
        NotificationEntity entity = new NotificationEntity();
        String content = "Hợp đồng %s đã bị huỷ do không thanh toán đúng hạn";
        entity.setContent(String.format(content, contractEntity.getContractCode()));
        entity.setCreatedDate(new Timestamp(new Date().getTime()));
        entity.setType(NotificationEntity.Type.CONTRACT_CANCELLED_NO_PAYMENT);
        entity.setExtraData(contractEntity.getContractCode());
        entity.setReceiver(RECEIVER_ALL_STAFF + "|^" + contractEntity.getCustomerCode() + "$");
        entity.setMethod(NotificationEntity.Method.WEB & NotificationEntity.Method.EMAIL);
        return entity;
    }

    /**
     * Type: 8
     * Trigger: Paid Pending Contract start date come
     * Receiver: Customer
     * Method: Web
     * Close notify trigger: Mark as read
     * @param contractEntity
     * @return
     */
    public static NotificationEntity contractStartDateCome(ContractEntity contractEntity) {
        NotificationEntity entity = new NotificationEntity();
        String content = "Hợp đồng %s đã bắt đầu có hiệu lực";
        entity.setContent(String.format(content, contractEntity.getContractCode()));
        entity.setCreatedDate(new Timestamp(new Date().getTime()));
        entity.setType(NotificationEntity.Type.CONTRACT_START_DATE_COME);
        entity.setExtraData(contractEntity.getContractCode());
        entity.setReceiver("^" + contractEntity.getCustomerCode() + "$");
        entity.setMethod(NotificationEntity.Method.WEB & NotificationEntity.Method.EMAIL);
        return entity;
    }

}
