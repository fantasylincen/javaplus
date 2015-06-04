package cn.mxz.fighter;

import java.io.Serializable;

/**
 *
 * 该加成适配器中的任何值都不会为负数
 *
 * @author 林岑
 * @since 2012年12月29日 13:58:07
 *
 */
public class AttributeEmpty implements Serializable, AttributeSetable, AttributeAddable {
	private static final long serialVersionUID = -8432962575730499173L;
	private int hp;
	private int attack;
	private int mAttack;
	private int defend;
	private int mDefend;
	private int speed;
	private int crit;
	private int dodge;
	private int block;
	private int rCrit;
	private int hit;
	private int rBlock;
	private int critAddition;
	private int magic;

	@Override
	public int getHp() {
		return hp;
	}

	@Override
	public int getAttack() {
		return attack;
	}

	@Override
	public int getMAttack() {
		return mAttack;
	}

	@Override
	public int getDefend() {
		return defend;
	}

	@Override
	public int getMDefend() {
		return mDefend;
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	@Override
	public int getCrit() {
		return crit;
	}

	@Override
	public int getDodge() {
		return dodge;
	}

	@Override
	public int getBlock() {
		return block;
	}

	@Override
	public int getRCrit() {
		return rCrit;
	}

	@Override
	public int getHit() {
		return hit;
	}

	@Override
	public int getRBlock() {
		return rBlock;
	}

	@Override
	public int getCritAddition() {
		return critAddition;
	}

	@Override
	public void setHp(int hp) {
		this.hp = hp;
	}

	@Override
	public void setAttack(int attack) {
		this.attack = attack;
	}

	@Override
	public void setMAttack(int mAttack) {
		this.mAttack = mAttack;
	}

	@Override
	public void setDefend(int defend) {
		this.defend = defend;
	}

	@Override
	public void setMDefend(int mDefend) {
		this.mDefend = mDefend;
	}

	@Override
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public void setCrit(int crit) {
		this.crit = crit;
	}

	@Override
	public void setDodge(int dodge) {
		this.dodge = dodge;
	}

	@Override
	public void setBlock(int block) {
		this.block = block;
	}

	@Override
	public void setRCrit(int rCrit) {
		this.rCrit = rCrit;
	}

	@Override
	public void setHit(int hit) {
		this.hit = hit;
	}

	@Override
	public void setRBlock(int rBlock) {
		this.rBlock = rBlock;
	}

	@Override
	public void setCritAddition(int critAddition) {
		this.critAddition = critAddition;
	}

	@Override
	public void addAttack(int v) {
		attack += v;
	}

	@Override
	public void addDefend(int v) {
		defend += v;
	}

	@Override
	public void addHp(int v) {
		hp += v;
	}

	@Override
	public void addBlock(int v) {
		block += v;
	}

	@Override
	public void addDodge(int v) {
		dodge += v;
	}

	@Override
	public void addCrit(int v) {
		crit += v;
	}

	@Override
	public void addMDefend(int v) {
		mDefend += v;
	}

	@Override
	public void addMAttack(int v) {
		mAttack += v;
	}

	@Override
	public void addSpeed(int v) {
		speed += v;
	}

	@Override
	public void addRBlock(int v) {
		rBlock += v;
	}

	@Override
	public void addHit(int v) {
		hit += v;
	}

	@Override
	public void addRCrit(int v) {
		rCrit += v;
	}

	@Override
	public void addCritAddition(int v) {
		critAddition += v;
	}

	@Override
	public int getMagic() {
		return magic;
	}

	@Override
	public void addMagic(int value) {
		magic += value;
	}

	@Override
	public void setMagic(int v) {
		magic = v;
	}

	@Override
	public String toString() {
		return "hp:" + hp + ", att:" + attack + ", mAtt:" + mAttack + ", def:" + defend + ", mDef:" + mDefend + ", spd:" + speed + ", crt:" + crit + ", dge:" + dodge + ", blk:" + block + ", rCrt:" + rCrit + ", hit:" + hit + ", rBlk:" + rBlock + ", critAdd:" + critAddition + ", mgc:" + magic;
	}



}
