package cn.vgame.a.gm;

import cn.vgame.a.turntable.Turntable;
import cn.vgame.a.turntable.Turntable.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 修改系统配置(刘雨诚请无视) ----------------- 无
 */
public class SetSystemInfoAction extends ActionSupport {

	private static final long serialVersionUID = -6115188889586627931L;

	/**
	 * 当系统库存大于某值时, 触发强制收分程序
	 */
	long maxKuCun;

	/**
	 * 当库存变为0时, 是否把回报率调节到正常值
	 */
	private String toNormal;
	/**
	 * 触发收分程序时, 强制干涉概率
	 */
	private double chuFaTunFenGaiLv;
	/**
	 * 触发收分程序时, 强制干涉时长(分钟)
	 */
	private int chuFaTunFenShiChang;

	/**
	 * 回报率档位(吐分速率)
	 */
	int dangWei;

	private int qiangZhiType;
	private double tunTuGaiLv;

	public void setMaxKuCun(long maxKuCun) {
		this.maxKuCun = maxKuCun;
	}

	public void setDangWei(int dangWei) {
		this.dangWei = dangWei;
	}

	public void setTunTuGaiLv(double tunTuGaiLv) {
		this.tunTuGaiLv = tunTuGaiLv;
	}

	public void setToNormal(String toNormal) {
		this.toNormal = toNormal;
	}

	public long getMaxKuCun() {
		return maxKuCun;
	}

	public int getDangWei() {
		return dangWei;
	}

	@Override
	public String execute() throws Exception {
		Controller c = Turntable.getInstance().getController();
		boolean isToNormal = "on".equals(toNormal);
		boolean isTun = qiangZhiType == 1;
		c.update(maxKuCun, isToNormal, dangWei, isTun, tunTuGaiLv,
				chuFaTunFenGaiLv, chuFaTunFenShiChang);
		return SUCCESS;
	}

	public double getTunTuGaiLv() {
		return tunTuGaiLv;
	}

	public String getToNormal() {
		return toNormal;
	}

	public int getQiangZhiType() {
		return qiangZhiType;
	}

	public void setQiangZhiType(int qiangZhiType) {
		this.qiangZhiType = qiangZhiType;
	}

	public int getChuFaTunFenShiChang() {
		return chuFaTunFenShiChang;
	}

	public void setChuFaTunFenShiChang(int chuFaTunFenShiChang) {
		this.chuFaTunFenShiChang = chuFaTunFenShiChang;
	}

	public double getChuFaTunFenGaiLv() {
		return chuFaTunFenGaiLv;
	}

	public void setChuFaTunFenGaiLv(double chuFaTunFenGaiLv) {
		this.chuFaTunFenGaiLv = chuFaTunFenGaiLv;
	}

}
