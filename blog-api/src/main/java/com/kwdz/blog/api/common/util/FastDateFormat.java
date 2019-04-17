package com.kwdz.blog.api.common.util;

import com.fasterxml.jackson.databind.util.StdDateFormat;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期工具类
 */
public class FastDateFormat extends SimpleDateFormat {

    //默认日期格式："年-月-日 时:分:秒 时区"
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss Z";

    //常用日期格式："年-月-日 时:分:秒"
    public static final String CHINA_PATTERN = "yyyy-MM-dd HH:mm:ss";

    //常用日期格式："年-月-日"
    public static final String BSS_PATTERN = "yyyyMMdd";

    //默认时区："GMT+0"
    public static final TimeZone GMT = TimeZone.getTimeZone("GMT+0000");

    //常用时区："GMT+8"
    public static final TimeZone GMT8 = TimeZone.getTimeZone("GMT+0800");

    //解析时间戳的工具类
    private StdDateFormat numberFormat = new StdDateFormat();

    //禁用构造器 请使用静态方法#get获取实例。
    private FastDateFormat() {
        this(DEFAULT_PATTERN, GMT);
    }

    //禁用构造器 请使用静态方法#get获取实例。
    private FastDateFormat(String pattern, TimeZone timeZone) {
        super(pattern);
        super.setTimeZone(timeZone);
    }

    /**
     * 静态方法：获取日期工具类。
     * <p>
     * 注意事项：默认时区为GMT+0、日期格式为"yyyy-MM-dd HH:mm:ss Z"。
     */
    public static FastDateFormat get() {
        return new FastDateFormat();
    }

    /**
     * 静态方法：获取日期工具类。
     * <p>
     * 例如：FastDateFormat.get(FastDateFormat.DEFAULT_PATTERN, FastDateFormat.GMT);
     */
    public static FastDateFormat get(String pattern, TimeZone timeZone) {
        return new FastDateFormat(pattern, timeZone);
    }

    //增强日期解析方法
    @Override
    public Date parse(String source) throws ParseException {
        try {
            if (StringUtils.isEmpty(source)) {
                return null;//解析空串
            }
            super.applyPattern(DEFAULT_PATTERN);
            return super.parse(source);//解析特定格式
        } catch (Exception e) {
            try {
                super.applyPattern(CHINA_PATTERN);
                return super.parse(source);//解析特定格式
            } catch (Exception ex) {
                return numberFormat.parse(source);//解析时间戳
            }
        }
    }

    //增强格式化日期方法 支持多种格式
    public String formatDate(Date date, String pattern) {
        String oldPattern = super.toPattern();
        super.applyPattern(pattern);
        String dateStr = super.format(date);
        super.applyPattern(oldPattern);
        return dateStr;
    }

    //当前时间增加天数
    public Date plusDays(int days)  {
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,days);
        return calendar.getTime();
    }

    //重要勿删：设置全局默认时区为为GMT+0，确保日期参数和消息转换等操作均采用0时区。
    public static void setDefaultTimeZoneToGMTBeforeRun() {
        TimeZone.setDefault(GMT);
    }

}
