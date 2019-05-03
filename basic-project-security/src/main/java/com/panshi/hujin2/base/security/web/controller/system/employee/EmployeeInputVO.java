package com.panshi.hujin2.base.security.web.controller.system.employee;

import com.panshi.hujin2.base.security.web.controller.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * @author ZhangZhiHao 2018/6/22 14:30
 */
@ApiModel("员工输入模型")
@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeeInputVO extends BaseVO {

    @ApiModelProperty(value = "用户名",required = true)
    @NotBlank(message = "G10881300")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

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
    private Integer enabled;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty(value = "部门id集合",required = true)
    @NotEmpty(message = "G10881302")
    private List<Integer> departmentIds;

    @ApiModelProperty(value = "角色id集合",required = true)
    @NotEmpty(message = "G10881303")
    private List<Integer> roleIds;
}
