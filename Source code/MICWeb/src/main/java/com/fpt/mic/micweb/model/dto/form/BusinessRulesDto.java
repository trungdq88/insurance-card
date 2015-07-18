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
    @NotNull(message = "Kỳ hạn hợp đồng tối thiểu không được bỏ trống")
    @Range(min = 0, message = "Kỳ hạn hợp đồng tối thiểu phải lớn hơn hoặc bằng 0")
    private Integer contractMinTerm;
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
    private Integer newCardRequestFee;
    @NotNull(message = "Phí vận chuyển không được bỏ trống")
    @Range(min = 0, message = "Phí vận chuyển phải lớn hơn hoặc bằng 0")
    private Integer deliveryFee;

    @NotNull(message = "Thời gian thêm thông tin sau khi hủy hợp đồng không được bỏ trống")
    @Range(min = 0, message = "Giới hạn thời gian thêm thông tin sau khi hủy hợp đồng phải lớn hơn hoặc bằng 0")
    private Integer updateContractDueDate;


    public BusinessRulesDto() {
    }

    public Integer getContractMinTerm() {
        return contractMinTerm;
    }

    public void setContractMinTerm(Integer contractMinTerm) {
        this.contractMinTerm = contractMinTerm;
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

    public Integer getNewCardRequestFee() {
        return newCardRequestFee;
    }

    public void setNewCardRequestFee(Integer newCardRequestFee) {
        this.newCardRequestFee = newCardRequestFee;
    }

    public Integer getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Integer deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Integer getUpdateContractDueDate() {
        return updateContractDueDate;
    }

    public void setUpdateContractDueDate(Integer updateContractDueDate) {
        this.updateContractDueDate = updateContractDueDate;
    }
}
