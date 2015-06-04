package cn.vgame.a.gm;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.javaplus.collections.map.Maps;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 重新加载系统配置(刘雨诚请无视)
 * -----------------
 * 无
 */
public class ExecScriptAction extends ActionSupport {

	private static final long serialVersionUID = -6099859675509539457L;

	@Override
	public String execute() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();

		// 创建脚本引擎管理器
		ScriptEngineManager manager = new ScriptEngineManager();

		// 创建JavaScript引擎
		ScriptEngine engine = manager.getEngineByName("groovy");

		Map<String, String[]> parameters = request.getParameterMap();
		ResponseArgs responseArgs = new ResponseArgs();

		for (Entry<String, String[]> e : parameters.entrySet()) {
			String key = e.getKey();
			String v = e.getValue()[0];
			
			try {
				if (v.matches("[0-9]+")) {
					engine.put(key, new Integer(v));

				} else if (v.matches("[0-9\\.]+")) {
					engine.put(key, new Double(v));
				} else if (v.trim().equals("true")) {
					engine.put(key, true);
				} else if (v.trim().equals("false")) {
					engine.put(key, false);
				} else {
					engine.put(key, v);
				}
			} catch (NumberFormatException e1) {
				engine.put(key, v);
			}
		}

		engine.put("session", request.getSession());
		engine.put("request", request);
		engine.put("response", response);
		engine.put("responseArgs", responseArgs);

		String script = request.getParameter("script");

		try {
			engine.eval(script);
		} catch (Throwable e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1);
		}

		String r = request.getParameter("responsePage");

		for (String key : responseArgs.keySet()) {
			Object value = responseArgs.getValue(key);
			r = r.replaceAll(key, value + "");
		}

		out.println(r);
		out.flush();
		out.close();
		return super.execute();
	}

}
