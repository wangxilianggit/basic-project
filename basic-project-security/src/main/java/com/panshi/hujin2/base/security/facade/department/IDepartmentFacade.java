package com.panshi.hujin2.base.security.facade.department;

import com.panshi.hujin2.base.domain.result.BasicResult;
import com.panshi.hujin2.base.security.service.system.department.DepartmentDTO;

import java.util.List;

/**
 * @author ZhangZhiHao 2018/6/22 16:28
 */
public interface IDepartmentFacade {

    /**
     * 添加部门
     *
     * @param departmentDTO 部门业务模型
     * @return void
     */
    BasicResult<Void> add(DepartmentDTO departmentDTO);

    /**
     * 删除部门
     *
     * @param departmentId 部门id
     * @return void
     */
    BasicResult<Void> delete(Integer departmentId);

    /**
     * 更新部门
     *
     * @param departmentDTO 部门业务模型
     * @return void
     */
    BasicResult<Void> update(DepartmentDTO departmentDTO);

    /**
     * 查询部门(树)
     *
     * @return 部门结构树
     */
    BasicResult<List<DepartmentDTO>> queryAllDepartmentTree();

}
