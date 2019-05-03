package com.panshi.hujin2.base.security.dao.model.system.employee;

import com.panshi.hujin2.base.security.dao.model.BaseDO;
import com.panshi.hujin2.base.security.dao.model.system.department.DepartmentDO;
import com.panshi.hujin2.base.security.dao.model.system.menu.ControllerAuthentication;
import com.panshi.hujin2.base.security.dao.model.system.role.RoleDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * @author ZhangZhiHao 2018/6/27 14:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeeDO extends BaseDO {

    /**
     * 描述
     */
    private String description;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 身份证号码
     */
    private String idNumber;

    /**
     * 住址
     */
    private String address;

    /**
     * 头像地址
     */
    private String headPortraitUrl;

    /**
     * 是否可用
     */
    private Boolean enabled;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 部门集合
     */
    private List<DepartmentDO> departmentDOS;

    /**
     * 角色集合
     */
    private List<RoleDO> roleDOS;

    /**
     * 菜单集合
     */
    private List<ControllerAuthentication> controllerAuthentications;

}
