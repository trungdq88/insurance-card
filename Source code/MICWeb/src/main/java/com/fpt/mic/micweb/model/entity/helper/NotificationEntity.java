package com.fpt.mic.micweb.model.entity.helper;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 7/3/15.
 */
@Entity
@Table(name = "mic_notification", schema = "", catalog = "mic_data")
public class NotificationEntity {
    public static class Type {
        public static final int CUSTOMER_CREATE_CONTRACT = 1;
        public static final int CUSTOMER_SEND_COMPENSATION = 2;
        public static final int CUSTOMER_SEND_NEW_CARD_REQUEST = 3;
        public static final int CONTRACT_NEARLY_EXPIRED_1 = 41;
        public static final int CONTRACT_NEARLY_EXPIRED_2 = 42;
        public static final int CONTRACT_NEARLY_EXPIRED_3 = 43;
        public static final int CONTRACT_EXPIRED = 5;
        public static final int CUSTOMER_REQUEST_CANCEL = 6;
        public static final int CONTRACT_CANCELLED_NO_PAYMENT = 7;
        public static final int CONTRACT_START_DATE_COME = 8;
        public static final int COMPENSATION_RESOLVED = 9;
    }
    public static class Method {
        public static final int WEB = 1;
        public static final int EMAIL = 2;
    }
    private int id;
    private String content;
    private String receiver;
    private int method;
    private int type;
    private String extraData;
    private Timestamp createdDate;
    private Timestamp resolvedDate;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "receiver")
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Basic
    @Column(name = "method")
    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    @Basic
    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "extra_data")
    public String getExtraData() {
        return extraData;
    }

    public void setExtraData(String relatedLink) {
        this.extraData = relatedLink;
    }

    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "resolved_date")
    public Timestamp getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(Timestamp resolvedDate) {
        this.resolvedDate = resolvedDate;
    }

    /**
     * Depend on the notification type, this method will return suitable link
     * @return
     */
    public String generateRelatedLink(String role) {
        switch (type) {
            case Type.CUSTOMER_CREATE_CONTRACT:
            case Type.CONTRACT_NEARLY_EXPIRED_1:
            case Type.CONTRACT_NEARLY_EXPIRED_2:
            case Type.CONTRACT_NEARLY_EXPIRED_3:
            case Type.CONTRACT_EXPIRED:
            case Type.CUSTOMER_REQUEST_CANCEL:
            case Type.CONTRACT_CANCELLED_NO_PAYMENT:
            case Type.CONTRACT_START_DATE_COME:
                return "/" + role + "/contract?action=detail&code=" + extraData;
            case Type.CUSTOMER_SEND_COMPENSATION:
            case Type.COMPENSATION_RESOLVED:
                return "/" + role + "/compensation?action=detail&code=" + extraData;
            case Type.CUSTOMER_SEND_NEW_CARD_REQUEST:
                return "/" + role + "/card?action=newCardRequest";
        }
        return null; // Wrong type
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotificationEntity that = (NotificationEntity) o;

        if (id != that.id) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (extraData != null ? !extraData.equals(that.extraData) : that.extraData != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (resolvedDate != null ? !resolvedDate.equals(that.resolvedDate) : that.resolvedDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (extraData != null ? extraData.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (resolvedDate != null ? resolvedDate.hashCode() : 0);
        return result;
    }
}
