package com.panshi.hujin2.base.security.service.system.role;

import com.panshi.hujin2.base.security.service.base.BaseBO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author ZhangZhiHao 2018/6/27 9:44
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleInputBO extends BaseBO {

    /**
     * 角色名字
     */
    private String name;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 是否可用
     */
    private Integer enabled;

    /**
     * 角色所含菜单的id的集合
     */
    private List<Integer> menuIds;
}
