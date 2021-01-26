package com.mercy.entity;

import com.google.common.collect.Lists;
import com.mercy.utils.CollectionUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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


    public static void main(String[] args) throws Exception {
        List<Type> list1 = Lists.newArrayList();
        for (int i = 0; i < 12; i++) {
            Type type = new Type();
            type.setName("name" + i);
            type.setPid(1L);
            type.setLevel(i);
            list1.add(type);
        }
        List<Type> list2 = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            Type type = new Type();
            type.setName("name" + i);
            type.setPid(1L);
            list2.add(type);
        }
        try {
            List<Type> objects = (List<Type>)CollectionUtil.receiveDefectListByClassAndFieldNames(list1, list2, Type.class, "name", "pid","亚索看好了");
            System.out.println(objects);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
}
