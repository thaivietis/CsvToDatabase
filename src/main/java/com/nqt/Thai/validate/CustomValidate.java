package com.nqt.Thai.validate;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // Giữ lại annotation tại runtime
@Target(ElementType.FIELD)
public @interface CustomValidate {
    String column();
    boolean required() default true;
}
