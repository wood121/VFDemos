package com.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @Target(ElementType.TYPE) 接口、类、枚举、注解
 * @Target(ElementType.FIELD) 字段、枚举的常量
 * @Target(ElementType.METHOD) 方法
 * @Target(ElementType.PARAMETER) 方法参数
 * @Target(ElementType.CONSTRUCTOR) 构造函数
 * @Target(ElementType.LOCAL_VARIABLE) 局部变量
 * @Target(ElementType.ANNOTATION_TYPE) 注解
 * @Target(ElementType.PACKAGE) 包
 * <p>
 * 作者：ibrothergang
 * 链接：https://www.jianshu.com/p/212b60080df3
 * 来源：简书
 * 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface WoodAnnotation {
    String value();
}

