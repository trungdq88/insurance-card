package com.fpt.mic.micweb.model.dto;

/**
 * Created by PhucNguyen on 28/07/2015.
 * check show or hide button renew and cancelled in contract detail of customer
 */
public class CheckShowingRenewCanceled {
    private String checkRenew;
    private String checkCancelled;
    public CheckShowingRenewCanceled() {
    }
    public CheckShowingRenewCanceled(String checkRenew, String checkCancelled) {
        this.checkRenew = checkRenew;
        this.checkCancelled = checkCancelled;
    }

    public void setCheckRenew(String checkRenew) {
        this.checkRenew = checkRenew;
    }

    public void setCheckCancelled(String checkCancelled) {
        this.checkCancelled = checkCancelled;
    }

    public String getCheckRenew() {

        return checkRenew;
    }

    public String getCheckCancelled() {
        return checkCancelled;
    }
}
