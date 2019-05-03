package com.panshi.hujin2.base.security.web.controller.system.menu;

import com.panshi.hujin2.base.security.service.system.menu.ButtonDTO;
import com.panshi.hujin2.base.security.web.controller.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author: wjc
 * @Description:
 * @Date: created in 2018/12/11 11:52
 */
@ApiModel("按钮输入模型")
@EqualsAndHashCode(callSuper = true)
@Data
public class ButtonVO extends BaseVO {

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    @ApiModelProperty(value = "按钮集合")
    List<ButtonDTO> buttons;
}
