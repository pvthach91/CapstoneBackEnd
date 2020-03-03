package com.pvthach.capstone.ultil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

    private static String pattern = "yyyy-MM-dd HH:mm:ss";

    public static Date convertToDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            return new Date();
        }
    }
}
