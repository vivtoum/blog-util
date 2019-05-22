package io.github.vivtoum.blog.api.common.util;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * <p>
 * 标题:
 * </p>
 * <p>
 * 描述: 日期处理类
 * </p>
 * <p>
 * 版权: Copyright (c) 2006
 * </p>
 * <p>
 * 公司: 广州瑞达通信技术有限公司
 * </p>
 *
 * @版本 1.0
 */
@Slf4j
public class DateHelper {
    // 每月天数(非润年)
    static int daysInMonth[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    // 闰年的特殊月份
    static final int MONTH_FEBRUARY = 2;

    public static final int PRECISE_YEAR = 1;
    public static final int PRECISE_MONTH = 2;
    public static final int PRECISE_DAY = 3;
    public static final int PRECISE_HOUR = 4;
    public static final int PRECISE_MINUTE = 5;
    public static final int PRECISE_SECOND = 6;
    public static final int PRECISE_MilliSECOND = 7;

    public static final String FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_MINUTE = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_DAY = "yyyy-MM-dd";

    /**
     * 获得当天日期
     *
     * @return 返回yyyy-mm-dd格式的日期如2004-05-29
     */
    public static String getCurrentDateStr() {
        String curDateStr = "";
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        curDateStr = String.valueOf(year) + "-";
        curDateStr += ((month < 10) ? "0" + String.valueOf(month) : String.valueOf(month)) + "-";
        curDateStr += ((day < 10) ? "0" + String.valueOf(day) : String.valueOf(day));

        return curDateStr;
    }

    /**
     * 获得当天日期 没有"-"
     *
     * @return 返回yyyymmdd格式的日期如20040529
     */
    public static String getCurrentDateStrs() {
        String curDateStr = "";
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        curDateStr = String.valueOf(year);
        curDateStr += ((month < 10) ? "0" + String.valueOf(month) : String.valueOf(month));
        curDateStr += ((day < 10) ? "0" + String.valueOf(day) : String.valueOf(day));

        return curDateStr;
    }

    /**
     * 获得当天日期 没有"-"
     *
     * @return 返回yyyymm格式的日期如200405
     */
    public static String getCurrentDateStrs2() {
        String curDateStr = "";
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        curDateStr = String.valueOf(year);
        curDateStr += ((month < 10) ? "0" + String.valueOf(month) : String.valueOf(month));

        return curDateStr;
    }

    /**
     * 获得当天日期，不带"-"的形式
     *
     * @return 返回yymmdd格式的日期, 如040529
     */
    public static String getCurrentDateStrNoDiv() {
        String curDateStr = "";
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        curDateStr = String.valueOf(year).substring(2, 4) + "";
        curDateStr += ((month < 10) ? "0" + String.valueOf(month) : String.valueOf(month)) + "";
        curDateStr += ((day < 10) ? "0" + String.valueOf(day) : String.valueOf(day));

        return curDateStr;
    }

    /**
     * 获得当前时间，精度到毫秒
     *
     * @return hh:mm:ss.xxx
     */
    public static String getCurrentTimeStr() {
        String curTimeSr = "";
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        int milliSecond = cal.get(Calendar.MILLISECOND);
        curTimeSr = ((hour < 10) ? "0" + String.valueOf(hour) : String.valueOf(hour)) + ":";
        curTimeSr += ((minute < 10) ? "0" + String.valueOf(minute) : String.valueOf(minute)) + ":";
        curTimeSr += ((second < 10) ? "0" + String.valueOf(second) : String.valueOf(second));
        curTimeSr += "." + String.valueOf(milliSecond);

        return curTimeSr;
    }

    /**
     * 获得当前时间
     *
     * @return hh:mm:ss
     */
    public static String getTimeStr() {
        String curTimeSr = "";
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        curTimeSr = ((hour < 10) ? "0" + String.valueOf(hour) : String.valueOf(hour)) + ":";
        curTimeSr += ((minute < 10) ? "0" + String.valueOf(minute) : String.valueOf(minute)) + ":";
        curTimeSr += ((second < 10) ? "0" + String.valueOf(second) : String.valueOf(second));

        return curTimeSr;
    }

    /**
     * 获得当天时间
     *
     * @return yyyy-mm-dd hh-mm-ss
     */
    public static String getDateTimeStr() {
        String curDateTimeStr = "";
        curDateTimeStr = getCurrentDateStr() + " " + getTimeStr();
        return curDateTimeStr;
    }

    /**
     * 获得当天时间，精度到毫秒
     *
     * @return yyyy-mm-dd hh-mm-ss.xxx
     */
    public static String getCurrentDateTimeStr() {
        String curDateTimeStr = "";
        curDateTimeStr = getCurrentDateStr() + " " + getCurrentTimeStr();
        return curDateTimeStr;
    }

    /**
     * 获得当天的日历日期
     *
     * @return
     */
    public static Calendar getCurrentCalendar() {
        return Calendar.getInstance();
    }

    /**
     * 获得当前年份
     *
     * @return yyyy
     */
    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static int getCurrentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前日
     *
     * @return
     */
    public static int getCurrentDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回传入日期是星期几,返回值1-星期一;2-星期二;.......7-星期日
     *
     * @param cal 传入的日历类型日期
     * @return
     */
    public static int getDayOfWeek(Calendar cal) {
        int dayOfWeek = 1;
        dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            dayOfWeek = 7;
        } else {
            dayOfWeek = dayOfWeek - 1;
        }
        return dayOfWeek;
    }

    /**
     * <p>
     * 标题: 计算当前日期是星期几
     * </p>
     * <p>
     * 描述:
     * </p>
     *
     * @param dateStr 日期格式：yyyy-mm-dd
     * @return 返回0-6,0表是星期日、1表示星期一....
     */
    public static int getDayOfWeek(String dateStr) {
        int weekDay = 0;
        try {
            int year = Integer.parseInt(dateStr.substring(0, 4));
            int month = Integer.parseInt(dateStr.substring(5, 7));
            int date = Integer.parseInt(dateStr.substring(8));
            GregorianCalendar dc = new GregorianCalendar();
            dc.set(Calendar.MONTH, month - 1);
            dc.set(Calendar.DAY_OF_MONTH, date);
            weekDay = (dc.get(Calendar.DAY_OF_WEEK) - 1 + 7) % 7;
        } catch (Exception e) {
            log.error("获取时间报错了！", e);
        }
        return weekDay;
    }

    /**
     * 取得传入日期的月有多少天
     *
     * @param cal 日历型日期
     * @return
     */
    public static int countDaysInMonth(Calendar cal) {
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;

        // 获取当前月含有的天数, 如果是闰年的二月, 加一天.
        int daysInCurMonth = daysInMonth[month - 1];
        if (isLeapYear(year) && (month == MONTH_FEBRUARY)) {
            daysInCurMonth += 1;
        }
        return daysInCurMonth;
    }

    /**
     * 判断是否闰月
     *
     * @param year 年份
     * @return
     */
    public static boolean isLeapYear(int year) {
        // 能被100整除, 不能被400整除的年份, 不是闰年.
        // 能被100整除, 也能被400整除的年份, 是闰年.
        if ((year % 100) == 0) {
            return ((year % 400) == 0);
        } else
        // 不能被100整除, 能被4整除的年份是闰年.
        {
            return ((year % 4) == 0);
        }
    }

    /**
     * 计算当前时间加上秒钟后的时间
     *
     * @param addedSecond 在当前时间上要加的秒数，注意输入的秒钟数不能大于一个月
     * @return yyyy-mm-dd hh-mm-ss.XXXX
     */
    public static String calculateDateTime(int addedSecond) {
        // 若要限制输入的秒钟数不能大于一个月，则应在此加以判断
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        int millisecond = cal.get(Calendar.MILLISECOND);
        // 获取当前月含有的天数, 如果是闰年的二月, 加一天.
        int daysInCurMonth = daysInMonth[month - 1];
        if (isLeapYear(year) && (month == MONTH_FEBRUARY)) {
            daysInCurMonth += 1;
        }
        addedSecond += second;
        second = addedSecond % 60;
        // 输入的分钟数不能大于一个月 pay attention to here
        minute = minute + addedSecond / 60;
        // 总的小时数
        hour = hour + minute / 60;
        // 分钟数
        minute = minute % 60;
        // 总的天数
        day = day + hour / 24;
        // 小时数
        hour = hour % 24;
        if (day > daysInCurMonth) {
            // 总的月份数,限制输入的秒钟数不能大于一个月的原因在此
            month = month + day / daysInCurMonth;
            // 天数
            day = day % daysInCurMonth;
        }
        if (month > 12) {
            // 总的年数
            year = year + month / 12;
            // 月份数
            month = month % 12;
        }
        String dateTimeStr = "1900-01-01";
        dateTimeStr = String.valueOf(year) + "-";
        dateTimeStr += ((month < 10) ? "0" + String.valueOf(month) : String.valueOf(month)) + "-";
        dateTimeStr += ((day < 10) ? "0" + String.valueOf(day) : String.valueOf(day)) + " ";
        dateTimeStr += ((hour < 10) ? "0" + String.valueOf(hour) : String.valueOf(hour)) + ":";
        dateTimeStr += ((minute < 10) ? "0" + String.valueOf(minute) : String.valueOf(minute)) + ":";
        dateTimeStr += ((second < 10) ? "0" + String.valueOf(second) : String.valueOf(second));
        dateTimeStr += "." + String.valueOf(millisecond);
        return dateTimeStr;
    }

    /**
     * 某一日期加上秒数后得到的日期,返回日历型日期
     *
     * @param cal    日历型日期
     * @param second 所要加上的秒数
     * @return
     */
    public static Calendar calendarAddSecond(Calendar cal, int second) {
        Calendar newCal = Calendar.getInstance();
        newCal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                cal.get(Calendar.DATE), cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
        newCal.add(Calendar.SECOND, second);
        return newCal;
    }

    /**
     * 某一日期加上天数后得到的日期,返回日历型日期
     *
     * @param cal 日历型日期
     * @param day 所要加上的天数
     * @return
     */
    public static Calendar calendarAddDay(Calendar cal, int day) {
        Calendar newCal = Calendar.getInstance();
        newCal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                cal.get(Calendar.DATE), cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
        newCal.add(Calendar.DATE, day);
        return newCal;
    }

    /**
     * 得到指定精度的时间字符串
     *
     * @param dateTimeString 原始时间字符串，格式为yyyy-mm-dd hh:mm:ss
     * @param precise        指定的精度,可指定返回到年、月、日、小时、分钟、秒等多种形式
     * @return
     */
    public static String customDateTimeStr(String dateTimeString, int precise) {
        if (dateTimeString == null) {
            dateTimeString = "";
            return dateTimeString;
        }
        if (dateTimeString.trim().length() == 0) {
            return dateTimeString;
        }
        if (dateTimeString.startsWith("1900")) {
            dateTimeString = "";
            return dateTimeString;
        }
        if (precise == PRECISE_YEAR) {
            dateTimeString = dateTimeString.substring(0, 4);
        }
        if (precise == PRECISE_MONTH) {
            dateTimeString = dateTimeString.substring(0, 7);
        }
        if (precise == PRECISE_DAY) {
            dateTimeString = dateTimeString.substring(0, 10);
        }
        if (precise == PRECISE_HOUR) {
            dateTimeString = dateTimeString.substring(0, 13);
        }
        if (precise == PRECISE_MINUTE) {
            dateTimeString = dateTimeString.substring(0, 16);
        }
        if (precise == PRECISE_SECOND) {
            dateTimeString = dateTimeString.substring(0, 19);
        }
        return dateTimeString;
    }

    /**
     * 计算两个时间之间的时间差,利用此方法可以比较两个时间的大小
     *
     * @param strDateTime1 减数，格式为yyyy-mm-dd hh:mm:ss
     * @param strDateTime2 被减数，格式为yyyy-mm-dd hh:mm:ss
     * @return strDateTime1 - strDateTime2的时间差，单位为毫秒
     */
    public static long computeInterval(String strDateTime1, String strDateTime2) {
        long interval = 0;
        Timestamp date1 = convertStrToDate(strDateTime1);
        Timestamp date2 = convertStrToDate(strDateTime2);
        interval = date1.getTime() - date2.getTime();
        return interval;
    }

    /**
     * 计算两个时间之间的时间差,返回值单位为秒
     *
     * @param cal1 减数，格式为日历型日期
     * @param cal2 被减数，格式为日历型日期
     * @return
     */
    public static int getTwoCalInterval(Calendar cal1, Calendar cal2) {
        Timestamp date1 = converCalendarToTimestamp(cal1);
        Timestamp date2 = converCalendarToTimestamp(cal2);
        long interval = date1.getTime() - date2.getTime();
        int returnValue = new Double(Math.ceil(interval / 1000))
                .intValue();
        return returnValue;
    }

    /**
     * 组装某天日期时间,返回日历型日期
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Calendar getCalendar(int year, int month, int day, int hour, int minute, int second) {
        Calendar newCal = Calendar.getInstance();
        newCal.set(year, month, day, hour, minute, second);
        return newCal;
    }

    /**
     * 组装某天日期时间,返回日历型日期
     *
     * @param cal
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Calendar getCalendar(Calendar cal, int hour, int minute, int second) {
        Calendar newCal = Calendar.getInstance();
        newCal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), hour, minute, second);
        return newCal;
    }

    /**
     * 将传入的calendar转换成String
     *
     * @param cal
     * @return (like 1900 - 01 - 01 00 : 00 : 00.000)
     */
    public static String converCalendarToString(Calendar cal) {
        String time = "1900-01-01 00:00:00.000";
        String m, d, h, mi, s;
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int date = cal.get(Calendar.DATE);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        if (month < 10) {
            m = "0" + month;
        } else {
            m = "" + month;
        }
        if (date < 10) {
            d = "0" + date;
        } else {
            d = "" + date;
        }
        if (hour < 10) {
            h = "0" + hour;
        } else {
            h = "" + hour;
        }
        if (minute < 10) {
            mi = "0" + minute;
        } else {
            mi = "" + minute;
        }
        if (second < 10) {
            s = "0" + second;
        } else {
            s = "" + second;
        }

        time = year + "-" + m + "-" + d + " " + h + ":" + mi + ":" + s + ".000";
        return time;
    }

    /**
     * 将传入的calendar转换成Timestamp
     *
     * @param cal 日历类型日期
     * @return
     */
    public static Timestamp converCalendarToTimestamp(Calendar cal) {
        String time = "1900-01-01 00:00:00.000";
        String m, d, h, mi, s;
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int date = cal.get(Calendar.DATE);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        if (month < 10) {
            m = "0" + month;
        } else {
            m = "" + month;
        }
        if (date < 10) {
            d = "0" + date;
        } else {
            d = "" + date;
        }
        if (hour < 10) {
            h = "0" + hour;
        } else {
            h = "" + hour;
        }
        if (minute < 10) {
            mi = "0" + minute;
        } else {
            mi = "" + minute;
        }
        if (second < 10) {
            s = "0" + second;
        } else {
            s = "" + second;
        }

        time = year + "-" + m + "-" + d + " " + h + ":" + mi + ":" + s + ".000";
        Timestamp date1 = convertStrToDate(time);
        return date1;
    }

    /**
     * 将Timestamp类型的日期转变成String类型
     *
     * @param date
     * @return 字符串, 如2005-12-01 12:10:11.122
     */
    public static String convertDateToStr(Timestamp date) {
        String result = "1900-01-01 00:00:00.000";
        if (date != null) {
            result = date.toString();
        }
        return result;
    }

    public static String convertDateToStr2(Timestamp date) {
        String result = "";
        if (date != null) {
            result = date.toString();
            if (result.length() > 19) {
                result = result.substring(0, 19);
                if (result.indexOf("00:00:00") > -1) {
                    result = result.substring(0, 10);
                }
            }

        }
        return result;
    }

    /**
     * 将String类型的日期转变成Timestamp类型
     *
     * @param strDate
     * @return
     */
    public static Timestamp convertStrToDate(String strDate) {
        if (strDate == null) {
            strDate = "1900-01-01 00:00:00.000";
        } else {
            if (strDate.trim().length() == 0) {
                strDate = "1900-01-01 00:00:00.000";
                //传入的日期不包含时间
            } else if (strDate.trim().length() == 10) {
                strDate += " 00:00:00.000";
                //传入的日期包含时间到分钟位,如2000-01-01 10:10
            } else if (strDate.trim().length() == 16) {
                strDate += ":00.000";
                //传入的日期包含时间到秒位,如2000-01-01 10:10:10
            } else if (strDate.trim().length() == 19) {
                strDate += ".000";
            }
        }
        return Timestamp.valueOf(strDate);
    }

    /**
     * 把日历类型转换成短日期
     *
     * @param cal
     * @return yyyy-mm-dd
     */
    public static String convertCalToShortDateStr(Calendar cal) {
        if (cal == null) {
            return "";
        }
        String curDateStr = "";
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        curDateStr = String.valueOf(year) + "-";
        curDateStr += ((month < 10) ? "0" + String.valueOf(month) : String.valueOf(month)) + "-";
        curDateStr += ((day < 10) ? "0" + String.valueOf(day) : String.valueOf(day));
        if (curDateStr != null && curDateStr.indexOf("1900") >= 0) {
            curDateStr = "";
        }
        return curDateStr;
    }

    /**
     * 把日历类型转换成长日期
     *
     * @param cal
     * @return yyyy-mm-dd hh:mm:ss
     */
    public static String convertCalToStr(Calendar cal) {
        if (cal == null) {
            return "";
        }
        String curDateStr = "";
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);

        curDateStr = String.valueOf(year) + "-";
        curDateStr += ((month < 10) ? "0" + String.valueOf(month) : String.valueOf(month)) + "-";
        curDateStr += ((day < 10) ? "0" + String.valueOf(day) : String.valueOf(day));
        curDateStr += " " + ((hour < 10) ? "0" + String.valueOf(hour) : String.valueOf(hour));
        curDateStr += ":" + ((minute < 10) ? "0" + String.valueOf(minute) : String.valueOf(minute));
        curDateStr += ":" + ((second < 10) ? "0" + String.valueOf(second) : String.valueOf(second));
        if (curDateStr != null && curDateStr.indexOf("1900") >= 0) {
            curDateStr = "";
        }
        return curDateStr;
    }

    /**
     * 将传入的String类型日期转换成日历型日期
     *
     * @param dateTime ("2004-01-18 10:50:30")
     * @return true or false
     */
    public static Calendar converStrToCalendar(String dateTime) {
        if (dateTime == null) {
            return null;
        }
        if (dateTime.length() == 0) {
            return null;
        }
        Timestamp timestamp = convertStrToDate(dateTime);
        Date date = (Date) timestamp;
        String getYearString = dateTime.substring(0, 4);
        int year = Integer.parseInt(getYearString);
        int month = date.getMonth();
        int day = date.getDate();
        int hour = date.getHours();
        int minute = date.getMinutes();
        int second = date.getSeconds();
        return getCalendar(year, month, day, hour, minute, second);
    }

    /**
     * 将日期字串转变成短日期的Calendar类型，这个方法转换到日期，时间不转换
     *
     * @param dateStr 字串形式的日期，它可以为"2005-10-01"或"2005-10-01 10:10:10"的形式
     * @return
     */
    public static Calendar convertStrToShortCalendar(String dateStr) {
        int year = 0, month = 0, date = 0;
        if ((dateStr != null) && (dateStr.trim().length() == 0)) {
            year = 1900;
            month = 1;
            date = 1;
        } else {
            year = Integer.parseInt(dateStr.substring(0, 4));
            month = Integer.parseInt(dateStr.substring(5, 7)) - 1;
            date = Integer.parseInt(dateStr.substring(8));
        }
        Calendar calendar = new GregorianCalendar(year, month, date);
        return calendar;
    }

    /**
     * 计算两个日期之间的天数 注意两个日期格式必须相同
     *
     * @param startDate 字符串开始时间
     * @param endDate   字符串结束时间
     * @param format    时间格式
     * @return int 相差天数
     */
    public static int countDays(String startDate, String endDate, String format) {
        try {
            SimpleDateFormat sf = new SimpleDateFormat(format);
            Date sDate = sf.parse(startDate);
            Date eDate = sf.parse(endDate);
            Calendar c = Calendar.getInstance();

            c.setTime(sDate);
            long ls = c.getTimeInMillis();

            c.setTime(eDate);
            long le = c.getTimeInMillis();

            return (int) ((le - ls) / (24 * 3600 * 1000));
        } catch (Exception e) {
            log.error("计算时间报错了！", e);
        }
        return -1;
    }

    /**
     * 计算两个日期音的工作日的天数
     *
     * @param startDate 开始日期，格式为：yyyy-MM-dd
     * @param endDate   结束日期，格式为：yyyy-MM-dd
     * @param holidays  节假日数组，日期格式为：yyyy-MM-dd
     * @return
     */
    public static int countWorkDays(String startDate, String endDate, String[] holidays) {
        int workDays = 0;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(sf.parse(startDate));
            String tmpDate = startDate;
            while (tmpDate.compareTo(endDate) < 0) {
                c.add(Calendar.DAY_OF_YEAR, 1);
                tmpDate = sf.format(c.getTime());
                boolean isHoliday = false;
                for (int i = 0; holidays != null && i < holidays.length; i++) {
                    if (holidays[i].equals(sf.format(c.getTime()))) {
                        isHoliday = true;
                        break;
                    }
                }
                if (isHoliday || isWeekHoliday(sf.format(c.getTime()))) {
                    continue;
                }
                workDays++;
            }
        } catch (Exception e) {
            log.error("计算时间报错了！", e);
        }
        return workDays;
    }

    /**
     * 根据开始日期算出给定的工作日天数后的日期
     *
     * @param startDate 开始日期，格式为：yyyy-MM-dd
     * @param holidays  节假日数组，日期格式为：yyyy-MM-dd； 注：此参数为NULL时，方法将自动去掉双休日(即双休日不算工作日)
     * @param workDays  工作日天数
     * @return
     */
    public static String getNextWorkDate(String startDate, String[] holidays, int workDays) {
        String endDate = "";
        try {
            int tmpDays = 0;
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(sf.parse(startDate));
            while (tmpDays < workDays) {
                c.add(Calendar.DAY_OF_YEAR, 1);
                boolean isHoliday = false;
                for (int i = 0; holidays != null && i < holidays.length; i++) {
                    if (holidays[i].equals(sf.format(c.getTime()))) {
                        isHoliday = true;
                        break;
                    }
                }
                if (isHoliday || isWeekHoliday(sf.format(c.getTime()))) {
                    continue;
                }
                tmpDays++;
            }
            endDate = sf.format(c.getTime());
        } catch (Exception e) {
            log.error("计算时间报错了！", e);
        }
        return endDate;
    }

    /**
     * 检查某一日期是否为双休日
     *
     * @param date 格式为：yyyy-MM-dd
     * @return 是双休日则返回true, 否则返回false
     */
    public static boolean isWeekHoliday(String date) {
        boolean isHoliday = false;
        int day = getDayOfWeek(date);
        if (day == 0 || day == 6) {
            isHoliday = true;
        }
        return isHoliday;
    }

    /**
     * 获取从某个UTC时间算起(Timestamp)，30天后 的UTC时间
     *
     * @return
     */

    public static Timestamp getOneMonthUTCTimebyOneUTC(Timestamp startUTC) {
        String start = convertDateToStr(startUTC);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        sf.setTimeZone(TimeZone.getTimeZone("GMT")); // 设置转换后的时区
        Calendar c1 = Calendar.getInstance();
        String year = start.substring(0, 4);
        String month = start.substring(5, 7);
        String day = start.substring(8, 10);
        //日历中的 开始时间
        c1.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));
        c1.add(Calendar.DAY_OF_MONTH, 30);
        String time2 = sf.format(c1.getTime());
        System.out.println(time2);
        Timestamp endUTC = convertStrToDate(time2);
        return endUTC;
    }

    // 2014 cyk
    public static Timestamp getUtcAftersomeHour(Timestamp startUTC, int hour) {
        String start = convertDateToStr(startUTC);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        //设置转换后的时区
        sf.setTimeZone(TimeZone.getTimeZone("GMT"));
        Calendar c1 = Calendar.getInstance();
        String year = start.substring(0, 4);
        String month = start.substring(5, 7);
        String day = start.substring(8, 10);
        //日历中的 开始时间
        c1.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));
        c1.add(Calendar.HOUR, hour);
        String time2 = sf.format(c1.getTime());
        System.out.println(time2);
        Timestamp endUTC = convertStrToDate(time2);
        return endUTC;
    }

    public static Timestamp getCurrentServerUTCTime() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        //设置时区
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        //获取UTC时间
        String strDate = dateFormat.format(System.currentTimeMillis());
        return Timestamp.valueOf(strDate);
    }

    public static List<Date> getCurrentUtcDate() {
        List<Date> listDate = new ArrayList<>();
        Timestamp t = getCurrentServerUTCTime();
        Calendar cc = Calendar.getInstance();
        cc.setTime(t);
        cc.add(Calendar.DAY_OF_MONTH, 1);//当前时间加上一条
        Date curDate1 = cc.getTime();
        cc.add(Calendar.DAY_OF_MONTH, -2);//当前时间减去一天
        Date curDate2 = cc.getTime();
        listDate.add(curDate1);
        listDate.add(curDate2);
        return listDate;
    }

    /**
     * 将UTC时间(Timestamp)转换成北京时间(String)
     *
     * @param timestamp
     * @param timeZone
     * @param formatPattern
     * @return
     */
    public static String utcTimeToLocalStr(Timestamp timestamp, String timeZone, String formatPattern) {
        //判断空参数
        if (null == timestamp) {
            return null;
        }
        String pattern = FORMAT_SECOND;
        if (formatPattern != null && !"".equals(formatPattern)) {
            pattern = formatPattern;
        }
        //根据时区获取偏移量
        int timeZoneOffset = TimeZone.getTimeZone(timeZone == null ? "" : timeZone).getRawOffset();
        //计算登陆所在地和北京的时差
        long time = timestamp.getTime() + timeZoneOffset;

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        //格式化时间
        String localStr = dateFormat.format(time);
        return localStr;
    }

    /**
     * 将UTC时间(Timestamp)转换成本地时间(String)
     *
     * @param timestamp
     * @param timeZone
     * @param formatPattern
     * @return
     */
    public static String utcTimeToLocal(Timestamp timestamp, String timeZone, String formatPattern) {
        // 判断空参数
        if (null == timestamp) {
            return null;
        }
        String pattern = FORMAT_SECOND;
        if (formatPattern != null && !"".equals(formatPattern)) {
            pattern = formatPattern;
        }
        //根据时区获取偏移量
        int timeZoneOffset = TimeZone.getTimeZone(timeZone == null ? "" : timeZone).getRawOffset();
        long time = timestamp.getTime() + timeZoneOffset;

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        //格式化时间
        String localStr = dateFormat.format(time);
        return localStr;
    }

    public static Timestamp utcDateToLocalDate(Date time, String timeZone) {
        if (null == time) {
            return new Timestamp(0);
        }
        long timebefore = 0;
        timebefore = time.getTime();
        //根据时区获取偏移量
        int timeZoneOffset = TimeZone.getTimeZone(timeZone == null ? "" : timeZone).getRawOffset();
        long timeafter = timebefore + timeZoneOffset;
        return new Timestamp(timeafter);
    }

    /**
     * 将UTC时间(String)转换成本地时间(String)
     *
     * @param timeStr
     * @param timeZone
     * @param formatPattern
     * @return
     */
    public static String utcTimeStrToLocalStr(String timeStr, String timeZone, String formatPattern) {
        // 判断空参数
        if (null == timeStr) {
            return null;
        }
        String pattern = FORMAT_SECOND;
        if (formatPattern != null && !"".equals(formatPattern)) {
            pattern = formatPattern;
        }
        //根据时区获取偏移量
        int timeZoneOffset = TimeZone.getTimeZone(timeZone == null ? "" : timeZone).getRawOffset();
        Timestamp timestamp = convertStrToDate(timeStr);
        //将时间加上偏移量
        long time = timestamp.getTime() + timeZoneOffset;

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        //格式化时间
        String localStr = dateFormat.format(time);
        return localStr;
    }

    /**
     * 将本地时间(Timestamp)转换成UTC时间(Timestamp)
     *
     * @param localTime
     * @param timeZone
     * @return
     */
    public static Timestamp toUTCTime(Timestamp localTime, String timeZone) {
        //根据时区获取偏移量
        int timeZoneOffset = TimeZone.getTimeZone(timeZone == null ? "" : timeZone).getRawOffset();
        //将时间加上偏移量
        long time = localTime.getTime() - timeZoneOffset;
        Timestamp utc = new Timestamp(time);
        return utc;
    }

    /**
     * 将本地时间(String)转换成UTC时间(Timestamp)
     *
     * @param localStr
     * @param timeZone
     * @return
     */
    public static Timestamp localStrToUTCTime(String localStr, String timeZone) {
        Timestamp localTime = convertStrToDate(localStr);
        //根据时区获取偏移量
        int timeZoneOffset = TimeZone.getTimeZone(timeZone == null ? "" : timeZone).getRawOffset();
        //将时间加上偏移量
        long time = localTime.getTime() - timeZoneOffset;
        Timestamp utc = new Timestamp(time);
        return utc;
    }

    /**
     * 将本地时间(String)转换成UTC时间(String)
     *
     * @param localStr
     * @param timeZone
     * @return
     */
    public static String localStrToUTCStr(String localStr, String timeZone,
                                          String formatPattern) {
        if (localStr == null || "".equals(localStr) || localStr.equals("undefined")) {
            return "";
        } else {
            Timestamp localTime = convertStrToDate(localStr);
            //根据时区获取偏移量
            int timeZoneOffset = TimeZone.getTimeZone(
                    timeZone == null ? "" : timeZone).getRawOffset();
            //将时间加上偏移量
            long time = localTime.getTime() - timeZoneOffset;

            String pattern = FORMAT_SECOND;
            if (formatPattern != null && !"".equals(formatPattern)) {
                pattern = formatPattern;
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            //格式化时间
            String utcStr = dateFormat.format(time);
            return utcStr;
        }
    }

    public static String utcDateToLocalStrForPortal(String timezone) {
        return timezone;
    }

    /**
     * 将传入的Date转成yyyy-MM-dd HH:mm:ss形式的String
     *
     * @param dat
     * @return like 1999-08-08 08:08
     */
    public static String converDateToFormatStr(Date dat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (dat != null) {
            return simpleDateFormat.format(dat);
        } else {
            return "";
        }
    }

    /**
     * 两时间比较
     *
     * @author bolin 2014-8-11
     */
    public static int getTwoTime(Long nowTime, String beginTime1) {
        int i = 0;
        String[] strArrary = beginTime1.split(" ");
        beginTime1 = strArrary[0] + " 00:00";
        if ((Long) DateHelper.convertStrToDate(beginTime1).getTime() > (Long) nowTime) {
            i = 1;
        }

        return i;
    }

    /**
     * 计算两个日期的时间差
     *
     * @param formatTime1
     * @param formatTime2
     * @return
     */
    public static int getTimeDifference(Timestamp formatTime1,
                                        Timestamp formatTime2) {
        SimpleDateFormat timeformat = new SimpleDateFormat(
                "yyyy-MM-dd,HH:mm:ss");
        long t1 = 0L;
        long t2 = 0L;
        try {
            t1 = timeformat.parse(getTimeStampNumberFormat(formatTime1)).getTime();
            t2 = timeformat.parse(getTimeStampNumberFormat(formatTime2)).getTime();
        } catch (Exception e) {
            log.error("计算时间报错了！", e);
        }

        // 因为t1-t2得到的是毫秒级,所以要初3600000得出小时.算天数或秒同理
        int date = (int) ((t1 - t2) / 3600000 / 24);

        return date;
    }

    /**
     * 计算两个日期与当前时间差
     *
     * @param formatTime1
     * @param formatTime2
     * @return
     */
    public static boolean getTimeDiff(Timestamp formatTime1,
                                      Timestamp formatTime2) {
        Date date = new Date();
        if (date.getTime() - formatTime1.getTime() > 0 && formatTime2.getTime() - date.getTime() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 格式化时间 Locale是设置语言敏感操作
     *
     * @param formatTime
     * @return
     */
    public static String getTimeStampNumberFormat(Timestamp formatTime) {
        SimpleDateFormat m_format = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss", new Locale("zh", "cn"));
        return m_format.format(formatTime);
    }

    /**
     * Date 转为 Timestamp
     *
     * @param date
     * @return
     */
    public static Timestamp converDateToTimestamp(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_SECOND);
        if (date != null) {
            return convertStrToDate(simpleDateFormat.format(date));
        }
        return null;
    }

    /**
     * 日期格式字符串转换成UNIX时间戳
     *
     * @param dateStr 字符串日期
     * @param format  如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String Date2UnixTimeStamp(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
        } catch (Exception e) {
            log.error("格式化时间报错了！", e);
        }
        return "";
    }

    /**
     * 日期转字符串（格式化）
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String Date2String(Date date, String pattern) {

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

        return sdf.format(date);

    }

    /**
     * 指定日期加上天数后的日期
     *
     * @param num     为增加的天数
     * @param date 指定日期
     */
    public static Date plusDay(Date date, int num) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
        date = ca.getTime();
        return date;
    }

}