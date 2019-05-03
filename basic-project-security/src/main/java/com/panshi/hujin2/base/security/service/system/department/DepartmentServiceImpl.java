package com.panshi.hujin2.base.security.service.system.department;

import com.panshi.hujin2.base.common.util.DozerUtils;
import com.panshi.hujin2.base.security.dao.mapper.system.department.DepartmentMapper;
import com.panshi.hujin2.base.security.dao.model.system.department.DepartmentDO;
import com.panshi.hujin2.base.security.service.system.employee_department.IEmployeeDepartmentService;
import com.panshi.hujin2.base.security.service.util.EmployeeUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author ZhangZhiHao 2018/6/22 16:01
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private IEmployeeDepartmentService employeeDepartmentService;

    @Override
    public void add(DepartmentDTO departmentDTO) {
        DepartmentDO departmentDO = dtoTransformDO(departmentDTO);
        departmentDO.setCreateOperatorId(EmployeeUtil.getCurrentEmployeeId());
        departmentMapper.add(departmentDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer departmentId) {
        Objects.requireNonNull(departmentId);
        Integer currentEmployeeId = EmployeeUtil.getCurrentEmployeeId();
        departmentMapper.delete(departmentId, currentEmployeeId);
        // 删除子部门的关联信息
        departmentMapper.deleteChildrenRelevance(departmentId, currentEmployeeId);
        // 删除部门和员工关联信息
        employeeDepartmentService.delete(null, departmentId);
    }

    @Override
    public void update(DepartmentDTO departmentDTO) {
        Objects.requireNonNull(departmentDTO.getId());
        DepartmentDO departmentDO = dtoTransformDO(departmentDTO);
        departmentDO.setModifyOperatorId(EmployeeUtil.getCurrentEmployeeId());
        departmentMapper.update(departmentDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DepartmentDTO> queryAllDepartmentTree() {
        List<DepartmentDO> departmentDOS = departmentMapper.select();
        if (CollectionUtils.isNotEmpty(departmentDOS)) {
            List<DepartmentDTO> departmentDTOS = new ArrayList<>();
            // 将所有父级部门加入集合
            for (DepartmentDO departmentDO : departmentDOS) {
                if (departmentDO.getParentId() == null) {
                    departmentDTOS.add(doTransformDTO(departmentDO));
                }
            }
            // 递归的设置所有父级部门的子部门
            if (CollectionUtils.isNotEmpty(departmentDTOS)) {
                for (DepartmentDTO departmentDTO : departmentDTOS) {
                    departmentDTO.setChildren(getChildren(departmentDTO.getId(), departmentDOS));
                }
                return departmentDTOS;
            }
        }
        return Collections.emptyList();
    }

    /**
     * 获取子部门,会递归的设置子部门子子部门...
     *
     * @param parentDepartmentId 父部门id
     * @param departmentDOS      部门数据源集合
     * @return 子部门的集合
     */
    private List<DepartmentDTO> getChildren(Integer parentDepartmentId, List<DepartmentDO> departmentDOS) {
        List<DepartmentDTO> children = new ArrayList<>();
        for (DepartmentDO departmentDO : departmentDOS) {
            if (departmentDO.getParentId() != null && departmentDO.getParentId().equals(parentDepartmentId)) {
                children.add(doTransformDTO(departmentDO));
            }
        }
        if (CollectionUtils.isNotEmpty(children)) {
            for (DepartmentDTO departmentDTO : children) {
                departmentDTO.setChildren(getChildren(departmentDTO.getId(), departmentDOS));
            }
        }
        return children;
    }

    // 以下为模型转换方法

    private DepartmentDTO doTransformDTO(DepartmentDO departmentDO) {
        return DozerUtils.convert(departmentDO, DepartmentDTO.class);
    }

    private DepartmentDO dtoTransformDO(DepartmentDTO departmentDTO) {
        return DozerUtils.convert(departmentDTO, DepartmentDO.class);
    }

    @Override
    public List<DepartmentDTO> dosTransformDto(List<DepartmentDO> departmentDOS) {
        if (CollectionUtils.isEmpty(departmentDOS)) {
            return Collections.emptyList();
        }
        return DozerUtils.convertList(departmentDOS, DepartmentDTO.class);
    }

}
