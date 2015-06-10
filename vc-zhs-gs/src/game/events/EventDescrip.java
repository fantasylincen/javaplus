package game.events;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * 用注解的方式处理包的描述信息
 * @author liukun 
 *
 */
//@Target (value = { ElementType.PACKAGE })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface EventDescrip {
	/**
	 * 包描述
	 * @return
	 */
	public String desc() default "";
	/**
	 * 包结构
	 * @return
	 */
	public String structure() default ""; 
}
