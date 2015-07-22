package com.fpt.mic.micweb.model.entity.helper;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by TriPQM on 07/05/2015.
 */
@Entity
@Table(name = "mic_business_rules", schema = "", catalog = "mic_data")
public class BusinessRulesEntity {
    private int id;
    private Timestamp startDate;
    private int startDateBefore;
    private int startDateAfter;
    private int contractDefaultTerm;
    private int contractMinTerm;
    private int paidDaterBefore;
    private int paidDateAfter;
    private int cancelDateBefore;
    private int cancelDateAfter;
    private int nearlyExceedExpiredOne;
    private int nearlyExceedExpiredTwo;
    private int nearlyExceedExpiredThree;
    private int paymentDueDate;
    private int newCardRequestFee;
    private int deliveryFee;
    private int updateContractDueDate;
    private int contractRenewLimit;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "start_date",insertable = false)
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "start_date_before")
    public int getStartDateBefore() {
        return startDateBefore;
    }

    public void setStartDateBefore(int startDateBefore) {
        this.startDateBefore = startDateBefore;
    }

    @Basic
    @Column(name = "start_date_after")
    public int getStartDateAfter() {
        return startDateAfter;
    }

    public void setStartDateAfter(int startDateAfter) {
        this.startDateAfter = startDateAfter;
    }

    @Basic
    @Column(name = "contract_default_term")
    public int getContractDefaultTerm() {
        return contractDefaultTerm;
    }

    public void setContractDefaultTerm(int contractDefaultTerm) {
        this.contractDefaultTerm = contractDefaultTerm;
    }
    @Basic
    @Column(name = "contract_min_term")
    public int getContractMinTerm() {
        return contractMinTerm;
    }

    public void setContractMinTerm(int contractMinTerm) {
        this.contractMinTerm = contractMinTerm;
    }

    @Basic
    @Column(name = "paid_date_before")
    public int getPaidDaterBefore() {
        return paidDaterBefore;
    }

    public void setPaidDaterBefore(int paidDaterBefore) {
        this.paidDaterBefore = paidDaterBefore;
    }

    @Basic
    @Column(name = "paid_date_after")
    public int getPaidDateAfter() {
        return paidDateAfter;
    }

    public void setPaidDateAfter(int paidDateAfter) {
        this.paidDateAfter = paidDateAfter;
    }

    @Basic
    @Column(name = "cancel_date_before")
    public int getCancelDateBefore() {
        return cancelDateBefore;
    }

    public void setCancelDateBefore(int cancelDateBefore) {
        this.cancelDateBefore = cancelDateBefore;
    }

    @Basic
    @Column(name = "cancel_date_after")
    public int getCancelDateAfter() {
        return cancelDateAfter;
    }

    public void setCancelDateAfter(int cancelDateAfter) {
        this.cancelDateAfter = cancelDateAfter;
    }

    @Basic
    @Column(name = "nearly_exceed_expired_one")
    public int getNearlyExceedExpiredOne() {
        return nearlyExceedExpiredOne;
    }

    public void setNearlyExceedExpiredOne(int nearlyExceedExpiredOne) {
        this.nearlyExceedExpiredOne = nearlyExceedExpiredOne;
    }

    @Basic
    @Column(name = "nearly_exceed_expired_two")
    public int getNearlyExceedExpiredTwo() {
        return nearlyExceedExpiredTwo;
    }

    public void setNearlyExceedExpiredTwo(int nearlyExceedExpiredTwo) {
        this.nearlyExceedExpiredTwo = nearlyExceedExpiredTwo;
    }

    @Basic
    @Column(name = "nearly_exceed_expired_three")
    public int getNearlyExceedExpiredThree() {
        return nearlyExceedExpiredThree;
    }

    public void setNearlyExceedExpiredThree(int nearlyExceedExpiredThree) {
        this.nearlyExceedExpiredThree = nearlyExceedExpiredThree;
    }

    @Basic
    @Column(name = "payment_due_date")
    public int getPaymentDueDate() {
        return paymentDueDate;
    }

    public void setPaymentDueDate(int paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    @Basic
    @Column(name = "new_card_request_fee")
    public int getNewCardRequestFee() {
        return newCardRequestFee;
    }

    public void setNewCardRequestFee(int newCardRequestFee) {
        this.newCardRequestFee = newCardRequestFee;
    }

    @Basic
    @Column(name = "delivery_fee")
    public int getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(int deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    @Basic
    @Column(name = "update_contract_due_date")
    public int getUpdateContractDueDate() {
        return updateContractDueDate;
    }

    public void setUpdateContractDueDate(int updateContractDueDate) {
        this.updateContractDueDate = updateContractDueDate;
    }

    @Basic
    @Column(name = "contract_renew_limit")
    public int getContractRenewLimit() {
        return contractRenewLimit;
    }

    public void setContractRenewLimit(int contractRenewLimit) {
        this.contractRenewLimit = contractRenewLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BusinessRulesEntity that = (BusinessRulesEntity) o;

        if (id != that.id) return false;
        if (startDateBefore != that.startDateBefore) return false;
        if (startDateAfter != that.startDateAfter) return false;
        if (contractDefaultTerm != that.contractDefaultTerm) return false;
        if (paidDaterBefore != that.paidDaterBefore) return false;
        if (paidDateAfter != that.paidDateAfter) return false;
        if (cancelDateBefore != that.cancelDateBefore) return false;
        if (cancelDateAfter != that.cancelDateAfter) return false;
        if (nearlyExceedExpiredOne != that.nearlyExceedExpiredOne) return false;
        if (nearlyExceedExpiredTwo != that.nearlyExceedExpiredTwo) return false;
        if (nearlyExceedExpiredThree != that.nearlyExceedExpiredThree) return false;
        if (paymentDueDate != that.paymentDueDate) return false;
        if (newCardRequestFee != that.newCardRequestFee) return false;
        if (deliveryFee != that.deliveryFee) return false;
        if (updateContractDueDate != that.updateContractDueDate) return false;
        if (contractRenewLimit != that.contractRenewLimit) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + startDateBefore;
        result = 31 * result + startDateAfter;
        result = 31 * result + contractDefaultTerm;
        result = 31 * result + paidDaterBefore;
        result = 31 * result + paidDateAfter;
        result = 31 * result + cancelDateBefore;
        result = 31 * result + cancelDateAfter;
        result = 31 * result + nearlyExceedExpiredOne;
        result = 31 * result + nearlyExceedExpiredTwo;
        result = 31 * result + nearlyExceedExpiredThree;
        result = 31 * result + paymentDueDate;
        result = 31 * result + newCardRequestFee;
        result = 31 * result + deliveryFee;
        result = 31 * result + updateContractDueDate;
        result = 31 * result + contractRenewLimit;
        return result;
    }
}
