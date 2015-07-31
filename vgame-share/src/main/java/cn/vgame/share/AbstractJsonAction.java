package cn.vgame.share;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.javaplus.collections.set.Sets;
import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.javaplus.log.Log;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;

public abstract class AbstractJsonAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8959639636946677376L;
	protected HttpServletResponse response;
	protected HttpServletRequest request;
	protected HttpSession session;
	private static Set<String> set;

	public AbstractJsonAction() {
		super();
	}

	@Override
	public final String execute() throws Exception {
		response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf8");
		request = ServletActionContext.getRequest();
		session = request.getSession();

		// Log.d("vgame SessionID:" + session.getId());

		StringBuffer url = request.getRequestURL();
		long memory = Runtime.getRuntime().freeMemory();
		Log.d(url, memory / (1024 * 1024) + "M");

		PrintWriter out = response.getWriter();
		Object r;
		try {
			r = exec();
			String jsonString = toJSONString(r);
			out.println(jsonString);

		} catch (GameException e) {

			String s = toJSONString(e.getErrorResult());
			out.println(s);
			Log.e(s);
			e(e);
		} catch (Throwable e) {

			String simpleName = e.getClass().getSimpleName();
			String message = e.getMessage();
			IErrorResult rr = buildErrorResult(simpleName, message);
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

		URL resource = AbstractJsonAction.class.getResource("excludes.txt");
		List<String> ps = Util.File.getLines(resource);

		for (String s : ps) {
			String trim = s.trim();
			if (!trim.isEmpty())
				set.add(trim);
		}

		return set;
	}

	protected abstract IErrorResult buildErrorResult(String simpleName,
			String message);

	/**
	 * 获取常量表里面 的constName常量
	 * 
	 * @param constName
	 */
	protected final int getConstInt(String constName) {
		return getRow(constName).getInt("value");
	}

	/**
	 * 获取常量表里面 的constName常量
	 * 
	 * @param constName
	 */
	protected final double getConstDouble(String constName) {
		return getRow(constName).getDouble("value");
	}

	/**
	 * 获取常量表里面 的constName常量
	 * 
	 * @param constName
	 */
	protected final String getConstString(String constName) {
		return getRow(constName).get("value");
	}

	/**
	 * 获取常量表里面 的constName常量
	 * 
	 * @param constName
	 */
	protected final boolean getConstBoolean(String constName) {
		return getRow(constName).getBool("value");
	}

	private Row getRow(String constName) {
		return getSheet("const").get(constName);
	}

	/**
	 * 获取系统配置表里面某个sheet
	 * 
	 * @param sheetName
	 * @return
	 */
	protected final Sheet getSheet(String sheetName) {
		return getGameXml().get(sheetName);
	}

	private Xml getGameXml() {
		return Xml.getXml();
	}

	private String toJSONString(Object r) {
		if (r == null) {
			return "{}";
		}
		String jsonString = JSON.toJSONString(r);
		return jsonString;
	}

	public abstract Object exec();

}