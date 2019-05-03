package com.panshi.hujin2.base.security.service.system.role_menu;

import com.panshi.hujin2.base.security.dao.mapper.system.role_menu.RoleMenuMapper;
import com.panshi.hujin2.base.security.dao.model.system.role.RoleRelevanceMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZhangZhiHao 2018/6/29 18:26
 */
@Service
public class RoleMenuServiceImpl implements IRoleMenuService {
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public void delete(Integer roleId, Integer menuId) {
        roleMenuMapper.delete(roleId, menuId);
    }

    @Override
    public void add(RoleRelevanceMenu roleRelevanceMenu) {
        roleMenuMapper.add(roleRelevanceMenu);
    }
}
