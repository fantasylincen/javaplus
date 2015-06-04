package cn.mxz.regist;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.base.service.AbstractService;
import cn.mxz.handler.RegisterService;
import cn.mxz.protocols.user.RegistP.CalendarPro;

@Component("registerService")
@Scope("prototype")
public class RegisterServiceImpl extends AbstractService implements RegisterService {

	@Override
	public void regist() {

		// getCity().getRegister().regist();
	}

	@Override
	public void remedy() {

		// getCity().getRegister().remedy();
	}

	@Override
	public CalendarPro getData() {

		// Register r = getCity().getRegister();
		//
		// return new RegistCalendarBuilder().build(r);
		return null;
	}

}
