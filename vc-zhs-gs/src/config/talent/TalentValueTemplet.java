package config.talent;

import game.talent.TalentType;

import java.util.HashMap;
import java.util.Map;

import org.jdom2.Element;

/**
 * 天赋属性模板
 * @author DXF
 *
 */
public class TalentValueTemplet {

	private final short    						id;

	// 属性对应数值
	private final Map<TalentType, Integer> 		values = new HashMap<TalentType, Integer>();
	
	// 需要时间 (表格里面是以分钟为单位   所以我们拿到后要*60 直接就在这先做了)
	private int							needTime;
	
	// 需要金币
	private int							needMoney;
	
	public short getId(){
		return this.id;
	}
	public float getValue( TalentType type ){
		return this.values.get(type);
	}
	public int getNeedTime(){
		return this.needTime;
	}
	public int getNeedMoney(){
		return this.needMoney;
	}
	
	public TalentValueTemplet( Element element ){
		
		this.id 	= Short.parseShort( element.getChildText( "id" ) );
		
		int value 	= Integer.parseInt( element.getChildText( "phyAttack" ) );
		this.values.put( TalentType.PHY_ATTACK, value );
		value 		= Integer.parseInt( element.getChildText( "magicAttack" ) );
		this.values.put( TalentType.MAGIC_ATTACK, value );
		value 		= Integer.parseInt( element.getChildText( "hp" ) );
		this.values.put( TalentType.HP, value );
		value 		= Integer.parseInt( element.getChildText( "phyPenetration" ) );
		this.values.put( TalentType.PHY_PENETRATION, value );
		value 		= Integer.parseInt( element.getChildText( "magicPenetration" ) );
		this.values.put( TalentType.MAGIC_PENETRATION, value );
		value 		= Integer.parseInt( element.getChildText( "phyResist" ) );
		this.values.put( TalentType.PHY_RESIST, value );
		value 		= Integer.parseInt( element.getChildText( "magicResist" ) );
		this.values.put( TalentType.MAGIC_RESIST, value );
		value 		= Integer.parseInt( element.getChildText( "dodge" ) );
		this.values.put( TalentType.DODGE, value );
		value 		= Integer.parseInt( element.getChildText( "hit" ) );
		this.values.put( TalentType.HIT, value );
		value 		= Integer.parseInt( element.getChildText( "crit" ) );
		this.values.put( TalentType.CRIT, value );
		value 		= Integer.parseInt( element.getChildText( "toughness" ) );
		this.values.put( TalentType.TOUGHNESS, value );
		
		try {
			String temp = element.getChildText( "needTime" );
			if( temp.isEmpty() ) temp = "0";
			needTime	= Integer.parseInt( temp ) * 60;
			temp 		= element.getChildText( "needMoney" );
			if( temp.isEmpty() ) temp = "0";
			needMoney	= Integer.parseInt( temp );
		} catch (Exception e) {
			needTime	= 0;
			needMoney	= 0;
		}
		
	}
	
}
