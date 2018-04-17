package com.lj.gps.frame.common;

/**
 * 常量类
 *
 * @author limin
 * @version 1.0
 * @history 2018年3月20日 create by 17653212@qq.com
 */
public final class Constants {

    /**
     * 系统根目录
     */
    public final static String CONTEXT_PATH_KEY = "webAppRootKey";
    public final static String CONTEXT_PATH_VALUE = "gps.evn.webapp";

    /**
     * 超级管理员的登录id
     */
    public final static int ADMIN = 10000;

    /**
     * 生成随机种子
     */
    public static final String NAME_SEED = "ABCDEFGHJKMNPQRSTUVWXYZ23456789";

    /**
     * 登录用户在SESSION中的KEY值
     */
    public final static String SESSION_LOGIN_USER = "_session_user";

    /**
     * 用户可用状态
     */
    public final static int USER_AVAILABLE_STATUS = 1;

    /**
     * 用户不可用状态
     */
    public final static int USER_UNAVAILABLE_STATUS = 0;

    /**
     * 设备在线
     */
    public final static int DEVICE_ONLINE = 1;

    /**
     * 设备离线
     */
    public final static int DEVICE_OFFLINE = 0;

    /**
     * 每个excel存放的数据量
     */
    public static final int POI_DIVISION = 50000;



    /**
     * 今天
     */
    public static final int TIMETYPE_TODAY = 1;
    /**
     * 昨天
     */
    public static final int TIMETYPE_YESTERDAY = 2;
    /**
     * 本周
     */
    public static final int TIMETYPE_THISWEEK = 3;
    /**
     * 上周
     */
    public static final int TIMETYPE_LASTWEEK = 4;
    /**
     * 本月
     */
    public static final int TIMETYPE_THISMONTH = 5;
    /**
     * 上月
     */
    public static final int TIMETYPE_LASTMONTH = 6;
    /**
     * 时间段
     */
    public static final int TIMETYPE_SCOPE = 7;
    /**
     * 时间范围 近12小时
     */
    public static final int TIMETYPE_IN_12_HOURS = 8;
    /**
     * 时间范围 近24小时
     */
    public static final int TIMETYPE_IN_24_HOURS = 9;
    /**
     * 近七天
     */
    public static final int TIMETYPE_IN_7_DAYS = 10;
    /**
     * 近30天
     */
    public static final int TIMETYPE_IN_30_DAYS = 11;
    /**
     * 时间段 带时间
     */
    public static final int TIMETYPE_SCOPE_WITH_TIME = 12;

    /**
     * 今年
     */
    public static final int TIMETYPE_YEAR = 14;

}