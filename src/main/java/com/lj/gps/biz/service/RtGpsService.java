package com.lj.gps.biz.service;

import com.lj.gps.biz.entity.DeviceInfo;
import com.lj.gps.biz.entity.RtGps;
import com.lj.gps.biz.mapper.DeviceInfoMapper;
import com.lj.gps.biz.mapper.RtGpsMapper;
import com.lj.gps.frame.utils.GPSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: liuyf<br/>
 * Date: 2017/9/7<br/>
 * Time: 17:45<br/>
 */
@Service
public class RtGpsService {

    @Autowired
    private RtGpsMapper rtGpsMapper;

    /**
     * 根据SN获取实时轨迹
     * @param sn
     * @return
     */
    public RtGps selectBySn(String sn){
        RtGps rgps=rtGpsMapper.selectByPrimaryKey(sn);
       /* double lat=rgps.getLatitude()/1000000;
        double lng=rgps.getLongitude()/1000000;
        double latLng[]= GPSUtil.gpsToGoogle(lat,lng);
        rgps.setAlat(latLng[0]);
        rgps.setAlng(latLng[1]);*/
        return rgps;
    }
}
