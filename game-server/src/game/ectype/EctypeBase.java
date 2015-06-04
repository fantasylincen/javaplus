package game.ectype;


import lombok.Setter;



/**
 * 存储副本的基本信息
 * @author DXF
 *
 */
public class EctypeBase {

	// 最后一个 副本ID
	private short 										lastId				= 0;
	
	// 最后一个 关卡ID					
	@Setter
	private int											lastTollgateid		= 0;
	
	
	/** 设置主线ID */
	public void setLastId( short id )
	{
		this.lastId = id;
	}
	/** 获得主线ID */
	public short getLastId()
	{
		return this.lastId;
	}
	
	/**
	 * 获取最后一个副本ID
	 * @return
	 */
	public int getLastPid() {
		return lastTollgateid;
	}
	
	/**
	 * 设置 支线列表
	 * @param content
	 */
	public void setFeederList( String content )
	{
		if( content.isEmpty() ) return;
		
		String[] points = content.split(",");
		
		addFeeder( Short.parseShort( points[0] ), Integer.parseInt( points[1] ) );
	}
	
	/**
	 * 获得 支线列表
	 * @return
	 */
	public String getFeederList() 
	{
		StringBuilder output 	= new StringBuilder();
		output.append( lastId ).append( "," ).append( lastTollgateid );
		return output.toString();
	}
	
	/**
	 * 添加一个记录
	 * @param eid
	 * @param mid
	 */
	public void addFeeder( short eid, int mid ) {
		this.lastId 		= eid;
		this.lastTollgateid = mid;
	}
	
	/**
	 * 当前副本ID 是否通过
	 * @param needLv
	 * @return
	 */
	public boolean isCustomsClearance( short eid ) {
		return eid < lastId;
	}
	
	public void clear()
	{
		lastId			= 0;
		lastTollgateid	= 0;
	}

	
}
