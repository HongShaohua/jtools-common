package com.hongshaohua.jtools.common.utils;

/**
 * Created by Aska on 2017/3/29.
 */
public class IntUtils {

    public static int Parse(String strInt) {
        return Integer.parseInt(strInt);
    }

    public static int Parse(String strInt, int defInt) {
        int result = defInt;
        try {
            result = Integer.parseInt(strInt);
        } catch (NumberFormatException e) {
            result = defInt;
        }
        return result;
    }

    public static boolean CanParse(String strInt) {
        try {
            Integer.parseInt(strInt);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }
}
