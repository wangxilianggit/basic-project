package com.panshi.hujin2.base.security.dao.mapper.system.employee_role;

import com.panshi.hujin2.base.security.dao.model.system.employee.EmployeeRelevanceRole;
import org.apache.ibatis.annotations.Param;

/**
 * @author ZhangZhiHao 2018/6/28 14:43
 */
public interface EmployeeRoleMapper {

    /**
     * 添加员工角色关联信息
     *
     * @param employeeRelevanceRole 员工角色关联信息
     */
    void add(EmployeeRelevanceRole employeeRelevanceRole);

    /**
     * 删除员工角色关联信息
     *
     * @param employeeId 员工id
     * @param roleId     角色id
     */
    void delete(@Param("employeeId") Integer employeeId, @Param("roleId") Integer roleId);

}
