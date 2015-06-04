package cn.vgame.b.gm;

import java.util.ArrayList;

import cn.javaplus.collections.list.Lists;
import cn.vgame.b.init.InitThread;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 重新加载系统配置(刘雨诚请无视)
 * -----------------
 * 无
 */
public class ReloadConfigAction extends ActionSupport {


	private static final long serialVersionUID = -6099859675509539457L;

	@Override
	public String execute() throws Exception {
		
		
		new InitThread().run();
		return super.execute();
	}
	

}
