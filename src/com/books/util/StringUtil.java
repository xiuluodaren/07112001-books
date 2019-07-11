package com.books.util;

/**
 * 作者:修罗大人
 * 时间:2019/1/16 下午3:54
 * 描述:StringUtil
 */

public class StringUtil {

    public static String upperFirstWord(String str) {
        String temp = str.substring(0, 1);
        return temp.toUpperCase() + str.substring(1);
    }

    public static boolean isEmpty(String s)
    {
        return s == null || "".equals(s);
    }

}
