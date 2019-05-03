package com.panshi.hujin2.base.security.web.controller.system.department;

import com.panshi.hujin2.base.common.util.DozerUtils;
import com.panshi.hujin2.base.domain.result.BasicResult;
import com.panshi.hujin2.base.security.facade.department.IDepartmentFacade;
import com.panshi.hujin2.base.security.service.system.department.DepartmentDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ZhangZhiHao 2018/6/21 15:44
 */
@Api("/部门管理")
@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private IDepartmentFacade departmentFacade;

    @ApiOperation("添加部门")
    @PostMapping
    public BasicResult<Void> add(@Valid @RequestBody DepartmentVO departmentVO) {
        DepartmentDTO departmentDTO = DozerUtils.convert(departmentVO, DepartmentDTO.class);
        return departmentFacade.add(departmentDTO);
    }

    @ApiOperation("删除部门")
    @DeleteMapping("/{departmentId:\\d+}")
    public BasicResult<Void> delete(@ApiParam("部门id") @PathVariable Integer departmentId) {
        return departmentFacade.delete(departmentId);
    }

    @ApiOperation("修改部门")
    @PutMapping
    public BasicResult<Void> update(@Valid @RequestBody DepartmentVO departmentVO) {
        DepartmentDTO departmentDTO = DozerUtils.convert(departmentVO, DepartmentDTO.class);
        return departmentFacade.update(departmentDTO);
    }

    @ApiOperation("查询部门(树)")
    @GetMapping("/tree")
    public BasicResult<List<DepartmentDTO>> queryAllDepartmentTree() {
        return departmentFacade.queryAllDepartmentTree();
    }

}
