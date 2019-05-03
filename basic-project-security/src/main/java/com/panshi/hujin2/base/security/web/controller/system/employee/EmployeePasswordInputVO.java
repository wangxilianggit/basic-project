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
@ApiModel("员工修改密码输入模型")
@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeePasswordInputVO extends BaseVO {

    @ApiModelProperty(value = "旧密码")
    @NotBlank
    private String oldPassword;

    @ApiModelProperty(value = "新密码")
    @NotBlank
    private String newPassword;

}
