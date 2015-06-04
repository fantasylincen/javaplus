package config.luckydraw;

import game.growup.Quality;

/**
 * 抽奖模板
 * @author DXF
 */
public class LuckydrawTemplet {

	// 英雄表格ID
	private final int 			nId;
	
	// 英雄初始品质
	private final Quality  		quality;
	
	// 英雄对应随机数
	private final int			rand;
	
	// 记录是否随到过
	private boolean				isRand = false;
	
	public LuckydrawTemplet( int nid, Quality quality, int rand ){
		this.nId 		= nid;
		this.quality 	= quality;
		this.rand		= rand;
	}
	
	public int getNid(){
		return this.nId;
	}
	
	public Quality getQuality(){
		return this.quality;
	}
	
	public int getRand(){
		return this.rand;
	}
	
	public void IsRand( boolean isRand ){
		this.isRand = isRand;
	}
	public boolean IsRand(){
		return this.isRand;
	}
	
}
