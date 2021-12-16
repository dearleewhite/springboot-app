package com.mercy.utils;

/**
 * @Description:
 * @Author: Huwl
 * @Date: 2021-10-14 15:54
 **/


public class BeanUtils extends org.springframework.beans.BeanUtils {
    public BeanUtils() {
    }

    public static <T> T copyProperties(Object source, Class<T> t) {
        try {
            T target = t.getConstructor().newInstance();
            copyProperties(source, target);
            return target;
        } catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }
}
