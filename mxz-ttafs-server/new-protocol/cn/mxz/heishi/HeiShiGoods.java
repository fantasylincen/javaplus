package cn.mxz.heishi;

import mongo.gen.MongoGen.HeiShiGoodsDto;
import cn.mxz.AgainstGoodsLibraryTemplet;
import cn.mxz.AgainstGoodsLibraryTempletConfig;
import cn.mxz.city.City;

/**
 * 黑市里面的物品
 * 
 * @author 林岑
 * 
 */
public class HeiShiGoods {

	private HeiShiGoodsDto dto;

	public HeiShiGoods(HeiShiGoodsDto dto) {
		this.dto = dto;
	}

	/**
	 * 名字
	 * 
	 * @return
	 */
	public String getName() {
		return getTemplet().getPropNeame();
	}

	/**
	 * 黑市物品ID
	 * 
	 * @return
	 */
	public int getId() {
		return dto.getId();
	}

	/**
	 * 物品数量
	 * 
	 * @return
	 */
	public int getCountRemain() {
		return dto.getRemainCount();
	}

	/**
	 * 限兑次数
	 * 
	 * @return
	 */
	public int getLimit() {
		return HeiShi.LIMIT;
	}

	/**
	 * 兑换的消耗
	 * 
	 * @return
	 */
	public String getNeeds() {
		String consume = getTemplet().getConsume();
		return consume;
	}

	private AgainstGoodsLibraryTemplet getTemplet() {
		return AgainstGoodsLibraryTempletConfig.get(dto.getId());
	}

	public void send(City city) {
		String prize = getPrize();
		city.getPrizeSender1().send(prize);

		int cc = getCountExchangeEvery();

		dto.setRemainCount(dto.getRemainCount() - cc);
		if (getCountRemain() <= 0)
			dto.setHasExchange(true);
	}

	String getPrize() {
		return getTemplet().getReward() + "," + getCountExchangeEvery();
	}

	/**
	 * 每次兑换数量
	 * 
	 * @return
	 */
	public int getCountExchangeEvery() {
		int countExchangeEvery = dto.getCountExchangeEvery();
		if (countExchangeEvery == 0) {
			return getCountRemain();
		}
		return countExchangeEvery;
	}

	public boolean hasExchange() {
		return dto.getHasExchange();
	}
}
