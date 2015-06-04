package game.hero;

import game.fighter.Hero;
import game.log.Logs;
import game.util.heroGrowup.HeroGrowupFormula;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import user.UserInfo;

import config.evolution.ConsumeBase;
import config.evolution.EvolutionRuleTemplet;
import config.evolution.EvolutionRuleTempletCfg;

/**
 * 选择排序
 * @author DXF
 */
public enum SelectSort {

	/**
	 * 正常模式
	 */
	NORMAL(1) {
		@Override
		public void run(List<List<Hero>> map, List<Hero> aggregate, UserInfo user ) {
			// 什么都不做
		}
	},
	
	/**
	 * 改变阵型
	 */
	CHANG_THE_SHAPE(2) {
		@Override
		public void run(List<List<Hero>> map, List<Hero> aggregate, UserInfo user ) {
			
			Hero nhero			= null;
			List<Hero> fronts 	= new ArrayList<Hero>();
			List<Hero> backups 	= new ArrayList<Hero>();
			
			for( int i = 0; i < aggregate.size(); i++ ){
				Hero hero		= aggregate.get(i);
				
				if( user.getTeamManager().getCaptain().getUID() == hero.getUID() )
					nhero		= hero;
				else if( user.getTeamManager().get( hero.getUID() ) != null )
					fronts.add( hero );
				else
					backups.add( hero );
			}
			
			aggregate.clear();
			aggregate.add( nhero );
			aggregate.addAll( fronts );
			aggregate.addAll( backups );
		}
		
	},
	
	/**
	 * 进化
	 */
	EVOLUTION(3) {
		@Override
		public void run(List<List<Hero>> map, List<Hero> aggregate, UserInfo user ) {
			
			List<Hero> fronts 	= new ArrayList<Hero>();
			List<Hero> backups 	= new ArrayList<Hero>();
			
			// 这里只是先把 材料足够的放到 aggregate 的前面  安装拍好序的形式来放
			for( int i = 0; i < aggregate.size(); i++ ){
					
					Hero hero						= aggregate.get(i);
					
					// 是否可以进化
					boolean isHave = false;
					
					// 如果可以进化 那么就 看材料 是否足够
					if( hero.isEvolution() ){
						// 这里先拷贝一份来进化
						Hero h							= new Hero( hero );
						h.evolution();
						byte actual 					= HeroGrowupFormula.actualNumberFormula( h.getQuality() );
						EvolutionRuleTemplet templet	= EvolutionRuleTempletCfg.getTempletById( actual );
						if( templet == null ){
							Logs.error( "进化排序  在表格找不到对应数据 id=" + actual );
							return ;
						}
						
						// 获得英雄对应 材料
						List<ConsumeBase> consumes 		= templet.getConsume();
						
						isHave = true;
						for( ConsumeBase consume : consumes ){
							
							int size = 0;
							
							// 获取 这个实际数的 英雄列表
							List<Hero> lists = map.get( consume.colour() );
							
							for( Hero nh : lists ){
								
								if( nh.getUID() == hero.getUID() ) continue;
								if( nh.getNid() != hero.getNid() ) continue;
								
								if( ++size >= consume.num() )
									break;
							}
							
							if( size < consume.num() )
							{
								isHave = false;
								break;
							}
						}
					}else{
					}
					
					if( isHave ){
						fronts.add( hero );
					}else{
						backups.add( hero );
					}
			}

			// 然后再把 备份放进 aggregate
			aggregate.clear();
			aggregate.addAll( fronts );
			aggregate.addAll( backups );
		}
	},
	
	/**
	 * 无团队 排序
	 */
	WITHOUT_THE_CAPTAIN(4) {
		@Override
		public void run( List<List<Hero>> map, List<Hero> aggregate, UserInfo user) {
		
			
		}
	}
	;
	
	private final byte number;
	SelectSort( int n ) {
		number = (byte) n;
	}
	public byte toNumber() {
		return number;
	}
	private static final Map<Byte, SelectSort> numToEnum = new HashMap<Byte, SelectSort>();
	static{
		for( SelectSort a : values() ){
			numToEnum.put( a.number, a );
		}
	}
	public static SelectSort fromNumber( int n ){
		return numToEnum.get( (byte)n );
	}
	
	    
	/** 排序 
	 * @param aggregate 
	 * @param user 
	 * @param type 
	 **/
	public abstract void run( List<List<Hero>> map, List<Hero> aggregate, UserInfo user );
	
}
