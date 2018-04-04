package com.lj.gps.biz.service;

import com.lj.gps.biz.entity.HisGps;
import com.lj.gps.biz.entity.RtGps;
import com.lj.gps.biz.mapper.HisGpsMapper;
import com.lj.gps.biz.mapper.RtGpsMapper;
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
public class HisGpsService {

    @Autowired
    private HisGpsMapper hisGpsMapper;

    /**
     * 根据SN获取实时轨迹
     * @param sn
     * @return
     */
    public List<HisGps> selectBySn(String sn){
        return hisGpsMapper.selectBySn(sn);
    }
}
