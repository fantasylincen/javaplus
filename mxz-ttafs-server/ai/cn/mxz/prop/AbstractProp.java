package cn.mxz.prop;



public abstract class AbstractProp implements Prop {

//	@Override
//	public boolean giftable() {
//		return PropTempletFactory.get(getTypeId()).getGiveable() == 1;
//	}

	@Override
	public final int getPropType() {

//		装备-15，消耗品-14，宝石-12，材料-13

		return PropTempletFactory.getPropType(getTypeId());
	}
}
