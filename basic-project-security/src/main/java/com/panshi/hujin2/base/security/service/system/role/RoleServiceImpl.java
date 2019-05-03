package com.panshi.hujin2.base.security.service.system.role;

import com.panshi.hujin2.base.common.util.DozerUtils;
import com.panshi.hujin2.base.security.dao.mapper.system.role.RoleMapper;
import com.panshi.hujin2.base.security.dao.model.system.menu.MenuDO;
import com.panshi.hujin2.base.security.dao.model.system.role.RoleDO;
import com.panshi.hujin2.base.security.dao.model.system.role.RoleRelevanceMenu;
import com.panshi.hujin2.base.security.dao.qo.RoleQO;
import com.panshi.hujin2.base.security.service.system.employee_role.IEmployeeRoleService;
import com.panshi.hujin2.base.security.service.system.menu.IMenuService;
import com.panshi.hujin2.base.security.service.system.role_menu.IRoleMenuService;
import com.panshi.hujin2.base.security.service.util.EmployeeUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author ZhangZhiHao 2018/6/22 16:02
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private IRoleMenuService roleMenuService;
    @Autowired
    private IEmployeeRoleService employeeRoleService;
    @Autowired
    private IMenuService menuService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(RoleInputBO roleInputBO) {
        Integer currentEmployeeId = EmployeeUtil.getCurrentEmployeeId();
        RoleDO roleDO = inputBOTransformDO(roleInputBO);
        roleDO.setCreateOperatorId(currentEmployeeId);
        roleMapper.add(roleDO);
        // 关联角色和菜单
        addRoleMenu(roleInputBO, roleDO.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer roleId) {
        Objects.requireNonNull(roleId);
        roleMapper.delete(roleId, EmployeeUtil.getCurrentEmployeeId());
        // 删除角色和菜单关联信息
        roleMenuService.delete(roleId, null);
        // 解除员工和角色的关联信息
        employeeRoleService.delete(null, roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RoleInputBO roleInputBO) {
        Integer roleId = roleInputBO.getId();
        Objects.requireNonNull(roleId);
        RoleDO roleDO = inputBOTransformDO(roleInputBO);
        roleDO.setModifyOperatorId(EmployeeUtil.getCurrentEmployeeId());
        roleMapper.update(roleDO);
        // 重新关联角色菜单
        roleMenuService.delete(roleId, null);
        addRoleMenu(roleInputBO, roleId);
    }

    @Override
    public Optional<RoleOutputBO> get(Integer roleId) {
        RoleDO roleDO = roleMapper.get(roleId);
        if (roleDO != null) {
            RoleOutputBO roleOutputBO = doTransformOutputBO(roleDO);
            List<MenuDO> menuDOS = roleDO.getMenuDOS();
            if (CollectionUtils.isNotEmpty(menuDOS)) {
                roleOutputBO.setMenuDTOS(menuService.dosTransFormDto(menuDOS));
            }
            return Optional.of(roleOutputBO);
        }
        return Optional.empty();
    }

    @Override
    public List<RoleOutputBO> query(RoleQO roleQO) {
        int count = roleMapper.count(roleQO);
        if (count < 1) {
            return Collections.emptyList();
        }
        roleQO.calculate(count);
        List<RoleDO> roleDOS = roleMapper.query(roleQO);
        if (CollectionUtils.isNotEmpty(roleDOS)) {
            return dosTransformOutputBo(roleDOS);
        }
        return Collections.emptyList();
    }

    private void addRoleMenu(RoleInputBO roleInputBO, Integer roleId) {
        Integer currentEmployeeId = EmployeeUtil.getCurrentEmployeeId();
        for (Integer menuId : roleInputBO.getMenuIds()) {
            roleMenuService.add(new RoleRelevanceMenu(roleId, menuId, currentEmployeeId));
        }
    }

    // 以下为模型转换方法

    private RoleDO inputBOTransformDO(RoleInputBO roleInputBO) {
        return DozerUtils.convert(roleInputBO, RoleDO.class);
    }

    private RoleOutputBO doTransformOutputBO(RoleDO roleDO) {
        return DozerUtils.convert(roleDO, RoleOutputBO.class);
    }

    @Override
    public List<RoleOutputBO> dosTransformOutputBo(List<RoleDO> roleDOS) {
        if (CollectionUtils.isEmpty(roleDOS)) {
            return Collections.emptyList();
        }
        return DozerUtils.convertList(roleDOS, RoleOutputBO.class);
    }

}
