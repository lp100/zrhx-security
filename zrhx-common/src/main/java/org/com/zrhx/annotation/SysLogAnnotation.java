package org.com.zrhx.annotation;

import java.lang.annotation.*;

/**
 * @Title: SysLog
 * @Description:  系统日志注解
 * @author: gs
 * @date: 2017/5/24 17:36
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogAnnotation {

	String value() default "";
}
