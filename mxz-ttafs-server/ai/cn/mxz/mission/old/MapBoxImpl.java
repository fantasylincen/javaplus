package cn.mxz.mission.old;

import java.util.List;

import message.S;
import cn.mxz.ConsumableTemplet;
import cn.mxz.ConsumableTempletConfig;
import cn.mxz.base.prize.PrizeInExcelRandomImpl;
import cn.mxz.base.prize.PrizeSender;
import cn.mxz.base.prize.PrizeSenderFactory;
import cn.mxz.base.prize.ZiJinBaoXiang;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.city.PlayerProperty;
import cn.mxz.user.Player;
import cn.mxz.util.debuger.Debuger;
import define.D;



public class MapBoxImpl implements MapBox {

	private int id;

	private List<Prize> all;

	public MapBoxImpl(int id, City city) {

		this.id = id;
		
		if(id == D.ZJBX_ID) {

			all = new ZiJinBaoXiang(city).open();
		} else {
			
			build(city);
		}

	}

	private void build(City city) {

		ConsumableTemplet temp = ConsumableTempletConfig.get(getId());

		PrizeSender ps = PrizeSenderFactory.getPrizeSender();

		if(temp == null) {

			throw new NullPointerException("id : " + getId());
		}

		ConsumableTemplet t = (ConsumableTemplet) temp;

		all = new PrizeInExcelRandomImpl(t, city).getAwards();
	}

	@Override
	public int getId() {
		return id;
	}


	@Override
	public void open(Player player) {

		int old = player.get(PlayerProperty.CASH);

		for (Prize p : all) {

			p.award(player);

			Debuger.debug("MapBoxImpl.open() 开启宝箱得到的奖励:" + p);
		}

		int n = player.get(PlayerProperty.CASH);

		if(n - old > 0) {
			City city = CityFactory.getCity(player.getId());
			city.getMessageSender().send(S.S10195, n - old);
		}

	}


	@Override
	public List<Prize> getAll() {

		return all;
	}


	@Override
	public int getIndex() {
		return 1;
	}

	@Override
	public int getPath() {
		return 1;
	}
}
