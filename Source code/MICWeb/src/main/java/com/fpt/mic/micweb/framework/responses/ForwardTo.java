package com.fpt.mic.micweb.framework.responses;

/**
 * Created by UDEWQ on 06/10/2015.
 */
public class ForwardTo extends ResponseObject {
    private String controllerUrl;
    public ForwardTo(String controllerUrl) {
        this.controllerUrl = controllerUrl;
    }

    public String getControllerUrl() {
        return controllerUrl;
    }

    public void setControllerUrl(String controllerUrl) {
        this.controllerUrl = controllerUrl;
    }
}
