package com.fpt.mic.micweb.controller.scheduler;

import com.fpt.mic.micweb.model.business.SchedulerBusiness;
import com.fpt.mic.micweb.model.dao.ContractDao;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class SchedulerJob implements Job {

    @Override
    public void execute(final JobExecutionContext ctx)
            throws JobExecutionException {

        System.out.println("Executing Job " + (new Date()));
        ContractDao contractDao = new ContractDao();
        Long count = contractDao.getAllContractCount();
        System.out.println("Count: " + count);
        SchedulerBusiness schedulerBusiness = new SchedulerBusiness();
        schedulerBusiness.updateContracts();

    }

}