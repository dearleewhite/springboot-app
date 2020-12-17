package com.mercy.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mercy.dao.TypeDao;
import com.mercy.entity.Type;
import com.mercy.mapper.TypeMapper;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: Huwl
 * @Date: 2020-12-17 10:37
 **/

@Repository("typeDao")
public class TypeDaoImpl extends ServiceImpl<TypeMapper, Type> implements TypeDao {
}