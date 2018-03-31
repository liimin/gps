package com.lj.gps.biz.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lj.gps.base.*;
import com.lj.gps.biz.entity.DeviceInfo;
import com.lj.gps.biz.service.DeviceService;
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
@RequestMapping(value = "/device")
public class DeviceController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(DeviceController.class);

    @Autowired
    private DeviceService deviceService;

    /**
     * 获取设备列表(带分页)
     *
     * @param pagination
     * @param di
     * @return
     */
    @RequestMapping("/getDeviceList")
    @ResponseBody
    public ResponseEntity<DeviceInfo> getDeviceList(Pagination pagination, DeviceInfo di) {
        PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize(), true);
        List<DeviceInfo> dList = deviceService.getUserList(di);
        ResponseEntity<DeviceInfo> responseObject = new ResponseEntity<>();
        long total = ((Page<DeviceInfo>) dList).getTotal();
        responseObject.setData(dList);
        responseObject.setRecordsTotal(total);
        responseObject.setRecordsFiltered(total);
        return responseObject;
    }
}
