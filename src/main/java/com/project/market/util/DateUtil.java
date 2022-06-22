package com.project.market.util;

import org.apache.commons.lang.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public Date getDaysTimes(Date date, boolean isMaxTime) {
        Date returnDate = null;

        if (date != null) {
            if (isMaxTime) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1;
                int day = cal.get(Calendar.DAY_OF_MONTH);
                LocalDateTime newDate = LocalDateTime.of(year, month, day, 23, 59, 59);
                returnDate = Date.from(newDate.atZone(ZoneId.systemDefault()).toInstant());
            } else {
                returnDate = DateUtils.truncate(date, Calendar.DATE);
            }
        }

        return returnDate;
    }

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

    public String getFormatsDateString() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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

    public Date generateDatetime(String date) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(df.parse(date));

        Date dates = cal.getTime();
        return dates;
    }

    public static String generateDatetimeToString(Date date) throws ParseException {
        String formatDate = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(formatDate);

        return dateFormat.format(date);
    }

    public static String convert2FullMonth(String month) {
        String monthname = "";
        if (month.equals("01")) {
            monthname = "มกราคม";
        } else if (month.equals("02")) {
            monthname = "กุมภาพันธ์";
        } else if (month.equals("03")) {
            monthname = "มีนาคม";
        } else if (month.equals("04")) {
            monthname = "เมษายน";
        } else if (month.equals("05")) {
            monthname = "พฤษภาคม";
        } else if (month.equals("06")) {
            monthname = "มิถุนายน";
        } else if (month.equals("07")) {
            monthname = "กรกฎาคม";
        } else if (month.equals("08")) {
            monthname = "สิงหาคม";
        } else if (month.equals("09")) {
            monthname = "กันยายน";
        } else if (month.equals("10")) {
            monthname = "ตุลาคม";
        } else if (month.equals("11")) {
            monthname = "พฤศจิกายน";
        } else if (month.equals("12")) {
            monthname = "ธันวาคม";
        }
        return monthname;
    }

    public static String convertFullDateThai(Date date) throws ParseException {
        String dateTime = generateDatetimeToString(date);
        String day = String.valueOf(Integer.valueOf(dateTime.substring(8, 10)));
        String month = convert2FullMonth(dateTime.substring(5, 7));
        String year = String.valueOf(Integer.parseInt(dateTime.substring(0, 4)) + 543);

        return day + " " + month + " " + year;
    }
}
