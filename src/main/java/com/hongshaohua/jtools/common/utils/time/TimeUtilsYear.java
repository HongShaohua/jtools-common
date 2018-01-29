package com.hongshaohua.jtools.common.utils.time;

import java.util.concurrent.TimeUnit;

/**
 * Created by Aska on 2018/1/22.
 */
public class TimeUtilsYear {

    public static final String DEFAULT_PATTERN_YEAR = "yyyy";

    public static String milliseconds2Year(long milliseconds) {
        return TimeUtilsMillisecond.milliseconds2Date(milliseconds, DEFAULT_PATTERN_YEAR);
    }

    public static String seconds2Year(long seconds) {
        return TimeUtilsSecond.seconds2Date(seconds, DEFAULT_PATTERN_YEAR);
    }

    public static String thisYear() {
        return milliseconds2Year(TimeUtilsMillisecond.currentMilliseconds());
    }

    public static long year2Milliseconds(String year) throws Exception {
        return TimeUtilsMillisecond.date2Milliseconds(year, DEFAULT_PATTERN_YEAR);
    }

    public static long year2Seconds(String year) throws Exception {
        return TimeUtilsSecond.date2Seconds(year, DEFAULT_PATTERN_YEAR);
    }

    public static long year2Milliseconds(int year) throws Exception {
        return year2Milliseconds(year + "");
    }

    public static long year2Seconds(int year) throws Exception {
        return year2Seconds(year + "");
    }

    public static int milliseconds2YearInt(long milliseconds) {
        return Integer.parseInt(milliseconds2Year(milliseconds));
    }

    public static int seconds2YearInt(long seconds) {
        return Integer.parseInt(seconds2Year(seconds));
    }

    public static int milliseconds2YearIndex(long milliseconds) {
        return Integer.parseInt(milliseconds2Year(milliseconds));
    }

    public static int seconds2YearIndex(long seconds) {
        return Integer.parseInt(seconds2Year(seconds));
    }

    public static long yearStartMilliseconds(String year) throws Exception {
        year = year + "-01-01 00:00:00:000";
        return TimeUtilsMillisecond.date2Milliseconds(year);
    }

    public static long yearStartMilliseconds(long milliseconds) throws Exception {
        String year = milliseconds2Year(milliseconds);
        return yearStartMilliseconds(year);
    }

    public static long yearStartSeconds(String year) throws Exception {
        year = year + "-01-01 00:00:00";
        return TimeUtilsSecond.date2Seconds(year);
    }

    public static long yearStartSeconds(long seconds) throws Exception {
        String year = seconds2Year(seconds);
        return yearStartSeconds(year);
    }

    public static long nextYearStartMilliseconds(long milliseconds) throws Exception {
        //由于年天数的不定长
        //使用先获取当年第一天的时间戳加上367天时间在计算一次当年开始时间
        long yearStartMilliseconds = yearStartMilliseconds(milliseconds);
        long nextYearTime = yearStartMilliseconds + TimeUnit.DAYS.toMillis(367);
        return yearStartMilliseconds(nextYearTime);
    }

    public static long nextYearStartSeconds(long seconds) throws Exception {
        //由于年天数的不定长
        //使用先获取当年第一天的时间戳加上367天时间在计算一次当年开始时间
        long yearStartSeconds = yearStartSeconds(seconds);
        long nextYearTime = yearStartSeconds + TimeUnit.DAYS.toSeconds(367);
        return yearStartSeconds(nextYearTime);
    }

    public static long yearEndMilliseconds(long milliseconds) throws Exception {
        return nextYearStartMilliseconds(milliseconds) - 1;
    }

    public static long yearEndSeconds(long seconds) throws Exception {
        return nextYearStartSeconds(seconds) - 1;
    }
}
