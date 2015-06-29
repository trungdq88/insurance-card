package com.fpt.mic.micweb.utils;

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
        System.out.println(timeStamp.toString());

        return timeStamp;
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

    public static Timestamp currentDateWithoutTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date dateWithoutTime = cal.getTime();
        return new Timestamp(dateWithoutTime.getTime());
    }
}
