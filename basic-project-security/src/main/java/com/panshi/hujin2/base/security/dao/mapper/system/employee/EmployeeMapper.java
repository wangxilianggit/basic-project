package com.panshi.hujin2.base.security.dao.mapper.system.employee;

import com.panshi.hujin2.base.security.dao.model.system.employee.EmployeeDO;
import com.panshi.hujin2.base.security.dao.qo.EmployeeQO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ZhangZhiHao 2018/6/22 16:07
 */
public interface EmployeeMapper {

    /**
     * 添加员工
     *
     * @param employeeDO 员工dao模型
     */
    void add(EmployeeDO employeeDO);

    /**
     * 删除员工
     *
     * @param employeeId        员工id
     * @param currentEmployeeId 当前操作的用户
     */
    void delete(@Param("employeeId") Integer employeeId, @Param("currentEmployeeId") Integer currentEmployeeId);

    /**
     * 更新员工
     *
     * @param employeeDO 员工dao模型
     */
    void update(EmployeeDO employeeDO);

    /**
     * 查询员工
     *
     * @param employeeId 员工id
     * @return 员工dao模型
     */
    EmployeeDO get(Integer employeeId);

    /**
     * 登录操作,查看员工所有信息
     *
     * @param username 用户名
     * @return 员工dao模型
     */
    EmployeeDO loadUserByUsername(String username);

    /**
     * 查询员工- 个数
     * @param employeeQO 员工查询条件
     * @return 符合条件的员工个数
     */
    int count(EmployeeQO employeeQO);

    /**
     * 查询员工- 列表
     *
     * @param employeeQO 员工查询条件
     * @return 符合条件的员工列表
     */
    List<EmployeeDO> query(EmployeeQO employeeQO);

    /**
     * 查询基本信息
     * @param id
     * @return
     */
    EmployeeDO getEmployeeBase(Integer id);

    EmployeeDO getByUserName(String username);

    List<EmployeeDO> selectEmployeeName(List<Integer> employeeIds);
}
