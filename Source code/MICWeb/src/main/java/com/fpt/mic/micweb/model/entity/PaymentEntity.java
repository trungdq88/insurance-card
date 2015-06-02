package com.fpt.mic.micweb.model.entity;

import javax.persistence.*;

/**
 * Created by dinhquangtrung on 6/2/15.
 */
@Entity
@Table(name = "mic_payment", schema = "", catalog = "mic_data")
public class PaymentEntity {
    private int id;
    private int date;
    private String method;
    private String service;
    private float amount;
    private String receiver;
    private String paypalTransId;
    private String contractCode;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date")
    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Basic
    @Column(name = "method")
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Basic
    @Column(name = "service")
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Basic
    @Column(name = "amount")
    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
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
    @Column(name = "paypal_trans_id")
    public String getPaypalTransId() {
        return paypalTransId;
    }

    public void setPaypalTransId(String paypalTransId) {
        this.paypalTransId = paypalTransId;
    }

    @Basic
    @Column(name = "contract_code")
    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentEntity that = (PaymentEntity) o;

        if (id != that.id) return false;
        if (date != that.date) return false;
        if (Float.compare(that.amount, amount) != 0) return false;
        if (method != null ? !method.equals(that.method) : that.method != null) return false;
        if (service != null ? !service.equals(that.service) : that.service != null) return false;
        if (receiver != null ? !receiver.equals(that.receiver) : that.receiver != null) return false;
        if (paypalTransId != null ? !paypalTransId.equals(that.paypalTransId) : that.paypalTransId != null)
            return false;
        if (contractCode != null ? !contractCode.equals(that.contractCode) : that.contractCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + date;
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (service != null ? service.hashCode() : 0);
        result = 31 * result + (amount != +0.0f ? Float.floatToIntBits(amount) : 0);
        result = 31 * result + (receiver != null ? receiver.hashCode() : 0);
        result = 31 * result + (paypalTransId != null ? paypalTransId.hashCode() : 0);
        result = 31 * result + (contractCode != null ? contractCode.hashCode() : 0);
        return result;
    }
}
