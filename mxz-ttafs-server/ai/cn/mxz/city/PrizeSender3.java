package cn.mxz.city;

import java.util.ArrayList;
import java.util.List;

import cn.mxz.bossbattle.Prize;
import cn.mxz.mission.old.PrizeImpl;
import cn.mxz.qiyu.QiYuButton;
import cn.mxz.qiyu.QiYuButtons;
import cn.mxz.qiyu.QiYuManager;

import com.google.common.collect.Lists;

/**
 * 这个奖励发放器用户发放格式为: 110023,1:14 的奖励, 表示 活动14开启时, 才发放110023
 * 
 * @author 林岑
 * 
 */
public class PrizeSender3 implements UserPrizeSender {

	private City city;

	public PrizeSender3(City city) {
		this.city = city;
	}

	@Override
	public void send(String prize) {
		List<Prize> all = buildPrizes(prize);
		for (Prize p : all) {
			p.award(city);
		}
	}

	@Override
	public List<Prize> buildPrizes(String prize) {
		ArrayList<Prize> ls = Lists.newArrayList();

		String[] ps = prize.split("\\|");
		for (String p : ps) {
			String[] split = p.split(":");
			int activityId = new Integer(split[1]);

			if (isOpen(activityId)) {

				String[] pp = split[0].split(",");
				int prizeId = new Integer(pp[0]);
				int count = new Integer(pp[1]);

				ls.add(new PrizeImpl(prizeId, count));
			}
		}
		return ls;
	}

	/**
	 * 判断某个活动是否开启
	 * 
	 * @param activityId
	 * @return
	 */
	private boolean isOpen(int activityId) {
		QiYuManager qm = city.getQiyuManager();
		QiYuButtons bs = qm.getButtons();
		for (QiYuButton b : bs.getButtons()) {
			int id = b.getId();
			if (activityId == id) {
				return b.isOpen();
			}
		}
		return false;
	}

}
