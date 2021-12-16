package com.mercy.entity.tools;

import com.mercy.constant.CommonConstant;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Huwl
 * @Date: 2021-10-14 15:48
 **/


public class Pager implements Serializable {


    /**
     * 当前页码
     */
    @Setter
    @Getter
    private Integer pageIndex;

    /**
     * 每页数量
     */
    @Setter
    @Getter
    private Integer pageSize;

    /**
     * 排序
     */
    @Getter
    @Setter
    private Integer orderByClause;

    public Pager() {

    }

    public Pager(Integer orderByClause) {
        this.orderByClause = orderByClause;
    }

    public Pager(Integer pageIndex, Integer pageSize, Integer orderByClause) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.orderByClause = orderByClause;
    }

    public Pager(Integer pageIndex, Integer pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }


    public Integer getPageIndex() {
        return pageIndex == null ? CommonConstant.DEFAULT_PAGE_INDEX : pageIndex;
    }

    public Integer getPageSize() {
        return pageSize == null ? CommonConstant.DEFAULT_PAGE_SIZE : pageSize;
    }
}
