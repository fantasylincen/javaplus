package config.mission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import game.log.Logs;
import org.jdom2.Element;

/**
 * 副本 模板
 * @author DXF
 *
 */
public class InstanceTemplet {

	/** 副本id */
	private final short					id;
	private final short					idTwo;
	private final String				name;
	private final String				desc;
	
	// 类型  （1，普通  2，精英  3，挑战  4，活动）
	private final EctypeType			type;
	
	// 需要等级
	private final short					needLv;
	
	// 关卡
	private final List<Integer>  		tollgate;
	
	
	/** 获得副本ID */
	public short getId() {
		return id;
	}
	public short getIdTwo(){
		return idTwo;
	}
	public String getName() {
		return name;
	}
	public String getDesc() {
		return desc;
	}
	public EctypeType getType() {
		return type;
	}
	
	public List<Integer> getGuanKaIDs( int guankID ) {
		
		List<Integer> result = new ArrayList<Integer>();
		
		int index = indexOf( guankID );
		if( index == -1 ) {
			if( guankID >= tollgate.get( tollgate.size() - 1 ) )
				result.addAll( tollgate );
		}else{
			for( int i = 0; i < index+1; i++ )
				result.add( tollgate.get(i) );
		}
		
		return result;
	}
	
	/**
	 * 这个是特殊 专门为精英副本准备 其他副本 还是用下面getNeedLv
	 * @return
	 */
	public short getNeedLvToElite(){
		return needLv;
	}
	public short getNeedLv(){
		short lv = needLv;
		// 如果是精英 特殊处理一下
		if( type == EctypeType.ELITE ){
			InstanceTemplet templet = InstanceTempletCfg.getTempletById( needLv );
			if( templet != null )
				lv = templet.getNeedLvToElite();
			else
				lv = 0;
		}
		return lv;
	}
	public boolean isEliteHaveThrough(short lastId) {
		
		// 这里为新手引导做下
		if( needLv == InstanceTempletCfg.FIRST_POINTS_ID[EctypeType.COMMON.toNumber() - 1])
			return true;
		
		return needLv < lastId;
	}
	
	public List<Integer> getTollgate(){
		return this.tollgate;
	}
	/**
	 * 根据关卡ID 找出在第几个位置
	 * @param missId
	 * @return
	 */
	public int indexOf( int missId ){
		return tollgate.indexOf( missId );
	}
	
	
	public InstanceTemplet( Element element ) {
		id 				= Short.parseShort( element.getChildText( "id" ) );
		idTwo			= Short.parseShort( element.getChildText( "idTwo" ) );
		name 			= element.getChildText( "name" );
		desc 			= element.getChildText( "desc" );
		type			= EctypeType.fromNumber( Integer.parseInt( element.getChildText( "type" ) ) );
		needLv			= Short.parseShort( element.getChildText( "levelNeed" ) );
		
		tollgate		= parseTollgate( element.getChildText( "tollgate" ) );
		
	}
	
	
	// 解析关卡
	private List<Integer> parseTollgate( String childText ) {

		List<Integer> toll = new ArrayList<Integer>();
		
		if( childText.isEmpty() ){
			Logs.error( "读取副本表 出错 tollgate == null  at=" + id );
			return toll;
		}

		String[] content = childText.split( "," );
		
		try {
			for( int i = 0; i < content.length; i++ )
				toll.add( Integer.parseInt( content[i] ) );
		} catch (Exception e) {
			throw new RuntimeException( "解析副本表格出错  at=" + id );
		}
		
		Collections.sort( toll, posComparator );
		
		return toll;
	}
	
	/**
	 * 判断是否有这个关卡ID
	 * @param missionId
	 * @return
	 */
	public boolean isHaveID( int missionId ) {
		
		for( int to : tollgate ){
			if( to == missionId )
				return true;
		}
		
		return false;
	}
	
    /**
     * 按照关卡ID 从小到大排列
     */
    public static final Comparator<Integer> posComparator = new Comparator<Integer>(){
        @Override
        public int compare( Integer t1, Integer t2 ) {
            return t1 - t2;
        }
    };
    
	@Override
	public String toString() {
		return "MissionTemplet [id=" + id + ", name=" + name + ", desc=" + desc
				+ ", type=" + type 
				+ ", needLv=" + needLv 
				+ ", tollgate=" + tollgate 
				+ "]";
	}

}
