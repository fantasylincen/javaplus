package cn.vgame.a.zhuang;

import java.util.List;

import cn.vgame.a.Server;

public class ShangZhuangListResult {
	
	/**
	 * 当前庄家金币
	 */
	public long getZhuangCoin() {
		ZhuangManager manager = Server.getZhuangManager();
		Zhuang z = manager.getZhuang();
		if(z == null)
			return 0;
		return z.getCoin();
	}
	
	/**
	 * 当前庄家昵称
	 */
	public String getZhuangNick() {
		ZhuangManager manager = Server.getZhuangManager();
		Zhuang z = manager.getZhuang();
		if(z == null)
			return null;
		return z.getNick();
	}
	/**
	 * 当前庄家ID
	 */
	public String getZhuangId() {
		ZhuangManager manager = Server.getZhuangManager();
		Zhuang z = manager.getZhuang();
		if(z == null)
			return null;
		return z.getRoleId();
	}
	

	/**
	 * 当前庄家剩余当庄回合数
	 */
	public int getZhuangRemainTimes() {
		ZhuangManager manager = Server.getZhuangManager();
		Zhuang z = manager.getZhuang();
		if(z == null)
			return 0;
		return z.getRemainTimes();
	}
	
	/**
	 * 庄家列表
	 */
	public List<Zhuang> getRoles() {
		return Server.getZhuangManager().getRoles();
	}
}
