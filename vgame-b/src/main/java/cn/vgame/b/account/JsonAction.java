package cn.vgame.b.account;




import cn.vgame.b.result.ErrorResult;
import cn.vgame.share.AbstractJsonAction;


@SuppressWarnings("serial")
public abstract class JsonAction extends AbstractJsonAction {

	protected ErrorResult buildErrorResult(String simpleName, String message) {
		return new ErrorResult(10012, simpleName, message);
	}

}
