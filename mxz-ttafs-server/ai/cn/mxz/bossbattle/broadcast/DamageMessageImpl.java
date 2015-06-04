package cn.mxz.bossbattle.broadcast;

import java.util.Comparator;

import cn.mxz.bossbattle.IDamageMessage;


class DamageComparator implements Comparator<DamageMessageImpl>{

	@Override
	public int compare(DamageMessageImpl o1, DamageMessageImpl o2) {
		return o2.getDamage() - o1.getDamage();
	}
	
}

public class DamageMessageImpl implements IDamageMessage {

	public static final Comparator<DamageMessageImpl> damageComparator = new DamageComparator();
	/**
	 * 时间戳
	 */
	private final int						timeStamp;
	
	/**
	 * 最后一次的伤害
	 */
	private final int						damage;
	
	/**
	 * 昵称
	 */
	private final String					nickName;

	public DamageMessageImpl(String nickName, int damage) {
		this.nickName = nickName;
		this.damage = damage;
		timeStamp = (int) (System.currentTimeMillis() / 1000);
	}

	/* （非 Javadoc）
	 * @see cn.mxz.bossbattle.broadcast.IDamageMessage#getTimeStamp()
	 */
	public int getTimeStamp() {
		return timeStamp;
	}

	/* （非 Javadoc）
	 * @see cn.mxz.bossbattle.broadcast.IDamageMessage#getDamage()
	 */
	@Override
	public int getDamage() {
		return damage;
	}

	/* （非 Javadoc）
	 * @see cn.mxz.bossbattle.broadcast.IDamageMessage#getNickName()
	 */
	@Override
	public String getNickName() {
		return nickName;
	}

		
}
