package com.lj.gps.biz.service;

public interface RedisService {

    public void set(String key, Object value, int expire);

    public Object get(String key);

}
