package com.panshi.hujin2.base.security.web.controller.system.role;

import com.panshi.hujin2.base.common.util.DozerUtils;
import com.panshi.hujin2.base.domain.result.BasicResult;
import com.panshi.hujin2.base.security.dao.qo.RoleQO;
import com.panshi.hujin2.base.security.facade.role.IRoleFacade;
import com.panshi.hujin2.base.security.service.system.role.RoleInputBO;
import com.panshi.hujin2.base.security.service.system.role.RoleOutputBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author ZhangZhiHao 2018/6/22 14:12
 */
@Api("角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleFacade roleFacade;

    @ApiOperation("添加角色")
    @PostMapping
    public BasicResult<Void> add(@Valid @RequestBody RoleInputVO roleInputVO) {
        RoleInputBO roleInputBO = DozerUtils.convert(roleInputVO, RoleInputBO.class);
        return roleFacade.add(roleInputBO);
    }

    @DeleteMapping("/{roleId:\\d+}")
    @ApiOperation("删除角色")
    public BasicResult<Void> delete(@ApiParam("角色id") @PathVariable Integer roleId) {
        return roleFacade.delete(roleId);
    }

    @ApiOperation("修改角色")
    @PutMapping
    public BasicResult<Void> update(@Valid @RequestBody RoleInputVO roleInputVO) {
        RoleInputBO roleInputBO = DozerUtils.convert(roleInputVO, RoleInputBO.class);
        return roleFacade.update(roleInputBO);
    }

    @ApiOperation("查询角色")
    @GetMapping("/{roleId:\\d+}")
    public BasicResult<RoleOutputVO> get(@ApiParam("角色id") @PathVariable Integer roleId) {
        BasicResult<Optional<RoleOutputBO>> result = roleFacade.get(roleId);
        if (result.getSuccess()) {
            Optional<RoleOutputBO> optional = result.getData();
            if (optional.isPresent()) {
                return BasicResult.ok(DozerUtils.convert(optional.get(), RoleOutputVO.class));
            }
            return BasicResult.ok();
        }
        return BasicResult.error(result.getCode(), result.getMessage());
    }

    @ApiOperation("查询角色-条件")
    @GetMapping
    public BasicResult<List<RoleOutputVO>> query(@ApiIgnore RoleQO roleQO) {
        BasicResult<List<RoleOutputBO>> result = roleFacade.query(roleQO);
        if (result.getSuccess()) {
            List<RoleOutputBO> roleOutputBOS = result.getData();
            if (CollectionUtils.isNotEmpty(roleOutputBOS)) {
                List<RoleOutputVO> roleOutputVOS = DozerUtils.convertList(roleOutputBOS, RoleOutputVO.class);
                return BasicResult.ok(roleOutputVOS, roleQO.getPage());
            }
            return BasicResult.ok(Collections.emptyList());
        }
        return BasicResult.error(result.getCode(), result.getMessage());
    }

}
