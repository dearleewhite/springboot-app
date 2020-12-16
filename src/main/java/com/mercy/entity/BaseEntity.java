package com.mercy.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: Huwl
 * @Date: 2020-12-16 09:55
 **/

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseEntity implements Serializable {


    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createUserId;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long modifiedUserId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    /**
     * 是否删除：0：否 1：是
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;


}
