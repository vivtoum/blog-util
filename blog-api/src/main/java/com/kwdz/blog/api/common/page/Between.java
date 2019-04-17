package com.kwdz.blog.api.common.page;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在分页时开启区间查询
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Between {

    /**
     * 最小值的实体属性名
     */
    String min();

    /**
     * 最大值的实体属性名
     */
    String max();
}
