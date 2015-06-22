package com.fpt.mic.micweb.model.dto;

/**
 * Created by TriPQMSE60746 on 06/10/2015.
 */
public class CheckoutRequestDto {

    private String paymentrequest_name;
    private String paymentrequest_desc;
    private String paymentrequest_qty;
    private String paymentrequest_itemamt;
    private String paymentrequest_taxamt;
    private String paymentrequest_amt;
    private String currencycodetype;
    private String paymenttype;
    private String paymentrequest_amt_l;

    public CheckoutRequestDto() {
    }

    public CheckoutRequestDto(String paymentrequest_name, String paymentrequest_desc, String paymentrequest_qty, String paymentrequest_itemamt, String paymentrequest_taxamt, String paymentrequest_amt, String currencycodetype, String paymenttype, String paymentrequest_amt_l) {
        this.paymentrequest_name = paymentrequest_name;
        this.paymentrequest_desc = paymentrequest_desc;
        this.paymentrequest_qty = paymentrequest_qty;
        this.paymentrequest_itemamt = paymentrequest_itemamt;
        this.paymentrequest_taxamt = paymentrequest_taxamt;
        this.paymentrequest_amt = paymentrequest_amt;
        this.currencycodetype = currencycodetype;
        this.paymenttype = paymenttype;
        this.paymentrequest_amt_l = paymentrequest_amt_l;
    }

    public String getQueryString() {
        return  "L_PAYMENTREQUEST_0_NAME0="     + this.paymentrequest_name + "&"+
                "L_PAYMENTREQUEST_0_DESC0="     + this.paymentrequest_desc + "&"+
                "L_PAYMENTREQUEST_0_QTY0="      + this.paymentrequest_qty + "&"+
                "PAYMENTREQUEST_0_ITEMAMT="     + this.paymentrequest_itemamt + "&"+
                "PAYMENTREQUEST_0_TAXAMT="      + this.paymentrequest_taxamt + "&"+
                "PAYMENTREQUEST_0_AMT="         + this.paymentrequest_amt + "&"+
                "currencyCodeType="             + this.currencycodetype + "&"+
                "paymentType="                  + this.paymenttype + "&"+
                "L_PAYMENTREQUEST_0_AMT="       + this.paymentrequest_amt_l;

    }

    public String getPaymentrequest_name() {
        return paymentrequest_name;
    }

    public void setPaymentrequest_name(String paymentrequest_name) {
        this.paymentrequest_name = paymentrequest_name;
    }

    public String getPaymentrequest_desc() {
        return paymentrequest_desc;
    }

    public void setPaymentrequest_desc(String paymentrequest_desc) {
        this.paymentrequest_desc = paymentrequest_desc;
    }

    public String getPaymentrequest_qty() {
        return paymentrequest_qty;
    }

    public void setPaymentrequest_qty(String paymentrequest_qty) {
        this.paymentrequest_qty = paymentrequest_qty;
    }

    public String getPaymentrequest_itemamt() {
        return paymentrequest_itemamt;
    }

    public void setPaymentrequest_itemamt(String paymentrequest_itemamt) {
        this.paymentrequest_itemamt = paymentrequest_itemamt;
    }

    public String getPaymentrequest_taxamt() {
        return paymentrequest_taxamt;
    }

    public void setPaymentrequest_taxamt(String paymentrequest_taxamt) {
        this.paymentrequest_taxamt = paymentrequest_taxamt;
    }

    public String getPaymentrequest_amt() {
        return paymentrequest_amt;
    }

    public void setPaymentrequest_amt(String paymentrequest_amt) {
        this.paymentrequest_amt = paymentrequest_amt;
    }

    public String getCurrencycodetype() {
        return currencycodetype;
    }

    public void setCurrencycodetype(String currencycodetype) {
        this.currencycodetype = currencycodetype;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getPaymentrequest_amt_l() {
        return paymentrequest_amt_l;
    }

    public void setPaymentrequest_amt_l(String paymentrequest_amt_l) {
        this.paymentrequest_amt_l = paymentrequest_amt_l;
    }
}