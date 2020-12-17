package com.mercy.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: Huwl
 * @Date: 2020-12-16 17:01
 **/

@Data
public class Type  extends BaseEntity{
    private String name;
    private Long pid;
    private Integer sort;
    private Integer level;
    /**
     * 是否显示[0-不显示，1显示]
     */
    private Integer showStatus;
    /**
     * 非数据库存在结构
     */
    @TableField(exist = false)
    private List<Type> children;
}
