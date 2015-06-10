package config;

import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;

import game.log.Logs;
import config.aa.ActivityAwardTempletCfg;
import config.activity.ActivityTempletCfg;
import config.boot.BootTempletCfg;
import config.critofjob.CritOfJobTempletCfg;
import config.dangrading.LevelPromoteRuleTempletCfg;
import config.dangrading.MateAwardTempletCfg;
import config.equipment.EquipmentTempletCfg;
import config.evolution.EvolutionRuleTempletCfg;
import config.fetter.FetterTempletCfg;
import config.fighter.HeroTempletCfg;
import config.fighter.NpcFighterTempletCfg;
import config.grow.HeroGrowTempletCfg;
import config.grow.UserGrowTempletCfg;
import config.illegal.IllegalCharCfg;
import config.loot.LootScaleTempletCfg;
import config.luckydraw.LuckydrawTempletCfg;
import config.message.MessageTempletCfg;
import config.mission.InstanceTempletCfg;
import config.mission.TollgateTempletCfg;
import config.recharge.RechargeTempletCfg;
import config.robot.MateRobotTempletCfg;
import config.saward.SystemAwardTempletCfg;
import config.skill.PassiveSkillTempletCfg;
import config.skill.accord.SkillTempletCfg;
import config.skill.captain.CaptainSkillTempletCfg;
import config.talent.TalentTempletCfg;
import config.talent.TalentValueTempletCfg;
import config.tefineith.RefineithFireTempletCfg;
import config.vip.VipInfoTempletCfg;
import define.SystemCfg;

/**
 * 配置表 测试
 * @author DXF
 *
 */
public class ConfigMain {
	
	
	
	public static void main( String[]  s ) throws IOException
	{
		PropertyConfigurator.configureAndWatch( "log4jconfig/log4j.properties" );
		
		SystemCfg.init();
		
		readAllCfg();
		
		System.out.println( "骚年你成功了 ！！！" );
		
	}

	/**
	 * 初始化系统配置文件
	 * @throws IOException 
	 */
	public static synchronized void readAllCfg() throws IOException{
		Logs.debug( "--------Start parsing configuration file" );
		
		try{
			// 羁绊
			FetterTempletCfg.init();
			// 玩家属性成长
			UserGrowTempletCfg.init();
			// 英雄属性成长
			HeroGrowTempletCfg.init();
			// 主动技能  此配置表必须先于NpcFighterTempletCfg初始化（每个npc都有相应的技能）
			SkillTempletCfg.init();
			// 被动技能  此配置表必须先于NpcFighterTempletCfg初始化（每个npc都有相应的技能）
			PassiveSkillTempletCfg.init();
			// 队长技能  此配置表必须先于NpcFighterTempletCfg初始化（每个npc都有相应的技能）
			CaptainSkillTempletCfg.init();
			// npc战士 此配置表必须先于MissionTempletCfg初始化，因此无需提前手动调用
			NpcFighterTempletCfg.init();
			// hero
			HeroTempletCfg.init();
			// 副本 
			InstanceTempletCfg.init();
			// 关卡
			TollgateTempletCfg.init();
			// 天赋
			TalentTempletCfg.init();
			// 天赋属性
			TalentValueTempletCfg.init();
			// 进化规则
			EvolutionRuleTempletCfg.init();
			// 抽奖几率
			LuckydrawTempletCfg.init();
			// 系统奖励
			SystemAwardTempletCfg.init();
			// 活动
			ActivityTempletCfg.init();
			// 非法字符
			IllegalCharCfg.init();
			// 职业暴击
			CritOfJobTempletCfg.init();
			// 段位提升规则
			LevelPromoteRuleTempletCfg.init();
			// 匹配奖励
			MateAwardTempletCfg.init();
			// 匹配抢夺金币比例
			LootScaleTempletCfg.init();
			// 公告
			MessageTempletCfg.init();
			// 试炼
			RefineithFireTempletCfg.init();
			// 匹配机器人
			MateRobotTempletCfg.init();
			// VIP信息
			VipInfoTempletCfg.init();
			// 装备信息
			EquipmentTempletCfg.init();
			// 引导赠送
			BootTempletCfg.init();
			// 充值信息
			RechargeTempletCfg.init();
			
			ActivityAwardTempletCfg.init();
			Logs.debug( "-----The configuration file parsing is complete\n" );
		}catch( Exception e ){
			e.printStackTrace();
			System.exit(0);
		}
	}

}
