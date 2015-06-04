package cn.mxz.enemy;

import cn.mxz.city.City;
import cn.mxz.protocols.user.enemy.EnemyP.EnemyPro;
import cn.mxz.user.Player;
import cn.mxz.user.builder.UserBaseBuilder;

class EnemyBuilder {

	public EnemyPro build(Enemy enemy) {
		EnemyPro.Builder b = EnemyPro.newBuilder();
		City city = enemy.getCity();

		if(city == null) {
			return null;
		}
		Player player = city.getPlayer();
		
		b.setUser(new UserBaseBuilder().build(player));
		return b.build();
	}

}
