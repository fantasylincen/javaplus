
package config.fighter;

import org.jdom2.Element;

import config.skill.PassiveSkillTemplet;
import config.skill.PassiveSkillTempletCfg;
import config.skill.accord.SkillTemplet;
import config.skill.accord.SkillTempletCfg;

/**
 * 战士模版
 * @author dxf
 * 2013-5-24 上午10:49:26
 */
public class FighterTempletBase {
	
	public final int							templetId;
	/**
	 * 名字
	 */
	public final String							name;
	
	/**
	 * 描述
	 */
	public final String							desc;
	
	/**
	 * npc类型 （这里是职业）
	 */
	public final Professional					professional;
	
	/**
	 * 基础血量
	 */
	public final int							hpBase;
	
	/**
	 * 攻击类型 (0,物理攻击  1,魔法攻击)
	 */
	public final byte							attackType;
	
	/**
	 * 基础攻击
	 */
	public final int							attackBase;
		
	/**
	 * 闪避
	 */
	public final 	float						dodge;	
	
	/**
	 * 普通攻击暴击
	 */	
	public final 	float						commonCrit;
	
	/**
	 * 技能攻击暴击
	 */	
	public final 	float						skillCrit;
	
	/**
	 * 普通攻击 技能模板
	 */
	public SkillTemplet					commonAttack;
	
	/**
	 * 技能攻击 技能模板
	 */
	public SkillTemplet					skillAttack;
	
	/**
	 * 被动技能 技能模板
	 */
	public PassiveSkillTemplet			passiveSkill;
	
	
	/**
	 * 是否怪物
	 */
	public final boolean						isMonster;
	
	public FighterTempletBase( Element element ) {
		
		templetId 		= Integer.parseInt( element.getChildText( "id" ) ) ;

		isMonster		= templetId / 100000 == 2;
		
		name 			= element.getChildText( "name" );
		desc 			= element.getChildText( "desc" );
		
		professional	= Professional.fromNumber( Integer.parseInt( element.getChildText( "professional" ) ) );
//		qualityBase		= Colour.valueOf( element.getChildText( "qualityBase" ).split(",")[0]  );
		
		hpBase 			= Integer.parseInt( element.getChildText( "hpBase" ) );
		
		attackType		= Byte.parseByte( element.getChildText( "attackType" ) );
		attackBase		= Integer.parseInt( element.getChildText( "attackBase" ) );
				
		dodge 			= Float.parseFloat( element.getChildText( "dodge" ) );
		commonCrit 		= Float.parseFloat( element.getChildText( "commonCrit" ) ); 
		skillCrit 		= Float.parseFloat( element.getChildText( "skillCrit" ) ); 
		
		try {
			String	common	= element.getChildText( "commonAttack" );
			commonAttack 	= common.isEmpty() ? null : SkillTempletCfg.getSkillTempletById( Integer.parseInt( common ) );
		} catch (Exception e) {
			commonAttack	= null;
		}
		
		try {
			String	skill	= element.getChildText( "skillAttack" );
			skillAttack 	= skill.isEmpty() ? null : SkillTempletCfg.getSkillTempletById( Integer.parseInt( skill ) );
		} catch (Exception e) {
			skillAttack		= null;
		}
		
		try {
			String	passive	= element.getChildText( "passiveAttack" );
			passiveSkill 	= passive.isEmpty() ? null : PassiveSkillTempletCfg.getById( Integer.parseInt( passive ) );
		} catch (Exception e) {
			passiveSkill	= null;
		}
			
	}

	@Override
	public String toString() {
		return "FighterTempletBase [templetId=" + templetId + ", name=" + name
				+ ", desc=" + desc + ", hpBase=" + hpBase + ", attackType=" + attackType
				+ ", attackBase=" + attackBase + ", dodge=" + dodge
				+ ", commonCrit=" + commonCrit + ", skillCrit=" + skillCrit
				+ ", commonAttack=" + commonAttack 
				+ ", skillAttack=" + skillAttack 
				+ ", passiveSkill=" + passiveSkill 
				+ "]";
	}
	
	
	
}
