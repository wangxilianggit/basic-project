package com.panshi.hujin2.base.security.service.system.menu;

import com.panshi.hujin2.base.security.dao.model.system.menu.MenuDescription;
import com.panshi.hujin2.base.security.service.base.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author ZhangZhiHao 2018/6/26 9:45
 */
@ApiModel("菜单模型对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuDTO extends BaseBO {

    @ApiModelProperty("父菜单id")
    private Integer parentId;

    @ApiModelProperty("前端URL地址")
    private String url;

    @ApiModelProperty("是否是按钮")
    private Integer isButton;

    @ApiModelProperty("按钮的key值")
    private String buttonKey;

    @ApiModelProperty("按钮图标")
    private String iconPath;

    @ApiModelProperty("是否可用")
    private Integer enabled;

    @ApiModelProperty("每个菜单映射的语言集合")
    private List<MenuDescription> menuDescriptions;

    @ApiModelProperty("当前菜单的子菜单集合")
    private List<MenuDTO> children;

    @ApiModelProperty("菜单下的控制器集合")
    private List<Integer> controllerIds;

    @ApiModelProperty("是否有权限")
    private String isAuth;
}
