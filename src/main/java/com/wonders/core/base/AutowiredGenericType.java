package com.wonders.core.base;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutowiredGenericType {
	
	boolean required() default true;
	
}
