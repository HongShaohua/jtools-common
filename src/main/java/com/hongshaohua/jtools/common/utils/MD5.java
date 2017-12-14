package com.hongshaohua.jtools.common.utils;

import java.security.MessageDigest;

/**
 * Created by Aska on 2017/10/11.
 */
public class MD5 {

    public static String encode(byte[] data) {
        String md5str = "";
        try {
            // 1 创建一个提供信息摘要算法的对象，初始化为md5算法对象
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 3 计算后获得字节数组,这就是那128位了
            byte[] buff = md.digest(data);

            // 4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串
            md5str = bytes2HexString(buff);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5str;
    }

    public static String encode(String data) {
        return encode(data.getBytes());
    }

    private static String bytes2HexString(byte[] b) {
        String r = "";

        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            r += hex.toLowerCase();
        }

        return r;
    }
}
