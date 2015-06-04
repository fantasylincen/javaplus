package cn.mxz.loganalysis;

import java.net.URL;
import java.util.Iterator;

import cn.javaplus.exception.UnImplMethodException;
import cn.javaplus.io.LinesReader;
import cn.javaplus.util.Util;

import com.google.common.io.Resources;

public class LoginBeanIterator implements Iterator<LoginBean> {

	private LinesReader linesReader;
	private Iterator<String> iterator;
	private LoginBean next;

	public LoginBeanIterator() {
		try {
			URL resource = Resources.getResource("log_login.sql");
			linesReader = new LinesReader(resource.openStream());
			iterator = linesReader.iterator();
		} catch (Exception e) {
			throw Util.Exception.toRuntimeException(e);
		}
	}
	
	public boolean hasNext() {
		if(next != null) {
			return true;
		}
		while (iterator.hasNext()) {
			String s = iterator.next();
			if (s.startsWith("INSERT INTO `log_login` VALUES ('")) {
				next = new LoginBean(s);
				return true;
			}
		}
		return false;
	}

	public LoginBean next() {
		LoginBean n = next;
		next = null;
		return n;
	}

	public void remove() {
		throw new UnImplMethodException();
	}

}
