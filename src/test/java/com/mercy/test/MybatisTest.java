package com.mercy.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mercy.dao.UserDao;
import com.mercy.entity.User;
import com.mercy.service.TypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @Description:
 * @Author: Huwl
 * @Date: 2020-12-30 11:03
 **/

@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisTest {


    @Autowired
    private UserDao userDao;
    @Autowired
    private TypeService typeService;

    @Test
    public void userTest(){
        LambdaQueryWrapper<User> queryWrapper = new QueryWrapper<User>().lambda();
        queryWrapper.and(w -> w.and(o -> o.in(User::getAge, Arrays.asList(1, 2)).and(wrapper -> wrapper.ge(User::getAge, 1)))
                .or()
                        .and(q -> q.in(User::getAge, Arrays.asList(1, 2)).ge(User::getAge, 1)

                                )
        );


    }
}
