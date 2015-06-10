package config.fighter;

import java.util.ArrayList;
import java.util.List;

import game.growup.Colour;
import game.log.Logs;

import org.jdom2.Element;

import config.fetter.FetterTemplet;
import config.fetter.FetterTempletCfg;
import config.skill.PassiveSkillTemplet;
import config.skill.PassiveSkillTempletCfg;
import config.skill.accord.SkillTemplet;
import config.skill.accord.SkillTempletCfg;
import config.skill.captain.CaptainSkillTemplet;
import config.skill.captain.CaptainSkillTempletCfg;

/**
 * 英雄模板
 * @author DXF
 *
 */
public class HeroTemplet  {

	public final int							id;
	
	public final String							name;
	
	/**
	 * 血量百分比
	 */
	public final short							hpPercent;
	
	/**
	 * 攻击百分比
	 */
	public final short							attackPercent;
	
	/**
	 * 最高品质
	 */
	public final Colour 						qualityMax;
	
	/**
	 * 攻击类型 (0,物理攻击  1,魔法攻击)
	 */
	public final byte							attackType;
	
	/**
	 * npc类型 （这里是职业）
	 */
	public final Professional					professional;
		
	/**
	 * 闪避
	 */
	public final 	float[]						dodge;	
	
	/**
	 * 普通攻击暴击
	 */	
	public final 	float[]						commonCrit;
	
	/**
	 * 技能攻击暴击
	 */	
	public final 	float[]						skillCrit;
	
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
	 * 队长技能 技能模板
	 */
	public CaptainSkillTemplet			captainSkill;
	
	/**
	 * 各位值 0钱 1经验
	 */
	public final int[]					extraValue;
	
	/**  羁绊 */
	public final List<FetterTemplet> 	fetters;
	
	public HeroTemplet(Element element) {
		
		id 				= Integer.parseInt( element.getChildText( "id" ) );    
		name			= element.getChildText( "name" );
		hpPercent 		= Short.parseShort( element.getChildText( "hpBase" ) );    
		attackPercent 	= Short.parseShort( element.getChildText( "attackBase" ) );    
		qualityMax		= Colour.fromNumber( Integer.parseInt( element.getChildText( "maxColor" ) ) );
		attackType		= Byte.parseByte( element.getChildText( "attackType" ) );
		professional	= Professional.fromNumber( Integer.parseInt( element.getChildText( "professional" ) ) );
		dodge			= analyzing( element.getChildText("dodge") );	
		commonCrit		= analyzing( element.getChildText("commonCrit") );	
		skillCrit		= analyzing( element.getChildText("skillCrit") );	
		String[] content= element.getChildText("extraValue").split(",");
		extraValue		= new int[content.length];
		for( int i = 0; i < content.length; i++ )
			extraValue[i]	= Integer.parseInt( content[i] );
		
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
			skillAttack 	= null;
		}
		
		try {
			String	passive	= element.getChildText( "passiveAttack" );
			passiveSkill 	= passive.isEmpty() ? null : PassiveSkillTempletCfg.getById( Integer.parseInt( passive ) );
		} catch (Exception e) {
			passiveSkill	= null;
		}
		
		try {
			String	captain	= element.getChildText( "captainSkill" );
			captainSkill 	= captain.isEmpty() ? null : CaptainSkillTempletCfg.getById( Integer.parseInt( captain ) );
		} catch (Exception e) {
			captainSkill	= null;
		}
		
		fetters				= analyzingFetter( element.getChildText( "fetterId" ) );
	}
	
	private List<FetterTemplet> analyzingFetter( String childText ) {
		if( childText == null || childText.isEmpty() ) return null;
		if( childText.equals( "无" ) ) return null;
		
		List<FetterTemplet> ret = new ArrayList<FetterTemplet>();
		String[] content = childText.split( "," );
		for( int i = 0; i < content.length; i++ ){
			FetterTemplet fetter = FetterTempletCfg.get( Integer.parseInt( content[i] ) );
			ret.add( fetter );
		}
		return ret;
	}

	// 解析数组
	private float[] analyzing( String content ){
		
		String[] list 	= content.split(",");
		
		float[] data 	= new float[2];
		if( list.length == 2 ){
			data[0] 	= Float.parseFloat( list[0] );
			data[1] 	= Float.parseFloat( list[1] );
		}else{
			Logs.error( "读取英雄配置表出错  list.length == 2！  at=" + id );
		}
		return data;
	}
	
	
}
