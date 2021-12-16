package com.mercy.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mercy.constant.CommonConstant;
import com.mercy.entity.tools.Pager;
import com.mercy.vo.BusinessPage;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: Huwl
 * @Date: 2021-10-14 15:45
 **/


public class PageUtil {

    /**
     * Page 类型转换
     *
     * @param pager the pager
     * @param <T>   record 类型
     * @return Page
     */
    public static <T> Page<T> convert(Pager pager) {
        return new Page<>(Optional.ofNullable(pager.getPageIndex()).orElse(CommonConstant.DEFAULT_PAGE_INDEX),
                Optional.ofNullable(pager.getPageSize()).orElse(CommonConstant.DEFAULT_PAGE_SIZE));
    }

    /**
     * Pager 类型转换
     *
     * @param pager the pager
     * @param <T>   list 类型
     * @return EhmPage
     */
    public static <T> BusinessPage<T> convertWithPager(Pager pager) {
        return new BusinessPage<T>().setList(Collections.emptyList()).setPageNum(pager.getPageIndex())
                .setPageSize(pager.getPageSize()).setPageSize(pager.getPageIndex()).setSize(0).setTotal(0);
    }

    /**
     * Page 类型转换
     *
     * @param page the page
     * @param <T>  record 类型
     * @return EhmPage
     */
    public static <T> BusinessPage<T> convert(IPage<T> page) {
        return new BusinessPage<T>()
                .setList(page.getRecords())
                .setPageNum((int) page.getCurrent())
                .setPageSize((int) page.getSize())
                .setPages((int) page.getPages())
                .setSize(page.getRecords().size())
                .setTotal((int) page.getTotal());
    }

    /**
     * Page 类型转换 & 转换分页记录对象类型, 比如把实体类转为vo
     *
     * @param page     分页数据
     * @param function 转换方法
     * @param <T>      转换前类型
     * @param <R>      转换后类型
     * @return Page
     */
    public static <T, R> BusinessPage<R> convert(IPage<T> page, Function<T, R> function) {
        return new BusinessPage<R>()
                .setList(page.getRecords().stream().map(function).collect(Collectors.toList()))
                .setPageNum((int) page.getCurrent())
                .setPageSize((int) page.getSize())
                .setPages((int) page.getPages())
                .setSize(page.getRecords().size())
                .setTotal((int) page.getTotal());
    }

    /**
     * 转换分页记录对象类型, 比如把实体类转为vo
     *
     * @param page     分页数据
     * @param function 转换方法
     * @param <T>      转换前类型
     * @param <R>      转换后类型
     * @return Page
     */
    public static <T, R> BusinessPage<R> convert(BusinessPage<T> page, Function<T, R> function) {
        return new BusinessPage<R>()
                .setList(page.getList().stream().map(function).collect(Collectors.toList()))
                .setPageNum(page.getPageNum())
                .setPageSize(page.getPageSize())
                .setPages(page.getPages())
                .setSize(page.getSize())
                .setTotal(page.getTotal());
    }

    /**
     * 转换成 LitePageInfo
     *
     * @param voList 自己组装的vo list
     * @param iPage  Mybatis plus IPage {@link IPage}
     * @param <VO>   vo type
     * @param <T>    page type
     * @return
     */
    public static <VO, T> BusinessPage<VO> convert(List<VO> voList, IPage<T> iPage) {
        if (iPage == null || voList == null) {
            return null;
        }
        return new BusinessPage<VO>()
                .setPageNum((int) iPage.getCurrent())
                .setPageSize((int) iPage.getSize())
                .setSize(voList.size())
                .setTotal((int) iPage.getTotal())
                .setPages((int) iPage.getPages())
                .setList(voList);
    }
}
