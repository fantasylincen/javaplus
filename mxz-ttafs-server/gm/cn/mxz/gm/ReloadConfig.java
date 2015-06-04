package cn.mxz.gm;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;

import cn.mxz.Loader;
import cn.mxz.base.config.ServerConfig;
import cn.mxz.prizecenter.PropIdCheck;

/**
 * 把道具id转为名字，用户客户端查询
 * @author Administrator
 *
 */
public class ReloadConfig  extends AbstractHandler {

//	String ret;
	
	ReloadConfig(){
		
		
	}
	
	@Override
	protected String doGet(Map<String, Object> parameters) {
		String ret = "<h4 class=\"bg-success\">恭喜，Excel导表成功，没有出现错误！<h4>";
		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);
		try{
			cn.javaplus.plugins.generator.excel.App.main(new String[0]);
			Loader.loadAll();
			//ret += "success";
		}
		catch( Exception e ){
			ret = "<h6 class=\"bg-danger\">";
			e.printStackTrace(out);
			ret += sw.toString();
			ret += "</h6>";
		}
		
		//ret += "\";";
		return ret;
	}


}
