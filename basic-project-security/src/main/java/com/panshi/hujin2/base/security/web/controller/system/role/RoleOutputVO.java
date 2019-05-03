package com.panshi.hujin2.base.security.web.controller.system.role;

import com.panshi.hujin2.base.security.service.system.menu.MenuDTO;
import com.panshi.hujin2.base.security.web.controller.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author ZhangZhiHao 2018/6/22 14:13
 */
@ApiModel("角色输出模型")
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleOutputVO extends BaseVO {

    @ApiModelProperty("角色名字")
    private String name;

    @ApiModelProperty("角色描述")
    private String description;

    @ApiModelProperty("是否可用")
    private Integer enabled;

    @ApiModelProperty("角色所包含的菜单集合")
    private List<MenuDTO> menuDTOS;
}
