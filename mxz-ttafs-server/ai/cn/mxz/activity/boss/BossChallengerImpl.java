package cn.mxz.activity.boss;

import java.util.Collections;
import java.util.List;

import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;

import com.lemon.commons.socket.ISocket;

import db.dao.impl.BossDamageDataDao;
import db.dao.impl.DaoFactory;
import db.domain.BossDamageData;


public class BossChallengerImpl implements BossChallenger {

	private String	userId;

	/**
	 * @param userId
	 */
	BossChallengerImpl(String userId) {

		this.userId = userId;
	}

	@Override
	public int getDamage(Boss boss) {

		BossDamageDataDao DAO = DaoFactory.getBossDamageDataDao();

		BossDamageData damage = DAO.get(boss.getId(), userId);

		return damage != null ? damage.getDamage() : 0;
	}

	@Override
	public boolean isKiller(Boss boss) {

		BossChallenger c = boss.getKiller();

		if (c == null) {

			return false;
		}

		return c.getId().equals(userId);
	}

	@Override
	public int getRank(Boss boss) {

		List<BossChallenger> cs = boss.getBossChallengers();

		Collections.sort(cs, new DamageComparetor(boss));

		int indexOf = cs.indexOf(this);

		if (indexOf == -1) {

			return cs.size();
		}

		return indexOf + 1;
	}

	@Override
	public City getCity() {

		City city = WorldFactory.getWorld().get(userId);

		if (city == null) {

			throw new NullPointerException("userId = " + userId);
		}

		return city;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BossChallengerImpl other = (BossChallengerImpl) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String getId() {
		return userId;
	}

	@Override
	public ISocket getSocket() {
		return getCity().getSocket();
	}

	@Override
	public int getLevel() {
		return getCity().getPlayer().getLevel();
	}
}
