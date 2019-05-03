package com.panshi.hujin2.base.security.web.controller.system.department;

import com.panshi.hujin2.base.security.web.controller.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author ZhangZhiHao 2018/6/21 15:46
 */
@ApiModel("部门模型")
@EqualsAndHashCode(callSuper = true)
@Data
public class DepartmentVO extends BaseVO {

    @ApiModelProperty("父部门id")
    private Integer parentId;

    @ApiModelProperty(value = "部门名称",required = true)
    @NotBlank(message = "G10881100")
    private String name;

    @ApiModelProperty("部门描述")
    private String description;

    @ApiModelProperty("是否可用")
    private Integer enabled;

}
