package com.books.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 作者: 修罗大人<br>
 * 时间: 2019-06-09 16:15<br>
 * 邮箱: 1448564807@qq.com<br>
 * 描述: <br>
 */
public class DateUtil {

    public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    public static String getDateTimeAsString(LocalDateTime localDateTime, String format) {
        if (localDateTime == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }

}
