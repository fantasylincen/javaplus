package cn.mxz.fighter;

import cn.mxz.Attribute;

public interface AttributeSetable extends Attribute {

	void setHp(int value);
	void setAttack(int value);
	void setDefend(int value);
	void setMAttack(int value);
	void setMDefend(int value);
	void setSpeed(int value);

	void setCrit(int value);
	void setRCrit(int value);
	void setCritAddition(int value);
	
	void setHit(int value);
	void setDodge(int value);

	void setBlock(int value);
	void setRBlock(int value);
	void setMagic(int v);

}