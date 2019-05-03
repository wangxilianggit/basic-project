package com.panshi.hujin2.base.security.dao.model.system.menu;

import com.panshi.hujin2.base.security.dao.model.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ZhangZhiHao 2018/6/26 20:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ControllerAuthentication extends BaseDO {

    /**
     * 菜单id
     */
    private Integer menuId;

    /**
     * 控制器地址
     */
    private String controllerUrl;

    /**
     * 请求方式
     */
    private String requestMethod;
}
