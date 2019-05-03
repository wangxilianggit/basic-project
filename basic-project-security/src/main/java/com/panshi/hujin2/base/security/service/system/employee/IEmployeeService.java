package com.panshi.hujin2.base.security.service.system.employee;

import com.panshi.hujin2.base.security.dao.qo.EmployeeQO;

import java.util.List;
import java.util.Optional;

/**
 * @author ZhangZhiHao 2018/6/21 10:10
 */
public interface IEmployeeService {

    /**
     * 添加员工
     *
     * @param employeeBO 员工业务模型
     */
    void add(EmployeeBO employeeBO);

    /**
     * 删除员工
     *
     * @param employeeId 员工id
     */
    void delete(Integer employeeId);

    /**
     * 更新员工
     *
     * @param employeeBO 员工业务模型
     */
    void update(EmployeeBO employeeBO);

    /**
     * 查询员工
     *
     * @param id 员工id
     * @return 员工业务模型
     */
    Optional<EmployeeBO> get(Integer id);

    /**
     * 查询员工- 列表
     *
     * @param employeeQO 员工查询对象
     * @return 员工业务模型列表
     */
    List<EmployeeBO> query(EmployeeQO employeeQO);

    /**
     * 查询管理员基本信息
     * @param id
     * @return
     */
    EmployeeBO getEmployeeBase(Integer id);

    List<EmployeeBO> selectEmployeeName(List<Integer> employeeIds);

    /**
     * 更新员工密码
     *
     * @param employeeBO 员工业务模型
     */
    void updatePassword(EmployeeBO employeeBO);

    /**
     * 更新员工手机号
     *
     * @param employeeBO 员工业务模型
     */
    void updatePhonenum(EmployeeBO employeeBO);
}
