package cn.mxz.enemy;

import java.util.List;

import cn.mxz.protocols.user.enemy.EnemyP.EnemyPro;
import cn.mxz.protocols.user.enemy.EnemyP.EnemysPro;

class EnemysBuilder {

	public EnemysPro build(List<Enemy> ls) {
		EnemysPro.Builder b = EnemysPro.newBuilder();
		for (Enemy enemy : ls) {

			EnemyPro build = new EnemyBuilder().build(enemy);

			if (build != null) {

				b.addEnemys(build);
			}
		}
		return b.build();
	}

}
