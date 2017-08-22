package com.wonders.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * 日期类型 Date 转 String
     *
     * @param date    要转换的Date类型日期
     * @param pattern 日期格式 eg:"yyyy-MM-dd HH:mm:ss"
     * @return 结果
     */
    public static String dateToString(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * 日期类型 String 转 Date
     *
     * @param source  要转换的String类型日期
     * @param pattern 日期格式 eg:"yyyy-MM-dd HH:mm:ss"
     * @return 结果
     * @throws ParseException 解析异常
     */
    public static Date stringToDate(String source, String pattern) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.parse(source);
    }

    /**
     * 获取当前的String类型日期
     *
     * @param pattern
     * @return
     */
    public static String currentDateToString(String pattern) {
        return dateToString(new Date(), pattern);
    }

    /**
     * 拼接startDate
     *
     * @param startDate 需要拼接的日期
     * @return 该日期的的当天起始时间
     */
    public static String appendStart(String startDate) {
        return (null != startDate && !startDate.equals("")) ? startDate + " 00:00:00" : null;
    }

    /**
     * 拼接endDate
     *
     * @param endDate 需要拼接的日期
     * @return 该日期的的当天截止时间
     */
    public static String appendEnd(String endDate) {
        return (null != endDate && !endDate.equals("")) ? endDate + " 23:59:59" : null;
    }
}
