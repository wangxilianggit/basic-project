package com.panshi.hujin2.base.security.facade.department;

import com.panshi.hujin2.base.common.factory.MessageFactory;
import com.panshi.hujin2.base.domain.result.BasicResult;
import com.panshi.hujin2.base.domain.result.BasicResultCode;
import com.panshi.hujin2.base.security.service.system.department.DepartmentDTO;
import com.panshi.hujin2.base.security.service.system.department.IDepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZhangZhiHao 2018/6/22 16:28
 */
@Service
public class DepartmentFacadeImpl implements IDepartmentFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private IDepartmentService departmentService;

    @Override
    public BasicResult<Void> add(DepartmentDTO departmentDTO) {
        try {
            departmentService.add(departmentDTO);
            return BasicResult.ok();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return BasicResult.error(BasicResultCode.ADD_FAIL.getCode(), MessageFactory.getMsg("G10881101"));
        }
    }

    @Override
    public BasicResult<Void> delete(Integer departmentId) {
        try {
            departmentService.delete(departmentId);
            return BasicResult.ok();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return BasicResult.error(BasicResultCode.DELETE_FAIL.getCode(), MessageFactory.getMsg("G10881102"));
        }
    }

    @Override
    public BasicResult<Void> update(DepartmentDTO departmentDTO) {
        try {
            departmentService.update(departmentDTO);
            return BasicResult.ok();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return BasicResult.error(BasicResultCode.UPDATE_FAIL.getCode(), MessageFactory.getMsg("G10881103"));
        }
    }

    @Override
    public BasicResult<List<DepartmentDTO>> queryAllDepartmentTree() {
        try {
            return BasicResult.ok(departmentService.queryAllDepartmentTree());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return BasicResult.error(BasicResultCode.QUERY_FAIL.getCode(), MessageFactory.getMsg("G10881104"));
        }
    }
}
