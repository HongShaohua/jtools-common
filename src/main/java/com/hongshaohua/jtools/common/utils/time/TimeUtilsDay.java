package com.hongshaohua.jtools.common.utils.time;

import java.util.concurrent.TimeUnit;

/**
 * Created by Aska on 2018/1/19.
 */
public class TimeUtilsDay {

    public static final String DEFAULT_PATTERN_DAY = "yyyy-MM-dd";

    public static String milliseconds2Day(long milliseconds) {
        return TimeUtilsMillisecond.milliseconds2Date(milliseconds, DEFAULT_PATTERN_DAY);
    }

    public static String seconds2Day(long seconds) {
        return TimeUtilsSecond.seconds2Date(seconds, DEFAULT_PATTERN_DAY);
    }

    public static String thisDay() {
        return milliseconds2Day(TimeUtilsMillisecond.currentMilliseconds());
    }

    public static long day2Milliseconds(String day) throws Exception {
        return TimeUtilsMillisecond.date2Milliseconds(day, DEFAULT_PATTERN_DAY);
    }

    public static long day2Seconds(String day) throws Exception {
        return TimeUtilsSecond.date2Seconds(day, DEFAULT_PATTERN_DAY);
    }

    public static int day2DayInt(String day) {
        String y = day.substring(0, 4);
        String m = day.substring(5, 7);
        String d = day.substring(8, 10);
        String time = y + m + d;
        return Integer.parseInt(time);
    }

    public static int milliseconds2DayInt(long milliseconds) {
        return day2DayInt(milliseconds2Day(milliseconds));
    }

    public static int seconds2DayInt(long seconds) {
        return day2DayInt(seconds2Day(seconds));
    }

    public static int day2DayIndex(String day) {
        String d = day.substring(8, 10);
        String time = d;
        return Integer.parseInt(time);
    }

    public static int milliseconds2DayIndex(long milliseconds) {
        return day2DayIndex(milliseconds2Day(milliseconds));
    }

    public static int seconds2DayIndex(long seconds) {
        return day2DayIndex(seconds2Day(seconds));
    }

    public static String dayInt2Day(int dayInt) {
        String timeStr = dayInt + "";
        if(timeStr.length() != 8) {
            return null;
        }
        String y = timeStr.substring(0, 4);
        String m = timeStr.substring(4, 6);
        String d = timeStr.substring(6, 8);
        return y + "-" + m + "-" + d;
    }

    public static long dayInt2Milliseconds(int dayInt) throws Exception {
        return day2Milliseconds(dayInt2Day(dayInt));
    }

    public static long dayInt2Seconds(int dayInt) throws Exception {
        return day2Seconds(dayInt2Day(dayInt));
    }

    public static long dayStartMilliseconds(String day) throws Exception {
        day = day + " 00:00:00:000";
        return TimeUtilsMillisecond.date2Milliseconds(day);
    }

    public static long dayStartMilliseconds(long milliseconds) throws Exception {
        String day = milliseconds2Day(milliseconds);
        return dayStartMilliseconds(day);
    }

    public static long dayStartSeconds(String day) throws Exception {
        day = day + " 00:00:00";
        return TimeUtilsSecond.date2Seconds(day);
    }

    public static long dayStartSeconds(long seconds) throws Exception {
        String day = seconds2Day(seconds);
        return dayStartSeconds(day);
    }

    public static long nextDayStartMilliseconds(long milliseconds) throws Exception {
        return dayStartMilliseconds(milliseconds + TimeUnit.DAYS.toMillis(1));
    }

    public static long nextDayStartSeconds(long seconds) throws Exception {
        return dayStartSeconds(seconds + TimeUnit.DAYS.toSeconds(1));
    }

    public static long dayEndMilliseconds(long milliseconds) throws Exception {
        return nextDayStartMilliseconds(milliseconds) - 1;
    }

    public static long dayEndSeconds(long seconds) throws Exception {
        return nextDayStartSeconds(seconds) - 1;
    }
}
