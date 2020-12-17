package com.mercy.service.impl;

import com.mercy.dao.TypeDao;
import com.mercy.entity.Type;
import com.mercy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: Huwl
 * @Date: 2020-12-16 17:04
 **/

@Service("typeService")
public class TypeServiceImpl  implements TypeService {

    @Autowired
    private TypeDao typeDao;


    @Override
    public List<Type> listWithTree() {
        //查询所有
        List<Type> all = typeDao.list(null);
        //获取一级分类pid(o)
        List<Type> level1 = all.stream()
                .filter(type -> type.getPid() == 0)
                .map((currentType) ->{
                    currentType.setChildren(getChildren(currentType,all));
                    return currentType;})
                .sorted(Comparator.comparingInt(type -> (type.getSort() == null ? 0 : type.getSort()))).collect(Collectors.toList());
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
