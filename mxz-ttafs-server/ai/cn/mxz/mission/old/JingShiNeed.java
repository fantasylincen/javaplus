package cn.mxz.mission.old;

import message.S;
import cn.mxz.activity.heishi.HeishiManager;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.BaseRewards.BaseRewardId;
import cn.mxz.city.City;
import cn.mxz.user.Player;
import cn.mxz.util.needs.INeeds;

public class JingShiNeed implements INeeds {

	private Integer	count;
	private int id;

	public JingShiNeed(int id, Integer count) {
		this.id = id;
		this.count = count;
	}

	@Override
	public void checkEnouph(Player player) {

		int count = player.getCity().getHeishiManager().getQsjs();
		if(count < this.count) {
			throw new OperationFaildException(S.S10328);
		}
	}

	@Override
	public void deduct(Player player) {

		HeishiManager heishiManager = player.getCity().getHeishiManager();
		int need = getNeed(player);
		heishiManager.reduceJingShi(id + "," + need);
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

		int now = getNow(city);
		
	

		return now / count;
	}

	private int getNow(City city) {
		if (id == BaseRewardId.QiSeJingShi_110023) {
			return city.getHeishiManager().getQsjs();
		} else if (id == 110025) {//110025白水晶
			return city.getHeishiManager().getBsj();
		} else if (id == 110026) {//  110026黄水晶
			return city.getHeishiManager().getHsj();
		}
		throw new RuntimeException("无法识别的ID:" + id);
	}

	@Override
	public int getHaveNow(Player player) {

		int now = getNow(player.getCity());

		return now;
	}

	@Override
	public int getNeed(Player player) {

		return count;
	}

	@Override
	public int getId() {
		return id;
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
