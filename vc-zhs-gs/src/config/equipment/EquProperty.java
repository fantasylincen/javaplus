package config.equipment;

import lombok.Data;

/**
 * 装备属性
 * @author DXF
 *
 */
public class EquProperty {

	public EquPropertyType getType() {
		return type;
	}

	public void setType(EquPropertyType type) {
		this.type = type;
	}

	public int[] getValue() {
		return value;
	}

	public void setValue(int[] value) {
		this.value = value;
	}

	private EquPropertyType 		type;
	
	// 有7条属性  物理攻击,法术攻击,生命,暴击,韧性,命中,闪避
	private int[]					value = new int[7];
	
	public EquProperty( String childText, String childText2 ) {
		type			= EquPropertyType.fromNumber( Integer.parseInt( childText ) );
		String[] list 	= childText2.split(",");
	
		if( list.length != 7 || list == null ) 
			throw new RuntimeException( " 装备配置表错误    temp.length != 7  at=" + childText2 );
		
		for( int i = 0; i < list.length; i++ )
			value[i]	= Integer.parseInt( list[i] );
	}

	
}
