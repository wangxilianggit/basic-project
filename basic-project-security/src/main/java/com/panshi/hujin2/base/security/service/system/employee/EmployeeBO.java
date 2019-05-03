package com.panshi.hujin2.base.security.service.system.employee;

import com.panshi.hujin2.base.security.dao.model.system.menu.ControllerAuthentication;
import com.panshi.hujin2.base.security.service.system.department.DepartmentDTO;
import com.panshi.hujin2.base.security.service.system.role.RoleOutputBO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author ZhangZhiHao 2018/6/21 10:10
 */
@Data
public class EmployeeBO implements UserDetails {

    private static final long serialVersionUID = 3927989125076489847L;
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 描述
     */
    private String description;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

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
     * 部门id集合
     */
    private List<Integer> departmentIds;

    /**
     * 角色id集合
     */
    private List<Integer> roleIds;

    /**
     * 权限列表
     */
    private List<GrantedAuthority> authorities;

    /**
     * 部门集合
     */
    private List<DepartmentDTO> departmentDTOS;

    /**
     * 角色集合
     */
    private List<RoleOutputBO> roleOutputBOS;

    /**
     * 菜单集合
     */
    private List<ControllerAuthentication> controllerAuthentications;

    /**
     * 账户可用(未删除)?
     * 可以自定义逻辑判断,默认为true
     */
    @Override
    public boolean isEnabled() {
        // 可以自定义逻辑,默认为true
        return true;
    }

    /**
     * 账户未过期?
     * 可以自定义逻辑判断,默认为true
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户未锁定?
     * 可以自定义逻辑判断,默认为true
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码未过期?
     * 可以自定义逻辑判断,默认为true
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
