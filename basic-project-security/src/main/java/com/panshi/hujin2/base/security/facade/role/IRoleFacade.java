package com.panshi.hujin2.base.security.facade.role;

import com.panshi.hujin2.base.domain.result.BasicResult;
import com.panshi.hujin2.base.security.dao.qo.RoleQO;
import com.panshi.hujin2.base.security.service.system.role.RoleInputBO;
import com.panshi.hujin2.base.security.service.system.role.RoleOutputBO;

import java.util.List;
import java.util.Optional;

/**
 * @author ZhangZhiHao 2018/6/22 16:29
 */
public interface IRoleFacade {

    /**
     * 添加角色
     *
     * @param roleInputBO 角色业务模型
     * @return void
     */
    BasicResult<Void> add(RoleInputBO roleInputBO);

    /**
     * 删除角色
     *
     * @param roleId 角色id
     * @return void
     */
    BasicResult<Void> delete(Integer roleId);

    /**
     * 更新角色
     *
     * @param roleInputBO 角色业务模型
     * @return void
     */
    BasicResult<Void> update(RoleInputBO roleInputBO);

    /**
     * 查询角色
     *
     * @param roleId 角色id
     * @return 角色输出业务模型
     */
    BasicResult<Optional<RoleOutputBO>> get(Integer roleId);

    /**
     * 查询角色- 条件
     *
     * @param roleQO 角色查询对象
     * @return 角色信息列表
     */
    BasicResult<List<RoleOutputBO>> query(RoleQO roleQO);
}
