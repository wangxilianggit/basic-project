package com.panshi.hujin2.base.security.dao.mapper.system.role_menu;

import com.panshi.hujin2.base.security.dao.model.system.role.RoleRelevanceMenu;
import org.apache.ibatis.annotations.Param;

/**
 * @author ZhangZhiHao 2018/6/28 14:18
 */
public interface RoleMenuMapper {

    /**
     * 添加角色和菜单的关联信息
     *
     * @param roleRelevanceMenu 角色菜单管理模型
     */
    void add(RoleRelevanceMenu roleRelevanceMenu);

    /**
     * 删除角色菜单关联表信息
     *
     * @param roleId 角色id
     * @param menuId 菜单id
     */
    void delete(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

}
