package cn.mxz.util.checker;

import message.S;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.util.sencitive.SencitiveConfig;

/**
 * 敏感词检查器， 用于某个字符串中是否包含敏感词
 * @param text	某段字符串
 * @return
 */
public class CheckerSencitive {

	public void check(String text) {
		if(!SencitiveConfig.canUse(text)) {
			throw new OperationFaildException(S.S10001);
		}
	}

}
