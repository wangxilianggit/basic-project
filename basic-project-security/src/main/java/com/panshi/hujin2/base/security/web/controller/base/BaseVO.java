package com.panshi.hujin2.base.security.web.controller.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @program: pms-manage
 * @description: 基础VO
 * @author: Mr.ZhenYJ
 * @create: 2018-06-20 19:42
 **/
@ApiModel
@Data
public class BaseVO {

    @ApiModelProperty(value = "自增id")
    private Integer id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date modifyTime;

    /**
     * 创建人id
     */
    private Integer createOperatorId;

    /**
     * 修改人id
     */
    private Integer modifyOperatorId;

    /**
     * 修改人
     */
    private String modifyOperatorName;


}
