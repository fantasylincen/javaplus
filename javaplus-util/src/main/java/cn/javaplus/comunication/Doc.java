package cn.javaplus.comunication;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 文档说明
 * @author 林岑
 * @since 2014年2月15日 17:56:31 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Doc {

	String value();

}
