package config.grow;

import org.jdom2.Element;

/**
 * 英雄属性成长 模板
 * @author DXF
 *
 */
public class HeroGrowTemplet {

	// 等级
	private final short 		level;
	
	// 所需经验
	private final int 			exp;
	
	
	public HeroGrowTemplet( Element element )
	{
		this.level 			= Short.parseShort( element.getChildText( "id" ) );
		this.exp 			= Integer.parseInt( element.getChildText( "exp" ) );
	}
	
	/** 获得等级 */
	public short getLevel(){
		return this.level;
	}
	
	/** 获得 所需经验 */
	public int getExp(){
		return this.exp;
	}
	
	public String toString() {
		return "UserTemplet [level=" + level + ", exp=" + exp + "]";
	}
}
