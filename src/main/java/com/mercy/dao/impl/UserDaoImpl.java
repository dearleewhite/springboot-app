package com.mercy.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mercy.dao.UserDao;
import com.mercy.entity.User;
import com.mercy.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * 人员表 数据访问实现类
 *
 * @author dashdjasjh
 * @date 2021-12-07
 */
@Service
public class UserDaoImpl extends ServiceImpl<UserMapper, User> implements UserDao {
}