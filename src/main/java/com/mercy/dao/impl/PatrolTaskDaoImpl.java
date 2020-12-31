package com.mercy.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mercy.dao.PatrolTaskDao;
import com.mercy.entity.PatrolTask;
import com.mercy.mapper.PatrolTaskMapper;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: Huwl
 * @Date: 2020-12-31 11:14
 **/

@Repository("patrolTaskDao")
public class PatrolTaskDaoImpl extends ServiceImpl<PatrolTaskMapper, PatrolTask> implements PatrolTaskDao {
}
