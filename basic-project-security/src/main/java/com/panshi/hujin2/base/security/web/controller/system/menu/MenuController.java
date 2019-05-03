package com.panshi.hujin2.base.security.web.controller.system.menu;

import com.panshi.hujin2.base.common.util.DozerUtils;
import com.panshi.hujin2.base.domain.result.BasicResult;
import com.panshi.hujin2.base.security.dao.model.system.menu.MenuDescription;
import com.panshi.hujin2.base.security.facade.menu.IMenuFacade;
import com.panshi.hujin2.base.security.service.system.menu.ButtonDTO;
import com.panshi.hujin2.base.security.service.system.menu.MenuDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ZhangZhiHao 2018/6/22 9:11
 */
@Api("菜单管理")
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private IMenuFacade menuFacade;

    @ApiOperation("添加菜单")
    @PostMapping
    public BasicResult<Void> add(@Valid @RequestBody MenuVO menuVO) {
        MenuDTO menuDTO = DozerUtils.convert(menuVO, MenuDTO.class);
        menuDTO.setMenuDescriptions(DozerUtils.convertList(menuVO.getMenuDescriptionVOS(), MenuDescription.class));
        return menuFacade.add(menuDTO);
    }

    @ApiOperation("删除菜单")
    @DeleteMapping("/{id:\\d+}")
    public BasicResult<Void> delete(@ApiParam("菜单id") @PathVariable Integer id) {
        return menuFacade.delete(id);
    }

    @ApiOperation("修改菜单")
    @PutMapping
    public BasicResult<Void> update(@Valid @RequestBody MenuVO menuVO) {
        MenuDTO menuDTO = DozerUtils.convert(menuVO, MenuDTO.class);
        menuDTO.setMenuDescriptions(DozerUtils.convertList(menuVO.getMenuDescriptionVOS(), MenuDescription.class));
        return menuFacade.update(menuDTO);
    }

    @ApiOperation("查询菜单-员工")
    @GetMapping("/tree/{employeeId:\\d+}")
    public BasicResult<List<MenuDTO>> getEmployeeMenuTree(@PathVariable Integer employeeId) {
        return menuFacade.getEmployeeMenuTree(employeeId);
    }

    @ApiOperation("查询菜单-所有")
    @GetMapping("/tree")
    public BasicResult<List<MenuDTO>> queryAllMenuTree() {
        return menuFacade.queryAllMenuTree();
    }

    @ApiOperation("查询菜单下按钮(操作权限)")
    @PostMapping("/button")
    public BasicResult<List<ButtonDTO>> queryButton(@Valid @RequestBody ButtonVO buttonVO) {

        return menuFacade.queryButton(buttonVO);
    }
}
