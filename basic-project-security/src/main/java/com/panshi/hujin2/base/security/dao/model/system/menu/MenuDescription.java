package com.panshi.hujin2.base.security.dao.model.system.menu;

import com.panshi.hujin2.base.security.dao.model.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ZhangZhiHao 2018/6/22 9:14
 * 菜单的描述
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuDescription extends BaseDO {

    /**
     * 菜单id
     */
    private Integer menuId;

    /**
     * 语言
     */
    private String locale;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否可用
     */
    private Integer enabled;
}
