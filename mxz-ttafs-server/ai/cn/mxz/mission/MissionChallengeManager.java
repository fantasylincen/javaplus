package cn.mxz.mission;


import java.util.List;

import message.S;
import cn.javaplus.random.Fetcher;
import cn.javaplus.util.Util;
import cn.javaplus.util.Util.Collection;
import cn.mxz.MapTemplet;
import cn.mxz.MissionMapTemplet;
import cn.mxz.MissionMapTempletConfig;
import cn.mxz.VipPrivilegeTempletConfig;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.battle.MissionBattle;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.mission.type.MissionBattleImpl;
import cn.mxz.util.counter.CounterKey;

import com.google.common.collect.Lists;

import db.dao.impl.DaoFactory;
import db.dao.impl.MissionChallengeDao;
import db.domain.MissionChallenge;
import define.D;

/**
 * 副本boss直接挑战
 * @author Administrator
 *
 */
public class MissionChallengeManager {
	private final City										user;
	private final MissionChallengeDao 	db = DaoFactory.getMissionChallengeDao();
	List<MissionChallenge>									challenges;

	int[]													currentBossCamp;

	public MissionChallengeManager( City user ) {
		this.user = user;
		challenges = db.findByUname(user.getId() );

	}

	MissionBattle challenge( int missionId, boolean isBranch ){
		MissionChallenge mc = get( missionId );
		if( mc == null ){
			throw new OperationFaildException(S.S10165, missionId);
		}

		check( mc, isBranch );
		MissionBattle battle = doBattle( mc, isBranch );
		if( battle.isWin() ){
			user.getUserCounter().add( CounterKey.BOSS_CHALLENGE, 1, missionId );
		}

		addTodayChallengeCount( missionId, isBranch );
		return battle;
	}

	private MissionBattle doBattle(MissionChallenge mc, boolean isBranch) {
		MapTemplet templet = MissionMapTempletConfig.get( mc.getMissionId() );
		if( currentBossCamp == null ){
			throw new OperationFaildException(S.S10217 );
		}
		int[] camp = currentBossCamp;
		
		MissionBattle battle = new MissionBattleImpl(camp, user, templet.getId(), !isBranch, true,true);

		battle.fighting();

		return battle;

	}

	public int[] getEnemy( MissionMapTemplet templet, boolean isBranch ){
//		int size = templet.getMonsterNumber();
//		int[] monsterIdS = new int[size];
//
//		int[] enemy = new int[2];
//		if (isBranch) {
//			enemy[0] = Integer.parseInt(templet.getLineBossId());
//		} else {// 主线
//			enemy[0] = Integer.parseInt(templet.getBossId());
//		}
//		enemy[1] = 4;//位置站在中间
//		return enemy;
		int size = templet.getMonsterNumber();
		int[] monsterIdS = new int[size];
		if (isBranch) {
			monsterIdS[0] = Integer.parseInt(templet.getLineBossId());
		} else {// 主线
			monsterIdS[0] = Integer.parseInt(templet.getBossId());
		}
		int[] enemys = buildEnemys(templet, size - 1);// 随机生成小怪
		System.arraycopy(enemys, 0, monsterIdS, 1, enemys.length);

		List<Integer>		positions	= Lists.newArrayList(1, 3, 4, 5, 7);
		cn.javaplus.util.Util.Collection.upset(positions);
		int[] camp = new int[monsterIdS.length * 2];
		for (int i = 0; i < monsterIdS.length; i++) {
			camp[i * 2] = monsterIdS[i];
			camp[i * 2 + 1] = positions.get(i);
		}
		currentBossCamp = camp;
		return currentBossCamp;


	}
	private int[] buildEnemys(MapTemplet templet, int size ) {
		int[] monsters = new int[size];
		String[] monsterStr = templet.getMonsterId().split(",");
		for (int i = 0; i < size; i++) {
			monsters[i] = cn.javaplus.util.Util.Collection.getRandomOne(Collection.getIntegers(templet.getMonsterId()));
		}
		return monsters;
	}

	private void check( MissionChallenge mc, boolean isBranch ){


		if( isBranch && !mc.getBranchIsCross()){
			throw new OperationFaildException(S.S10168, mc.getMissionId());
		}

		int count = getTodayChallengeCount( mc.getMissionId(), isBranch );
		if( count >= D.FREE_BOSS_CHALLENGE ){
			throw new OperationFaildException(S.S10218);
		}

		user.getPlayer().reduce( PlayerProperty.PHYSICAL, 1 );
	}

	/**
	 * 返回某个关卡当日的挑战次数
	 * @param isBranch
	 * @param misssionId
	 * @return
	 */
	int getTodayChallengeCount( int missionId, boolean isBranch ){
		int arg = isBranch ? 1 : 0;
		return user.getUserCounter().get( CounterKey.BOSS_CHALLENGE, missionId, arg );
	}


	void addTodayChallengeCount( int missionId, boolean isBranch ){
		int count = getTodayChallengeCount( missionId, isBranch );
		int arg = isBranch ? 1 : 0;
		user.getUserCounter().set( CounterKey.BOSS_CHALLENGE, count+1, missionId, arg );
	}


	/**
	 * 返回某个关卡当日的重置挑战轮数，每重置一次，就可以获得D.FREE_BOSS_CHALLENGE挑战次数
	 * @param missionId
	 * @return
	 */
	int getTodayChallengeRound( int missionId, boolean isBranch ){
		int arg = isBranch ? 1 : 0;
		return user.getUserCounter().get( CounterKey.BOSS_CHALLENGE_ROUND, missionId,arg );
	}

	void resetTodayChallengeRound( int missionId,boolean isBranch ){
		int arg = isBranch ? 1 : 0;
		int vip = user.getVipPlayer().getLevel();
		int maxResetCount = VipPrivilegeTempletConfig.get( (byte)vip ).getDareTimes();
		int resetCount = getTodayChallengeRound( missionId, isBranch );
		if( resetCount >= maxResetCount ){
			System.err.println( user.getId() + "超过最大重置次数。当前重置次数:" + resetCount + "最大重置次数" + maxResetCount );
			return;
		}
		resetCount++;
		user.getUserCounter().set( CounterKey.BOSS_CHALLENGE_ROUND, resetCount, missionId,arg );
		user.getUserCounter().set( CounterKey.BOSS_CHALLENGE, 0, missionId,arg );//重置可挑战次数
	}


	public static void main(String[] args) {

	}

	MissionChallenge get( int missionId ){
		for( MissionChallenge mc : challenges ){
			if( mc.getMissionId() == missionId ){
				return mc;
			}
		}
		return null;
	}

	/**
	 * 处理数据库相关内容
	 *
	 * @param missionId
	 * @param branchIsCross
	 */
	public void setCross(int missionId, Boolean isBranch ) {
		MissionChallenge mc = get( missionId );
		if( mc == null ){
			MissionChallenge o = db.createDTO();
			if(isBranch){
				o.setBranchIsCross( true );
			}
			else{
				o.setMainIsCross( true );
			}
			o.setMissionId( missionId );
			o.setUname( user.getId() );
			challenges.add( o );
			db.add(o);
		}
		else {
			
			if(isBranch){
				if( mc.getBranchIsCross( ) ){
					
					return;
				}
				mc.setBranchIsCross( true );
			}
			else{
				if( mc.getMainIsCross( ) ){
					
					return;
				}
				mc.setMainIsCross( true );
			}

			db.update( mc );

		}
	}

//	public void refreshChallenge(int missionId) {
//		// TODO 自动生成的方法存根
//
//	}

	/**
	 * 指定的关卡的boss是否通过
	 * @param missionId
	 * @return
	 */
	public boolean isCross( int missionId, boolean isBranch ){
		MissionChallenge mc = get( missionId );
		if( mc == null ){
			return false;
		}
		if( isBranch ){
			return mc.getBranchIsCross();
		}
		else{
			return mc.getMainIsCross();
		}
	}
	
	/**
	 * 用一个数组返回章节进度，[总boss数，完成boss数]
	 * @param chapterId
	 * @return
	 */
	public int[] getCompletion( int chapterId ){
		int[] ret = new int[2];
		List<Integer> missionList = getMissionArray(chapterId);
		int allBoss = missionList.size() * 2;
		ret[0] = chapterId == 1 ? allBoss - 1 : allBoss;//仅仅只有第一关没有没有支线boss，因此要减1
		int crossCount = calcCrossBoss(missionList);
		//ret[1] = chapterId == 1 ? crossCount - 1 : crossCount;//仅仅只有第一关没有没有支线boss，因此要减1
		ret[1] = crossCount;
		return ret;
		
	}
	
	/**
	 * 获取此章节下的所有mission
	 * @param chapterId
	 * @return
	 */
	private List<Integer> getMissionArray( int chapterId ){
		List<MissionMapTemplet> all = MissionMapTempletConfig.findByChapterId(chapterId);
		Fetcher<MissionMapTemplet, Integer> fetcher = new Fetcher<MissionMapTemplet, Integer>() {

			@Override
			public Integer get(MissionMapTemplet t) {
				return t.getId();
			}
		};
		
		return Util.Collection.getListByOneFields(fetcher, all);
	}
	
	private int calcCrossBoss( List<Integer> missionList ){
		int ret = 0;
		for (Integer missionId : missionList) {
			MissionChallenge mc = get( missionId );
			if( mc != null ){
				if( mc.getMainIsCross() ){
					ret++;
				}
				if( mc.getBranchIsCross() ){
					ret++;
				}
			}
		}
		return ret;
	}
	
}
