package com.panshi.hujin2.base.domain.common.crawler;

import lombok.Data;

import java.util.Date;

@Data
public class BlackListUserBO {

    private String phoneNumber;

    private Integer creditStatus;

    private Date creditUpdateTime;

    private Integer source;

    private Date storageTime;
}
