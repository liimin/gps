package com.lj.gps.frame.common;

/**
 * 常量类
 *
 * @author liuyf@didihu.com.cn
 * @version 1.0
 * @history 2017年9月20日 create by liuyf@didihu.com.cn
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
    public final static int ADMIN_LOGINID = 1;

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

    /**
     * 主从设备标识：0主设备，1从设备
     */
    public static final int TRACK_MAIN = 0;
    public static final int TRACK_ISSUB = 1;

    /**
     * 设备激活状态：0未激活，1激活
     */
    public static final int TRACK_NOT_ACTIVE = 0;
    public static final String TRACK_NOT_ACTIVE_VALUE = "否";
    public static final int TRACK_ACTIVE = 1;
    public static final String TRACK_ACTIVE_VALUE = "是";

    /**
     * 车辆状态:0正常,1删除
     */
    public static final int VEHICLE_NORMAL = 0;
    public static final int VEHICLE_DELETE = 1;

    /**
     * 车辆使用中状态
     */
    public static final int VEHICLE_ISUSE_NORMAL = 2;

    /**
     * 车辆工单状态：0：正常 1：已激活 2：已删除
     */
    public static final int VEHICLE_WORKORDER_NORMAL = 0;
    public static final int VEHICLE_WORKORDER_ACTIVE = 1;
    public static final int VEHICLE_WORKORDER_DELETE = 2;

    /**
     * 车辆分类默认id
     */
    public static final int CLASS_INITIAL_ID = 1;
    /**
     * 车辆分组默认id
     */
    public static final int DEFAULT_GROUP_ID = 1;

    /**
     * 角色车辆关系类型：车辆分组
     */
    public static final int ROLE_VEHICLE_TYPE_GROUP = 0;
    /**
     * 角色车辆关系类型：车辆分类
     */
    public static final int ROLE_VEHICLE_TYPE_CLASS = 1;
    /**
     * 角色车辆关系类型：车辆
     */
    public static final int ROLE_VEHICLE_TYPE_VEHICLE = 2;


    /**
     * 查询类型：0:无条件,1设备号,2：姓名,3：车架号,4：工单号
     **/
    public static final int QUERY_TYPE_NO = 0;
    public static final int QUERY_TYPE_SN = 1;
    public static final int QUERY_TYPE_SATANDNO = 2;
    public static final int QUERY_TYPE_NAME = 3;
    public static final int QUERY_TYPE_WORKORDER_NO = 4;

    /**
     * 是否有源设备:Y有源设备,N无源设备
     */
    public static final String HASSOURCE_Y = "Y";
    public static final String HASSOURCE_N = "N";

    /**
     * 设备型号类型
     */
    public static final String GT700 = "GT700";
    public static final String GT702 = "GT702";
    public static final String GT703 = "GT703";
    public static final String GT703B = "GT703B";
    public static final String GT701a = "GT701A";
    public static final String GT801 = "GT801";
    public static final String GT700B = "GT700B";
    public static final String GT803 = "GT803";
    public static final String GT706 = "GT706";
    public static final String GT701C = "GT701C";

    /**
     * 数据切换，hbase或mysql
     */
    public static final String HBASE_STRING = "hbase";
    public static final String MYSQL_STRING = "mysql";

    /**
     * 电子栅栏，0关闭状态，1开启状态，2删除状态，3新增状态，4修改状态
     */
    public static final int ELECTRIC_FENCE_CLOSE = 0;
    public static final int ELECTRIC_FENCE_OPEN = 1;
    public static final int ELECTRIC_FENCE_DELETE = 2;
    public static final int ELECTRIC_FENCE_ADD = 3;
    public static final int ELECTRIC_FENCE_UPDATE = 4;

    /**
     * 预警类型-电池电压过低
     **/
    public final static int GPS_ALARM_LOWERVOL = 6;

    /**
     * 预警类型-断电/拆除报警
     **/
    public final static int GPS_ALARM_BLACKOUT = 1;

    /**
     * 预警类型-断电/拆除报警/电压过低
     **/
    public final static int GPS_ALARM = 16;

    /**
     * 常驻地查询时间范围;1:一个月 2:三个月 3:半年内 4:截至到目前,5:自定义，-1测试用
     */
    public static final String RESIDENT_TYPE_ONE = "1";
    public static final String RESIDENT_TYPE_THREE = "2";
    public static final String RESIDENT_TYPE_SIX = "3";
    public static final String RESIDENT_TYPE_PRESENT = "4";
    public static final String RESIDENT_TYPE_CUSTOM = "5";
    public static final String RESIDENT_TYPE_TEST = "-1";

    /**
     * 参数标识
     */
    public static final String LOG_TYPE_0 = "0";
    public static final String LOG_TYPE_1 = "1";
    public static final String LOG_TYPE_2 = "2";

    /**
     * 每个excel存放的数据量
     */
    public static final int POI_DIVISION = 50000;

    /**
     * 工单同步到App操作状态
     */
    public static final byte WORKORDER_SYNC_STATUS_ADD = 0;
    public static final byte WORKORDER_SYNC_STATUS_UPDATE = 1;
    public static final byte WORKORDER_SYNC_STATUS_DELETE = 2;

    /**
     * 广联返回成功码
     */
    public static final String GLSX_RET_CODE_SUCCESS = "0";

    /**
     * 厂商ID-博实结
     */
    public static final String BSJ_FACTORY_ID = "BSJ";

    /**
     * 0：树的父节点级别 1级(分组)
     **/
    public static final int TREE_PARENTNODE_LEVEL_ZERO = 0;

    /**
     * 1：树的父节点级别 2级(分类)
     **/
    public static final int TREE_PARENTNODE_LEVEL_ONE = 1;

    public static final String IMPORT_MATCH_WORK_ORDER="1";

    public static final String IMPORT_VEHICLE_WORK_ORDER="2";

    public static final String DEVICE_TEMPLATE_FILE_NAME="3";



    /**
     * 车辆验证状态
     * 1:通过,2:不通过
     */
    public static final int VALIDATE_PASS = 1;
    public static final int VALIDATE_NOPASS = 2;

    /** 绑定标识 */
    public static final String APP_BINDING = "1";
    /** 解绑标识 */
    public static final String APP_UNBINDING = "2";

    public static final Integer WORKORDER_ACTIVE = 1;
    /** 车辆使用中状态   */
    public static final int ISUSE_NORMAL = 2;

    /** 设备未激活   */
    public static final int TRACK_NOACTIVE = 0;

    /** 车辆工单已删除状态   */
    public static final int VEHICLEWORKORDER_DELETE = 2;

    public static final Integer MAX_FILE_SIZE = 10*1024*1024;

    /** 厂商ID*/
    public static final String MSJ_FACTORY_ID = "msj";


}