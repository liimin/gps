package com.lj.gps.biz.service;

import com.lj.gps.biz.entity.DeviceInfo;
import com.lj.gps.biz.entity.User;
import com.lj.gps.biz.entity.UserRole;
import com.lj.gps.biz.mapper.DeviceInfoMapper;
import com.lj.gps.biz.mapper.UserMapper;
import com.lj.gps.biz.mapper.UserRoleMapper;
import com.lj.gps.biz.model.UserRolesModel;
import com.lj.gps.frame.common.Constants;
import com.lj.gps.frame.utils.StringUtils;
import com.lj.gps.frame.utils.encryption.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: liuyf<br/>
 * Date: 2017/9/7<br/>
 * Time: 17:45<br/>
 */
@Service
public class DeviceService {

    @Autowired
    private DeviceInfoMapper deviceInfoMapper;


    /**
     * 根据用户ID获取用户信息
     *
     * @param di
     * @return
     */
    public List<DeviceInfo> getUserList(DeviceInfo di) {
        List<DeviceInfo> dList=deviceInfoMapper.selectAllDevice(di);
        return dList;
    }
}
