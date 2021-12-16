package com.mercy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mercy.dao.TypeDao;
import com.mercy.entity.Type;
import com.mercy.entity.tools.Pager;
import com.mercy.mapper.TypeMapper;
import com.mercy.service.TypeService;
import com.mercy.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
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
    @Resource
    private TypeMapper typeMapper;


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

    @Override
    public IPage<Type> listWithTreePage(Boolean loadChildren, Integer showLevel, String name, Pager pager) {
        LambdaQueryWrapper<Type> queryWrapper = this.assemblyQueryCond(name);
        return typeMapper.selectPage(new Page<>(pager.getPageIndex(), pager.getPageSize()), queryWrapper);
    }

    private LambdaQueryWrapper<Type> assemblyQueryCond(String name) {
        LambdaQueryWrapper<Type> queryWrapper = new LambdaQueryWrapper<>();
        if (null != name) {
            queryWrapper = new QueryWrapper<Type>()
                    .lambda()
                    /*.eq(null != dto.getId(), DeviceStore::getId, dto.getId())
                    .ne(null != dto.getNeId(), DeviceStore::getId, dto.getNeId())
                    .in(!CollectionUtils.isEmpty(dto.getIds()), DeviceStore::getId, dto.getIds())
                    .eq(null != dto.getCode() && !"".equals(dto.getCode()), DeviceStore::getCode, dto.getCode())
                    .eq(null != dto.getModelId(), DeviceStore::getModelId, dto.getModelId())
                    .in(CollectionUtils.isNotEmpty(dto.getModelIds()), DeviceStore::getModelId, dto.getModelIds())
                    .eq(null != dto.getCompanyId(), DeviceStore::getCompanyId, dto.getCompanyId())
                    .eq(null != dto.getMainFlag(), DeviceStore::getMainFlag, dto.getMainFlag())
                    .and(StringUtils.isNotBlank(dto.getName()), wrapper -> wrapper
                            .like(DeviceStore::getName, dto.getName())
                            .or().like(DeviceStore::getSizeCode, dto.getName()))
                    .orderByDesc(DeviceStore::getGmtCreate)*/;

        }
        return queryWrapper;
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
