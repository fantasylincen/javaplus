package cn.mxz.shangxiang;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.base.service.AbstractService;
import cn.mxz.handler.UserShangxiangService;
import cn.mxz.protocols.user.UserShangXiangP.TodayIsGetPro;

@Component("userShangxiangService")
@Scope("prototype")
public class UserShangxiangServiceImpl extends AbstractService implements UserShangxiangService {

	/**
	 * 获得上香状态
	 */
	@Override
	public TodayIsGetPro getSX() {
		return new TodayIsGetBuilder().build(getCity().getShangXiangPlayer());
	}

	/**
	 * 上香
	 */
	@Override
	public void sendSX() {
		getCity().getShangXiangPlayer().receive();
	}

}
