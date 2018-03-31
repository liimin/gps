package com.lj.gps.biz.mapper;

import com.lj.gps.biz.entity.DeviceInfo;
import com.lj.gps.biz.entity.DeviceInfoKey;
import com.lj.gps.biz.entity.User;

import java.util.List;

public interface DeviceInfoMapper {
    int deleteByPrimaryKey(DeviceInfoKey key);

    int insert(DeviceInfo record);

    int insertSelective(DeviceInfo record);

    DeviceInfo selectByPrimaryKey(DeviceInfoKey key);

    int updateByPrimaryKeySelective(DeviceInfo record);

    int updateByPrimaryKey(DeviceInfo record);

    List<DeviceInfo> selectAllDevice(DeviceInfo di);
}