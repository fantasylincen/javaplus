package cn.mxz.vip;

import java.util.List;

import cn.mxz.VipGiftTemplet;
import cn.mxz.VipGiftTempletConfig;
import cn.mxz.city.City;

import com.google.common.collect.Lists;

public class VipGiftStausImpl implements VipGiftStatus {

	private City	user;

	public VipGiftStausImpl(City user) {
		this.user = user;
	}

	@Override
	public List<Gift> getGifts() {
		List<Gift> ls = Lists.newArrayList();
		List<VipGiftTemplet> all = VipGiftTempletConfig.getAll();
		for (VipGiftTemplet vip : all) {
			ls.add(new GiftImpl(vip, user));
		}
		return ls;
	}

}
