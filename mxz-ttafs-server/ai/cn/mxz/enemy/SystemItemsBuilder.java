package cn.mxz.enemy;

import cn.mxz.protocols.user.enemy.EnemyP.SystemItemsPro;

public class SystemItemsBuilder {

	public SystemItemsPro buildDev() {
		SystemItemsPro.Builder b = SystemItemsPro.newBuilder();
		b.addItems(new SystemItemBuilder().buildDev());
		return b.build();
	}

}
