package com.mercy.utils;

import com.mercy.entity.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: 集合工具类
 * @Author: Huwl
 * @Date: 2021-01-26 14:31
 **/

@Slf4j
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

    /**
     * @param firstArrayList  第一个ArrayList
     * @param secondArrayList 第二个ArrayList
     * @return resultList 差集ArrayList
     * @方法描述：获取两个ArrayList的差集
     */

    public static <T> List<T> receiveDefectListByClassAndFieldNames(List<T> firstArrayList, List<T> secondArrayList, Class<T> clazz, String... fieldNames) throws Exception {
        if (CollectionUtils.isEmpty(firstArrayList)) {
            return Collections.emptyList();
        }
        if (!clazz.isInstance(firstArrayList.get(0))) {
            throw new Exception("集合的元素类与指定类的类型不一致!");
        }
        // 类型一致做转换
        List<T> objectList1 = translate(firstArrayList, clazz);
        if (CollectionUtils.isEmpty(secondArrayList)) {
            return objectList1;
        }
        if (!clazz.isInstance(secondArrayList.get(0))) {
            throw new Exception("集合的元素类与指定类的类型不一致!");
        }
        // 类型一致做转换
        List<T> objectList2 = translate(secondArrayList, clazz);
        List<String> fieldNamesFromPara = Arrays.stream(fieldNames).collect(Collectors.toList());
        if (fieldNamesFromPara.size() < 1) {
            throw new Exception();
        }
        Object o = objectList1.get(0);
        Field[] fields = o.getClass().getDeclaredFields();
        List<String> fieldNameList = Stream.of(fields).map(Field::getName).collect(Collectors.toList());
        List<String> fieldNamesError = fieldNamesFromPara.stream().filter(fieldNameFromPara -> !fieldNameList.contains(fieldNameFromPara)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(fieldNamesError)) {
            fieldNamesFromPara.removeAll(fieldNamesError);
            System.out.println("参数传入的以下属性不存在:" + fieldNamesError.toString());
        }
        return objectList1.stream()
                .filter(item -> !objectList2.stream()
                        .map(e -> getString(e, fieldNamesFromPara)).collect(Collectors.toList())
                        .contains(getString(item, fieldNamesFromPara)))
                .collect(Collectors.toList());
    }

    private static <T> List<T> translate(List<T> secondArrayList, Class<T> clazz) {
        return secondArrayList.stream().map(t -> {
            T obj = null;
            try {
                obj = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            assert obj != null;
            BeanUtils.copyProperties(t, obj);
            return obj;
        }).collect(Collectors.toList());
    }


    private static String getString(Object e, List<String> fieldNames) {
        StringBuilder sb = new StringBuilder();
        for (String fieldName : fieldNames) {
            try {
                Field field = e.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                sb.append(field.get(e)).append("|");
            } catch (NoSuchFieldException | IllegalAccessException noSuchFieldException) {
                noSuchFieldException.printStackTrace();
            }

        }
        return sb.toString();
    }
}
