package com.panshi.hujin2.base.security.web.controller.system.employee;

import com.panshi.hujin2.base.common.util.DozerUtils;
import com.panshi.hujin2.base.domain.result.BasicResult;
import com.panshi.hujin2.base.domain.result.BasicResultCode;
import com.panshi.hujin2.base.security.dao.qo.EmployeeQO;
import com.panshi.hujin2.base.security.facade.employee.IEmployeeFacade;
import com.panshi.hujin2.base.security.service.system.employee.EmployeeBO;
import com.panshi.hujin2.base.security.service.system.role.RoleOutputBO;
import com.panshi.hujin2.base.security.service.util.EmployeeUtil;
import com.panshi.hujin2.base.security.web.controller.system.role.RoleOutputVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author ZhangZhiHao 2018/6/21 15:36
 */
@Api("员工管理")
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeFacade employeeFacade;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation("添加员工")
    @PostMapping
    public BasicResult<Void> add(@Valid @RequestBody EmployeeInputVO employeeInputVO) {
        EmployeeBO employeeBO = DozerUtils.convert(employeeInputVO, EmployeeBO.class);
        return employeeFacade.add(employeeBO);
    }

    @ApiOperation("删除员工")
    @DeleteMapping("/{id:\\d+}")
    public BasicResult<Void> delete(@ApiParam("员工id") @PathVariable Integer id) {
        return employeeFacade.delete(id);
    }

    @ApiOperation("修改员工")
    @PutMapping
    public BasicResult<Void> update(@Valid @RequestBody EmployeeInputVO employeeInputVO) {
        EmployeeBO employeeBO = DozerUtils.convert(employeeInputVO, EmployeeBO.class);
        return employeeFacade.update(employeeBO);
    }

    @ApiOperation("查询员工")
    @GetMapping("/{id:\\d+}")
    public BasicResult<EmployeeOutputVO> get(@ApiParam("员工id") @PathVariable Integer id) {
        BasicResult<Optional<EmployeeBO>> result = employeeFacade.get(id);
        if (result.getSuccess()) {
            Optional<EmployeeBO> optional = result.getData();
            if (optional.isPresent()) {
                EmployeeBO employeeBO = optional.get();
                EmployeeOutputVO employeeOutputVO = boTransferVO(employeeBO);
                List<RoleOutputBO> roleOutputBOS = employeeBO.getRoleOutputBOS();
                List<RoleOutputVO> roleOutputVOS;
                if (CollectionUtils.isNotEmpty(roleOutputBOS)) {
                    roleOutputVOS = DozerUtils.convertList(roleOutputBOS, RoleOutputVO.class);
                } else {
                    roleOutputVOS = Collections.emptyList();
                }
                employeeOutputVO.setRoleOutputVOS(roleOutputVOS);
                return BasicResult.ok(employeeOutputVO);
            }
            return BasicResult.ok();
        }
        return BasicResult.error(result.getCode(), result.getMessage());
    }

    @ApiOperation("查询员工-条件")
    @GetMapping
    public BasicResult<List<EmployeeOutputVO>> query(EmployeeQO employeeQO) {
        BasicResult<List<EmployeeBO>> result = employeeFacade.query(employeeQO);
        if (result.getSuccess()) {
            List<EmployeeBO> employeeBOS = result.getData();
            if (CollectionUtils.isNotEmpty(employeeBOS)) {
                List<EmployeeOutputVO> employeeOutputVOS = new ArrayList<>();
                for (EmployeeBO employeeBO : employeeBOS) {
                    EmployeeOutputVO employeeOutputVO = boTransferVO(employeeBO);
                    List<RoleOutputBO> roleOutputBOS = employeeBO.getRoleOutputBOS();
                    List<RoleOutputVO> roleOutputVOS;
                    if (CollectionUtils.isNotEmpty(roleOutputBOS)) {
                        roleOutputVOS = DozerUtils.convertList(roleOutputBOS, RoleOutputVO.class);
                    } else {
                        roleOutputVOS = Collections.emptyList();
                    }
                    employeeOutputVO.setRoleOutputVOS(roleOutputVOS);
                    employeeOutputVOS.add(employeeOutputVO);
                }
                return BasicResult.ok(employeeOutputVOS, employeeQO.getPage());
            }
            return BasicResult.ok();
        }
        return BasicResult.error(result.getCode(), result.getMessage());
    }

    @ApiOperation("修改员工密码")
    @PutMapping("/password/update")
    public BasicResult<Void> updatePassword(@Valid @RequestBody EmployeePasswordInputVO employeePasswordInputVO) {

        BasicResult<Optional<EmployeeBO>> currentUserRes  = employeeFacade.get(EmployeeUtil.getCurrentEmployeeId());
        EmployeeBO currentUser = currentUserRes.getData().get();

        boolean matchRes = passwordEncoder.matches(employeePasswordInputVO.getOldPassword(), currentUser.getPassword());
        if(matchRes){
            EmployeeBO employeeBO = new EmployeeBO();
            employeeBO.setId(EmployeeUtil.getCurrentEmployeeId());
            employeeBO.setPassword(employeePasswordInputVO.getNewPassword());
            return employeeFacade.updatePassword(employeeBO);
        }
        return BasicResult.error(BasicResultCode.ERROR.getCode(),"原密码错误");
    }

    @ApiOperation("修改员工")
    @PutMapping("/phonenum/update")
    public BasicResult<Void> updatePhonenum(@Valid @RequestBody EmployeePhonenumInputVO employeePhonenumInputVO) {
        EmployeeBO employeeBO = new EmployeeBO();
        employeeBO.setPhoneNumber(employeePhonenumInputVO.getPhonenum());
        employeeBO.setId(EmployeeUtil.getCurrentEmployeeId());
        return employeeFacade.updatePhonenum(employeeBO);
    }

    private EmployeeOutputVO boTransferVO(EmployeeBO employeeBO) {
        EmployeeOutputVO employeeOutputVO = DozerUtils.convert(employeeBO, EmployeeOutputVO.class);
        employeeOutputVO.setEnabled(employeeBO.getEnabled());
        return employeeOutputVO;
    }
}