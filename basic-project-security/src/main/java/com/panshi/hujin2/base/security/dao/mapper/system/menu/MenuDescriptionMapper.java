package com.panshi.hujin2.base.security.dao.mapper.system.menu;


import com.panshi.hujin2.base.security.dao.model.system.menu.MenuDescription;
import org.apache.ibatis.annotations.Param;

/**
 * @author ZhangZhiHao 2018/6/28 14:20
 */
public interface MenuDescriptionMapper {

    /**
     * 添加菜单说明
     *
     * @param menuDescription 菜单说明dao模型
     */
    void add(MenuDescription menuDescription);

    /**
     * 删除菜单说明
     *
     * @param menuId 菜单id
     */
    void delete(Integer menuId);

    /**
     * 查询菜单描述
     *
     * @param
     */
    String getDescription(@Param("id") Integer id,@Param("languageAndCountry") String languageAndCountry);
}
