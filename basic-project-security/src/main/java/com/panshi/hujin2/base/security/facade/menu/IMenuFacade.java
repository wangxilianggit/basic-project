package com.panshi.hujin2.base.security.facade.menu;

import com.panshi.hujin2.base.domain.result.BasicResult;
import com.panshi.hujin2.base.security.service.system.menu.ButtonDTO;
import com.panshi.hujin2.base.security.service.system.menu.MenuDTO;
import com.panshi.hujin2.base.security.web.controller.system.menu.ButtonVO;

import java.util.List;

/**
 * @author ZhangZhiHao 2018/6/22 16:29
 */
public interface IMenuFacade {

    /**
     * 添加菜单
     *
     * @param menuDTO 菜单业务模型
     * @return void
     */
    BasicResult<Void> add(MenuDTO menuDTO);

    /**
     * 删除菜单
     *
     * @param id 菜单id
     * @return void
     */
    BasicResult<Void> delete(Integer id);

    /**
     * 更新菜单
     *
     * @param menuDTO 菜单业务模型
     * @return void
     */
    BasicResult<Void> update(MenuDTO menuDTO);

    /**
     * 查询所有菜单(树)
     *
     * @return 菜单业务模型(树)
     */
    BasicResult<List<MenuDTO>> queryAllMenuTree();

    /**
     * 获取员工的菜单(树)
     *
     * @param employeeId 员工id
     * @return 指定员工的菜单树
     */
    BasicResult<List<MenuDTO>> getEmployeeMenuTree(Integer employeeId);

    /**
     * 查询菜单下按钮(操作权限)
     *
     * @param buttonVO
     * @return 指定员工的菜单树
     */
    BasicResult<List<ButtonDTO>> queryButton(ButtonVO buttonVO);
}
