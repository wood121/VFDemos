package com.example.wood121.viewdemos.mine.annotation;

/**
 * Created by wood121 on 2018/3/19.
 */

public @interface TestAnnotation {
    int id() default 1;

    String msg() default "haha";
}
