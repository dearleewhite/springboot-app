package com.mercy.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mercy.dao.PatrolTaskDao;
import com.mercy.dao.UserDao;
import com.mercy.entity.PatrolTask;
import com.mercy.entity.User;
import com.mercy.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private PatrolTaskDao patrolTaskDao;
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void userTest() {
       /* LambdaQueryWrapper<User> queryWrapper = new QueryWrapper<User>().lambda();
        queryWrapper.and(q1 -> q1.in(User::getAge, Arrays.asList(1, 2)).ge(User::getAge, 1))
                .or(q2 -> q2.in(User::getAge, Arrays.asList(1, 2)).ge(User::getAge, 1));
        List<User> list = userDao.list(queryWrapper);
        System.out.println(list);
        Date endTime = new Date();
        Date startTime = new Date();
        List<Integer> states1 = Stream.of(2, 4).collect(Collectors.toList());
        List<Integer> states2 = Stream.of(5, 7).collect(Collectors.toList());
        //总数
        LambdaQueryWrapper<PatrolTask> queryWrapper1 = Wrappers.<PatrolTask>lambdaQuery()
                .eq(PatrolTask::getCompanyId, 1)
                .and(w -> w.and(q1 -> q1.in(PatrolTask::getState, states1).between(PatrolTask::getPlanStartTime, startTime, endTime))
                        .or(q2 -> q2.in(PatrolTask::getState, states2).between(PatrolTask::getActualStartTime, startTime, endTime)));
        List<PatrolTask> list1 = patrolTaskDao.list(queryWrapper1);
        System.out.println(list1);*/


    }


    @Test
    public void redisTest() {
//        redisUtil.set("first","123");
//        String first_key = (String)redisUtil.get("first");
//        System.out.println(first_key);
    }

    @Test
    public void deduplication() {
        /*User user1 = new User().setAge(23).setName("zhangsan");
        User user2 = new User().setAge(23).setName("lisi");
        User user3 = new User().setAge(24).setName("zhangsan");
        User user4 = new User().setAge(23).setName("zhangsan");
        List<User> users = Stream.of(user1, user2, user3, user4).collect(Collectors.toList());
        TreeSet collect = users.stream().collect(
                Collectors.toCollection(() -> new TreeSet(Comparator.comparing(User::getAge)))
        );*/
    }


}
