package com.panshi.hujin2.base.security.service.system.employee_role;

import com.panshi.hujin2.base.security.dao.model.system.employee.EmployeeRelevanceRole;

/**
 * @author ZhangZhiHao 2018/6/29 18:25
 */
public interface IEmployeeRoleService {

    void add(EmployeeRelevanceRole employeeRelevanceRole);

    void delete(Integer employeeId, Integer roleId);

}
