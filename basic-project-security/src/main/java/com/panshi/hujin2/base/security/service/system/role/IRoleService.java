package com.panshi.hujin2.base.security.service.system.role;

import com.panshi.hujin2.base.security.dao.model.system.role.RoleDO;
import com.panshi.hujin2.base.security.dao.qo.RoleQO;

import java.util.List;
import java.util.Optional;

/**
 * @author ZhangZhiHao 2018/6/22 16:02
 */
public interface IRoleService {


    /**
     * 添加角色
     *
     * @param roleInputBO 角色业务模型
     */
    void add(RoleInputBO roleInputBO);

    /**
     * 删除角色
     *
     * @param roleId 角色id
     */
    void delete(Integer roleId);

    /**
     * 更新角色
     *
     * @param roleInputBO 角色业务模型
     */
    void update(RoleInputBO roleInputBO);

    /**
     * 查询角色
     *
     * @param roleId 角色id
     * @return 角色输出业务模型
     */
    Optional<RoleOutputBO> get(Integer roleId);

    /**
     * 查询角色- 条件
     *
     * @param roleQO 角色查询对象
     * @return 角色信息列表
     */
    List<RoleOutputBO> query(RoleQO roleQO);

    /**
     * 角色模型转换的方法
     *
     * @param roleDOS 角色do集合
     * @return 角色outputBo集合
     */
    List<RoleOutputBO> dosTransformOutputBo(List<RoleDO> roleDOS);
}
