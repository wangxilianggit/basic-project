package com.panshi.hujin2.base.security.dao.model.system.role;

import com.panshi.hujin2.base.security.dao.model.BaseDO;
import com.panshi.hujin2.base.security.dao.model.system.menu.MenuDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author ZhangZhiHao 2018/6/27 9:44
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleDO extends BaseDO {

    /**
     * 角色名字
     */
    private String name;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 是否可用
     */
    private Integer enabled;

    private List<MenuDO> menuDOS;
}
