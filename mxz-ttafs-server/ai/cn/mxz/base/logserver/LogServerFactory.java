package cn.mxz.base.logserver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.mxz.base.telnet.SpringFactory;

class LogServerFactory implements SpringFactory {

	private ApplicationContext CONTEXT;

	private ApplicationContext getContext() {

		if (CONTEXT == null) {

			final String[] beans = new String[] { "log_server_beans.xml", };

			CONTEXT = new ClassPathXmlApplicationContext(beans);
		}

		return CONTEXT;
	}

	@Override
	public Object get(String name) {
		return getContext().getBean(name);
	}
}
