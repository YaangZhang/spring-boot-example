package com.sto.api.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhy
 * @create 2020-04-10-11:56
 */
@Target({ElementType.METHOD}) //适用于方法上
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoIdempotent {

}
