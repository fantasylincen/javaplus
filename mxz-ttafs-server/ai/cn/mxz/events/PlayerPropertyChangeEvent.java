package cn.mxz.events;

import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.user.Player;

public class PlayerPropertyChangeEvent {

	private PlayerProperty	playerProperty;
	private int				oldValue;
	private int				newValue;
	private Player			player;
	private boolean			hasGoldChanged;
	private City	city;

	public PlayerPropertyChangeEvent(City city, Player player, PlayerProperty playerProperty, boolean hasGoldChanged, int oldValue, int newValue) {
		this.city = city;
		this.player = player;
		this.playerProperty = playerProperty;
		this.hasGoldChanged = hasGoldChanged;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	public int getOldValue() {
		return oldValue;
	}

	public City getCity() {
		return city;
	}

	public int getNewValue() {
		return newValue;
	}

	public PlayerProperty getPlayerProperty() {
		return playerProperty;
	}

	public int getAdd() {
		return getNewValue() - getOldValue();
	}

	public int getReduce() {
		return getOldValue() - getNewValue();
	}

	/**
	 * 属性是否增加了
	 *
	 * @return
	 */
	public boolean isAdd() {
		return getAdd() > 0;
	}

	public Player getPlayer() {
		return player;
	}

	/**
	 * 元宝是否发生了变化
	 *
	 * @return
	 */
	public boolean hasGoldChanged() {
		return hasGoldChanged;
	}

}