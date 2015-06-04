package cn.mxz.mission.old.builder;

import cn.mxz.bossbattle.Prize;
import cn.mxz.mission.old.MapBox;
import cn.mxz.mission.old.PrizeImpl.PrizeBuilder;
import cn.mxz.protocols.user.mission.BoxP.BoxPro;
import cn.mxz.protocols.user.mission.BoxP.BoxPro.PrizePro;
import cn.mxz.protocols.user.mission.MissionP.BoxDataPro;
import cn.mxz.user.Player;

public class BoxDataBuilder {

	private BoxDataPro buildBox(MapBox b) {

		BoxDataPro.Builder bd = BoxDataPro.newBuilder();

		bd.setId(b.getId());

		bd.setPath(b.getPath());

		bd.setIndex(b.getIndex());

		// Debuger.debug("BoxDataBuilder.buildBox() 宝箱:" + b.getLocation());

		return bd.build();
	}

	// public Iterable<? extends BoxDataPro> build(List<MapBoxImpl> boxes) {
	//
	// List<BoxDataPro> all = Lists.newArrayList();
	//
	// for (MapBox b : boxes) {
	//
	// all.add(buildBox(b));
	// }
	//
	// return all;
	// }

	/**
	 * 构建宝箱内物品信息
	 *
	 * @param player
	 * @param box
	 * @return
	 */
	public BoxPro buildBoxContent(Player player, MapBox box) {

		BoxPro.Builder bd = BoxPro.newBuilder();

		bd.setPropType(0);

		for (Prize goods : box.getAll()) {

			PrizePro build = new PrizeBuilder().build(player, goods);

			bd.addPrizes(build);
		}

		return bd.build();
	}

	public BoxPro buildEmptyBoxContent(Player player, int id) {

		BoxPro.Builder b = BoxPro.newBuilder();

		BoxPro.PrizePro.Builder b2 = PrizePro.newBuilder();

		b2.setId(id);

		b2.setCount(1);
		
		b.setPropType(0);

		b.addPrizes(b2.build());

		return b.build();
	}

}
