package cn.mxz.mission;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.javaplus.random.Fetcher;
import cn.javaplus.util.Util;
import cn.mxz.base.prize.HuanHunDan;
import cn.mxz.base.service.AbstractService;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.battle.CampBuilder;
import cn.mxz.battle.MissionBattle;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;
import cn.mxz.fighter.FighterSnapshoot;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.handler.MissionService;
import cn.mxz.mission.star.MissionStar;
import cn.mxz.mission.star.MissionStarBuilder;
import cn.mxz.mission.star.MissionStarManager;
import cn.mxz.mission.star.Reward;
import cn.mxz.mission.type.DemonCamp2;
import cn.mxz.mission.type.DemonInCamp;
import cn.mxz.mission.type.DemonInCampResolver;
import cn.mxz.mission.type.EventType;
import cn.mxz.mission.type.MissionWarSituationBuilder;
import cn.mxz.prop.BoxBuilder;
import cn.mxz.protocols.user.UserP.UserPro;
import cn.mxz.protocols.user.battle.WarSituationP.CampPro;
import cn.mxz.protocols.user.god.FighterP.FighterPro;
import cn.mxz.protocols.user.mission.BoxP.BoxPro;
import cn.mxz.protocols.user.mission.MissionP.MissionChallengePro;
import cn.mxz.protocols.user.mission.MissionP.MissionNodePro;
import cn.mxz.protocols.user.mission.MissionP.MissionPro;
import cn.mxz.protocols.user.mission.MissionP.MissionPro.Builder;
import cn.mxz.protocols.user.mission.MissionP.MissionWarSituationPro;
import cn.mxz.protocols.user.mission.MissionP.RandomAddFriendPro;
import cn.mxz.team.builder.FighterBuilder;
import cn.mxz.user.builder.UserBuilder;
import cn.mxz.user.team.Formation;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.debuger.Debuger;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import com.lemon.commons.user.IUser;

import db.domain.UserMission;
import define.D;

@Component("missionService")
@Scope("prototype")
public class MissionServiceImpl extends AbstractService implements MissionService {

	@Override
	public void enter(int mapId) {
		IMissionManager m = getCity().getMission();
		m.enter(mapId);
	}

	@Override
	public void giveUp(int missionId) {

		FighterSnapshoot s = new FighterSnapshoot(getCity());
		s.snapshoot();

		IMissionManager mission = getCity().getMission();
		mission.giveUp(missionId);

		s.snapshoot();
	}
	
	/**
	 * 如果此关卡玩家仅仅通了 主线，支线未通过，则允许玩家直接跳到分叉点上
	 * 此时，当前关卡必须不为空
	 */
	@Override
	public void directJumpBranch(){
		IMissionManager mm = getCity().getMission();
		mm.directJumpBranch();
		
	}

	@Override
	public MissionPro getMissionData() {
		IMissionManager mm = getCity().getMission();
		return buildMissionPro(mm.getCurMission());
	}

	private MissionPro buildMissionPro(IMission m) {

		Builder b = MissionPro.newBuilder();
		if (m == null) {// 玩家没有进行闯关
			b.setMissionId(0);
		} else {

			b.setMissionId(m.getMissionId());

			// int nodeIndex = MissionPathCfg.getMapTemplet( m.getMissionId()
			// ).getById( m.getCurNode().getNode() ).getIndex();

			b.setPersonIndex(m.getCurrentNode().getTemplet().getIndex());

			b.setPersonPath(m.getCurrentNode().getTemplet().getPath());
			b.setCanJumpBranch( m.canJumpBranch() );

			//System.out.println( "当前节点是" + m.getCurrentNode().getTemplet().getIndex() );
			for (MissionNode node : m.getAllNodeS()) {
//				System.out.println( node );
				if (!node.isDone) {
					// if( node.getTemplet().isBranch() &&
					// node.getTemplet().isLast() ){
					// System.err.println( "abcd" +
					// node.getEvent().getMissionArg() + ":" + node.isDone + ":"
					// + node.getTemplet().getPath() + "_" +
					// node.getTemplet().getIndex() );
					// }
					cn.mxz.protocols.user.mission.MissionP.MissionNodePro.Builder newBuilder = MissionNodePro.newBuilder();
					newBuilder.setIndex(node.getTemplet().getIndex());
					newBuilder.setPath(node.getTemplet().getPath());
					newBuilder.setArg(node.getEvent().getBrief());
					newBuilder.setType(node.getEvent().getType().toNum());
					b.addNodes(newBuilder.build());
					//System.out.println( "有效的线路是 :" + node.getTemplet().getIndex() + ":" + node.getTemplet().getPath()  + ":内容为:" +  node.getEvent().getMissionArg());
				}
			}
		}
		return b.build();
	}

	@Override
	public CampPro getDemonCamp(int path) {

		IMissionManager mission = getCity().getMission();

		int[] demonIds = mission.getDemonCamp(path);

		List<DemonInCamp> demons = new DemonInCampResolver().resolve(demonIds, mission.getCurMissionId(), false, false, getCity());

		DemonCamp2 camp = new DemonCamp2(demons);

		return new CampBuilder().build(camp);
	}

	@Override
	public void encounterQuestion(int path) {

		IMissionManager mission = getCity().getMission();
		mission.run(path, EventType.RANDOM);
	}

	@Override
	public MissionWarSituationPro fighting(int path) {
		FighterSnapshoot s = new FighterSnapshoot(getCity());
		s.snapshoot();

		IMissionManager mission = getCity().getMission();
		MissionBattle b = (MissionBattle) mission.run(path, EventType.BATTLE);
		// IEvent e = mission.getCurMission().getNextNode().getEvent();
		// if(e.getType() != EventType.BATTLE ){
		// System.out.println( "这不是战斗:" + e.getType() + e.getType() );
		// }
		s.snapshoot();
		return new MissionWarSituationBuilder().build(b);
	}

	@Override
	public RandomAddFriendPro randomAddFriend() {
		IUser user = null;
		while (true) {
			List<IUser> onlineUsers = new ArrayList<IUser>(WorldFactory.getWorld().getOnlineAll());
			if (onlineUsers.size() <= 1) {
				List<IUser> allUsers = new ArrayList<IUser>(WorldFactory.getWorld().getAll());
				user = cn.javaplus.util.Util.Collection.getRandomOne(allUsers);
			} else {
				user = cn.javaplus.util.Util.Collection.getRandomOne(onlineUsers);
			}

			if (!user.getId().equals(getCity().getId())) {
				break;
			}

		}

		RandomAddFriendPro.Builder ret = RandomAddFriendPro.newBuilder();
		if (user != null) {
			City city = (City) user;
			UserPro buildOfUserPro = new UserBuilder().build(city);

			PlayerCamp selected = city.getFormation().getSelected();

			List<Hero> fightersFront = selected.getFighters();

			for (Hero hero : fightersFront) {
				FighterPro gp = new FighterBuilder().build(hero);
				ret.addGod(gp);
			}

			ret.setUser(buildOfUserPro);

		}

		return ret.build();

	}

	@Override
	public void crossEmpty(int path) {
		IMissionManager mission = getCity().getMission();
		mission.run(path, EventType.EMPTY);
	}

	@Override
	public BoxPro openChests() {
		System.out.println( "胜利通关领奖包qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq" );
		IMissionManager missionManager = getCity().getMission();
		List<Prize> e = missionManager.successCrossMission();

		return new BoxBuilder().build(e, getPlayer(), 1);
	}

	@Override
	public int getBoxes() {
		return getBoxesCount();
	}

	private int getBoxesCount() {
		IMissionManager mission = getCity().getMission();
		IMission curMission = mission.getCurMission();
		List<Integer> all = curMission.getPrizeBox();
		return all.size();
	}

	@Override
	public BoxPro openChest(int path) {
		IMissionManager mission = getCity().getMission();
		// @SuppressWarnings("unchecked")
		return (BoxPro) mission.run(path, EventType.PRIZE);
		// return new BoxBuilder().build(ls, getPlayer());
	}

	//
	// @Override
	// public int saveChest(int path) {
	// IMissionManager mission = getCity().getMission();
	// mission.save(path);
	// return getBoxesCount();
	// }

	@Override
	public int getRebirthSec() {
//		PlayerHero player = getCity().getTeam().getPlayer();
//		return player.getReviseRemainSec();
		return 0;
	}

	@Override
	public void backMainBranch() {
		IMissionManager mission = getCity().getMission();
		mission.backMainBranch();
	}

	@Override
	public String getStarByCapterId(int chapterId) {
		MissionStarManager m = getCity().getMission().getStarManager();
		List<MissionStar> all = m.getStarByCapterId(chapterId);
		return new MissionStarBuilder().build(all);
	}

	@Override
	public String getStarBySceneId(int sceneId) {
		MissionStarManager m = getCity().getMission().getStarManager();
		List<MissionStar> all = m.getStarBySceneId(sceneId);
		return new MissionStarBuilder().build(all);
	}

	@Override
	public String getStarByMissionId(int missionId) {
		MissionStarManager m = getCity().getMission().getStarManager();
		List<MissionStar> all = Lists.newArrayList(m.getStarByMissionId(missionId));
		return new MissionStarBuilder().build(all);
	}

	@Override
	public Boolean branchIsCross() {
		IMissionManager mission = getCity().getMission();
		return mission.branchIsCross();
	}

	@Override
	public MissionWarSituationPro challenge(int missionId, Boolean isBranch) {
		FighterSnapshoot s = new FighterSnapshoot(getCity());
		s.snapshoot();
		IMissionManager mission = getCity().getMission();
		MissionBattle mb = mission.challenge(missionId, isBranch);
		
		s.snapshoot();
		
		return new MissionWarSituationBuilder().build(mb);
	}

	@Override
	public void refreshChallenge(int missionId, Boolean isBranch) {
		IMissionManager mission = getCity().getMission();
		mission.refreshChallenge(missionId, isBranch);
	}

	@Override
	public CampPro getBossDemonCamp(int missionId, Boolean isBranch) {
		IMissionManager mission = getCity().getMission();

		int[] demonIds = mission.getBossCamp(missionId, isBranch);

		List<DemonInCamp> demons = new DemonInCampResolver().resolve(demonIds, missionId, true, !isBranch, getCity());

		DemonCamp2 camp = new DemonCamp2(demons);

		return new CampBuilder().build(camp);
	}

	@Override
	public int getStarReceivedStep(int chapterId) {
		IMissionManager mission = getCity().getMission();
		MissionStarManager sm = mission.getStarManager();
		return sm.getStarReceivedStep(chapterId);
	}

	@Override
	public void receivedStarReward(int chapterId) {
		IMissionManager mission = getCity().getMission();
		MissionStarManager sm = mission.getStarManager();
		sm.receivedStarReward(chapterId);
	}

	@Override
	public Boolean branchCross(int missionId) {
		IMissionManager mission = getCity().getMission();
		return mission.branchIsCross(missionId);
	}

	@Override
	public void raiseAllUp() {
		Formation f = getCity().getFormation();
		PlayerCamp s = f.getSelected();
		List<Hero> fs = s.getFighters();

		int count = 0;
		for (Hero hero : fs) {
			if (hero.isDeath()) {
				count++;
			}
		}
		checkProp(D.ID_HUAN_HUN_DAN, count);
		removeProp(D.ID_HUAN_HUN_DAN, count);

		for (Hero hero : fs) {

			if (hero.isDeath()) {
				HuanHunDan hhd = new HuanHunDan(hero.getTypeId());
				hhd.award(getPlayer());
			}

		}
	}

	private void removeProp(int huanHunDanId, int count) {
		getCity().getBagAuto().remove(huanHunDanId, count);
	}

	private void checkProp(int huanHunDanId, int count) {
		getCity().getChecker().checkProp(huanHunDanId, count);
	}

	@Override
	public String getCurrentStarByMissionId(int missionId) {
		MissionStarManager m = getCity().getMission().getStarManager();
		List<MissionStar> all = Lists.newArrayList(m.getCurrentStarByMissionId(missionId));
		return new MissionStarBuilder().build(all);
	}

	@Override
	public String refreshPosition() {
		IMissionManager mission = getCity().getMission();
		IMission curMission = mission.getCurMission();
		MissionNode currentNode = curMission.getCurrentNode();
		String s = currentNode.getTemplet().getPath() + "," + currentNode.getTemplet().getId();
		return s;

	}

	@Override
	public MissionChallengePro getChallengeData(int missionId) {
		IMissionManager mission = getCity().getMission();
		int ret[] = mission.getChallengeData(missionId);
		cn.mxz.protocols.user.mission.MissionP.MissionChallengePro.Builder builder = MissionChallengePro.newBuilder();
		builder.setTodayChallengeCount(ret[0]);
		builder.setTodayResetRoundCount(ret[1]);
		builder.setTodayChallengeCount1(ret[2]);
		builder.setTodayResetRoundCount1(ret[3]);
		return builder.build();
	}

	@Override
	public String getStarReceivedStep2(int chapterId) {

		IMissionManager mission = getCity().getMission();
		MissionStarManager sm = mission.getStarManager();

		JSONArray ar = new JSONArray();
		ar.add(new StarStatus(chapterId, Reward.TYPE.PERCENT_30, sm));
		ar.add(new StarStatus(chapterId, Reward.TYPE.PERCENT_60, sm));
		ar.add(new StarStatus(chapterId, Reward.TYPE.PERCENT_100, sm));

		Debuger.debug("MissionServiceImpl.getStarReceivedStep2()" + ar);

		return ar.toString();
	}

	@Override
	public void receivedStarReward2(int chapterId, int percent) {
		IMissionManager mission = getCity().getMission();
		MissionStarManager sm = mission.getStarManager();
		sm.receivedStarReward(chapterId, percent);
	}

	@Override
	public void resetMopingUp(int missionId, int type) {
		MoppingUp m = getCity().getMission().getMoppingUp();
		m.resetCount(missionId, type);
	}

	@Override
	public String mopingUp(int missionId, int type, int count) {
		MoppingUp m = getCity().getMission().getMoppingUp();
		
		FighterSnapshoot s = new FighterSnapshoot(getCity());
		s.snapshoot();
		
		MoppingUpResult r = m.moppingUp(missionId, type, count);
		
		s.snapshoot();
		
		String ret = r.isPlayerLevelUp() + "|" + r.isTriggerShenmo();
		// for (Prize prize : r.getPrizes()) {
		// ret += prize.getId();
		// ret += ",";
		// ret += prize.getCount();
		// ret += ",";
		//
		// }
		// if()

		Fetcher<Prize, String> fetcher = new Fetcher<Prize, String>() {

			@Override
			public String get(Prize t) {
				return t.getId() + "," + t.getCount();
			}
		};

		String linkWith = Util.Collection.linkWith(",", r.getPrizes(), fetcher);

		// 玩家是否升级,|id,数量,id,数量....
		// true|1111,2,5521,2
		return ret + "|" + linkWith;

	}

	@Override
	public String getMopingUpData(int missionId, int type) {

		MoppingUp m = getCity().getMission().getMoppingUp();
		int[] data = m.getData(missionId, type);
		
		String ret = Ints.join( ",", data );
		return ret;
	}

	@Override
	public String getRunningMission() {
		IMissionManager mission = getCity().getMission();
		StringBuilder sb = new StringBuilder();
		for( UserMission um : mission.getRunningMission() ){
			sb.append( um.getMissionId() + "," );
		}
		if( sb.length() > 1 ){
			return sb.substring( 0, sb.length() - 1 );
		}
		return sb.toString();
		
	}

	@Override
	public void resetMopingUpColdTime() {
		MoppingUp m = getCity().getMission().getMoppingUp();
		m.resetMopingUpColdTime();
		
	}

	@Override
	public String getCrossSituation(String missionId) {
		if( missionId.isEmpty() ){
			return "";
		}
		MissionManagerImpl mission = (MissionManagerImpl) getCity().getMission();
		String ret = "";
		for( String mId : missionId.split(",")){
			int id = Integer.parseInt(mId);
			ret += mId + ",";
			if( mission.mainIsCross(id) ){
				ret += "1,";
			}
			else{
				ret += "0,";
			}
			if( mission.branchIsCross(id) ){
				ret += "1|";
			}
			else{
				ret += "0|";
			
			}
		}
		
		if( ret.length() > 1 ){
			ret =  ret.substring( 0, ret.length() - 1 );
		}
		return ret;
	}

}
