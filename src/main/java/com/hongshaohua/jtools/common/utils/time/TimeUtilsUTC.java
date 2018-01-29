package com.hongshaohua.jtools.common.utils.time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by Aska on 2018/1/19.
 */
public class TimeUtilsUTC {

    public static String UTCDate(String pattern) {
        Calendar cal = GregorianCalendar.getInstance();
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        int dstOffset = cal.get(Calendar.DST_OFFSET);
        cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        Date date = new Date(cal.getTimeInMillis());
        DateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static String UTCDateMillisecond() {
        return UTCDate(TimeUtilsMillisecond.DEFAULT_PATTERN_MILLISECOND);
    }

    public static String UTCDateSecond() {
        return UTCDate(TimeUtilsSecond.DEFAULT_PATTERN_SECOND);
    }

    public static String milliseconds2UTCDate(long milliseconds, String pattern) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTimeInMillis(milliseconds);
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        int dstOffset = cal.get(Calendar.DST_OFFSET);
        cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        Date date = new Date(cal.getTimeInMillis());
        DateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static String seconds2UTCDate(long seconds, String pattern) {
        return milliseconds2UTCDate(TimeUnit.SECONDS.toMillis(seconds), pattern);
    }

    public static String milliseconds2UTCDateMillisecond(long milliseconds) {
        return milliseconds2UTCDate(milliseconds, TimeUtilsMillisecond.DEFAULT_PATTERN_MILLISECOND);
    }

    public static String seconds2UTCDateSecond(long seconds) {
        return seconds2UTCDate(seconds, TimeUtilsSecond.DEFAULT_PATTERN_SECOND);
    }
}
