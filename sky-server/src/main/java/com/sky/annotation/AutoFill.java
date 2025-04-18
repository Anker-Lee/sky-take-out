package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    /*
    这是一个注解的属性，类型为 OperationType（一个枚举类型）。
    使用 @AutoFill 时，必须指定一个 OperationType 的值，例如 @AutoFill(value = OperationType.INSERT)。
     */
    OperationType value();
}
