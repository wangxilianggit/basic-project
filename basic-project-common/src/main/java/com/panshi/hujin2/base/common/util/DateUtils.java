package com.panshi.hujin2.base.common.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by shenJianKang on 2017/11/28.
 */
public class DateUtils {

    public final static String FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String FORMAT1 = "dd-MM-yyyy";
    public final static String FORMAT2 = "HH:mm:ss dd-MM-yyyy";
    private final static String FORMAT3 = "yyyyMMdd";
    public final static String SIMPLE_FORMAT = "yyyy-MM-dd";


    private final static Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);



    /**
     * @title: dateCompare
     * @description: 按传入时间的‘年月日’比较日期大小(日期1在日期2之前返回-1；
     *                                           日期1在日期2之后返回1;
     *                                           相等返回0;)
     * @param date1 日期1
     * @param date2 日期2
     * @return
     */
    public static int dateCompare(Date date1, Date date2) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT3);
        String dateFirst = sdf.format(date1);
        String dateLast = sdf.format(date2);
        int dateFirstIntVal = Integer.parseInt(dateFirst);
        int dateLastIntVal = Integer.parseInt(dateLast);
        if (dateFirstIntVal > dateLastIntVal) {
            return 1;
        } else if (dateFirstIntVal < dateLastIntVal) {
            return -1;
        }
        return 0;
    }


    public static void main(String[] args) {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.MONTH, 1);
        Date newDate = calendar.getTime();

        System.out.println("dateCompare(currentDate,newDate) = " + dateCompare(currentDate,currentDate));
    }



    /**
     * 字符串转时间格式
     */
    public static Date stringToDate(String str) {
        Date date = null;
        if (!StringUtils.isEmpty(str)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                date = sdf.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    /**
     * 获取两个时间之间的秒数
     */
    public static long getSecondsBetween(Date d1, Date d2) {
        return Math.abs((d1.getTime() - d2.getTime()) / 1000);
    }

    /**
     * 获取当天第一秒
     */
    public static Date getFirstDate(Date current) {
        Calendar c = Calendar.getInstance();
        c.setTime(current);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取当天第一秒
     */
    public static Date getFirstDate() {
        return Date.from(LocalDateTime.of(LocalDate.now(), LocalTime.MIN).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取昨天第一秒
     */
    public static Date getFirstSecondYesterDay() {
        return Date.from(LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MIN).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取当天最后一秒
     */
    public static Date getEndDate(Date current) {
        Calendar c = Calendar.getInstance();
        c.setTime(current);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    /**
     * 获取当天最后一秒
     */
    public static Date getEndDate() {
        return Date.from(LocalDateTime.of(LocalDate.now(), LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取昨天最后一秒
     */
    public static Date getLastSecondYesterDay() {
        return Date.from(LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * @return int 天数
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        c1.set(Calendar.MILLISECOND, 0);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        c2.set(Calendar.MILLISECOND, 0);
        return (int) ((c2.getTimeInMillis() - c1.getTimeInMillis()) / (1000 * 3600 * 24));
    }

    /**
     * 计算两个时间的小时差,会有小数
     *
     * @param startDate 开始时间
     * @param endDate 结束时间
     */
    public static double differentHoursByMillisecond(Date startDate, Date endDate) {
        long different = endDate.getTime() - startDate.getTime();
        long minutesInMilli = 1000 * 60;
        long hoursInMilli = minutesInMilli * 60;
        // 计算差了多少小时
        double elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;
        // 计算差了多少分钟
        double elapsedMinutes = different / minutesInMilli;
        return BigDecimal.valueOf(elapsedHours)
                .add(BigDecimal.valueOf(elapsedMinutes)
                        .divide(BigDecimal.valueOf(60), 4, RoundingMode.HALF_UP))
                .setScale(1, RoundingMode.HALF_UP)
                .doubleValue();
    }

    /**
     * 计算两个时间的小时差及分钟差
     *
     * @param startDate 开始时间
     * @param endDate 结束时间
     */
    public static List<Long> differentHoursAndMinute(Date startDate, Date endDate) {
        long different = endDate.getTime() - startDate.getTime();
        long minutesInMilli = 1000 * 60;
        long hoursInMilli = minutesInMilli * 60;
        // 计算差了多少小时
        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;
        // 计算差了多少分钟
        long elapsedMinutes = different / minutesInMilli;
        List<Long> longs = new LinkedList<>();
        longs.add(elapsedHours);
        longs.add(elapsedMinutes);
        return longs;
    }

    /**
     * @return void
     */
    public static void formatParmsDate(Date startTime, Date endTime) {
        if (startTime == null && endTime != null) {
            startTime = getFirstDate(endTime);
            endTime = getEndDate(endTime);
        }
        if (endTime == null && startTime != null) {
            startTime = getFirstDate(startTime);
            endTime = getEndDate(startTime);
        }
    }

    /**
     * 计算两个日期相差的月份数
     *
     * @param date1 日期1
     * @param date2 日期2
     * @param pattern 日期1和日期2的日期格式
     *
     * @return 相差的月份数
     */
    public static int countMonths(String date1, String date2, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));
        int year = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        //开始日期若小月结束日期
        if (year < 0) {
            year = -year;
            return year * 12 + c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
        }
        return year * 12 + c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
    }

    /**
     * @param pattern 日期格式
     *
     * @return 当前日期 （字符串）
     */
    public static String getCurrentDateStr(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = new Date();
        return sdf.format(date);
    }

    /**
     * 时间比较
     *
     * @return date2>=date1 true ;date2<date1 false
     */
    public static Boolean compareWithDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(SIMPLE_FORMAT);
        String one = dateFormat.format(date1);
        String two = dateFormat.format(date2);
        if (one.compareTo(two) <= 0) {
            return true;
        }
        return false;
    }

    /**
     * 时间比较
     *
     * @return date2>=date1 true ;date2<date1 false
     */
    public static Boolean compareWithSecond(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
        String one = dateFormat.format(date1);
        String two = dateFormat.format(date2);
        if (one.compareTo(two) <= 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取墨西哥时间格式字符串
     *
     * @param date 需要转换的时间,传null就会获取当前时间
     *
     * @return HH:mm dd-MM-yyyy
     */
    public static String dateFormatMX(Date date) {
        return getDate(date);
    }

    /**
     * 获取墨西哥时间格式字符串
     *
     * @param date 需要转换的时间,传null就会获取当前时间
     *
     * @return 根据传入的日期格式返回
     */
    public static String dateFormatMX(Date date, String pattern) {
        return getDate(date, pattern);
    }

    /**
     * 获取巴西时间格式字符串
     *
     * @param date 需要转换的时间,传null就会获取当前时间
     *
     * @return HH:mm dd-MM-yyyy
     */
    public static String dateFormatBR(Date date) {
        return getDate(date);
    }

    /**
     * 获取巴西时间格式字符串
     *
     * @param date 需要转换的时间,传null就会获取当前时间
     *
     * @return 根据传入的日期格式返回
     */
    public static String dateFormatBR(Date date, String pattern) {
        return getDate(date, pattern);
    }

    private static String getDate(Date date) {
        LocalDateTime localDateTime;
        if (date != null) {
            localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        } else {
            localDateTime = LocalDateTime.now();
        }
        return localDateTime.format(DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy"));
    }

    private static String getDate(Date date, String pattern) {
        LocalDateTime localDateTime;
        if (date != null) {
            localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        } else {
            localDateTime = LocalDateTime.now();
        }
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static Date addDays(Date date, int amount) {
        return org.apache.commons.lang.time.DateUtils.addDays(date, amount);
    }

    /**
     * 计算两个时间相差多少个年
     *
     * @param start 起始年份字符串。格式如“2018-01-01”
     * @param end 结束年份字符串。格式如“2019-01-01”
     */
    public static int yearsBetween(String start, String end) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.setTime(sdf.parse(start));
        endDate.setTime(sdf.parse(end));
        return (endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR));
    }

}
