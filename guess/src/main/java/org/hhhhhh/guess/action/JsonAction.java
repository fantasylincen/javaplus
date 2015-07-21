package org.hhhhhh.guess.action;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hhhhhh.guess.ProphetException;
import org.hhhhhh.guess.ProphetExceptionAdaptor;

import cn.javaplus.collections.set.Sets;
import cn.javaplus.log.Log;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;

public abstract class JsonAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6070383320152390478L;
	/**
	 * 
	 */
	protected HttpServletResponse response;
	protected HttpServletRequest request;
	protected HttpSession session;
	private static Set<String> set;

	@Override
	public final String execute() throws Exception {
		response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf8");
		request = ServletActionContext.getRequest();
		session = request.getSession();

		StringBuffer url = request.getRequestURL();
		long memory = Runtime.getRuntime().freeMemory();
		Log.d(url, session.getId(), memory / (1024 * 1024) + "M");

		PrintWriter out = response.getWriter();
		Object r;
		try {
			r = exec();
			String jsonString = toJSONString(r);
			out.println(jsonString);

		} catch (ProphetException e) {
			
			String s = toJSONString(new ProphetExceptionAdaptor(e));
			
			out.println(s);
			Log.e(s);
			e(e);
		} catch (Throwable e) {

			String message = e.getMessage();
			ProphetException ee = new ProphetException(7, message);
			ProphetExceptionAdaptor rr = new ProphetExceptionAdaptor(ee);
			String s = toJSONString(rr);

			out.println(s);
			Log.e(s);
			e(e);
		} finally {
			out.flush();
			Closer.close(out);
		}

		return SUCCESS;
	}

	private void e(Throwable e) {
		StringWriter out = null;
		PrintWriter pw = null;

		try {
			out = new StringWriter();
			pw = new PrintWriter(out);
			e.printStackTrace(pw);
			String temp = out.toString();
			String[] split = temp.split("\r");
			for (int i = 0; i < split.length; i++) {

				String s = split[i];
				if (exclude(s)) {
					continue;
				}
				String trim = s.trim();
				if (!trim.isEmpty())
					Log.e(trim);
			}
		} finally {
			Closer.close(out, pw);
		}

	}

	private boolean exclude(String s) {
		Set<String> excludes = getExcluds();
		for (String sss : excludes) {
			if (s.contains(sss)) {
				return true;
			}
		}
		return false;
	}

	private Set<String> getExcluds() {
		if (set != null)
			return set;

		set = Sets.newHashSet();

		URL resource = this.getClass().getResource("excludes.txt");
		List<String> ps = Util.File.getLines(resource);

		for (String s : ps) {
			String trim = s.trim();
			if (!trim.isEmpty())
				set.add(trim);
		}

		return set;
	}


	private String toJSONString(Object r) {
		if (r == null) {
			return "{}";
		}
		String jsonString = JSON.toJSONString(r);
		return jsonString;
	}

	protected abstract Object exec();

}
