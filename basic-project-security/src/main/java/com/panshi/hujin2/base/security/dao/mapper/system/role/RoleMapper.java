package com.panshi.hujin2.base.security.dao.mapper.system.role;

import com.panshi.hujin2.base.security.dao.model.system.role.RoleDO;
import com.panshi.hujin2.base.security.dao.qo.RoleQO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ZhangZhiHao 2018/6/22 16:06
 */
public interface RoleMapper {

    /**
     * 添加角色
     *
     * @param roleDO 角色dao模型
     */
    void add(RoleDO roleDO);

    /**
     * 删除角色
     *
     * @param roleId            角色id
     * @param currentEmployeeId 当前操作用户id
     */
    void delete(@Param("roleId") Integer roleId, @Param("currentEmployeeId") Integer currentEmployeeId);

    /**
     * 更新角色
     *
     * @param roleDO 角色dao模型
     */
    void update(RoleDO roleDO);

    /**
     * 查询角色
     *
     * @param roleId 角色id
     * @return 角色dao模型
     */
    RoleDO get(Integer roleId);

    /**
     * 查询角色数量
     * @param roleQO 查询条件
     * @return 符合查询条件的角色的数量
     */
    int count(RoleQO roleQO);

    /**
     * 查询角色列表
     * @param roleQO 查询条件
     * @return 符合查询条件的角色的列表
     */
    List<RoleDO> query(RoleQO roleQO);
}
