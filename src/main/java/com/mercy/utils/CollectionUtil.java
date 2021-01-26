package com.mercy.utils;

import com.mercy.entity.Type;
import com.mercy.entity.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 集合工具类
 * @Author: Huwl
 * @Date: 2021-01-26 14:31
 **/


public class CollectionUtil {

    /**
     * @param firstArrayList  第一个ArrayList
     * @param secondArrayList 第二个ArrayList
     * @return resultList 差集ArrayList
     * @方法描述：获取两个ArrayList的差集
     */
    public static List<Type> receiveDefectList(List<Type> firstArrayList,
                                               List<Type> secondArrayList) {
        List<Type> resultList = firstArrayList.stream()
                .filter(item -> !secondArrayList.stream().map(e -> e.getName() + "|" + e.getPid())
                        .collect(Collectors.toList()).contains(item.getName() + "|" + item.getPid()))
                .collect(Collectors.toList());
        return resultList;
    }
}
