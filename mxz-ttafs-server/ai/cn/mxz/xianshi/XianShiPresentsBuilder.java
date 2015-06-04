package cn.mxz.xianshi;

import java.util.List;

import cn.mxz.protocols.user.shops.ShopsAllP.ShopToolsPro;
import cn.mxz.protocols.xianshi.XianShiP.XianShiPresentsPro;

public class XianShiPresentsBuilder {

	public XianShiPresentsPro build(List<ShopToolsPro> bd) {
		XianShiPresentsPro.Builder b = XianShiPresentsPro.newBuilder();
		for (ShopToolsPro g : bd) {
			b.addPros(new XianShiPresentBuilder().build(g));
		}
		return b.build();
	}

}
