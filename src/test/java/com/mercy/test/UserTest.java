package com.mercy.test;

import com.mercy.dao.UserDao;
import com.mercy.entity.Type;
import com.mercy.service.TypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description:
 * @Author: Huwl
 * @Date: 2020-12-16 16:17
 **/

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private TypeService typeService;

    @Test
    public void userTest(){
//        List<Type> types = typeService.listWithTree(Boolean.TRUE);
//        System.out.println(types);
    }

}
