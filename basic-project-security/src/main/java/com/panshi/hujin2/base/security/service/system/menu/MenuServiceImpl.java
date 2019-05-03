package com.panshi.hujin2.base.security.service.system.menu;

import com.panshi.hujin2.base.common.util.DozerUtils;
import com.panshi.hujin2.base.common.util.LocaleUtils;
import com.panshi.hujin2.base.security.dao.mapper.system.menu.MenuControllerMapper;
import com.panshi.hujin2.base.security.dao.mapper.system.menu.MenuDescriptionMapper;
import com.panshi.hujin2.base.security.dao.mapper.system.menu.MenuMapper;
import com.panshi.hujin2.base.security.dao.model.system.menu.MenuDO;
import com.panshi.hujin2.base.security.dao.model.system.menu.MenuDescription;
import com.panshi.hujin2.base.security.dao.model.system.menu.MenuRelevanceController;
import com.panshi.hujin2.base.security.service.system.role.IRoleService;
import com.panshi.hujin2.base.security.service.system.role.RoleOutputBO;
import com.panshi.hujin2.base.security.service.system.role_menu.IRoleMenuService;
import com.panshi.hujin2.base.security.service.util.EmployeeUtil;
import com.panshi.hujin2.base.security.web.controller.system.menu.ButtonVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ZhangZhiHao 2018/6/22 16:02
 */
@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private IRoleMenuService roleMenuService;
    @Autowired
    private MenuDescriptionMapper menuDescriptionMapper;
    @Autowired
    private MenuControllerMapper menuControllerMapper;
    @Autowired
    private IRoleService roleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(MenuDTO menuDTO) {
        MenuDO menuDO = dtoTransFormDo(menuDTO);
        menuDO.setCreateOperatorId(EmployeeUtil.getCurrentEmployeeId());
        menuMapper.add(menuDO);
        // 关联菜单和语言
        addMenuDescription(menuDO.getId(), menuDTO.getMenuDescriptions());
        // 关联菜单和控制器
        addMenuController(menuDO.getId(), menuDTO.getControllerIds());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer menuId) {
        Objects.requireNonNull(menuId);
        menuMapper.delete(menuId, EmployeeUtil.getCurrentEmployeeId());
        menuMapper.deleteByParentId(menuId, EmployeeUtil.getCurrentEmployeeId());
        // 删除菜单语言关联信息
        menuDescriptionMapper.delete(menuId);
        // 删除菜单角色关联信息
        roleMenuService.delete(null, menuId);
        // 删除菜单控制器关联信息
        menuControllerMapper.delete(menuId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(MenuDTO menuDTO) {
        Integer menuId = menuDTO.getId();
        Objects.requireNonNull(menuId);
        MenuDO menuDO = dtoTransFormDo(menuDTO);
        menuDO.setModifyOperatorId(EmployeeUtil.getCurrentEmployeeId());
        menuMapper.update(menuDO);
        // 重新关联菜单语言
        menuDescriptionMapper.delete(menuId);
        addMenuDescription(menuId, menuDTO.getMenuDescriptions());
        // 重新关联菜单控制器
        menuControllerMapper.delete(menuId);
        addMenuController(menuId, menuDTO.getControllerIds());
    }

    @Override
    public List<MenuDTO> queryAllMenuTree() {
        String languageAndCountry = LocaleUtils.getLanguageAndCountry();
        List<MenuDO> menuDOS = menuMapper.queryAllMenu();
        // 排序 将当期语言环境的菜单描述排在第一个
        for (MenuDO menuDO : menuDOS) {
            List<MenuDescription> menuDescriptions = menuDO.getMenuDescriptions();
            Map<Boolean, List<MenuDescription>> map = new HashMap<>(16);
            List<MenuDescription> other = new ArrayList<>();
            for (MenuDescription menuDescription : menuDescriptions) {
                if (menuDescription.getLocale().equals(languageAndCountry)) {
                    map.put(Boolean.TRUE, Collections.singletonList(menuDescription));
                } else {
                    other.add(menuDescription);
                }
            }
            List<MenuDescription> result = map.get(Boolean.TRUE);
            if (CollectionUtils.isNotEmpty(result)) {
                MenuDescription menuDescription = result.get(0);
                List<MenuDescription> resultList = Stream.of(menuDescription).collect(Collectors.toList());
                resultList.addAll(other);
                menuDO.setMenuDescriptions(resultList);
            } else {
                menuDO.setMenuDescriptions(other);
            }
        }
        return markMenuTree(menuDOS);
    }

    @Override
    public List<MenuDTO> getEmployeeMenuTree(Integer employeeId) {
        List<MenuDO> menuDOS = menuMapper.getEmployeeMenu(LocaleUtils.getLanguageAndCountry(), employeeId);
        return markMenuTree(menuDOS);
    }

    /**
     * 创建菜单树
     *
     * @param menuDOS 原始菜单列表
     * @return 菜单树
     */
    private List<MenuDTO> markMenuTree(List<MenuDO> menuDOS) {
        if (CollectionUtils.isNotEmpty(menuDOS)) {
            List<MenuDTO> menuDTOS = new ArrayList<>();
            for (MenuDO menuDO : menuDOS) {
                if (menuDO.getParentId() == null) {
                    menuDTOS.add(doTransformDto(menuDO));
                }
            }
            if (CollectionUtils.isNotEmpty(menuDTOS)) {
                for (MenuDTO menuDTO : menuDTOS) {
                    menuDTO.setChildren(getChildren(menuDTO.getId(), menuDOS));
                }
                return menuDTOS;
            }
            return Collections.emptyList();
        }
        return Collections.emptyList();
    }

    /**
     * 获取一个菜单的子菜单(会递归子子菜单及子子子菜单及子子子子菜单...)
     *
     * @param parentMenuId 父菜单id
     * @param menuDOS      菜单数据来源集合
     * @return 一个菜单的子菜单集合
     */
    private List<MenuDTO> getChildren(Integer parentMenuId, List<MenuDO> menuDOS) {
        List<MenuDTO> children = new ArrayList<>();
        for (MenuDO menuDO : menuDOS) {
            if (menuDO.getParentId() != null && menuDO.getParentId().equals(parentMenuId)) {
                children.add(doTransformDto(menuDO));
            }
        }
        if (CollectionUtils.isNotEmpty(children)) {
            for (MenuDTO menuDTO : children) {
                menuDTO.setChildren(getChildren(menuDTO.getId(), menuDOS));
            }
        }
        return children;
    }

    private void addMenuController(Integer menuId, List<Integer> controllerIds) {
        if (CollectionUtils.isNotEmpty(controllerIds)) {
            Integer currentEmployeeId = EmployeeUtil.getCurrentEmployeeId();
            for (Integer controllerId : controllerIds) {
                MenuRelevanceController menuRelevanceController = new MenuRelevanceController(menuId, controllerId, currentEmployeeId);
                menuControllerMapper.add(menuRelevanceController);
            }
        }
    }

    private void addMenuDescription(Integer menuId, List<MenuDescription> menuDescriptions) {
        if (CollectionUtils.isNotEmpty(menuDescriptions)) {
            Integer currentEmployeeId = EmployeeUtil.getCurrentEmployeeId();
            for (MenuDescription menuDescription : menuDescriptions) {
                menuDescription.setMenuId(menuId);
                menuDescription.setCreateOperatorId(currentEmployeeId);
                menuDescriptionMapper.add(menuDescription);
            }
        }
    }

    // 以下为模型转换方法

    private MenuDO dtoTransFormDo(MenuDTO menuDTO) {
        return DozerUtils.convert(menuDTO, MenuDO.class);
    }

    private MenuDTO doTransformDto(MenuDO menuDO) {
        return DozerUtils.convert(menuDO, MenuDTO.class);
    }

    @Override
    public List<MenuDTO> dosTransFormDto(List<MenuDO> menuDOS) {
        return DozerUtils.convertList(menuDOS, MenuDTO.class);
    }

    @Override
    public List<ButtonDTO> queryButton(ButtonVO buttonVO) {
        List<Integer> oldMenuIds=new ArrayList<>();
        //取出角色所有菜单(按钮)id,与传递的父菜单查询的子按钮进行匹配
        if(buttonVO.getRoleId()!=null) {
            Optional<RoleOutputBO> optional = roleService.get(buttonVO.getRoleId());
            if (optional.isPresent()) {
                RoleOutputBO roleOutputBO = optional.get();
                List<MenuDTO> oldMenuDTOS = roleOutputBO.getMenuDTOS();
                for (MenuDTO menuDTO:oldMenuDTOS){
                    oldMenuIds.add(menuDTO.getId());
                }
            }
        }
        List<ButtonDTO> buttons = buttonVO.getButtons();
        for(ButtonDTO buttonDTO:buttons){
            //获取菜单描述
            String name=menuDescriptionMapper.getDescription(buttonDTO.getId(),LocaleUtils.getLanguageAndCountry());
            buttonDTO.setName(name);
            List<MenuDO> menuDOS = menuMapper.getButton(LocaleUtils.getLanguageAndCountry(),buttonDTO.getId());
            if(menuDOS.size()>0) {
                List<MenuDTO> menuDTOS = DozerUtils.convertList(menuDOS, MenuDTO.class);
                for (MenuDTO menuDTO : menuDTOS) {
                    if (oldMenuIds.contains(menuDTO.getId())) {
                        menuDTO.setIsAuth("1");
                    }
                }
                buttonDTO.setButtonList(menuDTOS);
            }
        }
        return buttons;
    }

}
