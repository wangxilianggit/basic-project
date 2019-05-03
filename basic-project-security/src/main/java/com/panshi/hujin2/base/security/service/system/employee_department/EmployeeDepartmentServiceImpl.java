package com.panshi.hujin2.base.security.service.system.employee_department;

import com.panshi.hujin2.base.security.dao.mapper.system.employee_department.EmployeeDepartmentMapper;
import com.panshi.hujin2.base.security.dao.model.system.employee.EmployeeRelevanceDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZhangZhiHao 2018/6/29 18:19
 */
@Service
public class EmployeeDepartmentServiceImpl implements IEmployeeDepartmentService {

    @Autowired
    private EmployeeDepartmentMapper employeeDepartmentMapper;

    @Override
    public void add(EmployeeRelevanceDepartment employeeRelevanceDepartment) {
        employeeDepartmentMapper.add(employeeRelevanceDepartment);
    }

    @Override
    public void delete(Integer employeeId, Integer departmentId) {
        employeeDepartmentMapper.delete(employeeId, departmentId);
    }

}
