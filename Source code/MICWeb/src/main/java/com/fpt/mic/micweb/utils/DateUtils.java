package com.fpt.mic.micweb.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
}
