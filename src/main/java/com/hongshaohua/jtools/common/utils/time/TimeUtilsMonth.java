package com.hongshaohua.jtools.common.utils.time;

import java.util.concurrent.TimeUnit;

/**
 * Created by Aska on 2018/1/22.
 */
public class TimeUtilsMonth {

    public static final String DEFAULT_PATTERN_MONTH = "yyyy-MM";

    public static String milliseconds2Month(long milliseconds) {
        return TimeUtilsMillisecond.milliseconds2Date(milliseconds, DEFAULT_PATTERN_MONTH);
    }

    public static String seconds2Month(long seconds) {
        return TimeUtilsSecond.seconds2Date(seconds, DEFAULT_PATTERN_MONTH);
    }

    public static String thisMonth() {
        return milliseconds2Month(TimeUtilsMillisecond.currentMilliseconds());
    }

    public static long month2Milliseconds(String month) throws Exception {
        return TimeUtilsMillisecond.date2Milliseconds(month, DEFAULT_PATTERN_MONTH);
    }

    public static long month2Seconds(String month) throws Exception {
        return TimeUtilsSecond.date2Seconds(month, DEFAULT_PATTERN_MONTH);
    }

    public static int month2MonthInt(String month) {
        String y = month.substring(0, 4);
        String m = month.substring(5, 7);
        String time = y + m;
        return Integer.parseInt(time);
    }

    public static int milliseconds2MonthInt(long milliseconds) {
        return month2MonthInt(milliseconds2Month(milliseconds));
    }

    public static int seconds2MonthInt(long seconds) {
        return month2MonthInt(seconds2Month(seconds));
    }

    public static int month2MonthIndex(String month) {
        String m = month.substring(5, 7);
        String time = m;
        return Integer.parseInt(time);
    }

    public static int milliseconds2MonthIndex(long milliseconds) {
        return month2MonthIndex(milliseconds2Month(milliseconds));
    }

    public static int seconds2MonthIndex(long seconds) {
        return month2MonthIndex(seconds2Month(seconds));
    }

    public static String monthInt2Month(int monthInt) {
        String timeStr = monthInt + "";
        if(timeStr.length() != 8) {
            return null;
        }
        String y = timeStr.substring(0, 4);
        String m = timeStr.substring(4, 6);
        return y + "-" + m;
    }

    public static long monthInt2Milliseconds(int monthInt) throws Exception {
        return month2Milliseconds(monthInt2Month(monthInt));
    }

    public static long monthInt2Seconds(int monthInt) throws Exception {
        return month2Seconds(monthInt2Month(monthInt));
    }

    public static long monthStartMilliseconds(String month) throws Exception {
        month = month + "-01 00:00:00:000";
        return TimeUtilsMillisecond.date2Milliseconds(month);
    }

    public static long monthStartMilliseconds(long milliseconds) throws Exception {
        String month = milliseconds2Month(milliseconds);
        return monthStartMilliseconds(month);
    }

    public static long monthStartSeconds(String month) throws Exception {
        month = month + "-01 00:00:00";
        return TimeUtilsSecond.date2Seconds(month);
    }

    public static long monthStartSeconds(long seconds) throws Exception {
        String month = seconds2Month(seconds);
        return monthStartSeconds(month);
    }

    public static long nextMonthStartMilliseconds(long milliseconds) throws Exception {
        //由于月份天数的不定长
        //使用先获取当月第一天的时间戳加上32天时间在计算一次当月开始时间
        long monthStartMilliseconds = monthStartMilliseconds(milliseconds);
        long nextMonthTime = monthStartMilliseconds + TimeUnit.DAYS.toMillis(32);
        return monthStartMilliseconds(nextMonthTime);
    }

    public static long nextMonthStartSeconds(long seconds) throws Exception {
        //由于月份天数的不定长
        //使用先获取当月第一天的时间戳加上32天时间在计算一次当月开始时间
        long monthStartSeconds = monthStartSeconds(seconds);
        long nextMonthTime = monthStartSeconds + TimeUnit.DAYS.toSeconds(32);
        return monthStartSeconds(nextMonthTime);
    }

    public static long monthEndMilliseconds(long milliseconds) throws Exception {
        return nextMonthStartMilliseconds(milliseconds) - 1;
    }

    public static long monthEndSeconds(long seconds) throws Exception {
        return nextMonthStartSeconds(seconds) - 1;
    }
}
