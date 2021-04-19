package com.ashen.authority.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期与字符串转换工具类
 */
public class DateUtils {

    /**
     * 日期转字符串
     */
    public static String date2String(Date date, String patten) {
        return new SimpleDateFormat(patten).format(date);
    }

    /**
     * 字符串转日期
     */
    public static Date String2Date(String dateStr, String patten) throws ParseException {
        return new SimpleDateFormat(patten).parse(dateStr);
    }
}
