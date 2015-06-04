package cn.vgame.gate;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.javaplus.util.Closer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class GameServlet extends HttpServlet {

	private static final long serialVersionUID = 4549727692658633974L;

	@Override
	protected final void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	private void onError(Throwable e, PrintWriter out) {
		JSONObject o = new JSONObject();
		o.put("error", e.toString());
		println(out, o.toJSONString());
	}
	
	private void afterExec(PrintWriter out, Object exec) {
		if (exec == null) {
			println(out, new JSONObject().toJSONString());
		} else {
			println(out, JSON.toJSONString(exec));
		}
	}

	@Override
	protected final void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		try {
			Object exec = post(req, resp);
			afterExec(out, exec);
		} catch (Throwable e) {
			onError(e, out);
		} finally {
			Closer.close(out);
		}
	}

	private void println(PrintWriter out, String jsonString) {
		out.println(jsonString);
		out.flush();
	}

	public Object post(HttpServletRequest req, HttpServletResponse resp) {
		return null;
	}
}
