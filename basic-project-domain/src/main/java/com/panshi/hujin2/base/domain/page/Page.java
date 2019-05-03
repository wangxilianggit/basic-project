package com.panshi.hujin2.base.domain.page;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 分页对应的实体类
 */
@Data
@NoArgsConstructor
public class Page implements Serializable {

    private static final long serialVersionUID = -5023284507869763613L;
    /** 总条数 */
    private Integer totalNumber;

    /** 当前第几页 */
    private Integer currentPage = 1;

    /** 总页数 */
    private Integer totalPage;

    /** 每页显示条数 */
    private Integer pageSize = 10;

    /** 数据库中limit的参数，从第几条开始取 */
    private Integer dbIndex;

    /** 数据库中limit的参数，一共取多少条 */
    private Integer dbNumber;

    /** 是否分页 */
    private Boolean isPaging = Boolean.TRUE;

    public Page(Integer currentPage, Integer pageSize) {
        if (currentPage != null && currentPage > 0) {
            this.currentPage = currentPage;
        }
        if (pageSize != null && pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
        calculate();
    }

    /**
     * 根据当前对象中属性值计算并设置相关属性值
     */
    private void calculate() {
        totalPage = totalNumber % pageSize == 0 ? totalNumber / pageSize : totalNumber / pageSize + 1;
        dbIndex = (currentPage - 1) * pageSize;
        dbNumber = pageSize;
    }
}