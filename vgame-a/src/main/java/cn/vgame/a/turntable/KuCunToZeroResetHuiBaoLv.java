package cn.vgame.a.turntable;

import cn.javaplus.events.Listener;
import cn.vgame.a.turntable.Turntable.Controller;

/**
 * 系统库存变0 的时候, 重置回报率档位
 */
public class KuCunToZeroResetHuiBaoLv implements Listener<KuCunToZeroEvent> {

	@Override
	public void onEvent(KuCunToZeroEvent e) {
		Controller c = Turntable.getInstance().getController();
		c.setDangWeiToNormal();
	}

}
