package com.panshi.hujin2.base.redis;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * redicache 工具类
 */
@SuppressWarnings("unchecked")
@Component
public class RedisUtils {

    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 批量删除对应的value
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * 删除对应的value
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 读取缓存,并转换为需要的类型
     * 如果值不存在 会给该类型的0 (注:类型暂时只支持 Integer,Double) ,其他返回null
     */
    public <T> T get(final String key, Class<T> type) {
        if (exists(key)) {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            Object result = operations.get(key);
            String string = result.toString();
            if (Integer.TYPE.equals(type)) {
                return (T) Integer.valueOf(string);
            } else if (Double.TYPE.equals(type)) {
                return (T) Double.valueOf(string);
            } else {
                return (T) result;
            }
        } else {
            if (Integer.TYPE.equals(type)) {
                return (T) Integer.valueOf(0);
            } else if (Double.TYPE.equals(type)) {
                return (T) Double.valueOf(0);
            } else {
                return null;
            }
        }
    }

    public <T> T getAndAppendTime(String key, Class<T> type) throws InstantiationException, IllegalAccessException {
        return getAndAppendTime(key, type, null);
    }

    /**
     * 获取redis缓存数据 默认拼接当日时间戳
     *
     * @param key 缓存key
     * @param type 要转化的类型
     * @param plusDays 拼接的日期是否
     */
    public <T> T getAndAppendTime(String key, Class<T> type, Integer plusDays) throws InstantiationException, IllegalAccessException {
        key = keyAppendTime(key, plusDays);
        return get(key, type);
    }

    /**
     * 写入缓存
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 递增
     *
     * @param key 键
     * @param delta 要增加几(大于0)
     */
    public long increment(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    public double increment(String key, double amount) {
        return redisTemplate.opsForValue().increment(key, amount);
    }

    /**
     * 递增1
     *
     * @param key 键
     */
    public long incrementOne(String key) {
        return redisTemplate.opsForValue().increment(key, 1);
    }

    /**
     * 在key前面拼接日期 然后递增1
     * 如: 当前日期为2018/9/17  key = demo:statistics
     * 拼接后效果为 key = 20180917:demo:statistics
     *
     * @param key 键
     */
    public long appendTimeAndIncrementOne(String key) {
        key = keyAppendTime(key);
        // 缓存是否已经存在
        boolean exists = false;
        if (exists(key)) {
            exists = true;
        }
        // 缓存递增
        long value = redisTemplate.opsForValue().increment(key, 1);
        // 如果之前不存在,则设置失效时间
        if (!exists) {
            redisTemplate.expire(key, 2, TimeUnit.DAYS);
        }
        return value;
    }

    /**
     * 在key前面拼接日期 然后将此缓存数据增加amount
     *
     * @param key 键
     */
    public double appendTimeAndIncrementAmount(String key, double amount) {
        key = keyAppendTime(key);
        // 缓存是否已经存在
        boolean exists = false;
        if (exists(key)) {
            exists = true;
        }
        // 缓存递增
        Double value = redisTemplate.opsForValue().increment(key, amount);
        // 如果之前不存在,则设置失效时间
        if (!exists) {
            redisTemplate.expire(key, 2, TimeUnit.DAYS);
        }
        return value;
    }

    private String keyAppendTime(String key) {
        return keyAppendTime(key, null);
    }

    private String keyAppendTime(String key, Integer plusDays) {
        Date date;
        if (plusDays != null) {
            date = Date.from(LocalDate.now().plusDays(plusDays).atStartOfDay(ZoneId.systemDefault()).toInstant());
        } else {
            date = new Date();
        }
        return key + DateFormatUtils.format(date, "yyyyMMdd");
    }

    public void rightPush(final String key, Integer value) {
        ListOperations<String, Integer> listOperations = redisTemplate.opsForList();
        listOperations.rightPush(key, value);
    }

    public void rightPush(final String key, List<Integer> values) {
        ListOperations<String, Integer> listOperations = redisTemplate.opsForList();
        listOperations.rightPushAll(key, values);
    }

    public List<Integer> range(final String key, long start, long end) {
        ListOperations<String, Integer> listOperations = redisTemplate.opsForList();
        return listOperations.range(key, start, end);
    }

    public void remove(final String key, long count, Object value) {
        ListOperations<String, Integer> listOperations = redisTemplate.opsForList();
        listOperations.remove(key, count, value);
    }

}