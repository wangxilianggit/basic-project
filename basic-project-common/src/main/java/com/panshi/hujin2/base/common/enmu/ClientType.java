package com.panshi.hujin2.base.common.enmu;

import java.util.Objects;

public enum ClientType {
    WECHAT(1, "WeChat"),
    IOS(2, "iOS"),
    ANDROID(3, "Android");

    private Integer type;
    private String desc;

    ClientType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static ClientType getByType(Integer type) {
        for (ClientType t : values()) {
            if (Objects.equals(t.getType(), type))
                return t;
        }
        return null;
    }

    public static ClientType getByDesc(String desc) {
        for (ClientType t : values()) {
            if (t.getDesc().equals(desc))
                return t;
        }
        return null;
    }
}
