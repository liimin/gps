package com.lj.gps.biz.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lj.gps.base.*;
import com.lj.gps.biz.entity.DeviceInfo;
import com.lj.gps.biz.entity.User;
import com.lj.gps.biz.service.DeviceService;
import com.lj.gps.biz.service.StaticsService;
import com.lj.gps.frame.common.ResultCodeConstants;
import com.lj.gps.frame.utils.ErrorMsgUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 用户controller
 */
@Controller
@RequestMapping(value = "/statics")
public class StaticsController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(StaticsController.class);

    @Autowired
    private StaticsService staticsService;

    /**
     * 获取设备总数和在线状态
     *
     * @param pagination
     * @param di
     * @return
     */
    @RequestMapping("/getDeviceStatics")
    @ResponseBody
    public ResultEntity getDeviceStatics() {
        ResultEntity resultEntity = new ResultEntity();
        User user =getUser();
        resultEntity.setData(staticsService.getDeviceStatics(user));
        resultEntity.setReturnCode(ResultCode.SUCCESS);
        resultEntity.setMessage("获取成功!");
        return resultEntity;
    }
}
