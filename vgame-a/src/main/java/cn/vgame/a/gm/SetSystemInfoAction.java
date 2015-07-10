package cn.vgame.a.gm;

import cn.vgame.a.turntable.Turntable;
import cn.vgame.a.turntable.Turntable.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 修改系统配置(刘雨诚请无视) ----------------- 无
 */
public class SetSystemInfoAction extends ActionSupport {

	private static final long serialVersionUID = -6115188889586627931L;


	private int qiangZhiType;
	private double tunTuGaiLv;
	private long tunTuLiang;
	private double kuCunShuaiJian;
	
	public void setTunTuGaiLv(double tunTuGaiLv) {
		if(tunTuGaiLv < 0)
			tunTuGaiLv = 0;
		if(tunTuGaiLv > 0.8)
			tunTuGaiLv = 0.8;
		this.tunTuGaiLv = tunTuGaiLv;
	}


	@Override
	public String execute() throws Exception {
		Controller c = Turntable.getInstance().getController();
		boolean isTun = qiangZhiType == 1;
		c.update( isTun, tunTuGaiLv, tunTuLiang, kuCunShuaiJian);
		return SUCCESS;
	}

	public double getTunTuGaiLv() {
		return tunTuGaiLv;
	}

	public int getQiangZhiType() {
		return qiangZhiType;
	}

	public void setQiangZhiType(int qiangZhiType) {
		this.qiangZhiType = qiangZhiType;
	}


	public long getTunTuLiang() {
		return tunTuLiang;
	}


	public void setTunTuLiang(long tunTuLiang) {
		this.tunTuLiang = tunTuLiang;
	}


	public double getKuCunShuaiJian() {
		return kuCunShuaiJian;
	}


	public void setKuCunShuaiJian(double kuCunShuaiJian) {
		if(kuCunShuaiJian < 0)
			kuCunShuaiJian = 0;
		if(kuCunShuaiJian > 0.5)
			kuCunShuaiJian = 0.5;
		
		this.kuCunShuaiJian = kuCunShuaiJian;
	}

}
