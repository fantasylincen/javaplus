package cn.mxz.formation;

import cn.mxz.Attribute;
import cn.mxz.FormationTemplet;
import cn.mxz.FormationTempletConfig;
import cn.mxz.fighter.AttributeAddable;
import cn.mxz.fighter.AttributeEmpty;
import cn.mxz.user.team.god.Hero;
import define.D;

/**
 * 计算阵型的不同位置的加成
 * @author Administrator
 *
 */
enum TacticalAddtionWithPosition {

	FRONT {
		@Override
		Attribute getAddtion(Hero hero, FormationTemplet templet, int level ) {
			AttributeAddable attr = new AttributeEmpty();
			AdditionType at = AdditionType.fromNum( templet.getFrontFirst() );
			Attribute base = hero.getAdditions().getBase2();
//			at.add(attr, templet.getFrontFirstFixed(), base );2014年1月25日 14:07:37 林岑屏蔽, 加错了, 是固定值

			at.add(attr, (int) calc( templet.getFrontFirstFixed(),level ) );
			at.add(attr, calc( templet.getFrontFirstPar(),level ), base );	
			
			return attr;
		}
	},
	MIDDLE {
		@Override
		Attribute getAddtion(Hero hero, FormationTemplet templet, int level ) {
			AttributeAddable attr = new AttributeEmpty();
			AdditionType at = AdditionType.fromNum( templet.getFrontMiddle() );
			Attribute base = hero.getAdditions().getBase2();
//			at.add(attr, templet.getFrontMiddleFixed(), base );2014年1月25日 14:07:37 林岑屏蔽, 加错了, 是固定值

			at.add(attr, (int) calc( templet.getFrontMiddleFixed(),level ) );
			at.add(attr, calc( templet.getFrontMiddlePar(),level ), base );	

			return attr;
		}
	},
	TAIL {
		@Override
		Attribute getAddtion(Hero hero, FormationTemplet templet, int level ) {
			AttributeAddable attr = new AttributeEmpty();
			AdditionType at = AdditionType.fromNum( templet.getFrontTail() );
			Attribute base = hero.getAdditions().getBase2();
//			at.add(attr, templet.getFrontTailFixed(), base ); 2014年1月25日 14:07:37 林岑屏蔽, 加错了, 是固定值

			//System.out.println( hero + "阵法属性加成xxxxxxxxxxx " + calc( templet.getFrontTailFixed(),level ) + " ," + calc( templet.getFrontTailPar(),level ));
			at.add(attr, (int) calc( templet.getFrontTailFixed(),level ) );
			at.add(attr, calc( templet.getFrontTailPar(),level ), base );

			
			return attr;
		}
	};
	/**
	 * 基础数值*（1+（等级-1）*0.2）
	 * @param baseValue
	 * @param level
	 * @return
	 */
	private static float calc( float baseValue, int level){
		float ret = (float) (baseValue * ( 1 + (level - 1 ) * D.TACTICAL_LEVEL_ADDTION ));
		 ret = (float)(Math.round(ret*100))/100;
//		int x = (int) (ret * 100f);
//		ret = x / 100f;
		return ret;
	}
	public static void main(String[] args) {
		System.out.println( calc(197,24));
	}
	
/**
	 * 计算阵法加成，用于前端显示
	 * @param templet
	 * @return
	 */
	public static float[] showAddtion ( int templetId, int level ){
		FormationTemplet templet = FormationTempletConfig.get( templetId );

		float addtion[] = new float[3];
		float baseValue =  templet.getFrontFirstFixed() == 0 ? templet.getFrontFirstPar():templet.getFrontFirstFixed();
		addtion[0] = calc( baseValue, level );

		baseValue =  templet.getFrontMiddleFixed() == 0 ? templet.getFrontMiddlePar():templet.getFrontMiddleFixed();
		addtion[1] = calc( baseValue, level );

		baseValue =  templet.getFrontTailFixed() == 0 ? templet.getFrontTailPar():templet.getFrontTailFixed();
		addtion[2] = calc( baseValue, level );

		return addtion;
	}

	/**
	 * 通过位置获取所在位置类型：前、中、后
	 * @param position
	 * @return
	 */
	public static TacticalAddtionWithPosition parse( int position ){
		switch( position /3 ){
		case 0: return FRONT;
		case 1: return MIDDLE;
		default: return TAIL;
		}
	}

	abstract Attribute getAddtion( Hero hero, FormationTemplet templet, int level );

}
