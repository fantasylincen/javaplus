package cn.mxz.battle;

import java.util.List;

import cn.mxz.fighter.Fighter;

import com.google.common.collect.Lists;

/**
 *
 * 阵容
 * @author 林岑
 *
 * @param <T>
 */
public abstract class AbstractCamp<T extends Fighter> implements Camp<T> {

	@Override
	public Fighter get(int position) {
		List<T> lives = getLives();
		for (T t : lives) {
			if(getPosition(t) == position) {
				return t;
			}
		}
		return null;
	}

	private final List<T> getLives() {
		final List<T> all = Lists.newArrayList();
		List<T> fightersFront = getFighters();
		for (T f : fightersFront) {
			if(!f.isDeath()) {
				all.add(f);
			}
		}
		return all;
	}
}
