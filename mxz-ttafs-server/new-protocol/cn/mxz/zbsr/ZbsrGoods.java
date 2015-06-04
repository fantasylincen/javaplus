package cn.mxz.zbsr;

import mongo.gen.MongoGen.ZbsrGoodsDto;
import cn.mxz.EquipmentMerchantTemplet;
import cn.mxz.EquipmentMerchantTempletConfig;
import cn.mxz.city.City;
import define.D;

/**
 * 装备商人里面的物品
 * 
 * @author 林岑
 * 
 */
public class ZbsrGoods {

	private ZbsrGoodsDto dto;

	public ZbsrGoods(ZbsrGoodsDto dto) {
		this.dto = dto;
	}

	public String getName() {
		return getTemplet().getPropNeame();
	}

	public int getId() {
		return dto.getId();
	}

	public String getNeeds() {
		String consume = getTemplet().getConsume();
		return consume;
	}

	private EquipmentMerchantTemplet getTemplet() {
		return EquipmentMerchantTempletConfig.get(dto.getId());
	}

	public void send(City city) {
		String prize = getPrize();
		city.getPrizeSender1().send(prize);
		dto.setHasReceive(true);
	}

	String getPrize() {
		return dto.getEquipmentTempletId() + ",1";
	}

	public int getEquipmentTempletId() {
		return dto.getEquipmentTempletId();
	}

	public int getGoldOld() {
		String[] split = getNeeds().split(",");
		return new Integer(split[1]);
	}

	public int getGoldNew() {
		if(isTeJia()) {
			return (int) (getGoldOld() * D.ZHUANG_BEI_ZHE_KOU);
		}
		return getGoldOld();
	}

	public boolean isTeJia() {
		return dto.getIsTeJia();
	}

	public boolean getCanReceive() {
		return !getHasReceive();
	}

	private boolean getHasReceive() {
		return dto.getHasReceive();
	}
}
