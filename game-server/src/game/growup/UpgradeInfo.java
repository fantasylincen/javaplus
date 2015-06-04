package game.growup;

import util.ErrorCode;

/**
 * 英雄成长信息
 * @author DXF
 *
 */
public class UpgradeInfo {
	// 消耗金币
	private int					needGold;
	
	// 能升到的等级
	private short				getLevel;
	
	// 能获得生命加成
	private int					getHp;
	
	// 能获得攻击加成
	private int					getAttack;
	
	// 能获得经验值
	private int					getExp;
	
	// 技能升级几率
	private byte				skillOdds;
	
	// 错误码
	private ErrorCode			code;
	
	public void needGold( float needGold ){
		this.needGold = (int) (needGold < 1 ? 1 : needGold > (int)needGold ? needGold + 1 : needGold);
	}
	public int needGold(){
		return this.needGold;
	}
	
	public void getLevel( short getLevel ){
		this.getLevel = getLevel;
	}
	public short getLevel(){
		return this.getLevel;
	}
	
	public void getHp( int getHp ){
		this.getHp = getHp;
	}
	public int getHp(){
		return this.getHp;
	}
	
	public void getAttack( int getAttack ){
		this.getAttack = getAttack;
	}
	public int getAttack(){
		return this.getAttack;
	}
	
	public void getExp( int getExp ){
		this.getExp = getExp;
	}
	public int getExp(){
		return this.getExp;
	}
	
	public void skillOdds( byte skillOdds ){
		this.skillOdds = skillOdds;
	}
	public byte skillOdds(){
		return this.skillOdds;
	}
	
	public void code( ErrorCode code ){
		this.code = code;
	}
	public ErrorCode code(){
		return this.code;
	}
}
