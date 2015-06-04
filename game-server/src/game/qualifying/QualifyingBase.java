package game.qualifying;

import java.util.ArrayList;
import java.util.List;

import util.SystemTimer;

import define.GameData;

import lombok.Data;

/**
 * 排位基础类
 * @author DXF
 */
@Data
public class QualifyingBase {
	
	// 战绩
	private int		standings 		= 0;
	
	
	// 剩余次数
	private byte 	residueDegree 	= 5;
	// 可购买次数
	private byte 	frequency 		= 10;
	
	// 是否可以领取奖励
	private boolean isGetAward		= false;
	
	// 可以挑战的3个玩家
	private List<Challenges> challenge	= new ArrayList<Challenges>();

	// 战报
	private List<BattlefieldReport> battlefields = new ArrayList<BattlefieldReport>();
	
	public void reResidueDegree() {
		if( --residueDegree < 0 ) residueDegree = 0;
	}
	
	public boolean isChallenges(){
		return residueDegree > 0;
	}
	public void setIsGetAward(boolean b) {
		isGetAward = b;
	}
	
	public Challenges getChallenge( int uid ){
		for ( Challenges c : challenge ){
			if( c.getUid() == uid ) return c;
		}
		return null;
	}
	
	/**
	 * 是否可以挑战
	 * @param uid
	 * @return
	 */
	public boolean challengesIsHave( int uid ) {
		Challenges c = getChallenge(uid);
		if( c == null ) return false;
		if( c.getCdTimeToTimer() == 0 ){
			c.setCdTime( SystemTimer.currentTimeSecond() ); // 5分钟  5 * 60
			return true;
		}
		return false;
	}

	/**
	 * 是否可以购买
	 * @return
	 */
	public boolean isCanBuy() {
		return frequency > 0;
	}

	/**
	 * 开始购买
	 * @return
	 */
	public boolean startBuy() {
		++residueDegree;
		--frequency;
		return false;
	}

	public int buyNeedGold() {
		// 1-2：20，3-5：30，6-8：50，9-10：100
		byte yiBuyCount = (byte) (10 - frequency);
		
		if( yiBuyCount <= 2 ){
			return 20;
		}else if( yiBuyCount <= 5 ){
			return 30;
		}else if( yiBuyCount <= 8 ){
			return 50;
		}else {
			return 100;
		}
	}
	
	/** 开始领取奖励 */
	public int[] startGetAwardToGold( int rank ) {
		for( int i = 0; i < GameData.award.length - 1; i++ ){
			int[] data = GameData.award[i];
			
			if( rank <= data[0] ){
				return new int[]{ data[1], data[2] };
			}
		}
		int[] data = GameData.award[GameData.award.length-1];
		return new int[]{ data[1], data[2] };
	}

	public boolean battlefieldIsMax() {
		return battlefields.size() >= 20;
	}

	public void addBattlefield(BattlefieldReport battle) {
		battlefields.add( battle );
	}

	public void restBattlefield( BattlefieldReport battle ) {
		battle.uid = battlefields.remove(0).uid;
		battlefields.add( battle );
	}

	public BattlefieldReport getBattlefield( int id ) {
		for( BattlefieldReport b : battlefields)
			if( b.uid == id ) return b;
		return null;
	}

	public void reStandings() {
		++standings;
	}

	public void init() {
		residueDegree 	= 5;
		frequency		= 10;
	}



}
