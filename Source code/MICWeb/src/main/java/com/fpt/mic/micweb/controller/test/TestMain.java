package com.fpt.mic.micweb.controller.test;

import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.ReadableInstant;

import java.security.SecureRandom;
import java.util.Date;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/17/15.
 */
public class TestMain {
    public static void main(String[] args) {
        Date d1 = new Date(2015, 7, 29);
        Date d2 = new Date(2017, 8, 15);



        int diff = getMonthsBetween(d1, d2);
        System.out.println(diff + "<<<<");
    }

    public static int getMonthsBetween(Date d1, Date d2) {
        LocalDate date1 = new LocalDate(d1);
        LocalDate date2 = new LocalDate(d2);

        Months months = Months.monthsBetween(date1, date2);

        int diff = months.getMonths();

        LocalDate compare = date1.plusMonths(diff);
        if (compare.isBefore(date2)) {
            diff++;
        }

        return diff;
    }
}
