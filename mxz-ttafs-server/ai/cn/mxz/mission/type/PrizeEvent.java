package cn.mxz.mission.type;

import java.util.List;

import cn.javaplus.random.IntegerFetcher;
import cn.javaplus.util.Util.Collection;
import cn.javaplus.util.Util.Random;
import cn.mxz.BoxOddsTemplet;
import cn.mxz.BoxOddsTempletConfig;
import cn.mxz.ConsumableTemplet;
import cn.mxz.ConsumableTempletConfig;
import cn.mxz.city.City;
import cn.mxz.mission.old.MapBox;
import cn.mxz.mission.old.MapBoxImpl;
import cn.mxz.mission.old.builder.BoxDataBuilder;
import cn.mxz.protocols.user.mission.BoxP.BoxPro;

import com.google.common.collect.Lists;

import define.D;

public class PrizeEvent implements IEvent {

	/**
	 * 宝箱id
	 */
	private final int prizeId;

	public int getPrizeId() {
		return prizeId;
	}

	public PrizeEvent(int missionId) {
		List<BoxOddsTemplet> list = Lists.newArrayList();
		for (BoxOddsTemplet t : BoxOddsTempletConfig.getAll()) {
			if (showAble(t, missionId)) {
				list.add(t);
			}
		}

		int[] r = Collection.getArrayByOneFields(
				new IntegerFetcher<BoxOddsTemplet>() {

					@Override
					public Integer get(BoxOddsTemplet cup) {
						return cup.getBoxWeight();
					}
				}, list);

		int index = Random.getRandomIndex(r);
		prizeId = list.get(index).getBoxId();
	}

	public PrizeEvent(String arg) {
		prizeId = Integer.parseInt(arg);
	}

	/**
	 * 宝箱是否可以出现在地图上
	 *
	 * @param temp2
	 * @param missionId
	 * @return
	 */
	private boolean showAble(BoxOddsTemplet temp2, int missionId) {

		String mapId2 = temp2.getMapId().trim();

		String[] split = mapId2.split("-");

		try {

			int min = new Integer(split[0].trim());

			int max = new Integer(split[1].trim());

			return min <= missionId && missionId <= max;

		} catch (NumberFormatException e) {

			return true;
		}
	}

	@Override
	public Object run(City user) {

//		Debuger.debug("PrizeEvent.run() Prize 事件");
		if (prizeId == D.ZJBX_ID || prizeId == D.ZJBX_ID1) {//紫金宝箱是关卡结算的时候才开启，因此这里返回一个没有内容的对象
			BoxPro.Builder b = BoxPro.newBuilder();
			b.setPropType(0);
			return b.build();
		}

		final MapBox box = new MapBoxImpl(prizeId, user);

		final ConsumableTemplet c = ConsumableTempletConfig.get(box.getId());

		if (c.getIsOpen() == 1) { // 如果直接开启

			box.open(user.getPlayer());

			return new BoxDataBuilder().buildBoxContent(user.getPlayer(), box);

		} else {

			user.getBagAuto().addProp(box.getId(), 1);

			return new BoxDataBuilder().buildEmptyBoxContent(user.getPlayer(),
					box.getId());
		}
	}

	@Override
	public int getBrief() {
		return prizeId;
	}

	@Override
	public EventType getType() {
		return EventType.PRIZE;
	}

	@Override
	public String getMissionArg() {
		return prizeId + "";
	}

}
