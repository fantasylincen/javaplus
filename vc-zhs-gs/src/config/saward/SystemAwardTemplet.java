package config.saward;

import java.util.List;

import org.jdom2.Element;

import game.award.AwardInfo;
import game.award.AwardType;
import game.util.AwardCfgAnalysis;

/**
 * 系统奖励 模板
 * @author DXF
 */
public class SystemAwardTemplet {

	public final String		m_nName;
	public final int 		m_nId;
	
	/** 类型 */
	public final SAwardType 			m_nType;
	
	/** 条件 */
	public final int					m_nCondition;
	
	/** 奖励 */
	public final List<AwardInfo>  		m_nAward;
	
	public SystemAwardTemplet( Element element ){
		
		m_nId			= Integer.parseInt( element.getChildText( "id" ) );
		m_nName			= element.getChildText( "name" );
		
		m_nType 		= SAwardType.valueOf( element.getChildText( "type" ) );
		m_nCondition	= Integer.parseInt( element.getChildText( "condition" ) );
		
		m_nAward		= AwardCfgAnalysis.getAward( element.getChildText( "award" ) );
		
	}
	
	
	public boolean isHaveHero(){
		
		for( AwardInfo  a : m_nAward ){
			if( a.getAward() == AwardType.HERO ) return true;
		}
		
		return false;
	}
}
