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
	
	public int get_nUID() {
		return _nUID;
	}

	public void set_nUID(int _nUID) {
		this._nUID = _nUID;
	}

	public int get_nValue() {
		return _nValue;
	}

	public void set_nValue(int _nValue) {
		this._nValue = _nValue;
	}

	public byte get_count() {
		return _count;
	}

	public void set_count(byte _count) {
		this._count = _count;
	}

	public boolean isDie() {
		return isDie;
	}

	public void setDie(boolean isDie) {
		this.isDie = isDie;
	}

	public int[] getResurgenceTimes() {
		return resurgenceTimes;
	}

	public void setResurgenceTimes(int[] resurgenceTimes) {
		this.resurgenceTimes = resurgenceTimes;
	}

	public void setInspire(boolean isInspire) {
		this.isInspire = isInspire;
	}

	// 对大龙 累计 伤害
	private int 	_nValue	= 0;
	
	// 挑战大龙次数
	private byte 	_count;
	
	// 是否全部死亡
	@Getter
	private boolean isDie;
	
	// 记录时间
	private int		time;
	
	// 复活次数
	private int[]	resurgenceTimes = { 0,0 };
	
	private boolean isInspire;
	
	//开始进入战斗
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
		setTime(0);
		isDie 	= attackIsAllDie;
		if( isDie )
			setTime(SystemTimer.currentTimeSecond());
	}

	public boolean isEnter() {
		updatatime();
		return isDie;
	}
	
	/** 获得剩余时间 */
	public int getResidueTime(){
		
		updatatime();
		
		if( getTime() == 0 ) return 0;
		
//		int residue = 15 - (SystemTimer.currentTimeSecond() - time);
		int residue = 20 - (SystemTimer.currentTimeSecond() - getTime());
 		if( residue <= 0 ) residue = 1;
 		
		return residue;
	}
	
	private void updatatime(){
		
		if( getTime() == 0 || !isDie ) return ;
		
//		int s 		= 15;
		int s 		= 20;
		int curt 	= SystemTimer.currentTimeSecond() - getTime();
		if( curt >= s )
		{
			setTime(0);
			isDie	= false;
		}
	}

	/** 强制复活 
	 * @param type */
	public void resurgence() {
		setTime(0);
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

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public boolean isEnterCombat() {
		return isEnterCombat;
	}

	public void setEnterCombat(boolean isEnterCombat) {
		this.isEnterCombat = isEnterCombat;
	}
	
}
