package game.pvp;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import user.UserManager;
import util.UtilBase;

import define.DefaultCfg;

/**
 * 录像记录
 * @author DXF
 */
public class VideoRecording {

	private List<VideoBase> 	lists 			= new ArrayList<VideoBase>();
	
	// 剩余复仇次数
	private byte				revengeCount 	= 10;
	
	public List<VideoBase> getList(){
		return lists;
	}
	public VideoRecording(){
	}
	
	public byte getRevengeCount(){
		return revengeCount;
	}
	
	public void put( VideoBase base ) {
		lists.add( base );
		sorting();
	}
	
	/**
	 * 通过战报ID 获取战报信息
	 * @param id
	 * @return
	 */
	public VideoBase get( int id ){
		
		for( VideoBase v : lists ){
			if( v.mID == id )
				return v;
		}
		
		return null;
	}
	
	/**
	 * 删除 指定的一条战报
	 */
	public void remove( int id ){
		
		for( VideoBase v : lists )
		{
			if( v.mID == id )
			{
				lists.remove(v);
				return ;
			}
		}
	}

	public boolean isMax() {
		return lists.size() >= DefaultCfg.PAGE_MAX;
	}

	/**
	 * 获得最后一个
	 * @return
	 */
	public VideoBase getLast() {
		return lists.remove( lists.size() - 1 );
	}

	public void putData( ByteBuffer response ) {
		
		response.put( (byte)lists.size() );
		for( VideoBase v : lists ){
			response.putInt( v.mID );
			ByteBuffer content 	= v.mData.asReadOnlyBuffer();
			response.put( content );
			response.putInt( v.mTouserID );
			String name = UserManager.getInstance().getNickName( v.mTouserID );
			UtilBase.encodeString( response, name );
			response.putInt( v.mTime );
			response.put( (byte) (v.mIsComplex ? 1 : 0));
		} 
	}
	
	/** 排序  从小到大 根据时间 */
	public void sorting()
	{
		Collections.sort( lists, timeComparator );
	}
	
	/**
	 * 时间从小到大 确定
	 */
	private static final Comparator<VideoBase> timeComparator = new Comparator<VideoBase>(){
		@Override
		public int compare( VideoBase b1, VideoBase b2 ) {
			return b2.mTime - b1.mTime;
		}
	};

}
