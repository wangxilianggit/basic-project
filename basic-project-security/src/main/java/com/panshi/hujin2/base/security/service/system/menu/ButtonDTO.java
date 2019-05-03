package com.panshi.hujin2.base.security.service.system.menu;

import com.panshi.hujin2.base.security.service.base.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author: wjc
 * @Description:
 * @Date: created in 2018/12/11 15:49
 */
@ApiModel("按钮模型对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class ButtonDTO extends BaseBO {

    @ApiModelProperty("id")
    private  Integer id;

    @ApiModelProperty("菜单描述")
    private  String name;

    @ApiModelProperty("子按钮集合")
    private List<MenuDTO> buttonList;

}
