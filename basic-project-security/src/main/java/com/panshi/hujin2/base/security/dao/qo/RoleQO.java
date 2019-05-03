package com.panshi.hujin2.base.security.dao.qo;

import com.panshi.hujin2.base.domain.qo.BaseQO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ZhangZhiHao 2018/6/28 15:06
 */
@ApiModel("角色查询对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleQO extends BaseQO {

    @ApiModelProperty("角色名字")
    private String name;

    @ApiModelProperty("角色描述")
    private String description;
}
