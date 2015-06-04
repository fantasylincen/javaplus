package cn.mxz.fighter;

import cn.mxz.Attribute;

public interface AttributeAddable extends Attribute{

	void addAttack(int v);

	void addDefend(int v);

	void addHp(int v);

	void addBlock(int v);

	void addDodge(int v);

	void addCrit(int v);

	void addMDefend(int v);

	void addMAttack(int v);

	void addSpeed(int v);

	void addRBlock(int v);

	void addHit(int v);

	void addRCrit(int v);

	void addCritAddition(int v);

	void addMagic(int value);
}