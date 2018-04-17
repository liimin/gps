package com.lj.gps.biz.mapper;

import com.lj.gps.biz.entity.RTStatus;
import com.lj.gps.biz.entity.User;
import com.lj.gps.biz.model.Statics;

import java.util.List;

public interface RTStatusMapper {
    int deleteByPrimaryKey(String sn);

    int insert(RTStatus record);

    int insertSelective(RTStatus record);

    RTStatus selectByPrimaryKey(String sn);

    int updateByPrimaryKeySelective(RTStatus record);

    int updateByPrimaryKey(RTStatus record);

    List<RTStatus> selectAll(RTStatus rTStatus);
}