package cn.mxz.bossbattle;

import java.util.Collections;
import java.util.List;

import message.S;
import cn.javaplus.util.Util;
import cn.mxz.ActivityTemplet;
import cn.mxz.ActivityTempletConfig;
import cn.mxz.activity.ActivityIds;
import cn.mxz.bossbattle.battle.BossFighter;
import cn.mxz.city.City;
import cn.mxz.city.Messages;
import cn.mxz.util.counter.CounterKey;

import com.google.common.collect.Lists;

import define.D;

/**
 * 参与活动的所有玩家
 * @author Administrator
 *
 */
public class ChallengerManager {	
	
	/**
	 * 道具神符id
	 */
	public static final int					SHENFU_ID	= 130043;
	
	
	/**
	 * 所有参与活动的玩家
	 */
	private List<Challenger>				challengers = Lists.newArrayList();
	
	
	/**
	 * 返回参战玩家的信息，有可能为null
	 * 
	 * @null
	 * @param user
	 * @return
	 */
	public Challenger findByUser( City user ){
		for( Challenger challenger :challengers ){
			if( challenger.getUser() == user ){
				return challenger;
			}
		}
		return null;
	}
	
	public void add( City user ){
		if( findByUser( user) == null ){
			user.getBagAuto().remove( SHENFU_ID, D.BOSS_BATTLE_NEED_SHENFU );
			Challenger challenger = new Challenger( user );
			challengers.add( challenger );
			
			processTaiWan( user);
		}	
	}

	
	/**
	 * 处理台湾的黑市奖励功能
	 */
	private void processTaiWan( City user ) {
		if( D.LANGUAGE != 2 ){
			return;
		}
		ActivityTemplet temp = ActivityTempletConfig.get(ActivityIds.XianShiHeiShi_14);
		
		boolean in = Util.Time.isIn(temp.getTime());
		if( !in){//不在活动中
			return;
		}
		user.getUserCounter().add( CounterKey.HEISHI_BOSS, 1);
		if( user.getUserCounter().get( CounterKey.HEISHI_BOSS) == 2 ){
			user.getPrizeCenter().addPrize(5, D.HEI_SHI_SHUI_JING_JIANG_LI_4 , S.STR60295, S.STR60294);
		}
		
	}

	/**
	 * @return
	 */
	public final List<Challenger> getAllChallengers() {		
		return challengers;
	}
	
	/**
	 * 战斗结束后排序
	 */
	public void sort(){
		Collections.sort(challengers, Challenger.DAMAGE_COMPARATOR );
	}

	/**
	 * 获取挑战者的名次，返回-1说明无此挑战者
	 * @param challenger
	 * @return
	 */
	public int getRank(Challenger challenger) {
		int rank = 1;
		for( Challenger c : challengers ){
			if( c == challenger ){
				return rank;
			}
			rank++;
		}
		

		return -1;
	}


	
	/**
	 * 只考虑造成伤害的情况
	 * @return
	 */
	public List<Challenger> getTopTen() {
		List<Challenger> damageList = Lists.newArrayList();
		for (Challenger challenger : challengers) {
			if( challenger.getAllDamage() > 0 ){
				damageList.add(challenger);
			}
		}
		
		int count = Math.min( damageList.size(), D.BOSS_BATTLE_MAX_RANK_SIZE );
		return challengers.subList( 0, count );	
		
	}

	public void sendAward( BossFighter boss, Challenger killer ) {
//		for (Challenger c : challengers) {
//			c.getAward()..calcPrize(boss, rank, isKiller );
//		}
		String title = Messages.getText(S.S60213);
		String desc = Messages.getText(S.S60214);//c.getUser().getMessageSender().buildText(S.S60214);
		int rank = 1;
		for (Challenger c : challengers) {
			ActivityAward award = c.getAward();
			boolean isKiller = (c == killer);
			award.calcPrize(boss, rank, isKiller );
			//award.receive( c.getUser().getPlayer() );不在立即领奖，而是把奖品发到领奖中心领取
			
			
			int id = c.getUser().getPrizeCenter().addPrize(2,award.getPrizes(), desc,title);//放置奖励到领奖中心
			award.setId(id);
			c.setRank(rank);
			//IAwardInfo awardInfo = buildAwardInfo(boss, killer, c, award.getPrizes(), rank );
			//awards.put( c.getUser().getId(), awardInfo );
			rank++;
			
			//rankInfoList.add( new SimpleChallengerImpl( c, ) );
			//主动发包给客户端
			//Responses r = new Responses(c.getUser());			
			//r.getIBossTransform().responseGetAward( buildAwardInfo(boss, killer, c, award.getPrizes() ) );
			
		}
		//rankInfo = new RankInfoImpl(challengers, killer, killer, rank);
		//buildAwardInfo(boss, killer );
	}
	
//	private buildAwardInfo( BossFighter boss, Challenger killer ){
//		
//		for (Challenger c : challengers) {
//		AwardInfoImpl awardInfo = new AwardInfoImpl();
//		awardInfo.setRankinfo( new RankInfoImpl( getTopTen(), killer, c ) );
//		awardInfo.setBossHpMax( boss.getHpMax() );
//		awardInfo.setBossIsDie( boss.isDeath() );
//		awardInfo.setPrize( c.getAward().getPrizes() );
//		
//		awards.put( c.getUser().getId(), awardInfo );
//		}
//		
//		return awardInfo;
//	}

//	/**
//	 * @return awards
//	 */
//	public Map<String,IAwardInfo> getAwards() {
//		return awards;
//	}	
}
