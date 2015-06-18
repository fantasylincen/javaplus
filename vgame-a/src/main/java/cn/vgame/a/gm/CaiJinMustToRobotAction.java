package cn.vgame.a.gm;

import java.io.PrintWriter;
import java.util.List;
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
import cn.javaplus.util.Util;
import cn.vgame.a.Server;
import cn.vgame.a.turntable.Turntable;
import cn.vgame.share.Xml;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 彩金给某个机器人(刘雨诚请无视) ----------------- 无
 */
public class CaiJinMustToRobotAction extends ActionSupport {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4234595879665815099L;
	/**
	 * 机器人角色id
	 */
	private String id;

	@Override
	public String execute() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf8");
		
		String id2 = getId();
//		Server.getRobotManager().setCaiJinMustTo(id2); 2015年6月17日 17:58:16
		
		Turntable is = Turntable.getInstance();
		is.setMustGenerateId(getRandomJinShaId());
		
		return super.execute();
	}

	private int getRandomJinShaId() {
		Xml xml = Server.getXml();
		Sheet sheet = xml.get("weights");
		List<Row> all = sheet.find("type", "C");
		return Util.Random.getRandomOne(all).getInt("id");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
