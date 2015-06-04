package cn.mxz.chuangzhen;

import java.util.List;

import cn.mxz.CopterDifficultyTemplet;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.battle.Camp;
import cn.mxz.fighter.Fighter;
import cn.mxz.mission.old.demon.Demon;

import com.google.common.collect.Lists;

public class ChuangZhenHeadsImpl implements ChuangZhenHeads {

	private List<ChuangZhenHead> list;
	private ChuangZhenPlayer player;

	public ChuangZhenHeadsImpl(ChuangZhenPlayer player) {
		this.player = player;
	}

	@Override
	public List<ChuangZhenHead> getHeads() {
		if (list == null) {
			reset();
		}
		return list;
	}

	public void reset() {
		list = Lists.newArrayList();
		list.add(new ChuangZhenHeadImpl(buildCamp(1, player), 1));
		list.add(new ChuangZhenHeadImpl(buildCamp(2, player), 2));
		list.add(new ChuangZhenHeadImpl(buildCamp(3, player), 3));
	}

	/**
	 * 1:力战 2:奋战 3:血战
	 * 
	 * @param type
	 * @param player
	 * @return
	 */
	private static Camp<Demon> buildCamp(int type, ChuangZhenPlayer player) {
		CopterDifficultyTemplet temp = ChuangZhenUtil.getTemplet(type, player);
		return new ChuangZhenCamp(player, temp);
	}

	public Camp<? extends Fighter> camp(int type) {
		List<ChuangZhenHead> hs = getHeads();
		for (ChuangZhenHead h : hs) {
			if (h.getX() == type) {
				return ((ChuangZhenHeadImpl) h).camp();
			}
		}
		throw new SureIllegalOperationException("无法找到对应头像");
	}
}