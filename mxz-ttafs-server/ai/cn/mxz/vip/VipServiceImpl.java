package cn.mxz.vip;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.base.service.AbstractService;
import cn.mxz.handler.VipService;

@Component("vipService")
@Scope("prototype")

public class VipServiceImpl extends AbstractService  implements VipService {

	@Override
	public String getData() {
		VipPlayer vip = getCity().getVipPlayer();
		return toJson(vip.getGiftStatus());
	}

	@Override
	public void buy(int vipLevel) {
		VipPlayer vip = getCity().getVipPlayer();
		vip.buy(vipLevel);
	}
}
