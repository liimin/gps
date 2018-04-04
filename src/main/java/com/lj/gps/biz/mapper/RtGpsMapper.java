package com.lj.gps.biz.mapper;

import com.lj.gps.biz.entity.RtGps;

public interface RtGpsMapper {
    int deleteByPrimaryKey(String sn);

    int insert(RtGps record);

    int insertSelective(RtGps record);

    RtGps selectByPrimaryKey(String sn);

    int updateByPrimaryKeySelective(RtGps record);

    int updateByPrimaryKey(RtGps record);
}