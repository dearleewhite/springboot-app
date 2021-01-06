package com.mercy.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mercy.dao.TypeDao;
import com.mercy.entity.Type;
import com.mercy.service.TypeService;
import com.mercy.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: Huwl
 * @Date: 2020-12-16 17:04
 **/

@Service("typeService")
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;
    @Resource
    private RedisUtil redisUtil;


    @Override
    public List<Type> listWithTree(Boolean loadChildren, Integer showLevel, String name) {
        List<Type> typeList = (List<Type>) redisUtil.get("app:type:tree");
        if(null != typeList){
            return typeList;
        }
        //查询所有<小于的级数>
        List<Type> all = typeDao.list(Wrappers.<Type>lambdaQuery().
                le(Objects.nonNull(showLevel),Type::getLevel,showLevel + 1)
                .like(Objects.nonNull(name),Type::getName, name));
        //默认:获取一级分类pid=0
        //如果name存在,则只加载匹配到的树
        List<Type> level1 = all.stream()
                .filter(type -> type.getPid() == 0)
                .map((currentType) -> {
                    if (Objects.equals(Boolean.TRUE, loadChildren)) {
                        currentType.setChildren(getChildren(currentType, all));
                    }
                    return currentType;
                })
                .sorted(Comparator.comparingInt(type -> (type.getSort() == null ? 0 : type.getSort()))).collect(Collectors.toList());

        redisUtil.set("app:type:tree", level1);
        return level1;
    }


    private List<Type> getChildren(Type parentCategory, List<Type> all) {

        List<Type> children = all.stream().filter(currentCategory -> parentCategory.getId().equals(currentCategory.getPid()))
                .map(currentType -> {
                    currentType.setChildren(getChildren(currentType, all));
                    return currentType;
                }).sorted(Comparator.comparingInt(type -> (type.getSort() == null ? 0 : type.getSort())))
                .collect(Collectors.toList());

        return children;
    }
}
