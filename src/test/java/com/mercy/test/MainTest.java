package com.mercy.test;

import com.mercy.entity.User;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: Huwl
 * @Date: 2020-12-23 19:44
 **/


public class MainTest {
    public static void main(String[] args) {
        List<User> list = Collections.emptyList();
        List<User> collect = list.stream().filter(v -> "11".equals(v.getName())).collect(Collectors.toList());
        Map<String, List<User>> collect1 = list.stream().collect(Collectors.groupingBy(User::getName));
        collect1.forEach((key,value) ->{
            System.out.println(key+"11111");
        });
        System.out.println(collect);
        System.out.println(collect1);
    }
}
