package cn.mxz.equipment;

import cn.javaplus.math.Fraction;
import cn.javaplus.util.Util;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.user.Player;

public class JiaPlayer implements Player {

	private Player player;

	public int getGiftGold() {
		return player.getGiftGold();
	}

	public int getRechargeGold() {
		return player.getRechargeGold();
	}

	public int getClientType() {
		return player.getClientType();
	}

	public int getPhysicalMax() {
		return player.getPhysicalMax();
	}

	public int getPowerMax() {
		return player.getPowerMax();
	}

	public City getCity() {
		return player.getCity();
	}

	// private String randomNick;
	private int level = -1;
	private String id;
	private int mainFighterTypeId;
	private String nick;

	// public JiaPlayer(Player player, int mainFighterTypeId) {
	// this.player = player;
	// id = player.getId();
	// this.mainFighterTypeId = mainFighterTypeId;
	// }

	public JiaPlayer(Player player, int mainFighterTypeId, String nick, String id) {
		this.player = player;
		this.mainFighterTypeId = mainFighterTypeId;
		this.id = id;
		this.nick = nick;
	}

	@Override
	public int getMainFighterTypeId() {
		return mainFighterTypeId;
	}

	/**
	 * @return
	 * @see cn.mxz.user.Player#getLevel()
	 */
	@Override
	public int getLevel() {
		if (level == -1) {
			level = player.getLevel();
			int i = Util.Random.get(-5, 0);
			level += i;
			if (level < 1) {
				level = 1;
			}
		}
		return level;
	}

	/**
	 * @return
	 * @see cn.mxz.user.Player#getNick()
	 */
	@Override
	public String getNick() {
		// if (randomNick == null) {
		// NickManager nm = WorldFactory.getWorld().getNickManager();
		// randomNick = nm.getRandomNick();
		// }
		// return /* "-" + */randomNick;
		return nick;
	}

	@Override
	public void reduceGold(int value) {
		player.reduceGold(value);
	}

	@Override
	public int getVipLevel() {
		return player.getVipLevel();
	}

	@Override
	public int getDanId() {
		return player.getDanId();
	}

	@Override
	public int getTotalRechargeGold() {
		return player.getTotalRechargeGold();
	}

	@Override
	public int getShenJia() {
		return player.getShenJia();
	}

	@Override
	public void addGiftGold(int count) {
		player.addGiftGold(count);
	}

	@Override
	public void addGold(int add) {
		player.addGold(add);
	}

	@Override
	public String getThirdPartyId() {
		return player.getThirdPartyId();
	}

	@Override
	public boolean isThirdPartyPlayer() {
		return player.isThirdPartyPlayer();
	}

	@Override
	public void saveThirdPartyId(String userId) {
		player.saveThirdPartyId(userId);
	}

	@Override
	public void saveClientType(int clientType) {
		player.saveClientType(clientType);
	}

	@Override
	public int getGold() {
		return player.getGold();
	}

	/**
	 * @return
	 * @see cn.javaplus.serchengine.SearchAble#getSearchedField()
	 */
	@Override
	public String getSearchedField() {
		return player.getSearchedField();
	}

	@Override
	public void reduceGoldOrJinDing(int value) {
		player.reduceGoldOrJinDing(value);
	}

	@Override
	public int getGoldAndJinDing() {
		return player.getGoldAndJinDing();
	}

	/**
	 * @return
	 * @see cn.mxz.user.Player#getExp()
	 */
	@Override
	public Fraction getExp() {
		return player.getExp();
	}

	/**
	 * @return
	 * @see cn.mxz.user.Player#getHeroInfo()
	 */
	@Override
	public Fraction getHeroInfo() {
		return player.getHeroInfo();
	}

	/**
	 * @return
	 * @see cn.mxz.user.Player#getPhysical()
	 */
	@Override
	public Fraction getPhysical() {
		return player.getPhysical();
	}

	/**
	 * @return
	 * @see cn.mxz.user.Player#getPower()
	 */
	@Override
	public Fraction getPower() {
		return player.getPower();
	}

	/**
	 * @return
	 * @see cn.mxz.user.Player#getId()
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * @param count
	 * @see cn.mxz.user.Player#addExp(int)
	 */
	@Override
	public void addExp(int count) {
		player.addExp(count);
	}

	/**
	 * @return
	 * @see cn.mxz.user.Player#isMan()
	 */
	@Override
	public boolean isMan() {
		return player.isMan();
	}

	/**
	 * @param property
	 * @return
	 * @see cn.mxz.user.Player#get(cn.mxz.city.PlayerProperty)
	 */
	@Override
	public int get(PlayerProperty property) {
		return player.get(property);
	}

	/**
	 * @param playerProperty
	 * @param add
	 * @see cn.mxz.user.Player#add(cn.mxz.city.PlayerProperty, int)
	 */
	@Override
	public void add(PlayerProperty playerProperty, int add) {
		player.add(playerProperty, add);
	}

	/**
	 * @param playerProperty
	 * @param reduce
	 * @see cn.mxz.user.Player#reduce(cn.mxz.city.PlayerProperty, int)
	 */
	@Override
	public void reduce(PlayerProperty playerProperty, int reduce) {
		player.reduce(playerProperty, reduce);
	}

	/**
	 * @param p
	 * @param v
	 * @see cn.mxz.user.Player#set(cn.mxz.city.PlayerProperty, int)
	 */
	@Override
	public void set(PlayerProperty p, int v) {
		player.set(p, v);
	}

	/**
	 * @return
	 * @see cn.mxz.user.Player#getLastLoginSec()
	 */
	@Override
	public int getLastLoginSec() {
		return player.getLastLoginSec();
	}

	/**
	 * @param newNick
	 * @see cn.mxz.user.Player#setNick(java.lang.String)
	 */
	@Override
	public void setNick(String newNick) {
		player.setNick(newNick);
	}

	/**
	 * @param code
	 * @see cn.mxz.user.Player#setInvitationCode(java.lang.String)
	 */
	@Override
	public void setInvitationCode(String code) {
		player.setInvitationCode(code);
	}

	/**
	 * @return
	 * @see cn.mxz.user.Player#getInvitationCode()
	 */
	@Override
	public String getInvitationCode() {
		return player.getInvitationCode();
	}

	@Override
	public void updateShenJia() {
		player.updateShenJia();
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void updateGoldFromThirdParty() {
		// TODO 自动生成的方法存根
		
	}

}
