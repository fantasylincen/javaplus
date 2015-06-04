package cn.mxz.base.prize;

import java.util.Iterator;
import java.util.List;

import cn.javaplus.random.WeightFetcher;
import cn.javaplus.util.Util;
import cn.mxz.BranchBossFallTemplet;
import cn.mxz.BranchBossFallTempletConfig;
import cn.mxz.city.City;
import cn.mxz.city.FunctionOpenManager;
import cn.mxz.mission.Generator;
import cn.mxz.user.Player;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;

public class ZXSJGenerator implements Generator {

	private int	type;
	private Player player;
	private City city;

	public ZXSJGenerator(int type, Player player) {
		this.type = type;
		this.player = player;
		city = player.getCity();
	}

	@Override
	public int generate() {

		List<BranchBossFallTemplet> s = getList();
		
		if(s.isEmpty()) {
			Debuger.error("长度为0 " + type + ", " + city + ", " + player);
		}

		BranchBossFallTemplet r = getRandomOne(s);

		return r.getPropId();
	}

	private BranchBossFallTemplet getRandomOne(
			List<BranchBossFallTemplet> s) {
		WeightFetcher<BranchBossFallTemplet> weightAble = new WeightFetcher<BranchBossFallTemplet>() {

			@Override
			public Integer get(BranchBossFallTemplet t) {
				return t.getWeight();
			}
		};

		BranchBossFallTemplet r = Util.Random.getRandomOneByWeight(s,
				weightAble);
		return r;
	}

	private List<BranchBossFallTemplet> getList() {
		List<BranchBossFallTemplet> s = Lists.newArrayList(BranchBossFallTempletConfig.getAll());
		Iterator<BranchBossFallTemplet> it = s.iterator();
		while (it.hasNext()) {
			BranchBossFallTemplet r = it.next();
			
			FunctionOpenManager fm = city.getFunctionOpenManager();
			
 			if(!fm.isOpen(r.getModulesId())) {
				it.remove();
				continue;
			}
			
			
			String range = r.getRange();
			List<Integer> all = Util.Collection.getIntegers(range);

			if (!all.contains(type)) {
				it.remove();
			}
		}
		return s;
	}


}
