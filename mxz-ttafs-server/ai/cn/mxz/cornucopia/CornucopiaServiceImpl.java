package cn.mxz.cornucopia;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.base.service.AbstractService;
import cn.mxz.handler.CornucopiaService;
import cn.mxz.protocols.cornucopia.CornucopiaP.CornucopiaPro;

@Component("cornucopiaService")
@Scope("prototype")

public class CornucopiaServiceImpl extends AbstractService implements CornucopiaService {

	@Override
	public CornucopiaPro getData() {
		Cornucopia c = CornucopiaFactory.getCornucopia(getId());
		return new CornucopiaBuilder().build(c);
	}

	@Override
	public void run() {
		Cornucopia c = CornucopiaFactory.getCornucopia(getId());
		c.run();

//		QiYuButtons message = getCity().getQiyuManager().getButtons();
//		MessageFactory.getQiYu().onButtonUpdate(getSocket(), toJson(message));

//		UserPro build = new UserBuilder().build(getCity());
//
//		MessageFactory.getUser().onUpdateUserList(getSocket(), build);
	}

}
