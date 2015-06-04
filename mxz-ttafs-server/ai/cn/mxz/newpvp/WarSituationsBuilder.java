package cn.mxz.newpvp;

import java.util.List;

import mongo.gen.MongoGen.PvpWarSituationDto;
import cn.mxz.city.City;
import cn.mxz.protocols.pvp.PvpP.VideoPro;
import cn.mxz.protocols.pvp.PvpP.WarSituationsPro;

class WarSituationsBuilder {

	private City city;

	public WarSituationsBuilder(City city) {
		this.city = city;
	}

	// 瞿伟用: 恩怨PVP标签
	public WarSituationsPro build(List<PvpWarSituationDto> page2) {

		WarSituationsPro.Builder b = WarSituationsPro.newBuilder();

		for (PvpWarSituationDto ws : page2) {

			VideoPro build = new VideoBuilder(city).build(ws);
			b.addVideos(build);
		}

		return b.build();
	}

	// 春生用: 斗法详情界面
	public WarSituationsPro buildCs(List<PvpWarSituationDto> page2) {

		WarSituationsPro.Builder b = WarSituationsPro.newBuilder();

		for (PvpWarSituationDto ws : page2) {

			VideoPro buildCs = new VideoBuilder(city).buildCs(ws);
			b.addVideos(buildCs);
		}

		return b.build();
	}

}
