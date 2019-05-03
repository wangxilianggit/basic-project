package com.panshi.hujin2.base.security.dao.qo;

import com.panshi.hujin2.base.domain.qo.BaseQO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ZhangZhiHao 2018/6/25 14:56
 */
@ApiModel("部门查询对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class DepartmentQO extends BaseQO {

    @ApiModelProperty("父部门id")
    private Integer parentId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门描述
     */
    private String description;

}
