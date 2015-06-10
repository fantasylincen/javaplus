package config.talent;

import org.jdom2.Element;

/**
 * 天赋 模板
 * @author DXF
 *
 */
public class TalentTemplet {

	private final byte 		id;
	
	public TalentTemplet( Element element ){
		this.id = Byte.parseByte( element.getChildText( "id" ) );
	}
	
	/** 天赋ID */
	public byte getId(){
		return this.id;
	}
	
}
