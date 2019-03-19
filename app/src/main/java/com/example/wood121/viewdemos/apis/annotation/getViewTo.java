package com.example.wood121.viewdemos.apis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>描述：通过注解来注入和创建对象</p>
 * 作者： wood121<br>
 * 日期： 2019/3/19 14:08<br>
 * 版本： v2.0<br>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface getViewTo {
    int value() default -1;
}
