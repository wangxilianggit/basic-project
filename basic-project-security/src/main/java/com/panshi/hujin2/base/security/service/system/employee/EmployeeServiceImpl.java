package com.panshi.hujin2.base.security.service.system.employee;

import com.panshi.hujin2.base.common.util.DataUtils;
import com.panshi.hujin2.base.common.util.DozerUtils;
import com.panshi.hujin2.base.security.dao.mapper.system.employee.EmployeeMapper;
import com.panshi.hujin2.base.security.dao.model.system.department.DepartmentDO;
import com.panshi.hujin2.base.security.dao.model.system.employee.EmployeeDO;
import com.panshi.hujin2.base.security.dao.model.system.employee.EmployeeRelevanceDepartment;
import com.panshi.hujin2.base.security.dao.model.system.employee.EmployeeRelevanceRole;
import com.panshi.hujin2.base.security.dao.model.system.menu.ControllerAuthentication;
import com.panshi.hujin2.base.security.dao.model.system.role.RoleDO;
import com.panshi.hujin2.base.security.dao.qo.EmployeeQO;
import com.panshi.hujin2.base.security.service.system.department.IDepartmentService;
import com.panshi.hujin2.base.security.service.system.employee_department.IEmployeeDepartmentService;
import com.panshi.hujin2.base.security.service.system.employee_role.IEmployeeRoleService;
import com.panshi.hujin2.base.security.service.system.role.IRoleService;
import com.panshi.hujin2.base.security.service.system.role.RoleOutputBO;
import com.panshi.hujin2.base.security.service.util.EmployeeUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author ZhangZhiHao 2018/6/21 10:11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeeServiceImpl implements IEmployeeService, UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private IEmployeeDepartmentService employeeDepartmentService;
    @Autowired
    private IEmployeeRoleService employeeRoleService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IRoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeDO employeeDO = employeeMapper.getByUserName(username);
        if (employeeDO != null) {
            employeeDO = employeeMapper.loadUserByUsername(username);
            EmployeeBO employeeBO = doTransformBO(employeeDO);
            // 设置部门集合
            List<DepartmentDO> departmentDOS = employeeDO.getDepartmentDOS();
            employeeBO.setDepartmentDTOS(departmentService.dosTransformDto(departmentDOS));
            // 设置角色集合
            List<RoleDO> roleDOS = employeeDO.getRoleDOS();
                List<RoleOutputBO> roleOutputBOS = roleService.dosTransformOutputBo(roleDOS);
                employeeBO.setRoleOutputBOS(roleOutputBOS);
            // 设置controller权限集合
            List<ControllerAuthentication> controllerAuthentications = employeeDO.getControllerAuthentications();
            if (CollectionUtils.isNotEmpty(controllerAuthentications)) {
                employeeBO.setControllerAuthentications(controllerAuthentications);
            }
            return employeeBO;
        }
        return new EmployeeBO();
    }

    @Override
    public void add(EmployeeBO employeeBO) {
        EmployeeDO employeeDO = boTransformDo(employeeBO);
        employeeDO.setCreateOperatorId(EmployeeUtil.getCurrentEmployeeId());
        employeeDO.setPassword(passwordEncoder.encode(employeeDO.getPassword()));
        employeeMapper.add(employeeDO);
        Integer employeeId = employeeDO.getId();
        // 添加员工部门关联信息
        addEmployeeDepartment(employeeId, employeeBO.getDepartmentIds());
        // 添加员工角色关联信息
        addEmployeeRole(employeeId, employeeBO.getRoleIds());
    }

    @Override
    public void delete(Integer employeeId) {
        Objects.requireNonNull(employeeId);
        employeeMapper.delete(employeeId, EmployeeUtil.getCurrentEmployeeId());
        // 解除员工和部门的关联
        employeeDepartmentService.delete(employeeId, null);
        // 解除员工和角色的关联
        employeeRoleService.delete(employeeId, null);
    }

    @Override
    public void update(EmployeeBO employeeBO) {
        Integer employeeId = employeeBO.getId();
        Objects.requireNonNull(employeeId);
        EmployeeDO employeeDO = boTransformDo(employeeBO);
        String password = employeeDO.getPassword();
        if (password != null) {
            employeeDO.setPassword(passwordEncoder.encode(password));
        }
        employeeDO.setModifyOperatorId(EmployeeUtil.getCurrentEmployeeId());
        employeeMapper.update(employeeDO);
        // 重新关联部门
        employeeDepartmentService.delete(employeeId, null);
        addEmployeeDepartment(employeeId, employeeBO.getDepartmentIds());
        // 重新关联角色
        employeeRoleService.delete(employeeId, null);
        addEmployeeRole(employeeId, employeeBO.getRoleIds());
    }

    @Override
    public Optional<EmployeeBO> get(Integer id) {
        EmployeeDO employeeDO = employeeMapper.get(id);
        if (employeeDO != null) {
            EmployeeBO employeeBO = doTransformBO(employeeDO);
            List<DepartmentDO> departmentDOS = employeeDO.getDepartmentDOS();
            employeeBO.setDepartmentDTOS(departmentService.dosTransformDto(departmentDOS));
            List<RoleDO> roleDOS = employeeDO.getRoleDOS();
            employeeBO.setRoleOutputBOS(roleService.dosTransformOutputBo(roleDOS));
            return Optional.of(employeeBO);
        }
        return Optional.empty();
    }

    @Override
    public List<EmployeeBO> query(EmployeeQO employeeQO) {
        int count = employeeMapper.count(employeeQO);
        if (count < 1) {
            return Collections.emptyList();
        }
        employeeQO.calculate(count);
        List<EmployeeDO> employeeDOS = employeeMapper.query(employeeQO);
        if (CollectionUtils.isNotEmpty(employeeDOS)) {
            List<EmployeeBO> employeeBOS = new ArrayList<>();
            for (EmployeeDO employeeDO : employeeDOS) {
                EmployeeBO employeeBO = doTransformBO(employeeDO);
                List<DepartmentDO> departmentDOS = employeeDO.getDepartmentDOS();
                employeeBO.setDepartmentDTOS(departmentService.dosTransformDto(departmentDOS));
                List<RoleDO> roleDOS = employeeDO.getRoleDOS();
                employeeBO.setRoleOutputBOS(roleService.dosTransformOutputBo(roleDOS));
                employeeBOS.add(employeeBO);
            }
            return employeeBOS;
        }
        return Collections.emptyList();
    }

    @Override
    public EmployeeBO getEmployeeBase(Integer id) {
        EmployeeDO employeeDO = employeeMapper.getEmployeeBase(id);
        return DozerUtils.convert(employeeDO, EmployeeBO.class);
    }

    @Override
    public List<EmployeeBO> selectEmployeeName(List<Integer> employeeIds) {
        List<EmployeeDO> employeeDOS = employeeMapper.selectEmployeeName(employeeIds);
        return DataUtils.transformList(employeeDOS, EmployeeBO.class);
    }

    @Override
    public void updatePassword(EmployeeBO employeeBO) {
        EmployeeDO employeeDO = boTransformDo(employeeBO);
        String password = employeeDO.getPassword();
        if (password != null) {
            employeeDO.setPassword(passwordEncoder.encode(password));
        }
        employeeDO.setModifyOperatorId(EmployeeUtil.getCurrentEmployeeId());
        employeeMapper.update(employeeDO);
    }

    @Override
    public void updatePhonenum(EmployeeBO employeeBO) {
        EmployeeDO employeeDO = boTransformDo(employeeBO);
        employeeDO.setModifyOperatorId(EmployeeUtil.getCurrentEmployeeId());
        employeeMapper.update(employeeDO);
    }

    private void addEmployeeDepartment(Integer employeeId, List<Integer> departmentIds) {
        if (CollectionUtils.isNotEmpty(departmentIds)) {
            Integer currentEmployeeId = EmployeeUtil.getCurrentEmployeeId();
            for (Integer departmentId : departmentIds) {
                EmployeeRelevanceDepartment employeeRelevanceDepartment = new EmployeeRelevanceDepartment(employeeId, departmentId, currentEmployeeId);
                employeeDepartmentService.add(employeeRelevanceDepartment);
            }
        }
    }

    private void addEmployeeRole(Integer employeeId, List<Integer> roleIds) {
        if (CollectionUtils.isNotEmpty(roleIds)) {
            Integer currentEmployeeId = EmployeeUtil.getCurrentEmployeeId();
            for (Integer roleId : roleIds) {
                EmployeeRelevanceRole employeeRelevanceRole = new EmployeeRelevanceRole(employeeId, roleId, currentEmployeeId);
                employeeRoleService.add(employeeRelevanceRole);
            }
        }
    }

    // 以下方法为模型转换

    private EmployeeBO doTransformBO(EmployeeDO employeeDO) {
        EmployeeBO employeeBO = DozerUtils.convert(employeeDO, EmployeeBO.class);
        employeeBO.setEnabled(employeeDO.getEnabled());
        return employeeBO;
    }

    private EmployeeDO boTransformDo(EmployeeBO employeeBO) {
        EmployeeDO employeeDO = DozerUtils.convert(employeeBO, EmployeeDO.class);
        employeeDO.setEnabled(employeeBO.getEnabled());
        return employeeDO;
    }
}