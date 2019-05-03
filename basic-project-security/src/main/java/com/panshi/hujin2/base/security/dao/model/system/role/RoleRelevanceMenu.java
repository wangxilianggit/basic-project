package com.panshi.hujin2.base.security.dao.model.system.role;

import com.panshi.hujin2.base.security.dao.model.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author ZhangZhiHao 2018/6/27 9:59
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class RoleRelevanceMenu extends BaseDO {

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 菜单id
     */
    private Integer menuId;

    public RoleRelevanceMenu(Integer roleId, Integer menuId, Integer createOperatorId) {
        this.roleId = roleId;
        this.menuId = menuId;
        this.createOperatorId = createOperatorId;
    }
}
