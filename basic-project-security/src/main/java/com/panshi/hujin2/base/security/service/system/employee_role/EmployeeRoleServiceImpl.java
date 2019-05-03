package com.panshi.hujin2.base.security.service.system.employee_role;

import com.panshi.hujin2.base.security.dao.mapper.system.employee_role.EmployeeRoleMapper;
import com.panshi.hujin2.base.security.dao.model.system.employee.EmployeeRelevanceRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZhangZhiHao 2018/6/29 18:25
 */
@Service
public class EmployeeRoleServiceImpl implements IEmployeeRoleService {
    @Autowired
    private EmployeeRoleMapper employeeRoleMapper;

    @Override
    public void add(EmployeeRelevanceRole employeeRelevanceRole) {
        employeeRoleMapper.add(employeeRelevanceRole);
    }

    @Override
    public void delete(Integer employeeId, Integer roleId) {
        employeeRoleMapper.delete(employeeId, roleId);
    }
}
