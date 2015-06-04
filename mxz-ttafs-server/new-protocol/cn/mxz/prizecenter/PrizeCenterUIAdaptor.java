package cn.mxz.prizecenter;

import java.util.List;

import cn.mxz.city.IPrizeCenter;

public class PrizeCenterUIAdaptor implements IPrizeCenterUI {

	private IPrizeCenter	prizeCenter;

	public PrizeCenterUIAdaptor(IPrizeCenter prizeCenter) {
		this.prizeCenter = prizeCenter;
	}

	@Override
	public List<IUserPrizePackage> getData() {
//		List<IUserPrizePackage> list = Lists.newArrayList();
//		IUserPrizePackage u = new UserPrizePackage("152021,4,152020,4", "test", 1, "test");
//		list.add( u );
//		return list;//记得修改回来
//		
		return prizeCenter.getData();

	}

}
