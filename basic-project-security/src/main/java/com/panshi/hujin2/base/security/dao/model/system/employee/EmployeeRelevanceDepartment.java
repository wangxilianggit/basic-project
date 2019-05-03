package com.panshi.hujin2.base.security.dao.model.system.employee;

import com.panshi.hujin2.base.security.dao.model.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author ZhangZhiHao 2018/6/27 14:51
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class EmployeeRelevanceDepartment extends BaseDO {

    /**
     * 员工id
     */
    private Integer employeeId;

    /**
     * 部门id
     */
    private Integer departmentId;

    public EmployeeRelevanceDepartment(Integer employeeId, Integer departmentId, Integer createOperatorId) {
        this.employeeId = employeeId;
        this.departmentId = departmentId;
        this.createOperatorId = createOperatorId;
    }
}
