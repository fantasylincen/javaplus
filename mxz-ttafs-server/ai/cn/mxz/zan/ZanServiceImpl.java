package cn.mxz.zan;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.base.service.AbstractService;
import cn.mxz.city.City;
import cn.mxz.handler.ZanService;
import cn.mxz.protocols.zan.ZanP.ZanPro;

@Component("zanService")
@Scope("prototype")
public class ZanServiceImpl  extends AbstractService implements ZanService { {

}

@Override
public ZanPro getData() {
	City user = getCity();
	ZanManager zan = user.getZanManager();
	ZanPro.Builder b =ZanPro.newBuilder();
	b.setNextGold( zan.getNextGold() );
	b.setCount( zan.getCount() );
	b.setTotalCount( zan.getTotalCount() );
	b.setLevel( zan.getZanLevel() );
	b.setTodayIsClick( zan.getTodayIsClick() );
	//b.setTomorrowGold( zan.getTomorrowGold() );
	
	return b.build();
	
}

@Override
public void clickZan() {
	City user = getCity();
	ZanManager zan = user.getZanManager();
	zan.clickZan();
	
}
}
