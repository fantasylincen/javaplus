package cn.mxz.bossbattle;

import java.util.List;

import cn.mxz.BossRankTemplet;
import cn.mxz.BossRankTempletConfig;
import cn.mxz.VipPrivilegeTempletConfig;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.fighter.Fighter;
import cn.mxz.mission.old.PrizeImpl;
import cn.mxz.script.Script;

import com.google.common.collect.Lists;

import define.D;

/**
 * 计算boss战奖励相关信息
 * @author Administrator
 *
 */
public class ActivityAward {

	/**
	 * 奖励
	 */
	private List<Prize>							prizes;
	private int									reputation;
	private final Challenger					me;
	/**
	 * 此奖励在领奖中心的id
	 */
	private int 								id;


	public ActivityAward(Challenger challenger) {
		this.me = challenger;
		prizes = Lists.newArrayList();
	}
	/**
	 * 根据名次取出相应的模板
	 * @param rank
	 * @return
	 */
	private BossRankTemplet getByRank( int rank ){
		int rankLevel = 0;
		for( BossRankTemplet t : BossRankTempletConfig.getAll() ){
			if( rank >= t.getStep() && rank <= t.getEnd() ){
				return t;
			}
		}
		return null;
	}
	
//	/**
//	 * 获取奖励，移动到奖励中心了
//	 */
//	public void receive( Player player ){
//		for( Prize p : prizes ){
//			p.award( player );
//		}
//	}
	
	public List<Prize> getPrizes() {
		return prizes;
	}
	
	/**
	 * 计算奖励
	 * 声望奖励
	 * 	基础声望值：为玩家对BOSS造成伤害就可以获得基础声望值；
	 * 	击杀声望值：在玩家对BOSS造成最后一击时，该名玩家获得击杀声望；
	 * 	排名声望值：玩家在BOSS伤害排名中进入榜单后根据排名情况获得排名奖励声望值，排名越靠前的玩家获得奖励越多；
	 * 	额外声望值：在BOSS被击杀后，参与BOSS攻击的所有玩家将会获得双倍声望值奖励；
	 * 
	 *  vip一定等级之上，再额外增加50%的声望
	 * 
	 * @param boss		属性
	 * @param rank		我的排名
	 * @param isKiller	自己是否击杀者
	 * @return
	 */
	public List<Prize> calcPrize( Fighter boss, int rank, boolean iskiller ){
		int rate = Script.Boss.getPrestige(WorldFactory.getWorld().getOnlineAll().size());
		int allDamage = me.getAllDamage();
		reputation = (int) ((allDamage/(float)boss.getHpMax()) * rate);//根据伤害百分比计算声望
		if( boss.isDeath() ){
			reputation *= 2;
		}
		BossRankTemplet t = getByRank(rank);		
		reputation += t.getPrestige();
		
		if( iskiller ){
			reputation += D.BOSS_KILLER_SHENGWANG;
		}
		
		
		int vipLevel = me.getUser().getPlayer().getVipLevel();
		int vipPrivilege = VipPrivilegeTempletConfig.get( (byte) vipLevel ).getBossAward();
		if( vipPrivilege == 1){
			reputation *= 1.5f;
		}
		
		prizes.add( new PrizeImpl(D.ID_REPUTATION, reputation) );
		
		//prizes.addAll( PrizeSenderFactory.getPrizeSender().buildPrizes( t.getAwards() ) );
		
		
		
//		int peiyangdan = me.getAddtion().getPeiyangdanFromGold() + me.getPeiyangdanFromChallengeCount(false);
//		prizes.add( new PrizeImpl(D.ID_PEIYANGDAN, peiyangdan) );
		

		
		
		return prizes;
		
	}
	public int getReputation() {
		return reputation;
	}
	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id 要设置的 id
	 */
	public void setId(int id) {
		this.id = id;
	}
}
