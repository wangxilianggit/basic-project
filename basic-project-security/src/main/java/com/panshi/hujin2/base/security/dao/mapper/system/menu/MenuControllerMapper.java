package com.panshi.hujin2.base.security.dao.mapper.system.menu;


import com.panshi.hujin2.base.security.dao.model.system.menu.MenuRelevanceController;

/**
 * @author ZhangZhiHao 2018/6/28 14:22
 */
public interface MenuControllerMapper {

    /**
     * 添加菜单控制器关联信息
     *
     * @param menuRelevanceController 菜单控制器管理模型
     */
    void add(MenuRelevanceController menuRelevanceController);

    /**
     * 删除菜单控制器关联信息
     *
     * @param menuId 菜单id
     */
    void delete(Integer menuId);
}
