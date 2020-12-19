package com.mercy.service;

import com.mercy.entity.Type;

import java.util.List;

/**
 * @Description:
 * @Author: Huwl
 * @Date: 2020-12-16 17:04
 **/
public interface TypeService {
    /**
     *
     * @param loadChildren 是否要加载子节点
     * @param showLevel 如果加载子节点,加载到的级数
     * @return
     */
    List<Type> listWithTree(Boolean loadChildren, Integer showLevel,  String name);
}
