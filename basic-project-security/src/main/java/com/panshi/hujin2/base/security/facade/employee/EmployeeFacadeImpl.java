package com.panshi.hujin2.base.security.facade.employee;

import com.panshi.hujin2.base.common.factory.MessageFactory;
import com.panshi.hujin2.base.domain.result.BasicResult;
import com.panshi.hujin2.base.domain.result.BasicResultCode;
import com.panshi.hujin2.base.security.dao.qo.EmployeeQO;
import com.panshi.hujin2.base.security.service.system.employee.EmployeeBO;
import com.panshi.hujin2.base.security.service.system.employee.IEmployeeService;
import com.panshi.hujin2.base.security.service.util.EmployeeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ZhangZhiHao 2018/6/22 16:30
 */
@Service
public class EmployeeFacadeImpl implements IEmployeeFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private IEmployeeService employeeService;

    @Override
    public BasicResult<Void> add(EmployeeBO employeeBO) {
        try {
            employeeService.add(employeeBO);
            return BasicResult.ok();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return BasicResult.error(BasicResultCode.ADD_FAIL.getCode(), MessageFactory.getMsg("G10881304"));
        }
    }

    @Override
    public BasicResult<Void> delete(Integer id) {
        try {
            employeeService.delete(id);
            return BasicResult.ok();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return BasicResult.error(BasicResultCode.DELETE_FAIL.getCode(), MessageFactory.getMsg("G10881305"));
        }
    }

    @Override
    public BasicResult<Void> update(EmployeeBO employeeBO) {
        try {
            employeeService.update(employeeBO);
            return BasicResult.ok();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return BasicResult.error(BasicResultCode.UPDATE_FAIL.getCode(), MessageFactory.getMsg("G10881306"));
        }
    }

    @Override
    public BasicResult<Optional<EmployeeBO>> get(Integer id) {
        try {
            Optional<EmployeeBO> optional = employeeService.get(id);
            return BasicResult.ok(optional);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return BasicResult.error(BasicResultCode.QUERY_FAIL.getCode(), MessageFactory.getMsg("G10881307"));
        }
    }

    @Override
    public BasicResult<List<EmployeeBO>> query(EmployeeQO employeeQO) {
        try {
            return BasicResult.ok(employeeService.query(employeeQO));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return BasicResult.error(BasicResultCode.QUERY_FAIL.getCode(), MessageFactory.getMsg("G10881307"));
        }
    }

    @Override
    public BasicResult<Void> updatePassword(EmployeeBO employeeBO) {
        try {
            employeeService.updatePassword(employeeBO);
            // 验证当前用户密码
            return BasicResult.ok();
        }catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return BasicResult.error(BasicResultCode.UPDATE_FAIL.getCode(), MessageFactory.getMsg("G10881306"));
        }
    }

    @Override
    public BasicResult<Void> updatePhonenum(EmployeeBO employeeBO) {
        try {
            employeeService.updatePhonenum(employeeBO);
            return BasicResult.ok();
        }catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return BasicResult.error(BasicResultCode.UPDATE_FAIL.getCode(), MessageFactory.getMsg("G10881306"));
        }
    }

}
