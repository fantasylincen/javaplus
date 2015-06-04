package cn.mxz.monthcard;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.base.service.AbstractService;
import cn.mxz.handler.MonthCardService;


@Component("monthCardService")
@Scope("prototype")
public class MonthCardServiceImpl extends AbstractService implements MonthCardService {

	@Override
	public String getDate() {
		MonthCard mc = getCity().getMonthCard();
		String ret = mc.isBuy() == true ? "1":"0";
		ret +=  "," + mc.getCurrentMonthRecharge();
		ret += ","; 
		ret += mc.getRemainSecond();
		

		return ret;
	}

	@Override
	public String buy() {
		MonthCard mc = getCity().getMonthCard();
		mc.add();
		return getDate();	
		
	}

}
