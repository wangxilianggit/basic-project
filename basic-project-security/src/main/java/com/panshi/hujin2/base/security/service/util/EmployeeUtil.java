package com.panshi.hujin2.base.security.service.util;

import com.panshi.hujin2.base.security.service.system.employee.EmployeeBO;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author ZhangZhiHao 2018/6/21 14:04
 */
public abstract class EmployeeUtil {

    public static EmployeeBO getCurrentEmployee() {
        return (EmployeeBO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static Integer getCurrentEmployeeId() {
        return getCurrentEmployee().getId();
    }
}
