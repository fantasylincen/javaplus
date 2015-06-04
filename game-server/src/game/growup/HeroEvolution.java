package game.growup;

import java.util.List;

import config.evolution.ConsumeBase;
import config.evolution.EvolutionRuleTemplet;
import config.evolution.EvolutionRuleTempletCfg;
import util.ErrorCode;
import game.fighter.Hero;
import game.log.Logs;
import game.util.heroGrowup.HeroGrowupFormula;

/**
 * 英雄进化
 * @author DXF
 *
 */
public class HeroEvolution {

	// 当前进化英雄
	private final Hero 		hero;
	
	public HeroEvolution( Hero hero ) {
		this.hero = hero;
	}

	/**
	 * 获得预进化结果
	 * @param list  
	 * @param trophy 
	 * @return
	 */
	public EvolutionInfo getReslut( List<Hero> list, int trophy )
	{
		EvolutionInfo info 	= new EvolutionInfo();
		info.code			= ErrorCode.SUCCESS;
		info.quality		= hero.getQuality();
		
		if( !hero.isEvolution() ){
			info.code		= ErrorCode.HEROGROWUP_NOT_EVOLUTION;
			// 表示 是正事进化 所以这里返回
//			if( list == null )
			return info;
		}
		
		Hero h				= new Hero( hero );
		h.evolution();
		
		byte actual 		= HeroGrowupFormula.actualNumberFormula( h.getQuality() );
		info.maxLevel		= HeroGrowupFormula.levelLimit[actual][1];
		info.quality		= h.getQuality();
		info.attack			= h.getAttack();
		info.hp				= h.getHpMax();
		info.carryLv		= h.getCarryLevel();
		
		EvolutionRuleTemplet templet	= EvolutionRuleTempletCfg.getTempletById( actual );
		if( templet == null ){
			Logs.error( "进化英雄时出错  在表格找不到对应数据 id=" + actual );
			info.code		= ErrorCode.UNKNOW_ERROR;
			return info;
		}
		info.needGold 		= templet.needGold();

		
		// 如果是需要奖杯的 
		if( templet.getNeedTrophy() != 0 ){
			
			info.needTrophy	= templet.getNeedTrophy();
			
			if( trophy < info.needTrophy )
				info.code	= ErrorCode.HEROGROWUP_NOT_MATERIAL;
			
			return info;
		}
		
		// 如果个数不一样 那说明有问题
		if( templet.count() != list.size() )
		{
			info.code		= ErrorCode.UNKNOW_ERROR;
			return info;
		}
		
		//--------- 下面检查 是有材料进化 ---------
		// 拷贝一份 材料
		List<ConsumeBase> consumes 		= templet.copyConsume();
		
		for( ConsumeBase consume : consumes ){
			
			for( Hero temp : list ){
				
				// 看是否是需要的品质
				if( consume.colour() != temp.getQuality().toNumber() )
					continue;
				if( consume.level() != temp.getQuality().getLevel() )
					continue;
				
				if( consume.cutNum() <= 0 )
					break;
			}
			
			if( consume.num() > 0 ){
				info.code		= ErrorCode.HEROGROWUP_NOT_MATERIAL;
				return info;
			}
		}
		
		return info;
	}


}
