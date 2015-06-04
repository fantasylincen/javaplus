package cn.mxz.fighter;

import cn.mxz.Addition2;
import cn.mxz.AdditionMultiplier;
import cn.mxz.Attribute;
import cn.mxz.base.TalentEmpty;
import cn.mxz.formation.AdditionType;

/**
 * 加成计算器
 *
 * @author 林岑
 */
public class AttributeCalculator {

	/**
	 * 两个加成相加
	 */
	public static AttributeSetable adding(Attribute... a) {
		final AttributeEmpty aa = new AttributeEmpty();

		for (Attribute attribute : a) {

			for (AdditionType at : AdditionType.values()) {
				at.set(aa, at.get(attribute) + at.get(aa));
			}
		}

		return aa;
	}



	public static AttributeSetable calcAddition(Attribute a1, Addition2 a2) {
		AdditionType at = AdditionType.fromNum( a2.getAdditionType() );
		AttributeEmpty a  = new AttributeEmpty();

		int value = a2.getAdditionValue();
		value += a2.getAdditionPercent() * at.get(a1);
//		int value = a1.get(at);
		at.set(a, value);

		return a;
//		int value = f.getFrontFirst();
//		if( value == 0 ){
//			value = f.getFrontFirstFixed() * hero.get
//		}
//		at.add( addtion, value);
	}

	public static AttributeSetable multiply(Attribute a, double v) {
		final AttributeEmpty aa = new AttributeEmpty();
		for (AdditionType at : AdditionType.values()) {
			at.set(aa, (int) (at.get(a) * v));
		}
		return aa;
	}

	static AttributeSetable multiply(Attribute a, AdditionMultiplier m) {

		final AttributeEmpty aa = new AttributeEmpty();

		for (AdditionType at : AdditionType.values()) {
			at.set(aa, (int) (at.get(a) * at.get(m)));
		}

		return aa;
	}


	public static AdditionMultiplier multiply(AdditionMultiplier m1, AdditionMultiplier m2) {

		final TalentEmpty aa = new TalentEmpty();
		for (AdditionType at : AdditionType.values()) {
			at.set(aa, (int) (at.get(m1) * at.get(m2)));
		}
		return aa;
	}


	public static AdditionMultiplier multiply(AdditionMultiplier m1, float v) {

		final TalentEmpty aa = new TalentEmpty();
		for (AdditionType at : AdditionType.values()) {
			at.set(aa, (int) (at.get(m1) * v));
		}
		return aa;
	}

	public static AttributeSetable copy(Attribute a1) {

		final AttributeEmpty aa = new AttributeEmpty();
		for (AdditionType at : AdditionType.values()) {
			at.set(aa, at.get(a1));
		}
		return aa;
	}


	public static AttributeSetable adding(Attribute a1, AdditionMultiplier a2) {

		final AttributeEmpty aa = new AttributeEmpty();
		for (AdditionType at : AdditionType.values()) {
			at.set(aa, (int) (at.get(a1) + at.get(a2)));
		}
		return aa;
	}

}
