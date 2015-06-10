package config.dangrading;

import java.util.List;

import game.award.AwardInfo;
import game.util.AwardCfgAnalysis;

import org.jdom2.Element;

public class MateAwardTemplet {

	private final byte	 				id;
	
	private final byte 					condition;
	
	private final List<AwardInfo> 		award;
	
	public MateAwardTemplet( Element element ) {
		
		id					= Byte.parseByte( element.getChildText( "id" ) );
		condition			= Byte.parseByte( element.getChildText( "condition" ) );
		award				= AwardCfgAnalysis.getAward( element.getChildText( "award" ) );
	}

	public byte getId() {
		return id;
	}
	
	public byte getCondition(){
		return condition;
	}
	
	public List<AwardInfo> getAward(){
		return award;
	}
	
}
