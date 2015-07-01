package com.fpt.mic.micweb.controller.scheduler;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JsonString;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.SchedulerBusiness;

import javax.servlet.annotation.WebServlet;
import java.util.Date;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 7/2/15.
 */
@WebServlet(name = "ManualSchedulerController", urlPatterns = "/scheduler")
public class ManualSchedulerController extends BasicController {
    public ResponseObject getView(R r) {
        System.out.println("Executing Schedule Job " + (new Date()));
        SchedulerBusiness schedulerBusiness = new SchedulerBusiness();
        schedulerBusiness.updateContracts();
        return new JsonString("true");
    }
}
