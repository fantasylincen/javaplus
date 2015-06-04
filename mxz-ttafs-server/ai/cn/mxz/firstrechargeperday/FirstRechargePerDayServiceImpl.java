package cn.mxz.firstrechargeperday;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.base.service.AbstractService;
import cn.mxz.handler.FirstRechargePerDayService;

@Component("firstRechargePerDayService")
@Scope("prototype")
public class FirstRechargePerDayServiceImpl extends AbstractService implements FirstRechargePerDayService {

	@Override
	public String getDate() {
		
		return FirstRechargePerDay.INSTANCE.getDate(getCity());
	}

	@Override
	public String getDate1() {
		return FirstRechargePerDay.INSTANCE.getDate1(getCity());
	}

	@Override
	public void getAward() {
		FirstRechargePerDay.INSTANCE.getAward( getCity() );
		
	}

}