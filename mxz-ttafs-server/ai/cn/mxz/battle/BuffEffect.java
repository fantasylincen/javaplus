package cn.mxz.battle;

public class BuffEffect {

	int round;
	int position;
	boolean isUnder;
	int bufferId;

	// 被Buff作用后的 气血
	int hp;
	// 被Buff作用后的 攻击
	int attack;
	// 被Buff作用后的 防御
	int defend;
	// 被Buff作用后的 法攻
	int mAttack;
	// 被Buff作用后的 法防
	int mDefend;
	// 被Buff作用后的 暴击
	int crit;
	// 被Buff作用后的 闪避
	int dodge;
	// 被Buff作用后的 速度
	int speed;

	public int getRound() {
		return round;
	}

	public int getPosition() {
		return position;
	}

	public boolean isUnder() {
		return isUnder;
	}

	public int getBufferId() {
		return bufferId;
	}

	public int getHp() {
		return hp;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefend() {
		return defend;
	}

	public int getmAttack() {
		return mAttack;
	}

	public int getmDefend() {
		return mDefend;
	}

	public int getCrit() {
		return crit;
	}

	public int getDodge() {
		return dodge;
	}

	public int getSpeed() {
		return speed;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setUnder(boolean isUnder) {
		this.isUnder = isUnder;
	}

	public void setBufferId(int bufferId) {
		this.bufferId = bufferId;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setDefend(int defend) {
		this.defend = defend;
	}

	public void setmAttack(int mAttack) {
		this.mAttack = mAttack;
	}

	public void setmDefend(int mDefend) {
		this.mDefend = mDefend;
	}

	public void setCrit(int crit) {
		this.crit = crit;
	}

	public void setDodge(int dodge) {
		this.dodge = dodge;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
