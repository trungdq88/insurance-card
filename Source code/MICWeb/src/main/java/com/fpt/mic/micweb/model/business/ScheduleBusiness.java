package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.CardDao;
import com.fpt.mic.micweb.model.dao.CustomerDao;
import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dao.PaymentDao;
import com.fpt.mic.micweb.model.entity.CardEntity;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.PaymentEntity;
import com.fpt.mic.micweb.utils.Constants;
import com.fpt.mic.micweb.utils.DateUtils;
import sun.util.calendar.BaseCalendar;
import sun.util.calendar.LocalGregorianCalendar;

import java.awt.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.lang.Thread;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by PhucNguyen on 24/06/2015.
 */
public class ScheduleBusiness {
    //    Timer timer = new Timer();
    Thread t = new Thread() {
        @Override
        public void run() {
            while (true) {
                try {
                    Calendar calendar = Calendar.getInstance();
                    java.util.Date date = new java.util.Date();
                    long hourNow = date.getHours();
                    long minuteNow = date.getMinutes();
                    //long secondsNow = date.getTime()/1000;
                    long canculatHour = 24 - hourNow;
                    long caculatMinute = 60 - minuteNow;
                    //long caculatSec = 60 - millisecondsNow;
                    long timeRemain = canculatHour * 60 * 60 * 1000 + caculatMinute * 60 * 1000;
                    Thread.sleep(timeRemain);
                    //write function here
                    TrackContracStatus();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public void TrackContracStatus() {
        ContractDao contractDao = new ContractDao();
        List<ContractEntity> listContract = contractDao.getAllContract();
        java.util.Date date = new java.util.Date();

        for (ContractEntity contract : listContract) {
            if ((new Timestamp(date.getTime()).after(contract.getExpiredDate()))) {
                contract.setStatus(Constants.ContractStatus.EXPIRED);
            }
            contractDao.update(contract);
        }

    }
    public void startTask() {
        t.start();
    }


}
