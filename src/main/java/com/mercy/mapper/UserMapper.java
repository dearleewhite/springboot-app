package com.mercy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mercy.entity.User;

/**
 * 人员表 Mapper 接口
 *
 * @author dashdjasjh
 * @date 2021-12-07
 */
public interface UserMapper extends BaseMapper<User> {

    User getById();
}