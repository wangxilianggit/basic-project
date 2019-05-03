package com.panshi.hujin2.base.security.web.controller.system.employee;

import com.panshi.hujin2.base.security.service.system.department.DepartmentDTO;
import com.panshi.hujin2.base.security.web.controller.base.BaseVO;
import com.panshi.hujin2.base.security.web.controller.system.role.RoleOutputVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author ZhangZhiHao 2018/6/22 14:30
 */
@ApiModel("员工输出模型")
@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeeOutputVO extends BaseVO {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("手机号码")
    private String phoneNumber;

    @ApiModelProperty("身份证号码")
    private String idNumber;

    @ApiModelProperty("住址")
    private String address;

    @ApiModelProperty("头像地址")
    private String headPortraitUrl;

    @ApiModelProperty("是否可用")
    private Boolean enabled;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("部门集合")
    private List<DepartmentDTO> departmentDTOS;

    @ApiModelProperty("角色集合")
    private List<RoleOutputVO> roleOutputVOS;
}
