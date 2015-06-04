package game.activity;

import util.SystemTimer;
import lombok.Getter;
import lombok.Setter;

/**
 * 参与活动的玩家
 */
public class ParticipationBase {

	// 玩家唯一ID
	private int 	_nUID;
	
	// 对大龙 累计 伤害
	private int 	_nValue	= 0;
	
	// 挑战大龙次数
	private byte 	_count;
	
	// 是否全部死亡
	@Getter
	private boolean isDie;
	
	// 记录时间
	@Getter @Setter
	private int		time;
	
	// 复活次数
	private int[]	resurgenceTimes = { 0,0 };
	
	private boolean isInspire;
	
	//开始进入战斗
	@Getter @Setter
	private boolean isEnterCombat = false;
	
	public ParticipationBase( int uid ){
		this._nUID 	= uid;
		_count		= 0;
		_nValue		= 0;
	}
	
	public int getUID(){
		return this._nUID;
	}

	public int getValue() {
		return this._nValue;
	}

	public void setValue( int damage ) {
		this._nValue += damage;
	}
	
	public byte getCount(){
		return _count;
	}

	public void setIsDie( boolean attackIsAllDie ) {
		time	= 0;
		isDie 	= attackIsAllDie;
		if( isDie )
			time = SystemTimer.currentTimeSecond();
	}

	public boolean isEnter() {
		updatatime();
		return isDie;
	}
	
	/** 获得剩余时间 */
	public int getResidueTime(){
		
		updatatime();
		
		if( time == 0 ) return 0;
		
//		int residue = 15 - (SystemTimer.currentTimeSecond() - time);
		int residue = 20 - (SystemTimer.currentTimeSecond() - time);
 		if( residue <= 0 ) residue = 1;
 		
		return residue;
	}
	
	private void updatatime(){
		
		if( time == 0 || !isDie ) return ;
		
//		int s 		= 15;
		int s 		= 20;
		int curt 	= SystemTimer.currentTimeSecond() - time;
		if( curt >= s )
		{
			time 	= 0;
			isDie	= false;
		}
	}

	/** 强制复活 
	 * @param type */
	public void resurgence() {
		time 	= 0;
		isDie	= false;
		
	}
	public void jiluRresurgenceTimes( int index ){
		++resurgenceTimes[index];
	}
	public int getRresurgenceTimes( int index ){
		return resurgenceTimes[index];
	}
	
	public boolean isInspire() {
		return this.isInspire;
	}
	public void stratInspire() {
		isInspire	= true;
	}
	public void closeInspire(){
		isInspire	= false;
	}
	
}
