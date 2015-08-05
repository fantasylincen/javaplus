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
import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.javaplus.log.Log;
import cn.vgame.a.Server;
import cn.vgame.a.turntable.Turntable;
import cn.vgame.a.turntable.TurntableUtil;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 本轮必出(刘雨诚请无视) ----------------- 无
 */
public class MustGenerateAction extends ActionSupport {

	private static final long serialVersionUID = -6099859675509539457L;

	private int id;

	@Override
	public String execute() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();
		Turntable t = Turntable.getInstance();
		int id = t.getMustGenerateId();
		if (getId() == id)
			t.setMustGenerateId(-1);
		else
			t.setMustGenerateId(getId());

		Log.d("must generate", getChinese());

		return super.execute();
	}

	private String getChinese() {
		try {
			Sheet sheet = Server.getXml().get("weights");
			Row row = sheet.get(getId());
			String type = row.get("type");
			return TurntableUtil.toChinese(type);
		} catch (Exception e) {
			e.printStackTrace();
			return "Unknown";
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
