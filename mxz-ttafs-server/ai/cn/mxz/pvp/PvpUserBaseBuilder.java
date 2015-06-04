package cn.mxz.pvp;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import cn.mxz.fighter.CacheFactory;
import cn.mxz.protocols.user.UserBaseP.UserBasePro;
import cn.mxz.protocols.user.UserBaseP.UserBasePro.Builder;
import cn.mxz.user.Player;
import cn.mxz.user.builder.UserBaseBuilder;


public class PvpUserBaseBuilder {

	public UserBasePro build(Player player) {

		//pvp 玩家
		Cache cache = CacheFactory.getCache("pvp.userCapacity", 300);

		String elementKey = player.getId();

		Element element = cache.get(elementKey);

		if(element == null) {


			element = new Element(elementKey, player.getShenJia());

			cache.put(element);
		}


		Builder b = new UserBaseBuilder().buildWithOutFightingCapCity(player);

		b.setFightingCapacity((Integer) element.getObjectValue());

		return b.build();
	}

}
