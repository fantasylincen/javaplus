package cn.mxz.pvp;

import java.util.List;

import message.S;
import cn.mxz.ArenaConvertTemplet;
import cn.mxz.ArenaConvertTempletConfig;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;

import com.google.common.collect.Lists;

public class PvpDuiHuanManager {

	private City city;
	private List<PvpDHItem> items;

	public PvpDuiHuanManager(City city) {
		this.city = city;
		items = Lists.newArrayList();
		List<ArenaConvertTemplet> all = ArenaConvertTempletConfig.getAll();
		for (ArenaConvertTemplet a : all) {
			items.add(new PvpDHItemImpl(a, city));
		}
	}

	public City getCity() {
		return city;
	}

	public PvpDHItem duiHuan(int typeId) {
		List<PvpDHItem> is = getItems();
		for (PvpDHItem p : is) {
			if(p.getTypeId() == typeId) {
				p.duiHuan();
				return p;
			}
		}
		throw new RuntimeException("ID错误:" + typeId);
	}

	public List<PvpDHItem> getItems() {
		return items;
	}
}
