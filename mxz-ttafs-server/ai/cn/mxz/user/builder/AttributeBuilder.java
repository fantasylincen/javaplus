package cn.mxz.user.builder;

import cn.mxz.Attribute;
import cn.mxz.protocols.user.AttributeP.AttributePro;

/**
 * 属性协议
 */
public class AttributeBuilder {

	public AttributePro build(Attribute a) {

		AttributePro.Builder b = AttributePro.newBuilder();

		b.setHp(a.getHp());
		b.setAttack(a.getAttack());
		b.setDefend(a.getDefend());
		b.setMAttack(a.getMAttack());
		b.setMDefend(a.getMDefend());
		b.setSpeed(a.getSpeed());

		b.setCrit(a.getCrit());
		b.setRCrit(a.getRCrit());
		b.setCritAddition(a.getCritAddition());

		b.setHit(a.getHit());
		b.setDodge(a.getDodge());

		b.setBlock(a.getBlock());
		b.setRBlock(a.getRBlock());
		b.setMagic(a.getMagic());

		return b.build();
	}

}
