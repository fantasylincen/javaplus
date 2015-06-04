package cn.mxz.xianshi;

import cn.mxz.MarketPlaceTemplet;
import cn.mxz.MarketPlaceTempletConfig;
import cn.mxz.protocols.user.shops.ShopsAllP.ShopToolsPro;
import cn.mxz.protocols.xianshi.XianShiP.XianShiPresentPro;

public class XianShiPresentBuilder {

	public XianShiPresentPro build(ShopToolsPro g) {
		XianShiPresentPro.Builder b = XianShiPresentPro.newBuilder();
		b.setId(g.getToolId());
		
		MarketPlaceTemplet temp = MarketPlaceTempletConfig.get(g.getToolId());

		if(temp.getMax() != -1) {
			b.setBuyTimes(g.getBuyCountToday());
		} else {
			b.setBuyTimes(g.getBuyCountHistory());
		}
		
		b.setRemianTimes(g.getCanBuyCount());
		
		b.setCountMax(g.getCountMax());
		
//		Debuger.debug("XianShiPresentBuilder.build()  " + g.getCanBuyCount());
		return b.build();
	}

}
