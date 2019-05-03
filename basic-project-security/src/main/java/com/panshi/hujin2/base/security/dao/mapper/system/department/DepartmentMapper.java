package com.panshi.hujin2.base.security.dao.mapper.system.department;

import com.panshi.hujin2.base.security.dao.model.system.department.DepartmentDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ZhangZhiHao 2018/6/22 16:06
 */
public interface DepartmentMapper {

    /**
     * 添加部门
     *
     * @param departmentDO 部门dao模型
     */
    void add(DepartmentDO departmentDO);

    /**
     * 删除部门
     *
     * @param departmentId      部门id
     * @param currentEmployeeId 当前用户id
     */
    void delete(@Param("departmentId") Integer departmentId, @Param("currentEmployeeId") Integer currentEmployeeId);

    /**
     * 删除子部门的关联信息
     * @param parentDepartmentId 父部门id
     * @param currentEmployeeId 当前操作用户id
     */
    void deleteChildrenRelevance(@Param("parentDepartmentId") Integer parentDepartmentId, @Param("currentEmployeeId") Integer currentEmployeeId);

    /**
     * 更新部门
     *
     * @param departmentDO 部门dao模型
     */
    void update(DepartmentDO departmentDO);

    /**
     * 获取所有部门信息
     * @return 所有的部门信息列表
     */
    List<DepartmentDO> select();
}
