package cn.mxz.qiyu;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import cn.javaplus.log.Debuger;
import cn.mxz.ActivityTemplet;
import cn.mxz.ActivityTempletConfig;
import cn.mxz.city.City;
import cn.mxz.hhdlb.HhdlbButton;
import cn.mxz.yunyou.YunYouPlayer;
import cn.mxz.yunyou.YunyouData;

import com.google.common.collect.Lists;

import define.D;

//1	ACTIVIT_001	魔神入侵
//2	ACTIVIT_002	保护妲己
//3	ACTIVIT_003	云游仙人
//4	ACTIVIT_004	招贤榜
//5	ACTIVIT_005	摇钱树
//6	ACTIVIT_006	密室探宝
//7	ACTIVIT_007	等级礼包
//8	ACTIVIT_008	每日签到
//9	ACTIVIT_009	BOSSPVE
//10	ACTIVIT_010	闯阵活动
//11	ACTIVIT_011	上香

public class QiYuButtonsImpl implements QiYuButtons {

	private City city;

	public QiYuButtonsImpl(City city) {
		this.city = city;
	}

	@Override
	public List<QiYuButton> getButtons() {

		List<QiYuButton> ls = Lists.newArrayList();
		ls.add(new ShenMoButton(city));
		ls.add(new DaJiButton(city));
		addYunYouButtons(ls);
		ls.add(new YaoQianShuButton(city));
		ls.add(new DengJiLiBaoButton(city));
		ls.add(new MeiRiQianDaoButton(city));
		BossButton e = new BossButton(city);
		ls.add(e);
		ls.add(new ChuangZhenButton(city));
		ls.add(new ShangXiangButton(city));

		// if (D.LANGUAGE == 1) {
		ls.add(new MonthCardButton(city));
		// }

		ls.add(new ZhaoXianBangButton(city));
		ls.add(new XianShiHeiShiButton(city));
		ls.add(new NvwaButton(city));
		ls.add(new FeedBackButton(city));
		ls.add(new ZbsrButton(city));
		ls.add(new YQButton(city));
		ls.add(new HhdlbButton(city));

		if (D.LANGUAGE == 1) {

			ShouChongYouLi button = new ShouChongYouLi(city);

			ls.add(button);

			if (!button.isOpen()) {
				ls.add(new MeiRiShouChong(city, button));
			}

		} else { // 台湾版本

			ShouChongYouLi button = new ShouChongYouLi(city);

			if (button.isOpen()) {
				ls.add(button);
			} else {
				ls.add(new MeiRiShouChong(city, button));
			}
		}

		ls.add(new ChengZhangJiHua(city));

		Comparator<QiYuButton> c = new Comparator<QiYuButton>() {

			@Override
			public int compare(QiYuButton o1, QiYuButton o2) {
				ActivityTemplet t1 = ActivityTempletConfig.get(o1.getId());
				ActivityTemplet t2 = ActivityTempletConfig.get(o2.getId());
				if (t1 == null) {
					return 1;
				}
				if (t2 == null) {
					return -1;
				}
				return t1.getRank() - t2.getRank();
			}
		};

		Collections.sort(ls, c);

		if (D.LANGUAGE != 2) {
			Iterator<QiYuButton> it = ls.iterator();
			while (it.hasNext()) {
				QiYuButton b = (QiYuButton) it.next();
				if (!b.isOpen()) {
					it.remove();
				}
			}
		} else { // 台湾
			int clientType = city.getPlayer().getClientType();
			Debuger.debug("QiYuButtonsImpl.getButtons()" + clientType);
			Iterator<QiYuButton> it = ls.iterator();
			while (it.hasNext()) {
				QiYuButton b = (QiYuButton) it.next();
				int id = b.getId();
				ActivityTemplet temp = ActivityTempletConfig.get(id);
				int show = temp.getShow();
				if (show != 1) {
					it.remove();
				}
				// 1 只有安卓显示
				if (clientType != 2 && temp.getOpen() == 1) {
					it.remove();
				}
			}
		}

		return ls;
	}

	/**
	 * 云游先人按钮
	 * 
	 * @param ls
	 */
	private void addYunYouButtons(List<QiYuButton> ls) {
		YunYouPlayer player = city.getYunYouPlayer();
		Collection<YunyouData> list = player.getList();
		for (YunyouData data : list) {
			ls.add(new YunYouButton(city, data));
		}
	}
}
