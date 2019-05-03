package com.panshi.hujin2.base.security.facade.menu;

import com.panshi.hujin2.base.common.factory.MessageFactory;
import com.panshi.hujin2.base.domain.result.BasicResult;
import com.panshi.hujin2.base.domain.result.BasicResultCode;
import com.panshi.hujin2.base.security.service.system.menu.ButtonDTO;
import com.panshi.hujin2.base.security.service.system.menu.IMenuService;
import com.panshi.hujin2.base.security.service.system.menu.MenuDTO;
import com.panshi.hujin2.base.security.web.controller.system.menu.ButtonVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZhangZhiHao 2018/6/22 16:29
 */
@Service
public class MenuFacadeImpl implements IMenuFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private IMenuService menuService;

    @Override
    public BasicResult<Void> add(MenuDTO menuDTO) {
        try {
            menuService.add(menuDTO);
            return BasicResult.ok();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return BasicResult.error(BasicResultCode.ADD_FAIL.getCode(), MessageFactory.getMsg("G10881006"));
        }
    }

    @Override
    public BasicResult<Void> delete(Integer id) {
        try {
            menuService.delete(id);
            return BasicResult.ok();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return BasicResult.error(BasicResultCode.DELETE_FAIL.getCode(), MessageFactory.getMsg("G10881007"));
        }
    }

    @Override
    public BasicResult<Void> update(MenuDTO menuDTO) {
        try {
            menuService.update(menuDTO);
            return BasicResult.ok();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return BasicResult.error(BasicResultCode.UPDATE_FAIL.getCode(), MessageFactory.getMsg("G10881008"));
        }
    }

    @Override
    public BasicResult<List<MenuDTO>> queryAllMenuTree() {
        try {
            return BasicResult.ok(menuService.queryAllMenuTree());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return BasicResult.error(BasicResultCode.QUERY_FAIL.getCode(), MessageFactory.getMsg("G10881009"));
        }
    }

    @Override
    public BasicResult<List<MenuDTO>> getEmployeeMenuTree(Integer employeeId) {
        try {
            return BasicResult.ok(menuService.getEmployeeMenuTree(employeeId));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return BasicResult.error(BasicResultCode.QUERY_FAIL.getCode(), MessageFactory.getMsg("G10881009"));
        }
    }

    @Override
    public BasicResult<List<ButtonDTO>> queryButton(ButtonVO buttonVO) {
        return BasicResult.ok(menuService.queryButton(buttonVO));
    }
}
