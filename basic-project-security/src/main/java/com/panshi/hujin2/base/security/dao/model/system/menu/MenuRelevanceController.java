package com.panshi.hujin2.base.security.dao.model.system.menu;

import com.panshi.hujin2.base.security.dao.model.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author ZhangZhiHao 2018/6/27 10:04
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class MenuRelevanceController extends BaseDO {

    /**
     * 菜单id
     */
    private Integer menuId;

    /**
     * 控制器id
     */
    private Integer controllerId;

    public MenuRelevanceController(Integer menuId, Integer controllerId, Integer createOperatorId) {
        this.menuId = menuId;
        this.controllerId = controllerId;
        this.createOperatorId = createOperatorId;
    }
}
