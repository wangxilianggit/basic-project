package com.panshi.hujin2.base.security.service.system.role_menu;

import com.panshi.hujin2.base.security.dao.model.system.role.RoleRelevanceMenu;

/**
 * @author ZhangZhiHao 2018/6/29 18:26
 */
public interface IRoleMenuService {

    void delete(Integer roleId, Integer menuId);

    void add(RoleRelevanceMenu roleRelevanceMenu);
}
