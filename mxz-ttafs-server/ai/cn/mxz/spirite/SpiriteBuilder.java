package cn.mxz.spirite;

import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.protocols.user.god.FighterP.SpiritePro;

class SpiriteBuilder {

	public SpiritePro build(Spirite spirite) {
		SpiritePro.Builder b = SpiritePro.newBuilder();
		b.setCount(spirite.getCount());
		b.setStep(spirite.getStep());
		b.setTypeId(spirite.getTypeId());
		b.setHasFighter(spirite.hasFighter());
		b.setCanLevelUp(spirite.canLevelUp());
		
		if (spirite.hasFighter()) {
			String uname = spirite.getDto().getUname();
			City city = CityFactory.getCity(uname);
			SpiriteManager sm = city.getSpiriteManager();

			int jinJieNeed = sm.getJinJieSpiriteNeed(spirite.getTypeId());

			b.setCountMax(jinJieNeed);
		} else {

			b.setCountMax(spirite.getCountMax());
		}

		// Debuger.debug("SpiriteBuilder.build() 魂魄ID:" + spirite.getTypeId() +
		// " 数量:" + spirite.getCount());
		return b.build();
	}

}
