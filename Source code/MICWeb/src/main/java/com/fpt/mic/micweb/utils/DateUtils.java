package com.fpt.mic.micweb.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by UDEWQ on 06/08/2015.
 */
public class  DateUtils {
    public static Timestamp stringToTime(String s) {
        Timestamp timestamp = null;
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(s);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
            e.printStackTrace();
        }
        if (timestamp == null){
            return timestamp = new Timestamp(new Date().getTime());
        }
        System.out.println(timestamp.toString());

        return timestamp;
    }
}
