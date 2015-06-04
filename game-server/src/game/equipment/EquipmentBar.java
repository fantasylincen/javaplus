package game.equipment;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;


import config.equipment.EquPropertyType;


/**
 * 装备栏
 * @author DXF
 */
public class EquipmentBar {

	// 6件装备
	private EquipmentInfo[]  bar = { null,null,null,null,null,null };
	
	public EquipmentInfo get (byte pos ) {
		if( pos < 0 || pos >= bar.length ) return null;
		return bar[pos];
	}
	public void set(byte pos, EquipmentInfo equip) {
		if( pos < 0 || pos >= bar.length ) return ;
		bar[pos] = equip;
	}
	
	/**
	 * 通过列表形式获得 已拥有装备
	 * @return
	 */
	public List<EquipmentInfo> get(){
		List<EquipmentInfo> list = new ArrayList<EquipmentInfo>();
		for( int i = 0; i < bar.length; i++ ){
			if( bar[i] != null )
				list.add( bar[i] );
		}
		return list;
	}
	
	/**
	 * 根据属性类型 获取对应数值
	 * @param qpt
	 * @return
	 */
	public int getOwnValue( EquPropertyType qpt ) {
		
		int result = 0;
		
		for( int i = 0; i < bar.length; i++ ){
			EquipmentInfo e = bar[i];
			if( e != null )
				result 		+= e.getValue()[qpt.toNumber()-1];
		}
		
		return result;
	}
	
	
	/**
	 * 将装备信息 发送前端
	 * @param response
	 */
	public void toByte( ByteBuffer response ) {
		List<EquipmentInfo> list = get();
		response.put( (byte) list.size() );
		for( EquipmentInfo e : list ){
			response.putInt( e.getUID() );
			response.putInt( e.getTemplet().getNId() );
		}
	}
	

	/**
	 * 将装备信息存入数据库
	 * @return
	 */
	public String toContent() {
		StringBuilder content = new StringBuilder();
		
		for( int i = 0; i < bar.length; i++ )
			content.append( bar[i] == null ? -1 : bar[i].getUID() ).append( "," );
		
		return content.toString();
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void remove( int id ) {
		for( int i = 0; i < bar.length; i++ )
		{
			if( bar[i] != null && bar[i].getUID() == id ){
				bar[i] = null;
				break;
			}
		}
	}
	
	/** 卸下所有装备 */
	public void demountAll() {
		for( int i = 0; i < bar.length; i++ )
			bar[i] = null;
	}
	
	public boolean isEmpty() {
		for( int i = 0; i < bar.length; i++ ) {
			if( bar[i] != null ) return false;
		}
		return true;
	}
	
}
