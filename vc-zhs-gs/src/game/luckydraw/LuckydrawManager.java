package game.luckydraw;

import game.growup.Colour;
import game.growup.Quality;

import java.util.ArrayList;
import java.util.List;

import notice.NoticeManager;

import config.luckydraw.LuckydrawHandle;
import config.luckydraw.LuckydrawTemplet;
import config.luckydraw.LuckydrawTempletCfg;
import user.UserManager;
import util.RandomUtil;
import util.UtilBase;


/**
 * 抽奖管理系统  单列
 * @author DXF
 */
public class LuckydrawManager {

	public static LuckydrawManager instance = new LuckydrawManager();
	private LuckydrawManager(){} // 私有构造方法　防止再次创建
	
	
	// 祈福  一次的几率   绿色20%  绿色精英35%  蓝色44%  紫色1%，
	private final static int[] prayOnceOdds 	= { 
		0,		// 绿
		4500,	// 绿+
		5000,	// 蓝
		0,		// 蓝+
		0,		// 蓝+1
		0,		// 蓝+2
		0,		// 蓝+3
		0,		// 蓝+4
		0,		// 蓝+5
		500,	// 紫
		0,		// 紫+
		0,		// 紫+1
		0,		// 紫+2
		0		// 紫+3
		};
	
	// 友情抽奖  一次的几率   绿色54%  绿色精英45%  蓝色1%
	private final static int[] fbPrayOnceOdds 	= { 
		4500,	// 绿
		5000,	// 绿+
		500,	// 蓝
		0,		// 蓝+
		0,		// 蓝+1
		0,		// 蓝+2
		0,		// 蓝+3
		0,		// 蓝+4
		0,		// 蓝+5
		0,		// 紫
		0,		// 紫+
		0,		// 紫+1
		0,		// 紫+2
		0		// 紫+3
		};

	// 十次的几率
	private final static int[] prayTenOdds		= { 9800, 200 };
	
	// 98%  出现（ 紫色1，蓝色3，绿色精英3，绿色3 ）
	// 2%  出现（ 紫色2，蓝色2，绿色精英3，绿色3 ）
	
	// 龙
	private final static int[] dragon			= { 30129, 30081 };
	private final static Quality[] dragonQ		= { new Quality( Colour.PURPLE, Colour.PURPLE, (byte) 0), new Quality( Colour.BLUE, Colour.BLUE, (byte) 0) };
	
	// 祈福 这个必须和prayTenOdds对应
	private final static int[][] prayTenNum		= { { 0, 3, 3, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 }, 
													{ 0, 2, 2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0 }
													};
	private final static int[][] prayTenNumD	= { { 1, 2 },{ 2, 2 } };
	
	// 友情抽奖 这个必须和prayTenOdds对应
	private final static int[][] fdPrayTenNum	= { { 4, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
													{ 4, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
													};
	private final static int[][] fdprayTenNumD	= { { 0, 2 },{ 0, 2 } };
	
	// 祈福 30%出现特殊英雄  70%出现非特殊英雄
	public final static int[]	specialOdds		= { 3000, 7000 };
	
	
	/**
	 * 开始抽奖
	 */
	public List<LuckydrawTemplet> startSweepstake( SweepstakeType type, int name ){
		
		if( type == SweepstakeType.ONCE || type == SweepstakeType.FD_ONCE  ){
			
			return onceSweepstake( name, type );
			
		}else if( type == SweepstakeType.TENTIMES || type == SweepstakeType.FD_TENTIMES ) {
			
			return tentimesSweepstake( name, type );
			
		}
		
		return null;
	}
	
	/** 一次抽奖  
	 * @param type */
	private List<LuckydrawTemplet> onceSweepstake( int name, SweepstakeType type ){
		
		List<LuckydrawTemplet> list = new ArrayList<LuckydrawTemplet>();
		
		int[] odds 		= type == SweepstakeType.ONCE ? prayOnceOdds : fbPrayOnceOdds;
		int maxRand 	= 0;
		
		byte i 			= 0;
		for( ; i < odds.length; i++ )
			maxRand		+= odds[i];
		int rand 		= RandomUtil.getRandomInt( 0, maxRand - 1 );
		
		for( i = 0; i < odds.length ; i++ ){
			if( rand - odds[i] < 0 ) break;
			rand 		-= odds[i];
		}
		
		LuckydrawTemplet temp = run( name, i, null );
		if( temp != null)
			list.add( temp );
		
		if( temp.getQuality().getColour() == Colour.PURPLE ){
			NoticeManager.getInstance().addTimely( 207, UtilBase.nToHero( UserManager.getInstance().getByName(name), list ) );
		}
		
		return list;
	}
	
	/** 十次抽奖 
	 * @param name 
	 * @param type 
	 * @return */
	private List<LuckydrawTemplet> tentimesSweepstake( int name, SweepstakeType type ){
		
		List<LuckydrawTemplet> list = new ArrayList<LuckydrawTemplet>();
		List<LuckydrawTemplet> lss = new ArrayList<LuckydrawTemplet>();
		
		int maxRand 	= 0;
		byte i			= 0;
		for ( ; i < prayTenOdds.length; i++ )	
			maxRand		+= prayTenOdds[i];
		
		// 先取得随机个数
		int rand		= RandomUtil.getRandomInt( 0, maxRand - 1 );
		for ( i = 0; i < prayTenOdds.length; i++ ){
			rand 		-= prayTenOdds[i];
			if( rand < 0 ) break;
		}
		
		// 然后根据个数 在获取英雄ID
		int[] nums		= type == SweepstakeType.TENTIMES ? prayTenNum[i] : fdPrayTenNum[i];
		int[] numsD		= type == SweepstakeType.TENTIMES ? prayTenNumD[i] : fdprayTenNumD[i];
		
		// 记录数组  让连续抽一种颜色的卡片不一样
		List<Integer> l	= new ArrayList<Integer>();
		
		for( i = (byte) (nums.length - 1); i >= 0; i-- ){
			
			l.clear();
			
			// 根据个数 获取 要求每个卡片不一样
			for( int j = 0; j < nums[i]; j++ ){
				
				LuckydrawTemplet temp 	= run( name, i, l );
				if( temp.getQuality().getColour() == Colour.PURPLE )
					lss.add( temp );
				
				list.add( temp );
				
				// 避免重复加
				if( l.indexOf( temp.getNid() ) == -1 )
					l.add( temp.getNid() );
			}
			
		}
		
		
		// 这里给龙
		for( i = 0; i < numsD.length; i++ ){
			for( int j = 0; j < numsD[i]; j++ ){
				LuckydrawTemplet temp = new LuckydrawTemplet( dragon[i] , dragonQ[i] , 0 );
				if( temp.getQuality().getColour() == Colour.PURPLE ){
//					lss.add( temp );
				}
				list.add( temp );
			}
		}
		
		if( !lss.isEmpty() )
			NoticeManager.getInstance().addTimely( 207, UtilBase.nToHero( UserManager.getInstance().getByName(name), lss ) );
		return list;
	}
	
	private LuckydrawTemplet run( int name, byte actualNum, List<Integer> l ){
		
		// 根据颜色 在配置表获取 对应数据
		LuckydrawHandle handle		= LuckydrawTempletCfg.getTempletById( actualNum );
		
		if( handle == null || handle.isEmpty() ) 
			handle					= LuckydrawTempletCfg.getDefault();
		
		// 然后 在随机一个 ID出来
		return handle.getRandNid( l );
	}
	
	public static void main( String[] args ){
		List<LuckydrawTemplet> list = new ArrayList<LuckydrawTemplet>();
		list.add( new LuckydrawTemplet(12, new Quality( Colour.BLUE, Colour.GOLDEN, (byte)1 ), 1));
		list.add( new LuckydrawTemplet(12, new Quality( Colour.GOLDEN, Colour.BLUE, (byte)1),1 ));
		list.add( new LuckydrawTemplet(12, new Quality( Colour.GREEN, Colour.GOLDEN, (byte)1), 1));
		list.add( new LuckydrawTemplet(12, new Quality( Colour.PURPLE, Colour.PURPLE, (byte)1), 1));
		System.out.println( UtilBase.nToHero( UserManager.getInstance().getByName(123123), list ) );
	}
	
}
