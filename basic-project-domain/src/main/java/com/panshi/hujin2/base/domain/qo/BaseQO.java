package com.panshi.hujin2.base.domain.qo;

import com.panshi.hujin2.base.domain.page.Page;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangZhiHao 2018/6/22 16:35
 */
@Data
public class BaseQO implements Serializable {

    private static final long serialVersionUID = -3152644233502278212L;
    /**
     * 当前页
     */
    protected Integer currentPage = 1;

    /**
     * 页面大小
     */
    protected Integer pageSize = 10;

    /** 是否分页 */
    private Boolean isPaging = Boolean.TRUE;

    /**
     * columns = name,create_time
     */
    protected List<String> columns;
    /**
     * orderBy
     */
    protected List<Boolean> orderBy;

    protected Page page;

    protected List<OrderObject> orderObjects;

    /**
     * 此方法,在controller入参的时候
     * 如果前端传了这个参数
     * 会被框架调用
     *
     * @param currentPage 当前页 前端选填
     */
    public void setCurrentPage(Integer currentPage) {
        if (currentPage != null && currentPage > 0) {
            this.currentPage = currentPage;
        }
        if (page == null) {
            page = new Page();
        }
        page.setCurrentPage(this.currentPage);
    }

    /**
     * 此方法,在controller入参的时候
     * 如果前端传了这个参数
     * 会被框架调用
     *
     * @param pageSize 页面大小 前端选填
     */
    public void setPageSize(Integer pageSize) {
        if (pageSize != null && pageSize > 0) {
            this.pageSize = pageSize;
        }
        if (page == null) {
            page = new Page();
        }
        page.setPageSize(this.pageSize);
    }

    /**
     * 此方法,在controller入参的时候
     * 如果前端传了这个参数
     * 会被框架调用
     *
     * @param isPaging 是否分页 前端选填
     */
    public void setIsPaging(Boolean isPaging) {
        if (isPaging != null) {
            this.isPaging = isPaging;
        }
        if (page == null) {
            page = new Page();
        }
        page.setIsPaging(this.isPaging);
    }

    public Page getPage() {
        orderBy();
        if (page == null) {
            page = new Page();
            return page;
        }
        return page;
    }

    /**
     * 按照对象中的字段设置排序信息
     */
    private void orderBy() {
        if (CollectionUtils.isNotEmpty(orderBy)
                && CollectionUtils.isNotEmpty(columns)
                && orderBy.size() == columns.size()) {
            List<OrderObject> orderObjects = new ArrayList<>();
            for (int i = 0; i < orderBy.size(); i++) {
                OrderObject orderObject = new OrderObject(columns.get(i), orderBy.get(i));
                orderObjects.add(orderObject);
            }
            this.orderObjects = orderObjects;
        }
    }

    /**
     * 首先按照对象中的字段设置排序信息
     * 然后获取Page(没有就创建)
     * 最后调用page的设置总数的方法
     *
     * @param count 条件查询中查到的数量
     */
    public void calculate(Integer count) {
        getPage().setTotalNumber(count);
    }

    public List<String> getColumns() {
        if (CollectionUtils.isEmpty(columns)) {
            columns = new ArrayList<>();
        }
        return columns;
    }

    public List<Boolean> getOrderBy() {
        if (CollectionUtils.isEmpty(orderBy)) {
            orderBy = new ArrayList<>();
        }
        return orderBy;
    }
}
