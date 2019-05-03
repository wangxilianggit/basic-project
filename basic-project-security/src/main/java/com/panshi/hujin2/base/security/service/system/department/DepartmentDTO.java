package com.panshi.hujin2.base.security.service.system.department;

import com.panshi.hujin2.base.security.service.base.BaseBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author ZhangZhiHao 2018/6/22 17:33
 */
@Api("部门传输模型")
@EqualsAndHashCode(callSuper = true)
@Data
public class DepartmentDTO extends BaseBO {

    @ApiModelProperty("父部门id")
    private Integer parentId;

    @ApiModelProperty("部门名称")
    private String name;

    @ApiModelProperty("部门描述")
    private String description;

    @ApiModelProperty("是否可用")
    private Integer enabled;

    private List<DepartmentDTO> children;
}
