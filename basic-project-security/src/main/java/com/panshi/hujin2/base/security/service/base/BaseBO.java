package com.panshi.hujin2.base.security.service.base;

import lombok.Data;

import java.util.Date;

/**
 * @author ZhangZhiHao 2018/6/22 16:42
 */
@Data
public class BaseBO {

    /**
     * 自增id
     */
    protected Integer id;

    /**
     * 创建时间
     */
    protected Date createTime;

    /**
     * 更新时间
     */
    protected Date modifyTime;

    /**
     * 创建人id
     */
    protected Integer createOperatorId;

    /**
     * 修改人id
     */
    protected Integer modifyOperatorId;

    /**
     * 修改人
     */
    private String modifyOperatorName;

}
