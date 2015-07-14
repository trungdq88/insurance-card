package com.fpt.mic.micweb.utils;

import com.fpt.mic.micweb.model.dao.helper.BusinessRulesDao;
import com.fpt.mic.micweb.model.entity.helper.BusinessRulesEntity;
import org.joda.time.LocalDate;

/**
 * Created by Kha on 01/07/2015.
 */
public class ConfigUtils {

    private LocalDate startDateMin;
    private LocalDate startDateMax;
    private LocalDate expiredDateMin;
    private LocalDate expiredDateMax;
    private LocalDate paidDateMin;
    private LocalDate paidDateMax;
    private LocalDate cancelDateMin;
    private LocalDate cancelDateMax;
    private float newCardFee;
    private float deliveryFee;

    public ConfigUtils() {
    }

    BusinessRulesDao dao = new BusinessRulesDao();
    BusinessRulesEntity entity = dao.getLastActiveBusinessRule();

    public LocalDate getStartDateMin() {
        return startDateMin = new LocalDate().minusDays(entity.getStartDateBefore());
    }

    public void setStartDateMin(LocalDate startDateMin) {
        this.startDateMin = startDateMin;
    }

    public LocalDate getStartDateMax() {
        return startDateMax = new LocalDate().plusDays(entity.getStartDateAfter());
    }

    public void setStartDateMax(LocalDate startDateMax) {
        this.startDateMax = startDateMax;
    }

    public LocalDate getExpiredDateMin() {
        return expiredDateMin = new LocalDate().plusMonths(entity.getContractMinTerm());
    }

    public void setExpiredDateMin(LocalDate expiredDateMin) {
        this.expiredDateMin = expiredDateMin;
    }

    public LocalDate getExpiredDateMax() {
        return expiredDateMax = new LocalDate().plusMonths(entity.getContractDefaultTerm());
    }

    public void setExpiredDateMax(LocalDate expiredDateMax) {
        this.expiredDateMax = expiredDateMax;
    }

    public LocalDate getPaidDateMin() {
        return paidDateMin = new LocalDate().minusDays(entity.getPaidDaterBefore());
    }

    public void setPaidDateMin(LocalDate paidDateMin) {
        this.paidDateMin = paidDateMin;
    }

    public LocalDate getPaidDateMax() {
        return paidDateMax = new LocalDate().plusDays(entity.getPaidDateAfter());
    }

    public void setPaidDateMax(LocalDate paidDateMax) {
        this.paidDateMax = paidDateMax;
    }

    public LocalDate getCancelDateMin() {
        return cancelDateMin = new LocalDate().minusDays(entity.getCancelDateBefore());
    }

    public void setCancelDateMin(LocalDate cancelDateMin) {
        this.cancelDateMin = cancelDateMin;
    }

    public LocalDate getCancelDateMax() {
        return cancelDateMax = new LocalDate().plusDays(entity.getCancelDateAfter());
    }

    public void setCancelDateMax(LocalDate cancelDateMax) {
        this.cancelDateMax = cancelDateMax;
    }

    public float getNewCardFee() {
        return newCardFee = entity.getNewCardRequestFee();
    }

    public void setNewCardFee(float newCardFee) {
        this.newCardFee = newCardFee;
    }

    public float getDeliveryFee() {
        return deliveryFee = entity.getDeliveryFee();
    }

    public void setDeliveryFee(float deliveryFee) {
        this.deliveryFee = deliveryFee;
    }
}