package cn.mxz.util.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import cn.javaplus.util.Util;

/**
 * GM管理拦截器
 *
 * @author 林岑
 * @time 2013-6-19
 */
@Aspect
@Component
public class TelnetCommandInterceptor {

	private static final String CONFIG = "execution(public void cn.mxz.base.telnet..*.run(java.io.PrintWriter, *))";

	@Around(CONFIG)
	public Object serviceLog(ProceedingJoinPoint jp) {

		try {
			jp.proceed();
		} catch (Throwable e) {
			throw Util.Exception.toRuntimeException(e);
		}

		return null;

	}
}
