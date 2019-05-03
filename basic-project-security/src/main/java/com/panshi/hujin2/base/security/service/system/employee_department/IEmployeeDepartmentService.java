package com.panshi.hujin2.base.security.service.system.employee_department;

import com.panshi.hujin2.base.security.dao.model.system.employee.EmployeeRelevanceDepartment;

/**
 * @author ZhangZhiHao 2018/6/29 18:18
 */
public interface IEmployeeDepartmentService {

    /**
     * 添加员工和部门关联
     *
     * @param employeeRelevanceDepartment 员工部门关联模型
     */
    void add(EmployeeRelevanceDepartment employeeRelevanceDepartment);

    /**
     * 删除员工和部门关联
     *
     * @param employeeId   员工id
     * @param departmentId 部门id
     */
    void delete(Integer employeeId, Integer departmentId);
}
