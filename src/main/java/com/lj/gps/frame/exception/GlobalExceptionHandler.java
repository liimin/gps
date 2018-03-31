package com.lj.gps.frame.exception;

import com.lj.gps.base.ResultEntity;
import com.lj.gps.frame.common.ResultCodeConstants;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 作者: sanri
 * 时间: 2018/3/3
 * 功能: 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 业务异常处理
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResultEntity businessExceptionHandler(HttpServletRequest req, Exception e){
        logger.error("业务异常:"+ e.getMessage());
        BusinessException businessException = (BusinessException) e;
        return businessException.getResultEntity();
    }

    /**
     * 系统异常处理
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = SystemErrorException.class)
    @ResponseBody
    public ResultEntity systemExceptionHandler(HttpServletRequest req, Exception e){
        logger.error("系统异常",e);
        SystemErrorException systemErrorException = (SystemErrorException) e;
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setReturnCode(ResultCodeConstants.EC_OTHER);
        resultEntity.setMessage("系统错误:" + e.getMessage());
        return resultEntity;
    }

    /**
     * 网络或资源找不到
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = IOException.class)
    @ResponseBody
    public ResultEntity ioException(HttpServletRequest req, Exception e){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setReturnCode(ResultCodeConstants.EC_OTHER);
        resultEntity.setMessage("网络连接错误或资源找不到:" + e.getMessage());
        return resultEntity;
    }

}
