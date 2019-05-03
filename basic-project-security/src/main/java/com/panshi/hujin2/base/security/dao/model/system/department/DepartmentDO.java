package com.panshi.hujin2.base.security.dao.model.system.department;

import com.panshi.hujin2.base.security.dao.model.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ZhangZhiHao 2018/6/22 17:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DepartmentDO extends BaseDO {

    /**
     * 父部门id
     */
    private Integer parentId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门描述
     */
    private String description;

    /**
     * 是否可用
     */
    private Integer enabled;
}
