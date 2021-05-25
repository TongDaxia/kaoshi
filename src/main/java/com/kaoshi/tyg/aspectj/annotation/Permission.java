package com.kaoshi.tyg.aspectj.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {

     Permit[] value() default {};

     public @interface Permit{
         FromPermission FROM_PERMISSION() default FromPermission.SAVE;
         UserPermission USER_PERMISSION() default UserPermission.NOTMATCH;

     }

}
