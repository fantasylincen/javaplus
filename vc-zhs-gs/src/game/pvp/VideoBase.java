package game.pvp;

import game.fighter.Hero;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 记录战报 
 * @author DXF
 */
public class VideoBase {

	/**
	 * 战报ID
	 */
	public int 				mID;

	/**
	 * 挑战者 用户ID
	 */
	public int				mTouserID;
	
	/**
	 * 挑战者 英雄列表
	 */
	public List<Hero> 		mALists		= new ArrayList<Hero>();
	
	/**
	 * 自己的英雄列表
	 */
	public List<Hero> 		mDLists		= new ArrayList<Hero>();
	
	/**
	 * 战报数据
	 */
	public ByteBuffer  		mData;
	
	/**
	 * 战报时间
	 */
	public int 				mTime;
	
	// 是否复仇
	public boolean 			mIsRevenge	= false;

	// 是否使用 复仇之心 
	public boolean 			mIsComplex	= false;

	/**
	 * 抢夺的金币
	 */
	public int 				mLootCash	= 0;

	/**
	 * 是否胜利
	 */
	public boolean 			mIsWin; 
	
	
	public VideoBase(int id, int userid, ByteBuffer data) {
		this.mID 		= id;
		this.mTouserID	= userid;
		this.mData		= data;
	}

	public void setTime(int t) {
		mTime = t;
	}
	
	public void setAList( List<Hero> list ){
		mALists.clear();
		mALists.addAll( list );
	}
	
	public void setDList( List<Hero> list ){
		mDLists.clear();
		mDLists.addAll( list );
	}

	public void putHeroData( ByteBuffer response, List<Hero> list, byte b ) {
		response.put( (byte)list.size() );
		for( Hero x : list ){
			response.putInt( x.getUID() ); ///////////
			response.putInt( x.getNid() );
			response.putShort( (short)0 ); ///////////
			x.getQuality().toByte( response );
			response.put( (byte) (x.getPosition() + b) );
			response.putInt( x.getHpMax() );
			response.putInt( 0 ); ///////////
			response.put( (byte) (x.getIsCaptain() ? 1 : 0) );
			response.put( (byte) 0 ); ///////////
			response.putInt( 0 ); /////////////////
		}
	}

}
