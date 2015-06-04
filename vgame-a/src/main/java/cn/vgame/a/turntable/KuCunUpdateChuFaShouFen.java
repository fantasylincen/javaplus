package cn.vgame.a.turntable;

import cn.javaplus.events.Listener;
import cn.vgame.a.turntable.Turntable.Controller;

/**
 * 系统库存大于警戒值, 强制触发收分程序N分钟
 */
public class KuCunUpdateChuFaShouFen implements Listener<KuCunUpdateEvent> {

	@Override
	public void onEvent(KuCunUpdateEvent e) {
		Controller c = Turntable.getInstance().getController();
		if (!c.isTunFening()) {
			long kuCun = c.getKuCun();
			
			//当系统库存小于该值的时候, 触发收分程序
			if (kuCun < c.getMaxKuCun()) {
				c.startTunFen();
			}
		}
	}

}
