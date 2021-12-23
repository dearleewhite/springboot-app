package com.mercy.annotation;


import java.lang.annotation.*;
// 该注解作用于方法
@Target(ElementType.METHOD)
// 该注解在代码运行时也是存在的
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Access {

    String[] value() default {};
    String[] authorities() default {};
    String[] roles() default {};
}