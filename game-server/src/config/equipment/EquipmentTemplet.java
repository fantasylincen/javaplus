package config.equipment;

import game.equipment.EColour;

import org.jdom2.Element;

import config.fighter.Professional;
import lombok.Data;

/**
 * 装备模板
 * @author DXF
 */
@Data
public class EquipmentTemplet {
	
	private final int 				nId;
	
	private final String 			name;
	
	private final String			desc;
	
	private final EquipType			type;
	
	private final EColour			color;
	
	private final Professional 		restrictJob;
	
	private final short				needLevel;
	
	private final int				synthesis;
	
	private final int				synthesisTime;
	
	private EquProperty 			property;
	
	
	public EquipmentTemplet( Element element ) {
		
		nId				= Integer.parseInt( element.getChildText( "id" ) );
		name			= element.getChildText( "name" );
		desc			= element.getChildText( "desc" );
		type			= EquipType.fromNumber( Integer.parseInt( element.getChildText( "type" ) ) );
		color			= EColour.fromNumber( Integer.parseInt( element.getChildText( "color" ) ) );
		restrictJob		= Professional.fromNumber( Integer.parseInt( element.getChildText( "needJob" ) ) );
		needLevel		= Short.parseShort( element.getChildText( "needLevel" ) );
		synthesis		= Integer.parseInt( element.getChildText( "Synthesis" ) );
		synthesisTime	= Integer.parseInt( element.getChildText( "SynthesisTime" ) );
		property		= new EquProperty( element.getChildText( "addType" ), element.getChildText( "addValue" ) );
	}

	
}
