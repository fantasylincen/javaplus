package cn.mxz.yoxi;

import cn.javaplus.security.DES;
import cn.javaplus.util.Util;
import cn.mxz.city.City;
import cn.mxz.util.debuger.Debuger;

public class YoXiTransformImpl implements YoXiTransform {


	@Override
	public void setUser(City user) {
	}

	@Override
	public YoXi yoXi(String yoXi, String pwd) {
		String checkPwd = checkPwd(pwd);
		if (checkPwd != null) {
			return new YoXiImpl(checkPwd);
		}

		yoXi = DES.decrypt(yoXi);
		
		if(yoXi == null) {
			return new YoXiImpl("derr");
		}
		
		YoXiR yoXiR = new YoXiR();
		yoXiR.run(yoXi);
		yoXiR.waitItRunOver();
		String result = yoXiR.getResult();
		Debuger.debug("YoXiTransformImpl.yoXi()    " + result);
		return new YoXiImpl(result);
	}

	private String checkPwd(String pwd) {
		int min = Util.Time.getCurrentMin();
		for (int i = -2; i < 3; i++) {
			String pwd2 = pwd(min + i);
			if (pwd2.equals(pwd)) {
				return null;
			}
		}
		return "perr ! time of the server error ?";
	}

	private String pwd(int min) {
		String text = min + " serverId = ?";
		
		for (int i = 0; i < 5; i++) {
			text = Util.Secure.md5(text + text);
		}
		
		return text;
	}

}
