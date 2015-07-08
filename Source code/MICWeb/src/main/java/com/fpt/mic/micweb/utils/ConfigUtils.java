package com.fpt.mic.micweb.utils;

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

    public LocalDate getStartDateMin() {
        return startDateMin = new LocalDate().minusDays(Constants.StaffConfiguration.START_DATE_BEFORE);
    }

    public void setStartDateMin(LocalDate startDateMin) {
        this.startDateMin = startDateMin;
    }

    public LocalDate getStartDateMax() {
        return startDateMax = new LocalDate().plusDays(Constants.StaffConfiguration.START_DATE_AFTER);
    }

    public void setStartDateMax(LocalDate startDateMax) {
        this.startDateMax = startDateMax;
    }

    public LocalDate getExpiredDateMin() {
        return expiredDateMin = new LocalDate().plusDays(Constants.StaffConfiguration.EXPIRED_DATE_AFTER);
    }

    public void setExpiredDateMin(LocalDate expiredDateMin) {
        this.expiredDateMin = expiredDateMin;
    }

    public LocalDate getExpiredDateMax() {
        return expiredDateMax = new LocalDate().plusYears(Constants.StaffConfiguration.CONTRACT_DEFAULT_TERM);
    }

    public void setExpiredDateMax(LocalDate expiredDateMax) {
        this.expiredDateMax = expiredDateMax;
    }

    public LocalDate getPaidDateMin() {
        return paidDateMin = new LocalDate().minusDays(Constants.StaffConfiguration.PAID_DATE_BEFORE);
    }

    public void setPaidDateMin(LocalDate paidDateMin) {
        this.paidDateMin = paidDateMin;
    }

    public LocalDate getPaidDateMax() {
        return paidDateMax = new LocalDate().plusDays(Constants.StaffConfiguration.PAID_DATE_AFTER);
    }

    public void setPaidDateMax(LocalDate paidDateMax) {
        this.paidDateMax = paidDateMax;
    }

    public LocalDate getCancelDateMin() {
        return cancelDateMin = new LocalDate().minusDays(Constants.StaffConfiguration.CANCEL_DATE_BEFORE);
    }

    public void setCancelDateMin(LocalDate cancelDateMin) {
        this.cancelDateMin = cancelDateMin;
    }

    public LocalDate getCancelDateMax() {
        return cancelDateMax = new LocalDate().plusDays(Constants.StaffConfiguration.CANCEL_DATE_AFTER);
    }

    public void setCancelDateMax(LocalDate cancelDateMax) {
        this.cancelDateMax = cancelDateMax;
    }

    public float getNewCardFee() {
        return newCardFee = Constants.PaymentFee.NEW_CARD_REQUEST_FEE;
    }

    public void setNewCardFee(float newCardFee) {
        this.newCardFee = newCardFee;
    }

    public float getDeliveryFee() {
        return deliveryFee = Constants.PaymentFee.DELIVERY_FEE;
    }

    public void setDeliveryFee(float deliveryFee) {
        this.deliveryFee = deliveryFee;
    }
}