package com.project.market.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public Date getFormatsDateMilli() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        Date dates = cal.getTime();
        return dates;
    }

    public String getFormatsDateMilliString() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        Date dates = cal.getTime();
        return df.format(dates);
    }

    public String generateDatetimeAndMilliSecString(String date) throws ParseException {
        String formatDate = "yyyy-MM-dd HH:mm:ss.SSS";
        DateFormat dateFormat = new SimpleDateFormat(formatDate);
        SimpleDateFormat df = new SimpleDateFormat(formatDate);

        Date d = df.parse(date);
        return dateFormat.format(d);
    }

    public Date generateDatetimeAndMilliSec(String date) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(df.parse(date));

        Date dates = cal.getTime();
        return dates;
    }
}
