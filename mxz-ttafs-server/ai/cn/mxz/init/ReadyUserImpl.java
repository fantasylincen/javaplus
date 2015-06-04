package cn.mxz.init;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import scala.annotation.meta.getter;
import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.user.init.ReadyUser;

/**
 * 
 * 待创建的用户信息
 * 
 * @author 林岑
 * @since 2013年5月28日 10:44:35
 * 
 */
@Component("readyUser")
@Scope("prototype")
public class ReadyUserImpl implements ReadyUser {

	private String userName;
	String gameId;

	@Override
	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private String accounts;

	public String getAccounts() {
		return accounts;
	}

	public void setAccounts(String lineKongId) {
		this.accounts = lineKongId;
	}

	private String nick;

	private int fighterTypeId;

	private String roleId;

	private int clientType;

	public int getClientType() {
		return clientType;
	}

	public void setClientType(int clientType) {
		this.clientType = clientType;
	}

	@Override
	public String getRoleId() {
		return roleId;
	}

	@Override
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return 用户昵称
	 */
	@Override
	public String getNick() {
		return nick;
	}

	@Override
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * 是否创建了昵称
	 */
	@Override
	public boolean hasNick() {
		return nick != null;
	}

	@Override
	public String toString() {
		return accounts + "|" + nick + "|" + fighterTypeId;
	}

	@Override
	public int getFighterTypeId() {
		return fighterTypeId;
	}

	@Override
	public void setFighterTypeId(int fighterTypeId) {
		this.fighterTypeId = fighterTypeId;
	}

	@Override
	public boolean isMan() {
		FighterTemplet temp = FighterTempletConfig.get(fighterTypeId);
		if (temp == null) {
			System.err.println("需要前端先调用setFighterTypeId方法才行");
			System.err.println("需要前端先调用setFighterTypeId方法才行");
			System.err.println("需要前端先调用setFighterTypeId方法才行");
			System.err.println("需要前端先调用setFighterTypeId方法才行");
			System.err.println("需要前端先调用setFighterTypeId方法才行");
			System.err.println("需要前端先调用setFighterTypeId方法才行");
			System.err.println("需要前端先调用setFighterTypeId方法才行");
			System.err.println("需要前端先调用setFighterTypeId方法才行");
			return false;
		}
		return temp.getSex() == 1;
	}
}
