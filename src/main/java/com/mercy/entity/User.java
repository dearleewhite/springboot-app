package com.mercy.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Description:
 * @Author: Huwl
 * @Date: 2020-12-16 09:56
 **/

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)		//链式调用
//@Indexes({@Index(documentClass = HomeInfo.class, documentFunctionClass = DeviceInfoFunction.class)})
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String name;


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        List<Integer> list1 = Arrays.asList(8,3, 4, 5);
        System.out.println(list);

    }
}
