package cn.mxz.fish;

import cn.mxz.protocols.user.fish.FishUserP.FishPro;

class FishBuilder {

	public FishPro build(Fish fish) {

		FishPro.Builder b = FishPro.newBuilder();

		b.setId(fish.getGridId());

		b.setType(fish.getTempletId());

		b.setCount(fish.getCount());

		return b.build();
	}
}
