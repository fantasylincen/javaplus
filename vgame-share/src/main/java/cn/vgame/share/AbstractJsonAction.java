package cn.vgame.share;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.javaplus.log.Log;
import cn.javaplus.util.Closer;

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

	public AbstractJsonAction() {
		super();
	}

	@Override
	public final String execute() throws Exception {
		response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf8");
		request = ServletActionContext.getRequest();
		session = request.getSession();
		
		Log.d("vgame SessionID:" + session.getId());
		
		StringBuffer url = request.getRequestURL();
		Log.d(url);
		
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
			e.printStackTrace();
			
		} catch (Throwable e) {
			
			String simpleName = e.getClass().getSimpleName();
			String message = e.getMessage();
			IErrorResult rr = buildErrorResult(simpleName, message);
			String s = toJSONString(rr);
			
			out.println(s);
			Log.e(s);
			e.printStackTrace();
		} finally {
			out.flush();
			Closer.close(out);
		}
	
		return SUCCESS;
	}

	protected abstract IErrorResult buildErrorResult(String simpleName, String message);

	/**
	 * 获取常量表里面  的constName常量
	 * @param constName
	 */
	protected final int getConstInt(String constName) {
		return getRow(constName).getInt("value");
	}

	/**
	 * 获取常量表里面  的constName常量
	 * @param constName
	 */
	protected final double getConstDouble(String constName) {
		return getRow(constName).getDouble("value");
	}

	/**
	 * 获取常量表里面  的constName常量
	 * @param constName
	 */
	protected final String getConstString(String constName) {
		return getRow(constName).get("value");
	}

	/**
	 * 获取常量表里面  的constName常量
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