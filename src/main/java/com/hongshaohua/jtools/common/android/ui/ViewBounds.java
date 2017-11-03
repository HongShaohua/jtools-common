package com.hongshaohua.jtools.common.android.ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aska on 2017/10/19.
 */
public class ViewBounds {

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public ViewBounds(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public int getX() {
        return (x1 + x2) / 2;
    }

    public int getY() {
        return (y1 + y2) / 2;
    }

    private static String[] splitArray(String str) {
        String startKey = "[";
        String endKey = "]";

        List<String> subStrs = new ArrayList<>();

        while(true) {
            int startIndex = str.indexOf(startKey);
            if(startIndex < 0) {
                break;
            }
            int endIndex = str.indexOf(endKey);
            if(endIndex < 0) {
                break;
            }
            String subStr = str.substring(startIndex + startKey.length(), endIndex);
            subStrs.add(subStr);
            str = str.substring(endIndex + endKey.length());
        }

        return subStrs.toArray(new String[]{});
    }

    private static int[] splitXY(String str) {
        String[] subStrs = str.split(",");
        if(subStrs.length != 2) {
            return null;
        }
        return new int[]{Integer.parseInt(subStrs[0]), Integer.parseInt(subStrs[1])};
    }

    public static ViewBounds create(String boundsStr) {
        String[] strs = splitArray(boundsStr);
        if(strs.length != 2) {
            return null;
        }
        int[] xy1 = splitXY(strs[0]);
        if(xy1 == null) {
            return null;
        }
        int[] xy2 = splitXY(strs[1]);
        if(xy2 == null) {
            return null;
        }
        return new ViewBounds(xy1[0], xy1[1], xy2[0], xy2[1]);
    }
}
