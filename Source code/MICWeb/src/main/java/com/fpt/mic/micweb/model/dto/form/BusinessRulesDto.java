package com.fpt.mic.micweb.model.dto.form;


import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * Created by TriPQM on 07/05/2015.
 */
public class BusinessRulesDto {
    @NotNull(message = "Giới hạn thời gian tạo hợp đồng không được bỏ trống")
    @Range(min = 0, message = "Giới hạn thời gian tạo hợp đồng phải lớn hơn hoặc bằng 0")
    private Integer startDateBefore;
    @NotNull(message = "Giới hạn thời gian tạo hợp đồng không được bỏ trống")
    @Range(min = 0, message = "Giá trị phải lớn hơn hoặc bằng 0")
    private Integer startDateAfter;
    @NotNull(message = "Kỳ hạn hợp đồng mặc định không được bỏ trống")
    @Range(min = 0, message = "Kỳ hạn hợp đồng mặc định phải lớn hơn hoặc bằng 0")
    private Integer contractDefaultTerm;
    @NotNull(message = "Giới hạn ngày thanh toán không được bỏ trống")
    @Range(min = 0, message = "Giới hạn ngày thanh toán không được để trống phải lớn hơn hoặc bằng 0")
    private Integer paidDaterBefore;
    @NotNull(message = "Giới hạn ngày thanh toán không được bỏ trống")
    @Range(min = 0, message = "Giới hạn ngày thanh toán không được để trống phải lớn hơn hoặc bằng 0")
    private Integer paidDateAfter;
    @NotNull(message = "Giới hạn ngày hủy hợp đồng không được bỏ trống")
    @Range(min = 0, message = "Giới hạn ngày hủy hợp đồng phải lớn hơn hoặc bằng 0")
    private Integer cancelDateBefore;
    @NotNull(message = "Giới hạn ngày hủy hợp đồng không được bỏ trống")
    @Range(min = 0, message = "Giới hạn ngày hủy hợp đồng phải lớn hơn hoặc bằng 0")
    private Integer cancelDateAfter;
    @NotNull(message = "Ngày thông báo hợp đồng sắp hết hạn lần 1 không được bỏ trống")
    @Range(min = 0, message = "Ngày thông báo hợp đồng sắp hết hạn lần 1 phải lớn hơn hoặc bằng 0")
    private Integer nearlyExceedExpiredOne;
    @NotNull(message = "Ngày thông báo hợp đồng sắp hết hạn lần 2 không được bỏ trống")
    @Range(min = 0, message = "Ngày thông báo hợp đồng sắp hết hạn lần 2 phải lớn hơn hoặc bằng 0")
    private Integer nearlyExceedExpiredTwo;
    @NotNull(message = "Ngày thông báo hợp đồng sắp hết hạn lần 3 không được bỏ trống")
    @Range(min = 0, message = "Ngày thông báo hợp đồng sắp hết hạn lần 3 phải lớn hơn hoặc bằng 0")
    private Integer nearlyExceedExpiredThree;
    @NotNull(message = "Thời hạn thanh toán hợp đồng không được bỏ trống")
    @Range(min = 0, message = "Thời hạn thanh toán hợp đồng phải lớn hơn hoặc bằng 0")
    private Integer paymentDueDate;
    @NotNull(message = "Phí làm lại thẻ")
    @Range(min = 0, message = "Phí làm lại thẻ phải lớn hơn hoặc bằng 0")
    private Float newCardRequestFee;
    @NotNull(message = "Phí vận chuyển không được bỏ trống")
    @Range(min = 0, message = "Phí vận chuyển phải lớn hơn hoặc bằng 0")
    private Float deliveryFee;

    public BusinessRulesDto() {
    }

    public Integer getStartDateBefore() {
        return startDateBefore;
    }

    public void setStartDateBefore(Integer startDateBefore) {
        this.startDateBefore = startDateBefore;
    }

    public Integer getStartDateAfter() {
        return startDateAfter;
    }

    public void setStartDateAfter(Integer startDateAfter) {
        this.startDateAfter = startDateAfter;
    }

    public Integer getContractDefaultTerm() {
        return contractDefaultTerm;
    }

    public void setContractDefaultTerm(Integer contractDefaultTerm) {
        this.contractDefaultTerm = contractDefaultTerm;
    }

    public Integer getPaidDaterBefore() {
        return paidDaterBefore;
    }

    public void setPaidDaterBefore(Integer paidDaterBefore) {
        this.paidDaterBefore = paidDaterBefore;
    }

    public Integer getPaidDateAfter() {
        return paidDateAfter;
    }

    public void setPaidDateAfter(Integer paidDateAfter) {
        this.paidDateAfter = paidDateAfter;
    }

    public Integer getCancelDateBefore() {
        return cancelDateBefore;
    }

    public void setCancelDateBefore(Integer cancelDateBefore) {
        this.cancelDateBefore = cancelDateBefore;
    }

    public Integer getCancelDateAfter() {
        return cancelDateAfter;
    }

    public void setCancelDateAfter(Integer cancelDateAfter) {
        this.cancelDateAfter = cancelDateAfter;
    }

    public Integer getNearlyExceedExpiredOne() {
        return nearlyExceedExpiredOne;
    }

    public void setNearlyExceedExpiredOne(Integer nearlyExceedExpiredOne) {
        this.nearlyExceedExpiredOne = nearlyExceedExpiredOne;
    }

    public Integer getNearlyExceedExpiredTwo() {
        return nearlyExceedExpiredTwo;
    }

    public void setNearlyExceedExpiredTwo(Integer nearlyExceedExpiredTwo) {
        this.nearlyExceedExpiredTwo = nearlyExceedExpiredTwo;
    }

    public Integer getNearlyExceedExpiredThree() {
        return nearlyExceedExpiredThree;
    }

    public void setNearlyExceedExpiredThree(Integer nearlyExceedExpiredThree) {
        this.nearlyExceedExpiredThree = nearlyExceedExpiredThree;
    }

    public Integer getPaymentDueDate() {
        return paymentDueDate;
    }

    public void setPaymentDueDate(Integer paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public Float getNewCardRequestFee() {
        return newCardRequestFee;
    }

    public void setNewCardRequestFee(Float newCardRequestFee) {
        this.newCardRequestFee = newCardRequestFee;
    }

    public Float getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Float deliveryFee) {
        this.deliveryFee = deliveryFee;
    }
}
