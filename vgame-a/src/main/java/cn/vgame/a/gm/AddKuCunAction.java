package cn.vgame.a.gm;

import cn.javaplus.log.Log;
import cn.vgame.a.Server;
import cn.vgame.a.turntable.Turntable;
import cn.vgame.a.turntable.Turntable.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 修改库存(刘雨诚请无视) ----------------- 无
 */
public class AddKuCunAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7808884153672122986L;
	private long add;

	@Override
	public String execute() throws Exception {
		Controller c = Turntable.getInstance().getController();
		
		c.setKuCun(c.getKuCun() + add);
		
		Server.getKeyValueSaveOnly().add("KU_CUN_TOU_FANG_LIANG", add);
		
		Log.d("add ku cun", add);
		
		return SUCCESS;
	}

	public long getAdd() {
		return add;
	}

	public void setAdd(long add) {
		this.add = add;
	}
}
