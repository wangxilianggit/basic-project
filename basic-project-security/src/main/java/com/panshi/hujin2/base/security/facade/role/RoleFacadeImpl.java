package com.panshi.hujin2.base.security.facade.role;

import com.panshi.hujin2.base.common.factory.MessageFactory;
import com.panshi.hujin2.base.domain.result.BasicResult;
import com.panshi.hujin2.base.domain.result.BasicResultCode;
import com.panshi.hujin2.base.security.dao.qo.RoleQO;
import com.panshi.hujin2.base.security.service.system.role.IRoleService;
import com.panshi.hujin2.base.security.service.system.role.RoleInputBO;
import com.panshi.hujin2.base.security.service.system.role.RoleOutputBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ZhangZhiHao 2018/6/22 16:29
 */
@Service
public class RoleFacadeImpl implements IRoleFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private IRoleService roleService;

    @Override
    public BasicResult<Void> add(RoleInputBO roleInputBO) {
        try {
            roleService.add(roleInputBO);
            return BasicResult.ok();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return BasicResult.error(BasicResultCode.ADD_FAIL.getCode(), MessageFactory.getMsg("G10881202"));
        }
    }

    @Override
    public BasicResult<Void> delete(Integer roleId) {
        try {
            roleService.delete(roleId);
            return BasicResult.ok();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return BasicResult.error(BasicResultCode.DELETE_FAIL.getCode(), MessageFactory.getMsg("G10881203"));
        }
    }

    @Override
    public BasicResult<Void> update(RoleInputBO roleInputBO) {
        try {
            roleService.update(roleInputBO);
            return BasicResult.ok();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return BasicResult.error(BasicResultCode.UPDATE_FAIL.getCode(), MessageFactory.getMsg("G10881204"));
        }
    }

    @Override
    public BasicResult<Optional<RoleOutputBO>> get(Integer roleId) {
        try {
            return BasicResult.ok(roleService.get(roleId));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return BasicResult.error(BasicResultCode.QUERY_FAIL.getCode(), MessageFactory.getMsg("G10881205"));
        }
    }

    @Override
    public BasicResult<List<RoleOutputBO>> query(RoleQO roleQO) {
        try {
            return BasicResult.ok(roleService.query(roleQO));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return BasicResult.error(BasicResultCode.QUERY_FAIL.getCode(), MessageFactory.getMsg("G10881205"));
        }
    }
}
