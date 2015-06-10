package config.boot;

import java.util.List;

import game.award.AwardInfo;
import game.util.AwardCfgAnalysis;
import lombok.Data;

import org.jdom2.Element;

public class BootTemplet {

	private final short					id;
	
	private final List<AwardInfo>		award;
	
	public BootTemplet(Element element) {
		
		id			= Short.parseShort( element.getChildText( "id" ) );
		award		= AwardCfgAnalysis.getAward( element.getChildText( "award" ) );
	}

	public short getId() {
		return id;
	}

	public List<AwardInfo> getAward() {
		return award;
	}

}
