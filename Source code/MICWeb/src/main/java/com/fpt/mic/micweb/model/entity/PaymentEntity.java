package com.fpt.mic.micweb.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/5/15.
 */
@Entity
@Table(name = "mic_payment", schema = "", catalog = "mic_data")
public class PaymentEntity {
    private int id;
    private Timestamp paidDate;
    private String paymentMethod;
    private String content;
    private float amount;
    private String receiver;
    private String paypalTransId;
    private String contractCode;
    private ContractEntity micContractByContractCode;
    private StaffEntity micStaffByReceiver;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "paid_date")
    public Timestamp getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Timestamp paidDate) {
        this.paidDate = paidDate;
    }

    @Basic
    @Column(name = "payment_method")
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
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
        if (Float.compare(that.amount, amount) != 0) return false;
        if (paidDate != null ? !paidDate.equals(that.paidDate) : that.paidDate != null) return false;
        if (paymentMethod != null ? !paymentMethod.equals(that.paymentMethod) : that.paymentMethod != null)
            return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (receiver != null ? !receiver.equals(that.receiver) : that.receiver != null) return false;
        if (paypalTransId != null ? !paypalTransId.equals(that.paypalTransId) : that.paypalTransId != null)
            return false;
        if (contractCode != null ? !contractCode.equals(that.contractCode) : that.contractCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (paidDate != null ? paidDate.hashCode() : 0);
        result = 31 * result + (paymentMethod != null ? paymentMethod.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (amount != +0.0f ? Float.floatToIntBits(amount) : 0);
        result = 31 * result + (receiver != null ? receiver.hashCode() : 0);
        result = 31 * result + (paypalTransId != null ? paypalTransId.hashCode() : 0);
        result = 31 * result + (contractCode != null ? contractCode.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "contract_code", referencedColumnName = "contract_code", nullable = false, insertable = false, updatable = false)
    public ContractEntity getMicContractByContractCode() {
        return micContractByContractCode;
    }

    public void setMicContractByContractCode(ContractEntity micContractByContractCode) {
        this.micContractByContractCode = micContractByContractCode;
    }

    @ManyToOne
    @JoinColumn(name = "receiver", referencedColumnName = "staff_code", nullable = true, insertable = false, updatable = false)
    public StaffEntity getMicStaffByReceiver() {
        return micStaffByReceiver;
    }

    public void setMicStaffByReceiver(StaffEntity micStaffByReceiver) {
        this.micStaffByReceiver = micStaffByReceiver;
    }
}
