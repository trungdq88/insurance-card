package com.fpt.mic.micweb.utils;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by TriPQM on 06/08/2015.
 * Modified by KhaNC on 06/08/2015.
 */
public class DateUtils {
    private static final int MILLIS_IN_SECOND = 1000;
    private static final int SECONDS_IN_MINUTE = 60;
    private static final int MINUTES_IN_HOUR = 60;
    private static final int HOURS_IN_DAY = 24;
    public static Timestamp stringToTime(String inputDate) {
        Timestamp timeStamp = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(inputDate);
            long time = parsedDate.getTime();
            timeStamp = new Timestamp(time);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        if (timeStamp == null) {
            return timeStamp = new Timestamp(new Date().getTime());
        }
        return timeStamp;
    }
    public static Timestamp addFiveYear (Timestamp timestamp) {
        Date dt = new Date(timestamp.getTime());
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.YEAR, 5);
        dt = c.getTime();
        timestamp = new Timestamp(dt.getTime());
        return timestamp;
    }
    public static Timestamp addOneYear (Timestamp timestamp) {
        Date dt = new Date(timestamp.getTime());
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.YEAR, 1);
        dt = c.getTime();
        timestamp = new Timestamp(dt.getTime());
        return timestamp;
    }
    public static Timestamp addMonth(Timestamp timestamp, int month) {
        Date dt = new Date(timestamp.getTime());
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.MONTH, month);
        dt = c.getTime();
        timestamp = new Timestamp(dt.getTime());
        return timestamp;
    }

    public static Timestamp currentDateWithoutTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date dateWithoutTime = cal.getTime();
        return new Timestamp(dateWithoutTime.getTime());
    }

    public static Timestamp convertDateTimeToDate (Timestamp date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set( Calendar.HOUR_OF_DAY, 0);
        cal.set( Calendar.MINUTE, 0);
        cal.set( Calendar.SECOND, 0);
        cal.set( Calendar.MILLISECOND, 0);
        Date day = cal.getTime();
        return new Timestamp(day.getTime());
    }

    public static long dateBetween(Timestamp t1, Timestamp t2) {
        long diffTime = t2.getTime() - t1.getTime();
        return diffTime / (MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY);
    }

    public static Timestamp currentTimeWithoutNanos() {
        Timestamp currentTime = new Timestamp(new Date().getTime());
        currentTime.setNanos(0);
        return currentTime;
    }
}
