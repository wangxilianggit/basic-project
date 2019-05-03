package com.panshi.hujin2.base.service;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * rpc服务统一上下文
 *
 */
@Data
public class Context implements Serializable {

    private static final long serialVersionUID = -9200842394224683365L;

    /** 区域 */
    private Locale locale;

    /** 上下文参数 */
    private Map<String, Object> itemMap = new HashMap<String, Object>();

    public <T> T put(final String name, final T object) {
        return (T) itemMap.put(name, object);
    }

    public <T> T remove(final String name) {
        return (T) itemMap.remove(name);
    }

    public <T> T get(final String name) {
        return (T) itemMap.get(name);
    }

    public void putAll(Map<String, Object> map) {
        this.itemMap.putAll(map);
    }

    public <T> T get(final String name, final T defaultValue) {
        T result = (T) itemMap.get(name);
        if (result == null) {
            result = defaultValue;
        }
        return result;
    }

    public boolean exist(String name) {
        return itemMap.containsKey(name);
    }

    public void clear() {
        itemMap.clear();
    }

    public Map<String, Object> getItemMap() {
        return itemMap;
    }

    public void setItemMap(Map<String, Object> itemMap) {
        this.itemMap = itemMap;
    }

    public int size() {
        return itemMap.size();
    }

    public boolean renameKey(String key, String newKey) {
        if (itemMap.containsKey(key)) {
            itemMap.put(newKey, itemMap.get(key));
            itemMap.remove(key);
            return true;
        }
        return false;
    }


}
