package com.panshi.hujin2.base.security.web.controller.system.menu;

import com.panshi.hujin2.base.security.web.controller.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author ZhangZhiHao 2018/6/22 9:14
 * 菜单的描述
 */
@ApiModel("菜单的描述")
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuDescriptionVO extends BaseVO {

    @ApiModelProperty("菜单id")
    private Integer menuId;

    @ApiModelProperty(value = "语言",required = true)
    @NotBlank(message = "G10881004")
    private String locale;

    @ApiModelProperty(value = "描述",required = true)
    @NotBlank(message = "G10881005")
    private String description;
}
