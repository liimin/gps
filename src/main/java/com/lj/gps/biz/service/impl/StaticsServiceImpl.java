package com.lj.gps.biz.service.impl;

import com.lj.gps.biz.entity.RTStatus;
import com.lj.gps.biz.entity.User;
import com.lj.gps.biz.mapper.DeviceInfoMapper;
import com.lj.gps.biz.mapper.RTStatusMapper;
import com.lj.gps.biz.mapper.RoleDeviceMapper;
import com.lj.gps.biz.model.Statics;
import com.lj.gps.biz.service.StaticsService;
import com.lj.gps.frame.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaticsServiceImpl implements StaticsService {

    @Autowired
    private RoleDeviceMapper roleDeviceMapper;

    @Autowired
    private RTStatusMapper rTStatusMapper;

    @Autowired
    private DeviceInfoMapper deviceInfoMapper;

    @Override
    public Statics getDeviceStatics(User user) {
        Statics statics=new Statics();
        List<String> listSNAll;
        if(user.getUserid()==Constants.ADMIN){
            listSNAll=deviceInfoMapper.selectAllSns();
        }else{
            listSNAll=roleDeviceMapper.selectAllSns(user.getRoleIds());//获取当前用户角色所有设备
        }
        RTStatus rs=new RTStatus();
        rs.setStatus(Constants.DEVICE_ONLINE);
        List<RTStatus> listRS=rTStatusMapper.selectAll(rs);//获取所有在线的设备
        List<RTStatus> listDevicesInRoleOnline=new ArrayList<>();//存放所有当前角色所有在线设备
        for(String sn : listSNAll){
            for(RTStatus rts : listRS){
                if(sn.equals(rts.getSn())){
                    listDevicesInRoleOnline.add(rts);
                    continue;
                }
            }
        }
        int total=listSNAll.size();
        int online=listDevicesInRoleOnline.size();
        int offline=total-online;
        statics.setTotal(total);
        statics.setOnline(online);
        statics.setOffline(offline);
        return statics;
    }
}
