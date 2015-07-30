package org.hhhhhh.fqzs.result;

import org.hhhhhh.fqzs.core.CoinStatus;

public class RoleData {

	long RechargeHistory;
	long CreateTime;

	long LastLoginTime;
	String Id;

	String OwnerId;

	String Nick;

	long Coin;
	long LaBa;

	long BankCoin;

	long Cd;

	int BankPasswordStatus;
	int BankPasswordCd;

	String BankPassword;

	public long getRechargeHistory() {
		return RechargeHistory;
	}

	public void setRechargeHistory(long rechargeHistory) {
		RechargeHistory = rechargeHistory;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public long getLastLoginTime() {
		return LastLoginTime;
	}

	public void setLastLoginTime(long lastLoginTime) {
		LastLoginTime = lastLoginTime;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getOwnerId() {
		return OwnerId;
	}

	public void setOwnerId(String ownerId) {
		OwnerId = ownerId;
	}

	public String getNick() {
		return Nick;
	}

	public void setNick(String nick) {
		Nick = nick;
	}

	public long getCoin() {
		return Coin;
	}

	public void setCoin(long coin) {
		Coin = coin;
	}

	public long getLaBa() {
		return LaBa;
	}

	public void setLaBa(long laBa) {
		LaBa = laBa;
	}

	public long getBankCoin() {
		return BankCoin;
	}

	public void setBankCoin(long bankCoin) {
		BankCoin = bankCoin;
	}

	public long getCd() {
		return Cd;
	}

	public void setCd(long cd) {
		Cd = cd;
	}

	public int getBankPasswordStatus() {
		return BankPasswordStatus;
	}

	public void setBankPasswordStatus(int bankPasswordStatus) {
		BankPasswordStatus = bankPasswordStatus;
	}

	public int getBankPasswordCd() {
		return BankPasswordCd;
	}

	public void setBankPasswordCd(int bankPasswordCd) {
		BankPasswordCd = bankPasswordCd;
	}

	public String getBankPassword() {
		return BankPassword;
	}

	public void setBankPassword(String bankPassword) {
		BankPassword = bankPassword;
	}

	public CoinStatus getCoinStatus() {
		return CoinStatus;
	}

	public void setCoinStatus(CoinStatus coinStatus) {
		CoinStatus = coinStatus;
	}

	public boolean isHasJinYan() {
		return hasJinYan;
	}

	public void setHasJinYan(boolean hasJinYan) {
		this.hasJinYan = hasJinYan;
	}

	public boolean isHasFengHao() {
		return hasFengHao;
	}

	public void setHasFengHao(boolean hasFengHao) {
		this.hasFengHao = hasFengHao;
	}

	public long getJiangQuan() {
		return JiangQuan;
	}

	public void setJiangQuan(long jiangQuan) {
		JiangQuan = jiangQuan;
	}

	public boolean isRobot() {
		return isRobot;
	}

	public void setRobot(boolean isRobot) {
		this.isRobot = isRobot;
	}

	public long getCoinAll() {
		return CoinAll;
	}

	public void setCoinAll(long coinAll) {
		CoinAll = coinAll;
	}

	CoinStatus CoinStatus;

	boolean hasJinYan;

	boolean hasFengHao;

	long JiangQuan;

	boolean isRobot;

	long CoinAll;

}