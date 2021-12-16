package com.mercy.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mercy.entity.User;

import java.util.Optional;

/**
 * 人员表 数据访问接口
 *
 * @author dashdjasjh
 * @date 2021-12-07
 */
public interface UserDao extends IService<User> {


    default User getOne(Long id) {
        return Optional.ofNullable(getById(id)).orElseThrow(() -> new RuntimeException("找不到数据"));
    }


}