package cn.vgame.a.yinshang;

import cn.javaplus.log.Log;

import com.opensymphony.xwork2.ActionSupport;

public class YinShangErrorAction extends ActionSupport {

	private static final long serialVersionUID = -3424899366181548090L;

	@Override
	public String execute() {
		Log.d("YinShangErrorAction");
		return SUCCESS;
	}
}
