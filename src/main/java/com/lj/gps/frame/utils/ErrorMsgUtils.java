package com.lj.gps.frame.utils;

import com.lj.gps.base.ResultEntity;
import com.lj.gps.frame.common.ResultCodeConstants;
import com.lj.gps.frame.utils.json.JsonBinder;

import javax.servlet.http.HttpServletResponse;

/**
 * 错误消息工具类
 *
 * @author liuyf@didihu.com.cn
 * @version 1.0
 * @history 2017年9月18日 create by liuyf@didihu.com.cn
 */
public class ErrorMsgUtils {

    private static JsonBinder binder = JsonBinder.buildNonDefaultBinder();

    /**
     * 产生错误消息 并通过response发送出去
     *
     * @param re
     * @param errorCode
     * @param res
     * @param params
     */
    public static void createErrorMsgAndWrite(ResultEntity re, String errorCode, HttpServletResponse res, Object... params) {
        createErrorMsg(re, errorCode, params);
        write(res, re);
    }

    /**
     * 产生错误消息
     *
     * @param re
     * @param errorCode
     * @param params
     */
    public static void createErrorMsg(ResultEntity re, String errorCode, Object... params) {
        re.setReturnCode(errorCode);
        re.setMessage(ResultCodeConstants.getErrorMsg(errorCode, params));
    }

    /**
     * 功能:向前端输出错误信息 <br/>
     *
     * @param message
     */
    public static void writeErrorMsg(String message, HttpServletResponse response) {
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setReturnCode(ResultCodeConstants.EC_OTHER);
        resultEntity.setMessage(message);
        write(response, resultEntity);
    }


    public static void write(HttpServletResponse res, ResultEntity re) {
        try {
            res.setContentType("application/json; charset=UTF-8");
            res.getOutputStream().write(binder.toJson(re).getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
