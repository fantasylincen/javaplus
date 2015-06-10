package game.battle.dbinfo;

import java.nio.ByteBuffer;

/**
 * 战报 信息
 * @author DXF
 *
 */
public class BattleInfo {
	
	// 唯一ID
	private int             uID;
	// 副本ID
	private short			ectypeId;
	// 关卡ID
	private int 			missionId;
	// 波数
	private byte			theLv;
	// 战报信息
	private ByteBuffer		data;
	// 存储时间
	private int				timer;
	
	
	public BattleInfo( short eid, int mid, byte the, ByteBuffer data, int time )
	{
		this.ectypeId	= eid;
		this.missionId 	= mid;
		this.theLv 		= the;
		this.data		= data;
		this.timer 		= time;
	}
	
	/**
	 * 设置副本ID
	 * @param id
	 */
	public void setEctypeId( short id ){
		this.ectypeId = id;
	}
	/**
	 * 获得副本ID
	 * @return
	 */
	public short getEctypeId(){
		return this.ectypeId;
	}
	
	/**
	 * 设置关卡ID
	 * @param miss
	 */
	public void setMissionId( int miss )
	{
		this.missionId	= miss;
	}
	/**
	 * 获得关卡ID
	 * @return
	 */
	public int getMissionId(){
		return this.missionId;
	}
	
	/**
	 * 设置波数
	 * @param theLv
	 */
	public void setTheLv( byte theLv )
	{
		this.theLv	= theLv;
	}
	/**
	 * 获得波数
	 * @return
	 */
	public byte getTheLv(){
		return this.theLv;
	}
	
	/**
	 * 设置战报信息
	 * @param data
	 */
	public void setData( ByteBuffer data )
	{
		this.data	= data;
	}
	/**
	 * 获得战报信息
	 * @return
	 */
	public ByteBuffer getData(){
		return this.data;
	}
	
	public void setTimer( int time ){
		this.timer = time;
	}
	public int getTimer(){
		return this.timer;
	}

	public void setUID(int id) {
		this.uID = id;
	}

	public int getUID() {
		return this.uID;
	}
}
