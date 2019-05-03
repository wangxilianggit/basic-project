package com.panshi.hujin2.base.security.web.controller.system.employee;

import com.panshi.hujin2.base.security.web.controller.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author ZhangZhiHao 2018/6/22 14:30
 */
@ApiModel("员工修改手机号输入模型")
@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeePhonenumInputVO extends BaseVO {

    @ApiModelProperty(value = "手机号")
    @NotBlank
    private String phonenum;

}
