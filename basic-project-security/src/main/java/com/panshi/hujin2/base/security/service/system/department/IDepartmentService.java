package com.panshi.hujin2.base.security.service.system.department;

import com.panshi.hujin2.base.security.dao.model.system.department.DepartmentDO;

import java.util.List;

/**
 * @author ZhangZhiHao 2018/6/22 15:57
 */
public interface IDepartmentService {

    /**
     * 添加部门
     *
     * @param departmentDTO 部门业务模型
     */
    void add(DepartmentDTO departmentDTO);

    /**
     * 删除部门
     *
     * @param departmentId 部门id
     */
    void delete(Integer departmentId);

    /**
     * 更新部门
     *
     * @param departmentDTO 部门业务模型
     */
    void update(DepartmentDTO departmentDTO);

    /**
     * 查询部门(树)
     *
     * @return 部门结构树
     */
    List<DepartmentDTO> queryAllDepartmentTree();

    /**
     * 部门模型转换
     *
     * @param departmentDOS 部门do模型集合
     * @return 部门dto模型集合
     */
    List<DepartmentDTO> dosTransformDto(List<DepartmentDO> departmentDOS);
}
