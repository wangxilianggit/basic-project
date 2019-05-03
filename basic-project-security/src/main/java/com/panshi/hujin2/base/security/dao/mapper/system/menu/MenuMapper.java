package com.panshi.hujin2.base.security.dao.mapper.system.menu;

import com.panshi.hujin2.base.security.dao.model.system.menu.MenuDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ZhangZhiHao 2018/6/22 16:06
 */
public interface MenuMapper {

    /**
     * 添加菜单
     *
     * @param menuDO 菜单dao模型
     */
    void add(MenuDO menuDO);

    /**
     * 删除菜单
     *
     * @param menuId            菜单id
     * @param currentEmployeeId 当前员工id
     */
    void delete(@Param("menuId") Integer menuId, @Param("currentEmployeeId") Integer currentEmployeeId);

    /**
     * 更新菜单
     *
     * @param menuDO 菜单dao模型
     */
    void update(MenuDO menuDO);

    /**
     * 查询所有菜单列表
     *
     * @return 菜单dao模型列表
     */
    List<MenuDO> queryAllMenu();

    /**
     * 查询指定员工的菜单列表
     *
     * @param languageAndCountry 语言
     * @param employeeId         员工id
     * @return 菜单dao模型列表
     */
    List<MenuDO> getEmployeeMenu(@Param("languageAndCountry") String languageAndCountry, @Param("employeeId") Integer employeeId);

    /**
     * 删除父菜单的时候连带删除其子菜单
     *
     * @param parentId          父菜单id
     * @param currentEmployeeId 当前操作用户id
     */
    void deleteByParentId(@Param("parentId") Integer parentId,@Param("currentEmployeeId") Integer currentEmployeeId);

    List<MenuDO> getButton(@Param("languageAndCountry")String languageAndCountry,@Param("id")Integer id);
}
