package com.lj.gps.biz.service.impl;

import javax.annotation.Resource;

import com.lj.gps.biz.service.RedisService;
import com.lj.gps.frame.utils.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    public void set(String key, Object value, int expire) {
        ValueOperations<String,Object> vo = redisTemplate.opsForValue();
        vo.set(key, value);
        if(expire!=0){
            redisTemplate.expire(key,expire,TimeUnit.SECONDS);
        }

    }
    public Object get(String key) {
        if(StringUtils.isEmpty(key)){
            return null;
        }
        ValueOperations<String,Object> vo = redisTemplate.opsForValue();
        return vo.get(key);
    }
}
