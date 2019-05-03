package com.panshi.hujin2.base.security.web.controller.system.role;

import com.panshi.hujin2.base.security.web.controller.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * @author ZhangZhiHao 2018/6/22 14:13
 */
@ApiModel("角色输入模型")
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleInputVO extends BaseVO {

    @ApiModelProperty(value = "角色名字", required = true)
    @NotBlank(message = "G10881200")
    private String name;

    @ApiModelProperty("角色描述")
    private String description;

    @ApiModelProperty("是否可用")
    private Integer enabled;

    @ApiModelProperty(value = "角色所含菜单的id的集合", required = true)
    @NotEmpty(message = "G10881201")
    private List<Integer> menuIds;


}
