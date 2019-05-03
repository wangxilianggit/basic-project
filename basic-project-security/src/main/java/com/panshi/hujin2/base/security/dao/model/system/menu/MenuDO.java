package com.panshi.hujin2.base.security.dao.model.system.menu;

import com.panshi.hujin2.base.security.dao.model.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ZhangZhiHao 2018/6/25 11:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDO extends BaseDO {

    /**
     * 父菜单id
     */
    private Integer parentId;

    /**
     * 前端URL地址
     */
    private String url;

    /**
     * 是否是按钮
     */
    private Integer isButton;

    /**
     * 按钮的key值
     */
    private String buttonKey;

    /**
     * 按钮图标
     */
    private String iconPath;

    /**
     * 是否可用
     */
    private Integer enabled;

    /**
     * 每个菜单映射的语言集合
     */
    private List<MenuDescription> menuDescriptions;
}
