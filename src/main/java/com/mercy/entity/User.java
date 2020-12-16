package com.mercy.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description:
 * @Author: Huwl
 * @Date: 2020-12-16 09:56
 **/

@Data
@EqualsAndHashCode(callSuper = false)
//@Indexes({@Index(documentClass = HomeInfo.class, documentFunctionClass = DeviceInfoFunction.class)})
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String name;

    private Integer age;
}
