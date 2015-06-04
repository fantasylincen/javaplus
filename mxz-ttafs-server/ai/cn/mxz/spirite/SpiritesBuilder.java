package cn.mxz.spirite;

import java.util.List;

import cn.mxz.protocols.user.god.FighterP.SpiritesPro;

public class SpiritesBuilder {

	
	public SpiritesPro build(SpiriteManager manager) {
		SpiritesPro.Builder b = SpiritesPro.newBuilder();
		List<Spirite> ls = manager.getSpirites();
		for (Spirite spirite : ls) {
			b.addSprites(new SpiriteBuilder().build(spirite));
		}
		return b.build();
	}

	public SpiritesPro build(List<Spirite> subList) {
		SpiritesPro.Builder b = SpiritesPro.newBuilder();
		for (Spirite spirite : subList) {
			b.addSprites(new SpiriteBuilder().build(spirite));
		}
		return b.build();
	}


}
