package game.luckydraw;

import game.growup.Colour;

/**
 * 抽奖 基础类
 * @author DXF
 */
public class LuckydrawBase {
	
	// 英雄表格ID
	private int 			nId;
	
	// 英雄初始品质
	private Colour  		colour;
	
	
	public LuckydrawBase( int nid, Colour colour ){
		this.nId 		= nid;
		this.colour 	= colour;
	}
	
	
	public int getNid(){
		return this.nId;
	}
	
	public Colour getQuality(){
		return this.colour;
	}
	
}
