package com.lj.gps.frame.utils;

import com.lj.gps.frame.common.Constants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 日期工具类
 *
 * @author liuyf@didihu.com.cn
 * @version 1.0
 * @history 2017年9月7日 create by liuyf@didihu.com.cn
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {

    public static String YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static String YMD = "yyyy-MM-dd";

    public enum TimeUnitType {
        DAY(1), HOUR(2), MINUTE(3), SECOND(4);

        private int value = 0;

        TimeUnitType(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    public enum CustomTimeType {
        TIMETYPE_TODAY(1), /**
         * 今天
         */
        TIMETYPE_YESTERDAY(2), /**
         * 昨天
         */
        TIMETYPE_THISWEEK(3), /**
         * 本周
         */
        TIMETYPE_LASTWEEK(4), /**
         * 上周
         */
        TIMETYPE_THISMONTH(5), /**
         * 本月
         */
        TIMETYPE_LASTMONTH(6), /**
         * 上月
         */
        TIMETYPE_SCOPE(7);
        /**
         * 时间段
         */

        private int value = 0;

        CustomTimeType(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }


    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static Date parseDate(String date) {
        return parseDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date parseDateYmd(String date) {
        return parseDate(date, "yyyy-MM-dd");
    }

    public static String formatDateYm(Date date) {
        return formatDate(date, "yyyyMM");
    }

    public static String formatDate(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatDateYmd(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }

    public static String formatDateYYmmdd(Date date) {
        return formatDate(date, "yyyyMMdd");
    }

    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            return formatter.format(date);

        } catch (Exception e) {
            return null;
        }
    }

    public static Date parseDate(String date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            return null;
        }

    }


    /**
     * 将时间置为23时59分钟59秒
     *
     * @param date
     * @return
     */
    public static Date setFullPassDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 将时间置为00时00分钟00秒
     *
     * @param date
     * @return
     */
    public static Date setFullPassDay1(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        return calendar.getTime();
    }

    /**
     * 将时间后退2小时
     *
     * @param date
     * @return
     */
    public static Date getFallBack2Hour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY,
                calendar.get(Calendar.HOUR_OF_DAY) - 2);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 将时间精确到小时
     *
     * @param date
     * @return
     */
    public static Date getTimeHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取两个时间间隔的天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getDiffDays(Date startDate, Date endDate) {
        long difftime = endDate.getTime() - startDate.getTime();
        return difftime / (24L * 60L * 60L * 1000L);
    }


    /**
     * 根据日期获取当天起始时间
     *
     * @param date
     * @return
     */
    public static Date getStartDateOfCurrentDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getStartYesterday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 根据日期获取下一天起始时间
     *
     * @param date
     * @return
     */
    public static Date getStartDateOfNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 根据日期当前日期顺延一周后的起始时间
     *
     * @param date
     * @return
     */
    public static Date getStartDateOfNextSevenDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 根据日期当前日期顺延一周后的起始时间
     *
     * @param date
     * @return
     */
    public static Date getStartDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 根据日期当前日期顺延一月后的起始时间
     *
     * @param date
     * @return
     */
    public static Date getStartDateOfNextMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /*
     * 封装一天只能的时间区域
     */
    public static List<Date> getStaticByDateDateArea(Date date) {
        List<Date> dates = new ArrayList<Date>();
        Date startdate = getStartDateOfCurrentDay(date);
        Date nextday = getStartDateOfNextDay(date);
        int step = 2;
        dates.add(startdate);
        for (int i = 1; i < 12; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startdate);
            calendar.add(Calendar.HOUR_OF_DAY, i * step);
            dates.add(calendar.getTime());
        }
        dates.add(nextday);
        return dates;
    }

    /*
     * 封装一周之内时间区域
     */
    public static List<Date> getStaticByWeekDateArea(Date date) {
        List<Date> dates = new ArrayList<Date>();
        Date startdate = getStartDateOfCurrentDay(date);
        Date nextday = getStartDateOfNextSevenDay(date);
        dates.add(startdate);
        for (int i = 1; i < 7; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startdate);
            calendar.add(Calendar.DAY_OF_MONTH, i);
            dates.add(calendar.getTime());
        }
        dates.add(nextday);
        return dates;
    }

    /*
     * 封装一周之内时间区域List<String>
     */
    public static List<String> getStaticByWeekLabel(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
        List<String> dates = new ArrayList<String>();
        Date startdate = getStartDateOfCurrentDay(date);
        dates.add(dateFormat.format(startdate));
        for (int i = 1; i < 7; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startdate);
            calendar.add(Calendar.DAY_OF_MONTH, i);
            dates.add(dateFormat.format(calendar.getTime()));
        }
        return dates;
    }

    /*
     * 封装一月之内时间区域
     */
    public static List<Date> getStaticByMonthDateArea(Date date) {
        List<Date> dates = new ArrayList<Date>();
        Date startdate = getStartDateOfMonth(date);
        Date nextday = getStartDateOfNextMonth(date);
        long daydiff = getDiffDays(startdate, nextday);
        dates.add(startdate);
        for (int i = 1; i < daydiff; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startdate);
            calendar.add(Calendar.DAY_OF_MONTH, i);
            dates.add(calendar.getTime());
        }
        dates.add(nextday);
        return dates;
    }

    /*
     *封装一点时间之内的时间区域（天）
     */
    public static List<Date> getStaticBySE(Date startDate, Date endDate) {
        List<Date> dates = new ArrayList<Date>();

        long daydiff = getDiffDays(startDate, endDate);
        dates.add(startDate);
        for (int i = 1; i < daydiff; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.DAY_OF_MONTH, i);
            dates.add(calendar.getTime());
        }
        dates.add(endDate);
        return dates;
    }

    /*
     * 封装一月之内时间区域
     */
    public static List<String> getStaticByMonthLabel(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        List<String> dates = new ArrayList<String>();
        Date startdate = getStartDateOfMonth(date);
        Date nextday = getStartDateOfNextMonth(date);
        long daydiff = getDiffDays(startdate, nextday);
        dates.add(dateFormat.format(startdate));
        for (int i = 1; i < daydiff; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startdate);
            calendar.add(Calendar.DAY_OF_MONTH, i);
            dates.add(dateFormat.format(calendar.getTime()));
        }
        return dates;
    }

    public static String formatDate(String format, Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * 根据时间段拼装sql语句
     * 时间段查询，例：DateUtils.appendSqlByTimeRange(Constants.TIMETYPE_SCOPE, "t.createtime", "2014-09-17", "2014-09-18");
     *
     * @param timeRange 查询时间：全部-0、今天-1、昨天-2、本周-3、上周-4、本月-5、上月-6、时间段-7、近12小时-8、近24小时-9、近7天-10、近30天-11
     * @param field     字段名，例：t.createtime
     * @param times     时间段查询，开始时间和结束时间
     * @return 2017.9.7  mym  修改  新增 近7天  近30天
     */
    public static String appendSqlByTimeRange(Integer timeRange, String field, String... times) {
        if (StringUtils.isEmpty(field)) {
            return "";
        }

        String appendSql = "";
        if (timeRange != null) {
            switch (timeRange) {
                case Constants.TIMETYPE_TODAY://今天
                    appendSql += " and date_format(" + field + ",'%Y-%m-%d') = date_format(NOW(),'%Y-%m-%d')";
                    break;
                case Constants.TIMETYPE_YESTERDAY:// 昨天
                    appendSql += " and date_format(" + field + ",'%Y-%m-%d')=date_format(DATE_ADD(NOW(),INTERVAL -1 DAY),'%Y-%m-%d')";
                    break;
                case Constants.TIMETYPE_THISWEEK:// 本周
                    appendSql += " and YEARWEEK(date_format(" + field + ",'%Y-%m-%d'),5) = YEARWEEK(now(),5) ";
                    break;
                case Constants.TIMETYPE_LASTWEEK:// 上周
                    appendSql += " and YEARWEEK(date_format(" + field + ",'%Y-%m-%d'),5) = YEARWEEK(now(),5)-1 ";
                    break;
                case Constants.TIMETYPE_THISMONTH:// 本月
                    appendSql += " and date_format(" + field + ",'%Y-%m')=date_format(now(),'%Y-%m') ";
                    break;
                case Constants.TIMETYPE_LASTMONTH:// 上月
                    appendSql += " and date_format(" + field + ",'%Y-%m')=date_format(DATE_SUB(curdate(), INTERVAL 1 MONTH),'%Y-%m') ";
                    break;
                case Constants.TIMETYPE_IN_7_DAYS:// 近7天
                    appendSql += " and date_sub(curdate(), INTERVAL 7 DAY) <= date(" + field + ") ";
                    break;
                case Constants.TIMETYPE_IN_30_DAYS:// 近30天
                    appendSql += " and date_sub(curdate(), INTERVAL 30 DAY) <= date(" + field + ") ";
                    break;
                case Constants.TIMETYPE_SCOPE:// 时间段
                    try {
                        if (null != times && times.length == 2) {
                            String stime = times[0];
                            String etime = times[1];
                            //时间校验
                            Date sdate = parseDate(stime, "yyyy-MM-dd");
                            Date edate = parseDate(etime, "yyyy-MM-dd");
                            if (null != sdate && null != edate && (edate.after(sdate) || sdate.equals(edate))) {
                                appendSql += " and " + field + " >= '" + stime + "' and " + field + " <= '" + getToCondtionDay(parseDate(etime, "yyyy-MM-dd")) + "'";
                            }
                        }
                        break;
                    } catch (Exception ex) {
                        break;
                    }
                case Constants.TIMETYPE_SCOPE_WITH_TIME:
                    try {
                        if (null != times && times.length == 2) {
                            String stime = times[0];
                            String etime = times[1];
                            //时间校验
                            Date sdate = parseDate(stime);
                            Date edate = parseDate(etime);
                            if (null != sdate && null != edate && (edate.after(sdate) || sdate.equals(edate))) {
                                appendSql += " and " + field + " >= '" + stime + "' and " + field + " <= '" + getToCondtionDay(parseDate(etime)) + "'";
                            }
                        }
                        break;
                    } catch (Exception ex) {
                        break;
                    }
            }
        }
        return appendSql;
    }

    /**
     * 获得今天日期
     *
     * @return
     */
    public static String getTodayDate() {
        Date date = new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
        return dateFm.format(date);
    }

    /**
     * 获得昨天日期
     *
     * @return
     */
    public static String getYesterday() {
      /*  Date date = new Date();
        date = new Date(date.getTime() - 1000 * 60 * 60 * 24);

        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
        return dateFm.format(date);*/
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -1);
        Date date = cal.getTime();
        return dateFm.format(date);

    }

    public static String getTomorrow() {
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 1);
        Date date = cal.getTime();
        return dateFm.format(date);

    }

    /****
     * 时间加一天
     * @param d1
     * @return
     */
    public static String getToCondtionDay(Date d1) {
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(d1);
        cal.add(Calendar.DATE, 1);
        Date date = cal.getTime();
        return dateFm.format(date);

    }

    /**
     * 获得本周一的日期
     *
     * @return
     */
    public static String getMondayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayofweek == 0) {
            dayofweek = 7;
        }
        c.add(Calendar.DATE, -dayofweek + 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(c.getTime());
    }

    /**
     * 获取本周星期天的日期
     *
     * @return
     */
    public static String getCurrentWeekday() {
//    	int mondayPlus = getMondayPlus();
//    	GregorianCalendar currentDate = new GregorianCalendar();
//    	currentDate.add(GregorianCalendar.DATE, mondayPlus+6);
//    	Date monday = currentDate.getTime();
//    	DateFormat df = DateFormat.getDateInstance();
//    	String preMonday = df.format(monday);
        Calendar cal = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        cal.add(Calendar.WEEK_OF_YEAR, 1);
        String preMonday = df.format(cal.getTime());
        return preMonday;
    }

    /****
     * 获取下周一日期
     * @return
     */
    public static String getXiaWeekday() {
//    	int mondayPlus = getMondayPlus();
//    	GregorianCalendar currentDate = new GregorianCalendar();
//    	currentDate.add(GregorianCalendar.DATE, mondayPlus+6);
//    	Date monday = currentDate.getTime();
//    	DateFormat df = DateFormat.getDateInstance();
//    	String preMonday = df.format(monday);
        Calendar cal = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        cal.add(Calendar.WEEK_OF_YEAR, 1);
        cal.add(Calendar.DATE, 1);
        String preMonday = df.format(cal.getTime());
        return preMonday;
    }


    private static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }
    }

    /**
     * 根据原来的时间（Date）获得相对偏移 N 月的时间（Date）
     *
     * @param protoDate   原来的时间（java.util.Date）
     * @param monthOffset （向前移正数，向后移负数）
     * @return 时间（java.util.Date）
     */
    public static Date getOffsetMonthDate(Date protoDate, int monthOffset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(protoDate);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - monthOffset);
        return cal.getTime();
    }

    /**
     * 根据原来的时间（Date）获得相对偏移 N 天的时间（Date）
     *
     * @param protoDate  原来的时间（java.util.Date）
     * @param dateOffset （向前移正数，向后移负数）
     * @return 时间（java.util.Date）
     */
    public static Date getOffsetDayDate(Date protoDate, int dateOffset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(protoDate);
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)
                - dateOffset);
        return cal.getTime();
    }

    /**
     * 得到上个月的最后一天
     */
    public static String getLastMonthLastDay() {
        //上个月最后一天
        Calendar calendar = Calendar.getInstance();
        //calendar.add(Calendar.MONTH, 1);    //加一个月
        calendar.set(Calendar.DATE, 1);        //设置为该月第一天
        calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(calendar.getTime());
    }

    /**
     * 得到上个月的第一天
     */
    public static String getLastMonthFirstDay() {
        //上个月第一天
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(new Date());
        gcLast.add(Calendar.MONTH, -1);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(gcLast.getTime());
    }

    /**
     * 获取上周一的日期
     *
     * @param date
     * @return
     */
    public static Date getLastWeekMonday(Date date) {
        Date a = org.apache.commons.lang.time.DateUtils.addDays(date, -1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(a);
        cal.add(Calendar.WEEK_OF_YEAR, -1);// 一周
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    /**
     * 获取上周日的日期
     *
     * @param date
     * @return
     */
    public static Date getLastWeekSunday(Date date) {
        Date a = org.apache.commons.lang.time.DateUtils.addDays(date, -1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(a);
        cal.set(Calendar.DAY_OF_WEEK, 1);
        return cal.getTime();
    }

    /***
     * 时间转为字符串
     * @param str 秒
     * @return
     */
    public static String conver(int str) {
        if (str > 0) {
            int s = str;
            int N = s / 3600;
            s = s % 3600;
            int K = s / 60;
            s = s % 60;
            int M = s;
            return N + "小时" + K + "分";
        } else {
            return "0";
        }


    }

    /***
     * 得到本月的第一天
     * @return
     */
    public static String getMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(calendar.getTime());
    }

    /***
     * 上月第一天
     * @return
     */
    public static String getPreviousMonthFirst() {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(5, 1);
        lastDate.add(2, -1);
        str = sdf.format(lastDate.getTime());
        return str;
    }

    /***
     * 上月最后一天
     * @return
     */
    public static String getPreviousMonthEnd() {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.add(2, -1);
        lastDate.set(5, 1);
        lastDate.roll(5, -1);
        str = sdf.format(lastDate.getTime());
        return str;
    }

    /**
     * 获取本月最后一天的日期
     *
     * @return
     */
    public static String getEndDateOfThisMonth() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        cal.set(Calendar.DATE, 1);
        cal.roll(Calendar.DATE, -1);
        Date endTime = cal.getTime();
        return sdf.format(endTime);
    }

    /**
     * 获取某月的最后一天
     *
     * @throws
     * @Title:getLastDayOfMonth
     * @Description:
     * @param:@param year
     * @param:@param month
     * @param:@return
     * @return:String
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());

        return lastDayOfMonth;
    }

    /**
     * 格式化传的时间为 X天X小时X分X秒
     *
     * @param durationTime
     * @return
     */
    public static String formatTimeInSec(long durationTime) {
        if (durationTime > 0) {
            int s = (int) durationTime;
            int day = s / (3600 * 24);
            s = s % (3600 * 24);
            int hour = s / 3600;
            s = s % 3600;
            int min = s / 60;
            s = s % 60;
            int sec = s;
            return day + "天" + hour + "小时" + min + "分" + sec + "秒";
        } else {
            return "0";
        }
    }


    /**
     * 格式化传的时间为 X天X小时X分
     *
     * @param durationTime
     * @return
     */
    public static String formatTimeInSec2(long durationTime) {
        if (durationTime > 0) {
            int s = (int) durationTime;
            int day = s / (3600 * 24);
            s = s % (3600 * 24);
            int hour = s / 3600;
            s = s % 3600;
            int min = s / 60;
            s = s % 60;
            int sec = s;
            return day + "天" + hour + "小时" + min + "分";
        } else {
            return "0";
        }
    }

    /**
     * 将时间后退8小时  北京时间转格林威治时间
     *
     * @param inputDate HH:mm:ss
     * @return
     */
    public static String getFallBack8Hour(String inputDate) {
        String recDate = "";
        SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = null;
        try {
            date = inputFormat.parse(inputDate);
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            // System.out.println("北京时间：" + cal.getTime().toString().substring(11, 19));
            cal.add(Calendar.HOUR, -8);
            recDate = cal.getTime().toString().substring(11, 19);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return recDate;
    }

    /**
     * 将时间前进8小时  格林威治时间转北京时间
     *
     * @param inputDate HH:mm:ss
     * @return
     */
    public static String getGoAhead8Hour(String inputDate) {
        String recDate = "";
        SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = null;
        try {
            date = inputFormat.parse(inputDate);
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            cal.add(Calendar.HOUR, +8);
            recDate = cal.getTime().toString().substring(11, 19);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return recDate;
    }

    /**
     * 作者:sanri <br/>
     * 时间:2017-4-28下午1:35:59<br/>
     * 功能:date1 时间大于(晚于)date2 返回 true ,其余返回 false <br/>
     * 入参: <br/>
     */
    public static boolean compareDate(String date1, String date2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return true;
            } else if (dt1.getTime() < dt2.getTime()) {
                return false;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 将时间前进N分钟
     *
     * @param date
     * @param minute 前进的分钟数
     * @return
     */
    public static Date getMinuteBefore(Date date, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minute);//要前进的分钟数
        return cal.getTime();
    }

    public static boolean compareDate(Date date1, Date date2) {
        if (date1.getTime() > date2.getTime()) {
            return true;
        } else if (date1.getTime() < date2.getTime()) {
            return false;
        } else {
            return false;
        }
    }

    /**
     * 将字符串转为unix时间戳
     *
     * @param dateString 2016-04-12 13:46:03
     * @return
     */
    public static String dateToTimeStamp(String dateString) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long stamp = 0;
        try {
            Date date = df.parse(dateString);
            stamp = date.getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return stamp + "";
    }

    /**
     * unix时间戳转换为字符串
     *
     * @param timeString 1454216477
     * @return
     */
    public static Date timestampToDate(String timeString) {
        //  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long timestamp = Long.parseLong(timeString) * 1000;
        //  String date = sdf.format(new java.util.Date(timestamp));
        return new Date(timestamp);
    }

    /**
     * unix时间戳转换为dateFormat
     *
     * @param timestampString
     * @return
     */
    public static String timestampToDateString(String timestampString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = sdf.format(new Date(timestamp));
        return date;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param one 时间1
     * @param two 时间2
     * @return long[] 返回值为：{天, 时, 分, 秒}
     */
    public static long[] getDistanceTimes(Date one, Date two) {
        long time1 = one.getTime();
        long time2 = two.getTime();
        long diff;
        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        long day = diff / (24 * 60 * 60 * 1000);
        long hour = (diff / (60 * 60 * 1000) - day * 24);
        long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        return new long[]{day, hour, min, sec};
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            long day = diff / (24 * 60 * 60 * 1000);
            long hour = (diff / (60 * 60 * 1000) - day * 24);
            long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            return day + "天" + hour + "小时" + min + "分" + sec + "秒";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 比较两个日期时间差
     *
     * @param startDate
     * @param endDate
     * @param timeUnitType
     * @return
     */
    public static long getDifferenceBetweenTwoTime(Date startDate, Date endDate, TimeUnitType timeUnitType) {
        long duration = endDate.getTime() - startDate.getTime();
        if (timeUnitType != null) {
            if (TimeUnitType.SECOND.value() == timeUnitType.value()) {
                return TimeUnit.MILLISECONDS.toSeconds(duration);
            } else if (TimeUnitType.MINUTE.value() == timeUnitType.value()) {
                return TimeUnit.MILLISECONDS.toMinutes(duration);
            } else if (TimeUnitType.HOUR.value() == timeUnitType.value()) {
                return TimeUnit.MILLISECONDS.toHours(duration);
            } else if (TimeUnitType.DAY.value() == timeUnitType.value()) {
                return TimeUnit.MILLISECONDS.toDays(duration);
            }
        }
        return duration;
    }

    /**
     * 获取几天前的时间
     *
     * @return
     */
    public static String getReductionDay(int dayNum) {
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -dayNum);
        Date date = cal.getTime();
        return dateFm.format(date);

    }

    /**
     * 校验时间格式
     *
     * @param yyyyMM
     */
    public static boolean checkyyyyMM(String yyyyMM) {
        if (null == parseDate(yyyyMM, "yyyyMM") || yyyyMM.length() > 6 || yyyyMM.length() < 5) {
            return false;
        }
        int year = Integer.parseInt(yyyyMM.substring(0, 4));
        int month = Integer.parseInt(yyyyMM.substring(4));
        Calendar cal = Calendar.getInstance();
        if (year <= 0 || year > cal.get(Calendar.YEAR) || month <= 0 || month > 12) {
            return false;
        }
        return true;
    }

    /**
     * 时间往前或者往后推移N天
     *
     * @param days
     * @param date
     * @return
     */
    public static Date getDayBefore(int days, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days);//把日期往后增加一天.整数往后推,负数往前移动
        return cal.getTime();
    }

    /**
     * 获取当年的第一天
     *
     * @param
     * @return
     */
    public static Date getCurrYearFirst() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取当年的最后一天
     *
     * @param
     * @return
     */
    public static Date getCurrYearLast() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取去年的第一天
     *
     * @param
     * @return
     */
    public static Date getLastYearFirst() {
        Calendar currCal = Calendar.getInstance();
        currCal.add(Calendar.YEAR, -1);
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取去年的最后一天
     *
     * @param
     * @return
     */
    public static Date getLastYearLast() {
        Calendar currCal = Calendar.getInstance();
        currCal.add(Calendar.YEAR, -1);
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }

    public static void main(String[] args) throws ParseException {

//        System.out.println(formatDate(getCurrentDate()));
//        System.out.println(formatDate(setFullPassDay(getCurrentDate())));
        Date startTime = DateUtils.getStartDateOfCurrentDay(DateUtils.getCurrentDate());
        Date endTime = DateUtils.setFullPassDay(startTime);

//        System.out.println(formatDate(getCurrYearFirst()));
//        System.out.println(formatDate(getCurrYearLast()));
//
//        System.out.println(formatDate(getLastYearFirst()));
//        System.out.println(formatDate(getLastYearLast()));
//
//        System.out.println(formatDate(getOffsetDayDate(startTime,7)));

        System.out.println(getLastMonthFirstDay());
        System.out.println(getPreviousMonthEnd());
//        System.out.println(getStartDateOfCurrentDay(parseDateYmd(getMondayOfThisWeek())));
        System.out.println(parseDateYmd(getLastMonthFirstDay()));
        System.out.println(formatDate(getStartDateOfCurrentDay(parseDateYmd(getLastMonthFirstDay()))));
        System.out.println(formatDate(getStartDateOfCurrentDay(parseDateYmd(getPreviousMonthEnd()))));
//        System.out.println(setFullPassDay(parseDate(getCurrentWeekday())));
    }

    /**
     * 获取当前日期是星期几
     *
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }
        return dayOfWeek;
    }


    /**
     * 作者:sanri <br/>
     * 时间:2017-9-15上午10:17:05<br/>
     * 功能:计算两个日期的间隔,使用时间戳间隔然后取整 <br/>
     *
     * @param date1    日期 1
     * @param date2    日期 2
     * @param timeUnit
     * @return 两个日期的时间间隔
     */
    public static long interval(Date date1, Date date2, TimeUnit timeUnit) {
        return interval(date1.getTime(), date2.getTime(), timeUnit);
    }

    public static long interval(Calendar cal1, Calendar cal2, TimeUnit timeUnit) {
        return interval(cal1.getTimeInMillis(), cal2.getTimeInMillis(), timeUnit);
    }

    public static long interval(long millis1, long millis2, TimeUnit timeUnit) {
        long duration = Math.abs(millis2 - millis1);
        return interval(duration, timeUnit);
    }

    public static long interval(long duration, TimeUnit timeUnit) {
        return timeUnit.convert(duration, TimeUnit.MILLISECONDS);
    }

    /**
     * 作者:sanri <br/>
     * 时间:2017-9-15下午12:41:04<br/>
     * 功能:计算两个日期间的自然日期间隔 从后一天开始计算,只算天数<br/>
     * 注:此方法没有包含当天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long naturalIntervalDay(Calendar cal1, Calendar cal2) {
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        //add by sanri at 2017/11/21 解决不同年,算出来的天数不一致的问题
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);

        if (year1 == year2) {
            //如果是同年,则直接可以时间相减
            return Math.abs(day2 - day1);
        }
        int yearSwap = year1;
        if (year1 > year2) {
            //交换两个年
            year1 = year2;
            year2 = yearSwap;
        }
        int timeDistance = 0;
        for (int i = year1; i < year2; i++) {
            if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {    //闰年
                timeDistance += 366;
            } else {
                timeDistance += 365;
            }
        }

        return Math.abs(timeDistance + (day2 - day1));
    }

    public static long naturalIntervalDay(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return naturalIntervalDay(calendar1, calendar2);
    }

    public static long naturalIntervalDay(long millis1, long millis2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(millis1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(millis2);
        return naturalIntervalDay(calendar1, calendar2);
    }

    /**
     * 获取两个时间间隔的分钟数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getDiffMins(Date startDate, Date endDate) {
        long difftime = endDate.getTime() - startDate.getTime();
        return difftime / (60L * 1000L);
    }

//    public static void main(String[] args) throws ParseException {
//        System.out.println(getMinuteBefore(new Date(), -6));
//    }
}
