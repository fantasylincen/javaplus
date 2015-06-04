package cn.mxz.init;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.Attribute;
import cn.mxz.base.service.AbstractService;
import cn.mxz.city.AttributeRecorder;
import cn.mxz.city.City;
import cn.mxz.events.Events;
import cn.mxz.handler.UserService;
import cn.mxz.levelupreward.JiaUserLevelUpBuilder;
import cn.mxz.protocols.user.UserP.UserLevelUpPro;
import cn.mxz.protocols.user.UserP.UserPro;
import cn.mxz.user.builder.UserBuilder;
import cn.mxz.util.debuger.Debuger;

@Component("userService")
@Scope("prototype")

public class UserServiceImpl extends AbstractService implements UserService {

	@Override
	public UserPro getData() {

		UserBuilder bd = new UserBuilder();

		UserPro build = bd.build(getCity());

		Events.getInstance().dispatch(new UserGetDataEvent(getCity()));
		
		return build;
	}

	@Override
	public int getFightingCapacity() {

		return 0;
	}

	@Override
	public UserLevelUpPro getUserLevelUpData() {

		City city = getCity();

		AttributeRecorder r = city.getAttributeRecorder();
		Attribute l = r.getLastAttribute();
		if(l == null || r.getLastLevel() != city.getLevel() - 1) {

			Debuger.debug("UserServiceImpl.getUserLevelUpData() 虚假的上一等级属性");
			return new JiaUserLevelUpBuilder().build(city);
		}
		Debuger.debug("UserServiceImpl.getUserLevelUpData() 真实的上一等级属性");

		return new JiaUserLevelUpBuilder().build(city, l);
	}

}
