package com.panshi.hujin2.base.redis;

import com.panshi.hujin2.base.common.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * Created by xueyongfeng on 2017/8/23.
 */
@Component
public class RedisClient {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private JsonUtil jsonUtil;

    public ValueOperations<String, String> opsObject;

    @PostConstruct
    private void init() {
        opsObject = stringRedisTemplate.opsForValue();
    }

    public void set(String key, Object o) {
        if (o instanceof String) {
            opsObject.set(key, o.toString());
        } else {
            String value = jsonUtil.toString(o);
            opsObject.set(key, value);
        }
    }

    public void set(String key, Object o,long timeout, TimeUnit unit) {
        if (o instanceof String) {
            opsObject.set(key, o.toString(),timeout, unit);
        } else {
            String value = jsonUtil.toString(o);
            opsObject.set(key, value,timeout, unit);
        }
    }

    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

}
