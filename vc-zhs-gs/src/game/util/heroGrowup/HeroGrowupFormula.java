package game.util.heroGrowup;

import config.grow.HeroGrowTempletCfg;
import game.fighter.Hero;
import game.growup.Colour;
import game.growup.Quality;

/**
 * 英雄成长公式
 * @author DXF
 */
public class HeroGrowupFormula {
	
	// 品质的对应实际数
	public static final byte[][] actualNumber = {
		
		//----------------每个颜色能加到多少  每个等级的实际数----------------
						
						{  0, 1 					},	// 绿
						
						{  2, 3, 4, 5, 6, 7, 8		},	// 蓝
						
						{  9, 10, 11, 12, 13		},	// 紫
						
								};
		
	// 品质等级限制
	public static final short[][] levelLimit = {
		
		//----------------下限--------------上限----------------
		
						{  0		, 		25		},	// 绿
						
						{  25		, 		30		},	// 绿+
						
						{  30		, 		50		},	// 蓝
						
						{  50		, 		60		},	// 蓝+
						
						{  60		, 		65		},	// 蓝+1
						
						{  65		, 		70		},	// 蓝+2
						
						{  70		, 		75		},	// 蓝+3
						
						{  75		, 		80		},	// 蓝+4
						
						{  80		, 		85		},	// 蓝+5
						
						{  75		, 		80		},	// 紫
						
						{  80		, 		85		},	// 紫+
						
						{  85		, 		90		},	// 紫+1
						
						{  90		, 		95		},	// 紫+2
						
						{  95		, 		100		},	// 紫+3
		
								};
	
	
	// 攻击系数
	private static final Modulus[] modulus = {

		//--------------升级加成----------达到品质加成----------------
			
			new Modulus(  85 		, 		0 		),	// 绿
			new Modulus(  105 		, 		0	 	),	// 绿+
			new Modulus(  125 		, 		0	 	),	// 蓝
			new Modulus(  135 		, 		0	 	),	// 蓝+
			new Modulus(  145 		, 		0	 	),	// 蓝+1
			new Modulus(  155 		, 		0	 	),	// 蓝+2
			new Modulus(  165 		, 		0	 	),	// 蓝+3
			new Modulus(  175 		, 		0	 	),	// 蓝+4
			new Modulus(  185 		, 		0	 	),	// 蓝+5
			new Modulus(  195 		, 		0	 	),	// 紫
			new Modulus(  205 		, 		0	 	),	// 紫+
			new Modulus(  215 		, 		0	 	),	// 紫+1
			new Modulus(  225 		, 		0	 	),	// 紫+2
			new Modulus(  235 		, 		0	 	),	// 紫+3
			
								};
	
	
	/** 高级技能升级几率 */
	public static final byte[] highSkillOdds 	= { 30, 50, 80 };
	
	/** 初级技能升级几率 */
	public static final byte[] primarySkillOdds = { 30, 50, 80 };
	
	/** 获得品质总个数 */
	public static int size(){
		return levelLimit.length;
	}
	
	/**
	 * 根据等级和品质 获取攻击系数
	 * @param level (英雄等级)
	 * @param quality (英雄品质)
	 * @return
	 */
	public static int attackModulusFormula( short level, Quality quality ){
		
		int result 			= 0;
		
		// 根据品质获取 实际数
		byte actualNum		= actualNumberFormula( quality ) ;
		
		result				= modulus[actualNum].upgrade * level;
		
//		// 根据品质获取 数据链
//		byte[] link 		= dataLink( quality );
//		short change		= level;
//		
//		for( byte idx = 0; idx < link.length; idx++ ){
//			byte i					= link[idx];
//			
//			// 先来就把  达到品质加成  加上
//			result					+= modulus[i].achieveQuality;
//			
//			// 如果还有等级 就加上
//			if( change > 0 ){
//				short changeLevel 	= level > levelLimit[i][1] ? levelLimit[i][1] : level;
//				change				= (short) Math.max( changeLevel - levelLimit[i][0], 0 );
//				result				+= modulus[i].upgrade * change;
//			}
//			
//			if( actualNum == i ) break;
//		}
		
		return result;
	}
	
	/**
	 * 根据品质算出实际数
	 * @param quality
	 * @return
	 */
	public static byte actualNumberFormula( Quality quality ) {
		
		byte index 	= quality.getColour().toNumber();
		if( index >= actualNumber.length ) index = (byte)(actualNumber.length - 1);
		
		byte[] color = actualNumber[index];
		
		index		= quality.getLevel();
		if( index >= color.length ) index = (byte)(color.length - 1);
		
		return color[index];
	}

	
	
	/**
	 * 根据品质算出 闪避值 和 暴击值 
	 * @param quality
	 * @return
	 */
	public static float qualityToFormula( Quality quality, float[] dodge ) {
		
		float value = dodge[0] + quality.toNumber() * dodge[1];
		
		// 如果到最高了 还要加一次
		if( quality.isMax() ){
			value 	= value + dodge[1];
		}
		return value;
	}
	
	
	/** 获得数据链 */
	public static byte[] dataLink( Quality quality ){
		
		if( quality.getMaxColour() == Colour.BLUE ){
			byte[] data = {0,1,2,3,4,5,6,7,8};
			return data;
		}else if( quality.getMaxColour() == Colour.PURPLE){
			byte[] data = {0,1,2,3,4,5,6,9,10,11,12,13};
			return data;
		}else{
			byte[] data = {0,1};
			return data;
		}
	} 
	
	/**
	 * 还差多少经验满级 公式
	 * @param level
	 * @param exp 
	 * @param quality 
	 * @return
	 */
	public static int gptuExp( short level, int exp, Quality quality ) 
	{
		int result 			= 0;
		
		// 根据品质获取 实际数
		byte actualNum		= actualNumberFormula( quality ) ;
		
		for( short i = level; i < levelLimit[actualNum][1]; i++ ){
			result 			+= HeroGrowTempletCfg.getTempletById( i ).getExp();
		}
		
		result				= result - exp;
		
		return result;
	}
	
	/**
	 * 获取技能升级几率
	 * @param lordHero
	 * @param quality
	 * @return
	 */
	public static byte getSkillOdds( Hero lordHero, Quality quality ) {
		
		if( quality == null )
			return 0;
		
		if( lordHero.getQuality().isElite() && quality.isElite() )
			return highSkillOdds[ quality.getColour().toNumber() ];
		
		if( !lordHero.getQuality().isElite() && !quality.isElite() ) 
			return primarySkillOdds[ quality.getColour().toNumber() ];
		
		return 0;
	}
	
	/**
	 * 进化 公式
	 * @param quality
	 * @return
	 */
	public static Quality evolution( Quality quality ) 
	{
		// 根据品质获取 数据链
		byte[] link 		= dataLink( quality );
		
		// 根据品质获取 实际数
		byte actualNum		= actualNumberFormula( quality );
		
		// 然后找出 进化到下一个品质的实际数
		byte i = 0;
		while( link[i++] != actualNum );
		
		if( i >= link.length ) i = (byte)(link.length - 1);
		i = link[i];
		
		return getActualToQuality( quality, i);
	}
	
	/**
	 * 根据实际数 获得品质
	 * @param quality
	 * @param actualNum
	 * @return
	 */
	public static Quality getActualToQuality( Quality quality, byte actualNum ){
		// 根据实际数 算出品质
		for( byte j = 0; j < actualNumber.length; j++ ){
			for( byte k = 0; k < actualNumber[j].length; k++ ){
				if( actualNum == actualNumber[j][k] ){
					
					Colour colour 	= Colour.fromNumber(j); 
					if( colour.toNumber() > quality.getMaxColour().toNumber() )
						colour 		= quality.getMaxColour();
					
					quality.setColour( colour );
					
					
					byte level		= getColourMaxLevel(colour);
					if( k > level ) 
						k			= level;
						
					quality.setLevel( k );
					
					return quality;
				}
			}
		}
		
		return quality;
	}
	
	/**
	 * 根据颜色获取 颜色最大等级
	 * @param colour
	 * @return
	 */
	public static byte getColourMaxLevel( Colour colour ){
		return (byte) (actualNumber[colour.toNumber()].length - 1);
	}
	
	/**
	 * 根据品质获取携带等级
	 * @return
	 */
	public static short getHeroCarryLv( Quality quality )
	{
		short clv		= 1;
	
		if (quality.getColour() == Colour.PURPLE && quality.getLevel() > 0)
			clv 		= (short) ((quality.getLevel() - 1) * 5 + 60);

		return clv;
	}
	
	public static void main( String[] arg ){
		
		HeroGrowTempletCfg.init();
		
		Quality q = new Quality(Colour.GREEN, Colour.BLUE, (byte)1 );
		System.out.print( "绿+：\t" );
		System.out.print( attackModulusFormula( (short)20 , q ) + "\t" );
			
//		for( int i = 1; i <= 120; i++ )
//			System.out.print( "\t" + i );
//		System.out.println();
//		System.out.println();
//		
//		Quality q = new Quality(Colour.GREEN, Colour.BLUE, (byte)0 );
//		System.out.print( "绿：\t" );
//		for( int i = 1; i <= 120; i++ ){
//			System.out.print( attackModulusFormula( (short)i , q ) + "\t" );
//		}
//		System.out.println();
//		
//		q = new Quality(Colour.GREEN, Colour.BLUE, (byte)1 );
//		System.out.print( "绿+：\t" );
//		for( int i = 1; i <= 120; i++ ){
//			System.out.print( attackModulusFormula( (short)i , q ) + "\t" );
//		}
//		System.out.println();
//		
//		q = new Quality(Colour.BLUE, Colour.PURPLE, (byte)0 );
//		System.out.print( "蓝：\t" );
//		for( int i = 1; i <= 120; i++ ){
//			System.out.print( attackModulusFormula( (short)i , q ) + "\t" );
//		}
//		System.out.println();
//		
//		q = new Quality(Colour.PURPLE, Colour.PURPLE, (byte)0 );
//		System.out.print( "紫：\t" );
//		for( int i = 1; i <= 120; i++ ){
//			System.out.print( attackModulusFormula( (short)i , q ) + "\t" );
//		}
//		System.out.println();
//		
//		q = new Quality(Colour.PURPLE, Colour.PURPLE, (byte)1 );
//		System.out.print( "紫+：\t" );
//		for( int i = 1; i <= 120; i++ ){
//			System.out.print( attackModulusFormula( (short)i , q ) + "\t" );
//		}
//		System.out.println();
//		
//		
		
		
		
		//165290
//		System.out.println( gptuExp((short)23,0,q) );
		
//		System.out.println( "进化前：颜色=" + q.getColour() + ", 等级=" + q.getLevel() );
//		
//		q = evolution( q );
//		
//		System.out.println( "进化后：颜色=" + q.getColour() + ", 等级=" + q.getLevel() );
	}

	
}









/**
 * 攻击系数 （生命根据算出来的攻击力*3）
 * @author DXF
 */
class Modulus{
	
	/** 升级加成 */
	public int upgrade;
	
	/** 达到品质加成 */
	public int achieveQuality;
	
	public Modulus( int upgrade, int achieveQuality ){
		this.upgrade 		= upgrade;
		this.achieveQuality = achieveQuality;
	}
}