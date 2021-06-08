package com.xiaojiangtun.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * Created by hello on 2019/1/29.
 */
public class DateUtil {
    private static final Logger log = LoggerFactory.getLogger(DateUtil.class);

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_FORMAT_YYMMDD = "yyyy年MM月dd日";
    public static final String DATE_TIME_FORMAT_MMDD = "MMdd";
    public static final String DATE_TIME_FORMAT_YYYY = "yyyy";
    public static final String DATE_TIME_HHMM_FORMAT = "HH:mm";
    public static final String DATE_TIME_HHMMSS_FORMAT = "HH:mm:ss";

    public static final String DATE_TIME_HOURS_FORMAT = "yyyy-MM-dd-HH";

    public static final String NYRHM_FORMAT = "yyyy年MM月dd日 HH:mm";

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String YYYY_MM_DD = "yyyyMMdd";

    public static final String HH_MM_SS = "HHmmss";

    public static final String YR = "M月d日";

    public static final String YRH = "M月d日HH:mm";

    public static final String FULL_YR = "MM月dd日";

    public static final String timeFormatStr = " 23:59:59";

    public static final String DATE_YYYY_MM_DD = "yyyy年MM月dd日";

    public static final String DATE_YYYY_MM_DD_SPOT = "yyyy.MM.dd";

    public static final DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    /**
     * 从Date类型的时间中提取日期部分
     */
    public static Date getDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 从Date类型的时间中提取时间部分
     */
    public static Date getTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, 1970);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /***
     * 获取当前时间yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurTime() {
        // new Date()为获取当前系统时间
        SimpleDateFormat df = new SimpleDateFormat(DateUtil.DATE_TIME_FORMAT);
        return df.format(new Date());
    }

    /**
     * 根据字符串yyyy-MM-dd HH:mm:ss
     * 返回一定格式date
     *
     * @param dateStr
     * @return
     */
    public static Date getDateTimeByStr(String dateStr) {
        if (null == dateStr || "".equals(dateStr)) {
            return null;
        }
        Date date = null;
        try {
            SimpleDateFormat localDf = new SimpleDateFormat(DateUtil.DATE_TIME_FORMAT);
            date = localDf.parse(dateStr);
        } catch (ParseException e) {
            log.error("转换Date类型字符串错误：date={},error={}", date, e);
        }
        return date;
    }

    public static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    public static Date addMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    public static Date addHours(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

    public static Date addMinutes(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    /**
     * 根据格式获取日期字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String getDateTimeStr(Date date, String format) {
        String dataStr = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat(format);
            dataStr = df.format(date);
        } catch (Exception e) {
            log.error("转换Date类型字符串错误：date={},format={},error={}", date, format, e);
        }
        return dataStr;
    }

    /**
     * 从Date类型的时间中提取时间部分
     */
    public static boolean compareIsBetweenTime(Date nowDate, Date startDate, Date endDate) {
        Date dataStart = getTime(startDate);
        Date dataEnd = getTime(endDate);
        Date dataNow = getTime(nowDate);
        return (dataNow.after(dataStart) && dataNow.before(dataEnd));
    }

    /**
     * 从Date类型的时间中提取时间部分闭合
     */
    public static boolean compareIsBetweenTimeEq(Date nowDate, Date startDate, Date endDate) {
        Date dataStart = getTime(startDate);
        Date dataEnd = getTime(endDate);
        Date dataNow = getTime(nowDate);
        return (dataNow.after(dataStart) && dataNow.before(dataEnd) || dataNow.compareTo(dataStart) == 0 || dataNow.compareTo(dataEnd) == 0);
    }

    /**
     * 返回两个时间的间隔秒数
     *
     * @param fromDate
     * @param toDate
     * @return
     */
    public static Long getIntervalTime(LocalDateTime fromDate, LocalDateTime toDate) {
        return ChronoUnit.SECONDS.between(fromDate, toDate);
    }

    /**
     * 返回指定时间的间隔
     *
     * @param fromDate
     * @return
     */
    public static Long getIntervalTime(LocalDateTime fromDate) {
        String dateStr = LocalDate.now().toString();
        LocalDateTime fableDate = LocalDateTime.parse(dateStr.concat(timeFormatStr), formatterTime);
        return getIntervalTime(fromDate, fableDate);
    }

    /**
     * 返回当前时间到当天23:59:59的间隔秒数
     *
     * @return
     */
    public static Long getIntervalTime() {
        return getIntervalTime(LocalDateTime.now());
    }

    /**
     * 获取间隔某天数的时间
     *
     * @param date
     * @param day
     * @return
     */
    public static String getIntervalDate(Date date, int day) {
        Date newDate = addDays(date, day);
        SimpleDateFormat df = new SimpleDateFormat(DateUtil.DATE_TIME_FORMAT);
        SimpleDateFormat nyr = new SimpleDateFormat("yyyy年MM月dd日");
        return nyr.format(newDate);

    }

    public static String getIntervalDate(Date date, int day, DateFormat format) {
        Date newDate = addDays(date, day);
        return format.format(newDate);

    }

    /**
     * 获取间隔某小时的时间
     *
     * @param date
     * @param hours
     * @return
     */
    public static String getIntervalHours(Date date, int hours) {
        Date newDate = addHours(date, hours);
        SimpleDateFormat nyrhm = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        return nyrhm.format(newDate);

    }

    /**
     * 获取间隔某小时的时间
     *
     * @param date
     * @param hours
     * @return
     */
    public static String getIntervalHour(Date date, int hours) {
        Date newDate = addHours(date, hours);
        SimpleDateFormat nyrhm = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        return nyrhm.format(newDate);

    }

    public static String getIntervalHoursFormat(Date date, int hours) {
        Date newDate = addHours(date, hours);
        SimpleDateFormat nyrhmFm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return nyrhmFm.format(newDate);

    }

    public static String getIntervalHoursNew(Date date, int hours) {
        Date newDate = addHours(date, hours);
        SimpleDateFormat localDf = new SimpleDateFormat(DateUtil.DATE_TIME_FORMAT);
        return localDf.format(newDate);

    }
    public static String getIntervalHHmmFormat(Date date, int hours) {
        Date newDate = addHours(date, hours);
        SimpleDateFormat nyrhmFm = new SimpleDateFormat("HH:mm");
        return nyrhmFm.format(newDate);

    }
    public static String getIntervalHoursFormat(Date date, int hours, DateFormat format) {
        Date newDate = addHours(date, hours);
        return format.format(newDate);

    }

    /**
     * 获取间隔某分钟的时间
     *
     * @param date
     * @param minutes
     * @return
     */
    public static String getIntervalMinutes(Date date, int minutes) {
        Date newDate = addMinutes(date, minutes);
        SimpleDateFormat nyrhm = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        return nyrhm.format(newDate);

    }

    /**
     * 获取间隔某分钟的时间
     *
     * @param date
     * @param minutes
     * @return
     */
    public static String getIntervalMinutesNew(Date date, int minutes) {
        Date newDate = addMinutes(date, minutes);
        SimpleDateFormat localDf = new SimpleDateFormat(DateUtil.DATE_TIME_FORMAT);
        return localDf.format(newDate);

    }

    public static String getStabornTime(Date date, int day, String time) {
        Date newDate = addDays(date, day);
        SimpleDateFormat nyrOne = new SimpleDateFormat(DateUtil.DATE_FORMAT);
        String newDateStr = nyrOne.format(newDate);
        return newDateStr.concat(time);

    }

    public static Date getStabornDate(Date date, int day, String time) throws ParseException {
        Date newDate = addDays(date, day);
        SimpleDateFormat nyrOne = new SimpleDateFormat(DateUtil.DATE_FORMAT);
        String newDateStr = nyrOne.format(newDate);
        SimpleDateFormat df = new SimpleDateFormat(DateUtil.DATE_TIME_FORMAT);
        return df.parse(newDateStr.concat(time));

    }

//    public static String compareEndTime(String endTime, String closeTime) {
//        try {
//            String endTimeDate = Constant.PREFIX_DATE + endTime;
//            String closeTimeDate = DateUtil.getIntervalHoursNew(Constant.DF.parse(Constant.PREFIX_DATE + closeTime), -1);
//            if (endTimeDate.compareTo(closeTimeDate) > 0) {
//                return closeTimeDate.split(" ")[1];
//            } else {
//                return endTime;
//            }
//        } catch (ParseException e) {
//            log.info("compareEndTime error: ", e);
//        }
//        return endTime;
//    }
//
//    /**
//     * @param startTime 大网揽收时间
//     * @param opTime    门店开店时间
//     * @return
//     */
//    public static String compareStartTime(String startTime, String opTime) {
//        try {
//            String startTimeDate = Constant.PREFIX_DATE + startTime;
//            String opTimeDate = DateUtil.getIntervalMinutesNew(Constant.DF.parse(Constant.PREFIX_DATE + opTime), 0);
//            if (startTimeDate.compareTo(opTimeDate) > 0) {
//                return startTime;
//            } else {
//                return opTimeDate.split(" ")[1];
//            }
//        } catch (ParseException e) {
//            log.info("compareStartTime error: ", e);
//        }
//        return startTime;
//    }

    /**
     * 获取今天是周几
     *
     * @return
     */
    public static String getWeek() {
        String week = "";
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        if (weekday == 1) {
            week = "周日";
        } else if (weekday == 2) {
            week = "周一";
        } else if (weekday == 3) {
            week = "周二";
        } else if (weekday == 4) {
            week = "周三";
        } else if (weekday == 5) {
            week = "周四";
        } else if (weekday == 6) {
            week = "周五";
        } else if (weekday == 7) {
            week = "周六";
        }
        return week;
    }

    /**
     * 获取传入的日期是星期几
     *
     * @param date
     * @return
     */
    public static String getWeekByDate(Date date) {
        String week = "";
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        if (weekday == 1) {
            week = "周日";
        } else if (weekday == 2) {
            week = "周一";
        } else if (weekday == 3) {
            week = "周二";
        } else if (weekday == 4) {
            week = "周三";
        } else if (weekday == 5) {
            week = "周四";
        } else if (weekday == 6) {
            week = "周五";
        } else if (weekday == 7) {
            week = "周六";
        }
        return week;
    }

    public static Date string2DateTime(String dateString) {
        if (org.apache.commons.lang3.StringUtils.isBlank(dateString)) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(dateString);
        } catch (Exception e) {
            return null;
        }
    }

    public static String formartTime(Date date) {
        SimpleDateFormat nyrhms = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        return nyrhms.format(date);

    }

    public static void main(String[] args) throws ParseException {
//        System.out.println("test=====" + Constant.PAY_DATE_FAMAT.format(DateUtil.addMinutes(new Date(), 30)));
//
//        System.out.println("===" + compareIsBetweenTimeEq(Constant.DF.parse("2020-01-04 16:00:01"), Constant.DF.parse(Constant.startDateStr), Constant.DF.parse(Constant.endDateStr)));

        System.out.println("09:00:00".substring(0, 5));
    }

    public static Date stringToDate(String time,
                                    String format) {
        Date parse = null;
        try {
            if (!StringUtils.isEmpty(time)) {
                parse = new SimpleDateFormat(format).parse(time);
            }
        } catch (ParseException e) {
            log.error(String.format("日期解析有误：格式【%s】", format));
        }
        return parse;
    }

    public static Date today() {
        return Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date today(Integer hour,
                             Integer minute,
                             Integer second) {
        Date date = today();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (hour != null) {
            calendar.set(Calendar.HOUR_OF_DAY, hour);
        }
        if (minute != null) {
            calendar.set(Calendar.MINUTE, minute);
        }
        if (second != null) {
            calendar.set(Calendar.SECOND, second);
        }
        return calendar.getTime();
    }

    public static Date yesterday() {
        return Date.from(LocalDate.now().plusDays(-1).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param startTime 时间参数 1 格式：1990-01-01 12:00:00
     * @param endTime   时间参数 2 格式：2009-01-01 12:00:00
     * @return long[] 返回值为：{天, 时, 分, 秒}
     */
    public static long[] getDistanceTimes(Date startTime, Date endTime) {
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;

        long time1 = startTime.getTime();
        long time2 = endTime.getTime();
        long diff;
        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        day = diff / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

        long[] times = {day, hour, min, sec};
        return times;
    }

    /**
     * 根据格式获取日期字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String getLocalDateTimeStr(Date date, String format) {
        String dataStr = null;
        try {
            dataStr = DateTimeFormatter.ofPattern(format)
                    .format(date.toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime()
                    );
        } catch (Exception e) {
            log.error("转换Date类型字符串错误：date={},format={},error={}", date, format, e);
        }
        return dataStr;
    }

    /**
     * 根据字符串yyyy-MM-dd HH:mm:ss
     * 返回一定格式date
     *
     * @param dateStr
     * @return
     */
    public static Date getLocalDateTimeByStr(String dateStr) {
        if (null == dateStr || "".equals(dateStr)) {
            return null;
        }
        Date date = null;
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
            LocalDateTime ldt = LocalDateTime.parse(dateStr, df);
            date = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        } catch (Exception e) {
            log.error("转换Date类型字符串错误：date={},error={}", date, e);
        }
        return date;
    }

    /**
     * 获取日期格式化类
     * @param dateFormat
     * @return
     */
    public static SimpleDateFormat getDf(String dateFormat){
        return new SimpleDateFormat(dateFormat);
    }

    /**
     * 获取当前时间的上个月之yyyy-MM
     * @return
     */
    public static String getLastMonthFromNow(){
        LocalDate today = LocalDate.now();
        today = today.minusMonths(1);
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM");
        return formatters.format(today);
    }

    public static Date getOneYearAfterByDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//设置起时间
        cal.add(Calendar.YEAR, 1);//增加一年
        return cal.getTime();
    }

}
