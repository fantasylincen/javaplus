package cn.mxz.nvwa;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import message.S;
import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.NvwaDao;
import mongo.gen.MongoGen.NvwaDto;
import cn.javaplus.log.Debuger;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;
import cn.mxz.system.SystemCounter;
import cn.mxz.system.SystemCounterKey;

import com.google.common.collect.Lists;

public class Nvwa {

	public class MyComparator implements Comparator<SplitLine> {

		@Override
		public int compare(SplitLine a1, SplitLine a2) {
			return a2.getCount() - a1.getCount();
		}

	}

	private City city;
	private NvwaDto dto;

	public Nvwa(City city) {
		this.city = city;
		dto = getDto();
	}

	private NvwaDto getDto() {
		if(dto != null) {
			return dto;
		}
		NvwaDao dao = Daos.getNvwaDao();
		dto = dao.get(city.getId());
		if (dto == null) {
			dto = new NvwaDto();
			dto.setUname(city.getId());
			dao.save(dto);
		}
		return dto;
	}

	public City getCity() {
		return city;
	}

	public void buy() {
		if (NvwaRule.isStart() && NvwaRule.getRemainSec() >= 0) {
			int priceNew = getPriceNew();
			city.getPlayer().reduceGold(priceNew);
			city.getBagAuto().addProp(NvwaRule.getBoxId(), 1);
			SystemCounter ins = SystemCounter.getInstance();
			if (city.isTester()/* || city.getId().equals("lc2")*/) {
				ins.add(SystemCounterKey.NVWA_BOUGHT_COUNT, 2000);
			} else {
				ins.add(SystemCounterKey.NVWA_BOUGHT_COUNT, 1);
			}
			save(priceNew);
		} else {
			throw new OperationFaildException(S.S10325);
		}
	}

	private void save(int priceNew) {
		int buyCount = dto.getBuyCount() + 1;
		Debuger.debug("Nvwa.save()" + buyCount);
		dto.setBuyCount(buyCount);
		dto.setGoldAll(dto.getGoldAll() + priceNew);
		Daos.getNvwaDao().save(dto);
	}

	public int getRemainSec() {
		return NvwaRule.getRemainSec();
	}

	public int getCountNow() {
		return SystemCounter.getInstance().get(
				SystemCounterKey.NVWA_BOUGHT_COUNT);
	}

	public int getGoldWillBack() {
		int all = getGoldAll();
		float allNeed = getRealNeed();
		int back = (int) (all - allNeed);
		return back;
	}

	public int getBoughtCount() {
		return getDto().getBuyCount();
	}

	public int getPriceOld() {
		return NvwaRule.getBasePrice();
	}

	public int getPriceNew() {
		int old = getPriceOld();
		int zheKou = getZheKou();
		return (int) (old * (float) zheKou / 10);
	}

	/**
	 * 已经花了多少钱
	 * 
	 * @return
	 */
	public int getGoldAll() {
		return getDto().getGoldAll();
	}

	public int getGold() {
		return city.getPlayer().getGold();
	}

	/**
	 * 活动结束后, 退还玩家多用了的钱
	 */
	public void sendBack() {
		NvwaDto dto = getDto();
		if (dto.getIsSendBack()) {
			return;
		}
		if (getRemainSec() <= 0) {
			sendGold();
			dto.setIsSendBack(true);
			Daos.getNvwaDao().save(dto);
		}
	}

	private void sendGold() {
		int back = getGoldWillBack();
		if(back <= 0) {
			return;
		}
		String str = "110009," + back;
		city.getPrizeCenter().addPrize(3, str, S.STR10260, S.STR10260);
	}

	/**
	 * 实际需要花多少钱
	 * 
	 * @return
	 */
	private float getRealNeed() {
		int boughtCount = getBoughtCount();
		int price = getPriceNew();
		float allNeed = price * boughtCount;
		return allNeed;
	}

	/**
	 * 折扣 0 - 10
	 * 
	 * @return
	 */
	public int getZheKou() {
		List<SplitLine> lines = Lists.newArrayList(NvwaRule.getLines());
		Comparator<SplitLine> c = new MyComparator();
		Collections.sort(lines, c);
		for (SplitLine l : lines) {
			if (getCountNow() >= l.getCount()) {
				return l.getZheKou();
			}
		}
		return 10;
	}
}
