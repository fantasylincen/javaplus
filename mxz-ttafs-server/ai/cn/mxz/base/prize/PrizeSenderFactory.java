package cn.mxz.base.prize;

import java.util.ArrayList;
import java.util.List;

import cn.mxz.ConsumableTemplet;
import cn.mxz.PrizeInExcel;
import cn.mxz.PropTemplet;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;
import cn.mxz.prop.PropTempletFactory;
import define.D;

/**
 * 奖励工厂
 * 
 * @author 林岑
 * 
 */
public class PrizeSenderFactory {

	/**
	 * 
	 * 使用某个道具后的奖励
	 * 
	 * @param typeId
	 *            道具ID
	 * @param fighterId
	 * @param city
	 * @return
	 */
	public static List<AwardAble> createUserPropPrize(Integer typeId,
			int fighterId, City city) {

		List<AwardAble> ls = new ArrayList<AwardAble>();

		if (typeId == 130001) {

			ls.add(new HuaQiCao());

		} else if (typeId == 130002) {

			ls.add(new HuiQiDan());

		} else if (typeId == 130003) {

			ls.add(new ZhiXieCao(fighterId));

		} else if (typeId == D.ID_HUAN_HUN_DAN) {

			ls.add(new HuanHunDan(fighterId));

		} else if (typeId == D.PVP_FUWEN_ID) {

			ls.add(new PvpFuWen());

		} else if (typeId == 130005) {

			ls.add(new BaiXingCao());

		} else if (typeId == 130035) {

			ls.add(new ShenXingDan());

		} else if (typeId == 140029) {
			
			ls.addAll(new JinBaoXiang(city).open());

		} else if (typeId == D.ZJBX_ID) {

			ls.addAll(new ZiJinBaoXiang(city).open());
			
		} else if (typeId == 130048) {
			
			ls.add(new YueKa());
		} else {

			PropTemplet pt = PropTempletFactory.get(typeId);

			if (pt instanceof PrizeInExcel) {

				PrizeInExcel temp = (PrizeInExcel) pt;

				if (temp instanceof ConsumableTemplet) {

					ConsumableTemplet t = (ConsumableTemplet) temp;

					List<Prize> awards = new PrizeInExcelRandomImpl(t, city)
							.getAwards();
					
					ls.addAll(awards);

				} else {

					final PrizeSender sender = PrizeSenderFactory
							.getPrizeSender();

					List<Prize> bp = sender.buildPrizes(city.getPlayer(),
							temp.getAwards());

					ls.addAll(bp);
				}
			}
		}

		return ls;
	}

	/**
	 * 奖励发放器
	 * 
	 * @return
	 */
	public static PrizeSender getPrizeSender() {

		return new PrizeSenderImpl();
	}

	/**
	 * 奖励发放器 (新规则)
	 * 
	 * @return
	 */
	public static PrizeSender getPrizeSender2() {

		return new PrizeSenderImpl2();
	}

}
