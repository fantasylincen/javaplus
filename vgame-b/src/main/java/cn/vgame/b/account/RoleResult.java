package cn.vgame.b.account;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.vgame.b.Server;
import cn.vgame.plantform.Plantform;
import cn.vgame.share.KeyValue;
import cn.vgame.share.Xml;

public class RoleResult {

	/**
	 * 
	 */
	private final Role role;
	private final HttpSession session;

	/**
	 * @param session 
	 * @param createRoleResult
	 */
	public RoleResult(Role r, HttpSession session) {
		role = r;
		this.session = session;
	}

	public long getRechargeHistory() {
		return role.getRechargeHistory();
	}

	public long getCreateTime() {
		return role.getCreateTime();
	}

	public long getLastLoginTime() {
		return role.getLastLoginTime();
	}

	public String getId() {
		return role.getId();
	}

	public String getOwnerId() {
		return role.getOwnerId();
	}

	public String getNick() {
		return role.getNick();
	}

	public long getCoin() {
		return role.getCoin();
	}

	public long getLaBa() {
		return role.getLaBa();
	}

	public long getBankCoin() {
		return role.getBankCoin();
	}


	public boolean hasJinYan() {
		return role.hasJinYan();
	}

	public boolean hasFengHao() {
		return role.hasFengHao();
	}

	public boolean isRobot() {
		return role.isRobot();
	}

	public long getCoinAll() {
		return role.getCoinAll();
	}
	
}