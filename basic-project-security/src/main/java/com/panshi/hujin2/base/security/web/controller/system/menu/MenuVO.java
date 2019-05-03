package com.panshi.hujin2.base.security.web.controller.system.menu;

import com.panshi.hujin2.base.security.web.controller.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author ZhangZhiHao 2018/6/22 9:11
 */
@ApiModel("菜单输入模型")
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuVO extends BaseVO {

    @ApiModelProperty("父菜单id")
    private Integer parentId;

    @ApiModelProperty(value = "前端URL地址", required = true)
    @NotBlank(message = "G10881000")
    private String url;

    @ApiModelProperty(value = "是否是按钮", required = true)
    @NotNull(message = "G10881001")
    private Integer isButton;

    @ApiModelProperty("按钮的key值")
    private String buttonKey;

    @ApiModelProperty(value = "按钮图标", required = true)
    @NotBlank(message = "G10881002")
    private String iconPath;

    @ApiModelProperty("是否可用")
    private Integer enabled;

    @ApiModelProperty(value = "每个菜单映射的语言集合", required = true)
    @Valid
    @NotEmpty(message = "G10881003")
    private List<MenuDescriptionVO> menuDescriptionVOS;

    @ApiModelProperty("菜单下的控制器集合")
    private List<Integer> controllerIds;

}
