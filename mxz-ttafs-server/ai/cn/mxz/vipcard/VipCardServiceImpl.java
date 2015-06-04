package cn.mxz.vipcard;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.base.service.AbstractService;
import cn.mxz.handler.VipCardService;
import cn.mxz.protocols.vip.VipCardP.VipCardPro;

@Component("vipCardService")
@Scope("prototype")

public class VipCardServiceImpl extends AbstractService  implements VipCardService {

	@Override
	public VipCardPro getData() {
		VipCardPlayer p = getCity().getVipCardPlayer();
		VipCardPro.Builder b = VipCardPro.newBuilder();
		b.setRemainDay( p.getRemainDay() );
		b.setType( p.getVipCardType() );
		b.setHasReceived(p.hasReceived());
		return b.build();
	}

	@Override
	public void buy() {
		VipCardPlayer p = getCity().getVipCardPlayer();
		p.buy();

	}



}
