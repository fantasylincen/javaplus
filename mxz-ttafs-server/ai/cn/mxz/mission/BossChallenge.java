package cn.mxz.mission;

import message.S;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.util.counter.CounterKey;
import define.D;

public class BossChallenge {
	private final City			user;

	public BossChallenge( City user ) {
		this.user = user;
	}

	void challenge( int missionId, boolean isMain ){

	}

	private void check( int missionId, boolean isMain ){
		int count = user.getUserCounter().get( CounterKey.BOSS_CHALLENGE, missionId );
		if( count >= D.FREE_BOSS_CHALLENGE ){
			throw new OperationFaildException(S.S10166);
		}

		user.getPlayer().reduce(PlayerProperty.PHYSICAL, 1 );




	}
	public static void main(String[] args) {

	}

}
