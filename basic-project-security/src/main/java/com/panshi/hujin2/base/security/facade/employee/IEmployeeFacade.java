package com.panshi.hujin2.base.security.facade.employee;

import com.panshi.hujin2.base.domain.result.BasicResult;
import com.panshi.hujin2.base.security.dao.qo.EmployeeQO;
import com.panshi.hujin2.base.security.service.system.employee.EmployeeBO;

import java.util.List;
import java.util.Optional;

/**
 * @author ZhangZhiHao 2018/6/22 16:29
 */
public interface IEmployeeFacade {

    /**
     * 添加员工
     *
     * @param employeeBO 员工业务模型
     * @return void
     */
    BasicResult<Void> add(EmployeeBO employeeBO);

    /**
     * 删除员工
     *
     * @param id 员工id
     * @return void
     */
    BasicResult<Void> delete(Integer id);

    /**
     * 更新员工
     *
     * @param employeeBO 员工业务模型
     * @return void
     */
    BasicResult<Void> update(EmployeeBO employeeBO);

    /**
     * 查询员工
     *
     * @param id 员工id
     * @return 员工业务模型
     */
    BasicResult<Optional<EmployeeBO>> get(Integer id);

    /**
     * 查询员工- 列表
     *
     * @param employeeQO 员工查询对象
     * @return 员工业务模型列表
     */
    BasicResult<List<EmployeeBO>> query(EmployeeQO employeeQO);

    /**
     * 员工修改密码
     *
     * @param employeeBO
     * @return void
     */
    BasicResult<Void> updatePassword(EmployeeBO employeeBO);

    /**
     * 员工修改手机号
     *
     * @param employeeBO
     * @return void
     */
    BasicResult<Void> updatePhonenum(EmployeeBO employeeBO);
}
