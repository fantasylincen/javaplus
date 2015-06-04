package config.luckydraw;

import game.luckydraw.LuckydrawManager;

import java.util.ArrayList;
import java.util.List;

import util.RandomUtil;

/**
 * 抽奖处理 每个品质的 对应数据
 * @author DXF
 *
 */
public class LuckydrawHandle {

	// 非特殊 数据
	private List<LuckydrawTemplet> 		notSpecialData 	= new ArrayList<LuckydrawTemplet>();
	
	// 特殊 数据
	private List<LuckydrawTemplet> 		specialData 	= new ArrayList<LuckydrawTemplet>();
	
	// 最大随机数
	private int							maxRand		= 0;
	
	public LuckydrawTemplet getRandNid( List<Integer> list ) {
		
		List<LuckydrawTemplet> data = init( list );
		
		int rand 					= RandomUtil.getRandomInt( 0, maxRand - 1 );
		
		LuckydrawTemplet templet 	= null;
		
		for( int i = 0; i < data.size(); i++ ){
			templet 				= data.get(i);
			if( templet.IsRand() ) continue;
			
			rand 					-= templet.getRand();
			if( rand < 0 ) break;
		}
		
		return templet;
	}
	
	
	// 初始化  检查 是否有随到过得 防止连续随机到一个ID
	private List<LuckydrawTemplet> init( List<Integer> list ){
		
		List<LuckydrawTemplet> data = getData();
				
		maxRand			= 0;
		for( LuckydrawTemplet templet : data ){
			templet.IsRand( false );
			
			// 检查 是否随到过
			if( list != null && list.indexOf( templet.getNid() ) != -1 )
				templet.IsRand( true );
			else
				maxRand += templet.getRand();
		}
		return data;
	}

	
	private List<LuckydrawTemplet> getData(){
		
		if( notSpecialData.isEmpty() )
			return specialData;
		if( specialData.isEmpty() )
			return notSpecialData;
		
		int max						= 0;
		byte i						= 0;
		for( i = 0; i < LuckydrawManager.specialOdds.length; i++ )
			max						+= LuckydrawManager.specialOdds[i];
		
		int rand 					= RandomUtil.getRandomInt( 0, max - 1 );
		for( i = 0; i < LuckydrawManager.specialOdds.length; i++ ){
			rand					-= LuckydrawManager.specialOdds[i];
			if( rand < 0 ) break;
		}
		
		return i == 0 ? specialData : notSpecialData;
	}
	
	
	public boolean isEmpty(){
		return notSpecialData.isEmpty() && specialData.isEmpty();
	}
	
	/**
	 * 添加一个
	 * @param templet
	 * @param isSpecial 
	 */
	public void add( LuckydrawTemplet templet, byte isSpecial ) {
		
		if( isSpecial == 0 )
			notSpecialData.add( templet );
		else
			specialData.add( templet );
	}
	
}
