package com.panshi.hujin2.base.security.dao.mapper.system.employee_department;

import com.panshi.hujin2.base.security.dao.model.system.employee.EmployeeRelevanceDepartment;
import org.apache.ibatis.annotations.Param;

/**
 * @author ZhangZhiHao 2018/6/28 10:51
 */
public interface EmployeeDepartmentMapper {

    /**
     * 添加员工部门关联信息
     *
     * @param employeeRelevanceDepartment 员工部门关联信息
     */
    void add(EmployeeRelevanceDepartment employeeRelevanceDepartment);

    /**
     * 删除员工部门关联信息
     *
     * @param employeeId   员工id
     * @param departmentId 部门id
     */
    void delete(@Param("employeeId") Integer employeeId, @Param("departmentId") Integer departmentId);


}
