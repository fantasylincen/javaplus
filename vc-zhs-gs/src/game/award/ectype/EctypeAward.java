package game.award.ectype;


import game.award.AwardInfo;

import java.util.List;

/**
 * 一个完整关卡准备 奖励物品
 * @author DXF
 *
 */
public class EctypeAward {

	// 战斗ID
	private int						fightId;
	// 关卡ID
	private  int					id;
	// 当前第几波怪
	private  byte					theLv;
	private List<AwardInfo>			content;
	
	// 是否胜利
	private boolean 				isWin;
	// 试炼bossID
	private int 					fireBoss;
	
	public EctypeAward( int id, byte the, List<AwardInfo> content, int uid, boolean isWin ) {
		super();
		this.id			= id;
		this.theLv 		= the;
		this.fightId 	= uid;
		this.isWin 		= isWin;
		this.setContent(content);
	}
	
	public List<AwardInfo> getContent() {
		return content;
	}

	public void setContent(List<AwardInfo> content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setTheLv( byte the ){
		this.theLv = the;
	}

	public byte getTheLv(){
		return this.theLv;
	}
	
	public void setFightId( int id ){
		this.fightId = id;
	}
	public int getFightId(){
		return this.fightId;
	}
	
	public void setIsWin( boolean iswin ){
		this.isWin = iswin;
	}
	public boolean getIsWin(){
		return this.isWin;
	}
	
	public void setFireBoss( int fireBoss ){
		this.fireBoss = fireBoss;
	}
	public int getFireBoss(){
		return fireBoss;
	}
	/**
	 * 获得奖励物品总个数
	 * @return
	 */
	public byte getAwardCount()
	{
		byte count = 0;
		
		if( content != null ){
			for( AwardInfo ai : content ){
				count += ai.getNumber();
			}
		}
		
		return count;
	}

	public String getContentStr() {

		StringBuilder output = new StringBuilder("");
		
		if( content != null ){
			for( AwardInfo ai : content )
			{
				output.append( ai.getAward().toNumber() + "," );
				output.append( ai.getPropId() + "," );
				output.append( ai.getNumber() );
				output.append( "|" );
			}
		}
		
		return output.toString();
	}
}
