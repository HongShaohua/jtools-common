package com.hongshaohua.jtools.common.utils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aska on 2017/9/26.
 */
public class NameValueParser {

    public static NameValuePair parse(String data, String nameValueSplitStr) {
        String[] nameValue = data.split(nameValueSplitStr);
        if(nameValue.length != 2) {
            return null;
        }
        String name = nameValue[0];
        String value = nameValue[1];
        return new BasicNameValuePair(name, value);
    }

    public static List<NameValuePair> parseList(String data, String listSplitStr, String nameValueSplitStr) {
        List<NameValuePair> pairs = new ArrayList<>();
        String[] items = data.split(listSplitStr);
        for(String item : items) {
            NameValuePair pair = parse(item, nameValueSplitStr);
            if(pair != null) {
                pairs.add(pair);
            }
        }
        return pairs;
    }
}
