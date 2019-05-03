package com.panshi.hujin2.base.domain.qo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZhangZhiHao 2018/6/25 15:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderObject {

    /**
     * 排序字段
     */
    private String orderByColumn;

    /**
     * 排序规则
     */
    private Boolean orderByDesc;

}
