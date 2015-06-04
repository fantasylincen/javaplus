package cn.mxz.listeners.xianshichongji;

import message.S;
import cn.javaplus.util.Util;
import cn.mxz.events.Listener;
import cn.mxz.events.PlayerHeroLevelUpEvent;
import define.D;

/**
 * 限时冲级活动
 * 
 * @author 林岑
 * 
 */
public class PlayerHeroLevelUpXianShiChongJi implements
		Listener<PlayerHeroLevelUpEvent> {

	@Override
	public void onEvent(PlayerHeroLevelUpEvent e) {
		if (D.LANGUAGE != 2) { // 只有台湾有这个功能
			return;
		}
		
		if (!Util.Time.isIn(D.XIAN_SHI_CHONG_JI_SHI_JIAN)) {
			return;
		}

		if (e.getLevel() == D.XIAN_SHI_CHONG_JI_DENG_JI) {
			e.getCity().getPrizeCenter().addPrize(3, D.XIAN_SHI_CHONG_JI_JIANG_LI, S.STR10344, S.STR10343);
		}
	}

}
