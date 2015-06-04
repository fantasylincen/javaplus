package cn.javaplus.mxzrobot.actions;

import cn.javaplus.util.Util;

public class AnwerMyName extends ActionBase {

	public String execute(Args args) {
		int a = Util.Random.get(0, 7);
		if(a == 0) {
			return "不告诉你";
		}
		if(a == 1) {
			return "我忘了";
		}
		if(a == 2) {
			return "小小泡泡糖";
		}
		if(a == 3) {
			return "你大爷";
		}
		if(a == 4) {
			return "叫我大哥";
		}
		if(a == 5) {
			return "漫想族超级机器人";
		}
		if(a == 6) {
			return "猜猜猜";
		}
		return "你猜";
	}

}
