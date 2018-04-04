package com.lj.gps.biz.mapper;

import com.lj.gps.biz.entity.HisGps;
import com.lj.gps.biz.entity.Role;

import java.util.List;

public interface HisGpsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HisGps record);

    int insertSelective(HisGps record);

    HisGps selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HisGps record);

    int updateByPrimaryKey(HisGps record);

    List<HisGps> selectBySn(String sn);
}