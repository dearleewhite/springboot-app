package com.mercy.test;

import com.mercy.dao.UserDao;
import com.mercy.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void userTest(){
        User user = new User();
        user.setId(111111111L);
        user.setAge(23);
        user.setName("zhangsan");
        userDao.save(user);
    }

}
