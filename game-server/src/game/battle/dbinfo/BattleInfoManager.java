package game.battle.dbinfo;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import define.DefaultCfg;

import user.UserInfo;

/**
 * 战斗信息 管理 中心 <br>
 * 这里主要是 存储和查看 战斗信息 <br>
 * 以后考虑 是否放入user类   如果频繁查看 就需要放入user  现在暂时不需要 所以为单列
 * @author DXF
 *
 */
public class BattleInfoManager {

	private final BattleInfoDataProvider 	db 				= BattleInfoDataProvider.getInstance();
	private final UserInfo					user;
	private List<BattleInfo> 				battleInfo;
	
	// 每个玩家 最大 存储信息个数
	private final int  						MAX_INFO_NUM 	= DefaultCfg.MAX_FIGHT_INFO_NUM;
		
	public BattleInfoManager( UserInfo user ){
		this.user 	= user;
		battleInfo 	= db.get( user );
		sorting();
	}
	
	/**
	 * 获得所有战报信息
	 * @return
	 */
	public List<BattleInfo> getBattleInfo(){
		return battleInfo;
	}
	
	/**
	 * 添加一条 战报  并返回这条战报唯一ID
	 * @param data
	 */
	public int addBattleInfo( BattleInfo data )
	{
		// 如果超过上限  那么就将最早的数据覆盖掉
		if( battleInfo.size() >= MAX_INFO_NUM )
		{
			return upData( data );
		}else{
			return add( data );
		}
	}
	
	/**
	 * 获得指定  的战报信息
	 * @param uid 唯一ID
	 * @return
	 */
	public BattleInfo getBattleByteData( int uid )
	{
		if( battleInfo.size() == 0 ) 
			return null;
		
		for( BattleInfo b : battleInfo ){
			if( b.getUID() == uid ){
				return b;
			}
		}
		
		return null;
	}
	
	
	
	// 修改
	private int upData(  BattleInfo data  ){
		
		data.setUID( battleInfo.get(0).getUID() );
		
		data.getData().flip();
		battleInfo.set(0, data);
		
		db.upData( user, data );
		
		sorting();
		
		return data.getUID();
	}
	
	// 添加
	private int add(  BattleInfo data  ){
		
		data.setUID( user.getBasisUniqueID().BATTLEINFO_ID() );
		
		if( db.add( user, data ) == 0 ){
			data.getData().flip();
			battleInfo.add( data );
		}
		
		sorting();
		
		return data.getUID();
	}
	
	/** 排序  从小到大 根据时间 */
	private void sorting()
	{
		Collections.sort( battleInfo, timeComparator );
	}
	
	/**
	 * 时间从小到大 确定
	 */
	private static final Comparator<BattleInfo> timeComparator = new Comparator<BattleInfo>(){
		@Override
		public int compare( BattleInfo b1, BattleInfo b2 ) {
			return b1.getTimer() - b2.getTimer();
		}
	};
	
	
	public static void main( String s[] ){
		
	}
	
	
}
