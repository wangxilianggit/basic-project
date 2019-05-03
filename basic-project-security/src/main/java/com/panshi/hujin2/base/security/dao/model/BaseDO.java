package com.panshi.hujin2.base.security.dao.model;

import lombok.Data;

import java.util.Date;

/**
 * @author ZhangZhiHao 2018/6/22 16:34
 */
@Data
public class BaseDO {

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
    protected String modifyOperatorName;

}
