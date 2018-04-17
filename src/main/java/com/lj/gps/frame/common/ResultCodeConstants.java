package com.lj.gps.frame.common;

import com.lj.gps.base.ResultCode;
import org.apache.commons.lang.StringUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回码常量类
 *
 * @author xiaopeng@didihu.com.cn
 * @version 1.0
 * @history 2014年8月18日 create by xiaopeng@didihu.com.cn
 */
public final class ResultCodeConstants extends ResultCode {

    /**
     * 成功代码
     */
    public static final String EC_SUCCESS = "0";


    /** 统一的失败代码 */
    public static final String EC_FAIL = "1";

    /**
     * 其它错误
     */
    public static final String EC_OTHER = "-100";

    // -1000 ~ -1999安全管理模块错误代码
    /**
     * 鉴权失败
     */
    public final static String EC_ACCESS_GRANT_FAIL = "-1000";

    /**
     * IP鉴权失败
     */
    public final static String EC_IP_ACCESS_GRANT_FAIL = "-1001";

    /**
     * 签名验证失败
     */
    public final static String EC_SIGN_ACCESS_GRANT_FAIL = "-1002";

    /**
     * SESSION验证失败
     */
    public final static String EC_SESSION_INVALID = "-1003";

    /** 验证码错误 */
    public final static String CHECK_CODE_ERROR = "9003";

    /** 验证码过期 */
    public final static String CHECK_CODE_OVER_TIME = "9004";

    /** 验证码发送过快 */
    public final static String CHECK_CODE_TOO_FAST = "9005";

    // -3000 ~ -3999 数据库错误码
    /**
     * 数据库错误
     */
    public static final String EC_DB_FAIL = "-3000";

    // 4000 ~ 4999 鉴权或参数验证错误代码
    /**
     * 缺少必填参数
     */
    public final static String PARAM_IS_REQUIRED = "4000";

    /**
     * 参数类型错误
     */
    public final static String PARAM_TYPE_FAIL = "4001";

    /**
     * 参数长度错误
     */
    public final static String PARAM_LENGTH_FAIL = "4002";

    /**
     * 参数小于最小值
     */
    public final static String PARAMS_MINVALUE_FAIL = "4003";

    /**
     * 参数大于最大值
     */
    public final static String PARAMS_MAXVALUE_FAIL = "4004";

    /**
     * 参数解析失败
     */
    public final static String PARAMS_PARSE_FAIL = "4005";

    /**
     * 文件上传失败
     */
    public final static String FILE_UPLOAD_FAIL = "4006";

    // 9000以上（业务错误）
    /**
     * 不存在的用户名
     */
    public final static String USERNAME_NOT_EXIST = "9001";

    /**
     * 密码错误
     */
    public final static String PASSWORD_ERROR = "9002";

    /**
     * 用户组已经存在
     */
    public final static String USER_GROUP_NAME_EXISTED = "9003";

    /**
     * 用户已经存在
     */
    public final static String USERNAME_EXISTED = "9004";

    /**
     * 角色已经存在
     */
    public final static String ROLE_NAME_EXISTED = "9005";

    /**
     * 名称已存在
     */
    public static final String NAME_EXISTS = "9006";

    /** 设备已激活   */
    public static final String MAINTRACK_ISACTIVE = "9009";

    /**
     * 错误消息描述
     */
    public static Map<String, String> ERRORMSG_MAP = new HashMap<>();

    static {
        ERRORMSG_MAP.put(EC_OTHER, "其它错误");
        ERRORMSG_MAP.put(EC_ACCESS_GRANT_FAIL, "鉴权失败");
        ERRORMSG_MAP.put(EC_IP_ACCESS_GRANT_FAIL, "IP鉴权失败");
        ERRORMSG_MAP.put(EC_SIGN_ACCESS_GRANT_FAIL, "签名验证失败");
        ERRORMSG_MAP.put(EC_SESSION_INVALID, "session验证失败");
        ERRORMSG_MAP.put(EC_DB_FAIL, "数据库错误");
        ERRORMSG_MAP.put(PARAM_IS_REQUIRED, "参数: {0} 为必填项");
        ERRORMSG_MAP.put(PARAM_TYPE_FAIL, "参数: {0} 类型错误");
        ERRORMSG_MAP.put(PARAM_LENGTH_FAIL, "参数: {0} 长度错误");
        ERRORMSG_MAP.put(PARAMS_MINVALUE_FAIL, "参数: {0} 小于最小值:{1}");
        ERRORMSG_MAP.put(PARAMS_MAXVALUE_FAIL, "参数: {0} 大于最大值:{1}");
        ERRORMSG_MAP.put(PARAMS_PARSE_FAIL, "参数解析失败");

        ERRORMSG_MAP.put(USERNAME_NOT_EXIST, "不存在的用户名");
        ERRORMSG_MAP.put(PASSWORD_ERROR, "密码错误");
        ERRORMSG_MAP.put(CHECK_CODE_ERROR, "验证码错误");
        ERRORMSG_MAP.put(CHECK_CODE_OVER_TIME, "验证码已过期");
        ERRORMSG_MAP.put(CHECK_CODE_TOO_FAST, "验证码发送太频繁，请稍后重试！");

        ERRORMSG_MAP.put(USERNAME_EXISTED, "用户已经存在");
        ERRORMSG_MAP.put(ROLE_NAME_EXISTED, "角色已经存在");
        ERRORMSG_MAP.put(NAME_EXISTS, "名称已存在");
        ERRORMSG_MAP.put(MAINTRACK_ISACTIVE, "设备已激活");
    }

    public static String getErrorMsg(String errorCode, Object... parms) {
        String msg = ERRORMSG_MAP.get(errorCode);
        if (StringUtils.isNotEmpty(msg)) {
            msg = MessageFormat.format(msg, parms);
        }
        return msg;
    }

}
