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
public class TimeUtilsMillisecond {

    public static final String DEFAULT_PATTERN_MILLISECOND = "yyyy-MM-dd HH:mm:ss:SSS";

    public static String milliseconds2Date(long milliseconds, int timezone, String pattern) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTimeInMillis(milliseconds);
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        int dstZoneOffset = (int) TimeUnit.HOURS.toMillis(timezone);
        int dstOffset = cal.get(Calendar.DST_OFFSET);
        cal.add(Calendar.MILLISECOND, -(zoneOffset - dstZoneOffset + dstOffset));
        Date date = new Date(cal.getTimeInMillis());
        DateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static String milliseconds2Date(long milliseconds, int timezone) {
        return milliseconds2Date(milliseconds, timezone, DEFAULT_PATTERN_MILLISECOND);
    }

    public static String milliseconds2Date(long milliseconds, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date(milliseconds));
    }

    public static String milliseconds2Date(long milliseconds) {
        return milliseconds2Date(milliseconds, DEFAULT_PATTERN_MILLISECOND);
    }

    public static long currentMilliseconds() {
        return System.currentTimeMillis();
    }

    public static String currentDate() {
        return milliseconds2Date(currentMilliseconds());
    }

    public static long date2Milliseconds(String date, String pattern) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.parse(date).getTime();
    }

    public static long date2Milliseconds(String date) throws Exception {
        return date2Milliseconds(date, DEFAULT_PATTERN_MILLISECOND);
    }
}
