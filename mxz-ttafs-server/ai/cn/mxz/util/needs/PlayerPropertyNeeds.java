package cn.mxz.util.needs;

import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.user.Player;
import cn.mxz.util.checker.PlayerChecker;

class PlayerPropertyNeeds implements INeeds {

	private PlayerProperty	playerProperty;

	private Integer	count;


	public PlayerPropertyNeeds(PlayerProperty playerProperty, Integer count) {

		this.playerProperty = playerProperty;

		this.count = count;
	}

	@Override
	public void checkEnouph(Player player) {

		PlayerChecker playerChecker = new PlayerChecker(player);

		playerChecker.checkPlayerProperty(playerProperty, count);
	}

	@Override
	public void deduct(Player player) {

		player.reduce(playerProperty, getNeed(player));
	}

	@Override
	public void discount(float discount) {

		count = (int) (count * discount);
	}

	@Override
	public int getDeductTimesMax(City city) {

		if(count == 0) {

			return 0;
		}

		int now = city.getPlayer().get(playerProperty);

		return now / count;
	}

	@Override
	public int getHaveNow(Player player) {

		int now = player.get(playerProperty);

		return now;
	}

	@Override
	public int getNeed(Player player) {

		return count;
	}

	@Override
	public int getId() {
		return playerProperty.getId();
	}

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public void checkEnouph(City user) {
		checkEnouph(user.getPlayer());
	}

	@Override
	public void deduct(City user) {
		deduct(user.getPlayer());

	}

	@Override
	public int getHaveNow(City user) {
		return getHaveNow(user.getPlayer());
	}

	@Override
	public int getNeed(City user) {
		return getNeed(user.getPlayer());
	}
}
