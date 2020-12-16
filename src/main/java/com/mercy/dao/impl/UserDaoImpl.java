package com.mercy.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mercy.dao.UserDao;
import com.mercy.entity.User;
import com.mercy.mapper.UserMapper;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: Huwl
 * @Date: 2020-12-16 09:58
 **/

@Repository("userDao")
public class UserDaoImpl extends ServiceImpl<UserMapper, User> implements UserDao {
}
