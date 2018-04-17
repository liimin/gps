package com.lj.gps.biz.service;

import com.lj.gps.biz.entity.User;
import com.lj.gps.biz.model.Statics;

public interface StaticsService {

    public Statics getDeviceStatics(User user);

}
