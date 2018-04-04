package com.lj.gps.biz.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lj.gps.base.*;
import com.lj.gps.biz.entity.DeviceInfo;
import com.lj.gps.biz.entity.HisGps;
import com.lj.gps.biz.entity.RtGps;
import com.lj.gps.biz.service.HisGpsService;
import com.lj.gps.biz.service.RtGpsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 用户controller
 */
@Controller
@RequestMapping(value = "/track")
public class TrackController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(TrackController.class);

    @Autowired
    private RtGpsService rtGpsService;

    @Autowired
    private HisGpsService hisGpsService;

    /**
     * 获取实时位置点
     * @return
     */
    @RequestMapping("/getRtBySn")
    @ResponseBody
    public ResultEntity<RtGps> getRtBySn(String sn) {
        ResultEntity<RtGps> resultEntity = new ResultEntity<>();
        resultEntity.setReturnCode(ResultCode.SUCCESS);
        resultEntity.setMessage("获取成功");
        resultEntity.setData(rtGpsService.selectBySn(sn));
        return resultEntity;
    }


    /**
     * 获取历史轨迹
     * @return
     */
    @RequestMapping("/getHisBySn")
    @ResponseBody
    public ResponseEntity<HisGps> getHisBySn(String sn) {
        ResponseEntity<HisGps> responseObject = new ResponseEntity<>();
        responseObject.setData(hisGpsService.selectBySn(sn));
        return responseObject;
    }
}
