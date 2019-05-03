package com.panshi.hujin2.base.security.service.system.menu;


import com.panshi.hujin2.base.security.dao.model.system.menu.MenuDO;
import com.panshi.hujin2.base.security.web.controller.system.menu.ButtonVO;

import java.util.List;

/**
 * @author ZhangZhiHao 2018/6/22 16:02
 */
public interface IMenuService {

    /**
     * 添加菜单
     *
     * @param menuDTO 菜单业务模型
     */
    void add(MenuDTO menuDTO);

    /**
     * 删除菜单
     *
     * @param menuId 菜单id
     */
    void delete(Integer menuId);

    /**
     * 更新菜单
     *
     * @param menuDTO 菜单业务模型
     */
    void update(MenuDTO menuDTO);

    /**
     * 查询所有菜单(树)
     *
     * @return 菜单业务模型(树)
     */
    List<MenuDTO> queryAllMenuTree();

    /**
     * 获取员工的菜单(树)
     *
     * @param employeeId 员工id
     * @return 指定员工的菜单树
     */
    List<MenuDTO> getEmployeeMenuTree(Integer employeeId);


    /**
     * 菜单模型转换
     *
     * @param menuDOS 菜单do集合
     * @return 菜单dto集合
     */
    List<MenuDTO> dosTransFormDto(List<MenuDO> menuDOS);


    /**
     * 查询菜单下按钮(操作权限)
     *
     * @param buttonVO
     * @return 菜单dto集合
     */
    List<ButtonDTO> queryButton(ButtonVO buttonVO);
}
