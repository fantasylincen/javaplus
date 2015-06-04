package cn.vgame.a.gm;

import cn.vgame.a.turntable.Turntable;
import cn.vgame.a.turntable.Turntable.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 修改库存(刘雨诚请无视) ----------------- 无
 */
public class SetKuCunAction extends ActionSupport {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6609009239528949488L;

	private long kuCun;

	@Override
	public String execute() throws Exception {
		Controller c = Turntable.getInstance().getController();
		c.setKuCun(kuCun);
		
		return SUCCESS;
	}

	public long getKuCun() {
		return kuCun;
	}

	public void setKuCun(long kuCun) {
		this.kuCun = kuCun;
	}
}
