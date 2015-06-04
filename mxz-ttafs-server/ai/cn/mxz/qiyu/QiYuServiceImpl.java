package cn.mxz.qiyu;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.base.exception.IllegalOperationException;
import cn.mxz.base.service.AbstractService;
import cn.mxz.handler.QiYuService;

@Component("qiYuService")
@Scope("prototype")
public class QiYuServiceImpl extends AbstractService implements QiYuService {

	@Override
	public String getButtons() {
//		throw new IllegalOperationException("xxxx");
		QiYuButtons message = getCity().getQiyuManager().getButtons();
//		throw new IllegalOperationException("神魔已死亡，下次加油吧！");
		return toJson(message);
		
	}

}
