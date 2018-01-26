package com.hongshaohua.jtools.common.utils.time;

import java.util.concurrent.TimeUnit;

/**
 * Created by Aska on 2018/1/19.
 */
public class TimeUtilsSecond {

    public static final String DEFAULT_PATTERN_SECOND = "yyyy-MM-dd HH:mm:ss";

    public static String seconds2Date(long seconds, int timezone, String pattern) {
        return TimeUtilsMillisecond.milliseconds2Date(TimeUnit.SECONDS.toMillis(seconds), timezone, pattern);
    }

    public static String seconds2Date(long seconds, int timezone) {
        return seconds2Date(seconds, timezone, DEFAULT_PATTERN_SECOND);
    }

    public static String seconds2Date(long seconds, String pattern) {
        return TimeUtilsMillisecond.milliseconds2Date(TimeUnit.SECONDS.toMillis(seconds), pattern);
    }

    public static String seconds2Date(long seconds) {
        return seconds2Date(seconds, DEFAULT_PATTERN_SECOND);
    }

    public static long currentSeconds() {
        return TimeUnit.MILLISECONDS.toSeconds(TimeUtilsMillisecond.currentMilliseconds());
    }

    public static String currentDate() {
        return seconds2Date(currentSeconds());
    }

    public static long date2Seconds(String date, String pattern) throws Exception {
        return TimeUnit.MILLISECONDS.toSeconds(TimeUtilsMillisecond.date2Milliseconds(date, pattern));
    }

    public static long date2Seconds(String date) throws Exception {
        return date2Seconds(date, DEFAULT_PATTERN_SECOND);
    }
}
