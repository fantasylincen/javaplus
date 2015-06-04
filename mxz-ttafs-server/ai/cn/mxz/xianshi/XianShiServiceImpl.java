package cn.mxz.xianshi;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.base.service.AbstractService;
import cn.mxz.handler.XianShiService;
import cn.mxz.protocols.user.shops.ShopsAllP.ShopToolsPro;
import cn.mxz.protocols.xianshi.XianShiP.XianShiPresentsPro;
import cn.mxz.shop.Shopper;
import cn.mxz.shop.ShopsAllBuilder;

@Component("xianShiService")
@Scope("prototype")
public class XianShiServiceImpl extends AbstractService implements XianShiService {

	@Override
	public XianShiPresentsPro getPresents() {

//		List<Present> presents = getCity().getPresentManager().getAll();

		List<ShopToolsPro> bd = new ShopsAllBuilder().getToolsAll(getCity());
		
		XianShiPresentsBuilder b = new XianShiPresentsBuilder();

		return b.build(bd);

	}


	@Override
	public void buyPresent(int id, int count) {
		new Shopper(getCity()).buy(id, count);
//		PresentManager presents = getCity().getPresentManager();
//		presents.buy(id, count);
	}

}
