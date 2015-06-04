package cn.mxz.mission;

import groovy.ui.SystemOutputInterceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import message.S;
import cn.javaplus.util.Util;
import cn.javaplus.util.Util.Array;
import cn.mxz.ActivityTemplet;
import cn.mxz.ActivityTempletConfig;
import cn.mxz.CopyTemplet;
import cn.mxz.CopyTempletConfig;
import cn.mxz.MissionMapTemplet;
import cn.mxz.MissionMapTempletConfig;
import cn.mxz.activity.ActivityIds;
import cn.mxz.base.config.ServerConfig;
import cn.mxz.base.exception.IllegalOperationException;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.base.exception.UnImportentException;
import cn.mxz.battle.MissionBattle;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.events.EventDispatcher2Impl;
import cn.mxz.events.Events;
import cn.mxz.events.MissionEndEvent;
import cn.mxz.events.mission.MissionEnterEvent;
import cn.mxz.listeners.ClearStarListener;
import cn.mxz.mission.old.events.EnterEvent;
import cn.mxz.mission.star.MissionStarManager;
import cn.mxz.mission.star.MissionStarManagerImpl;
import cn.mxz.mission.type.EventType;
import cn.mxz.mission.type.IEvent;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;

import db.dao.impl.DaoFactory;
import db.domain.UserMission;
import db.domain.UserMissionImpl;
import define.D;

public class MissionManagerImpl extends EventDispatcher2Impl implements IMissionManager {
	
	class ComparatorRunningMission implements Comparator<UserMission>{

		 @Override
		public int compare(UserMission arg0, UserMission arg1) {
			 return arg0.getCreateDate() - arg1.getCreateDate();
		 }
	}

	/**
	 *  允许同时闯关的最大次数!!!!【千万不能改成1】
	 *  因为程序设定当前最新关卡是不被清空的，如果只设定1关，会造成死循环
	 *  详细查看MissionManagerImpl.enter()
	 */	
	private static final int				MAX_CROSS_MISSION_COUNT	= 20;

	// private UserMission curUMDto = null;
	private IMission						curMission				= null;
	private final City						user;

	private List<UserMission>				runningmission;
	// private IMission curMission;

	private MissionStarManager				starManager;

	private final MissionChallengeManager	mcManager;

	/**
	 * 扫荡
	 */
	private final MoppingUp					moppingUp;

	public MissionManagerImpl(City user) {
		this.user = user;
		runningmission = DaoFactory.getUserMissionDao().findByUname(user.getId());
		if (runningmission == null) {
			runningmission = Lists.newArrayList();
			// missionList.add( createDto() );
		}
		else{
			Collections.sort(runningmission, new ComparatorRunningMission() );
		}
		
		mcManager = new MissionChallengeManager(user);
		moppingUp = new MoppingUp(user, mcManager );
		addListener(new ClearStarListener());
		int curMissionId = user.getUserCounterHistory().get(CounterKey.MISSION_CURRENT_ID);
		if (curMissionId != 0) {
			enter(curMissionId);
		}
	}

	private UserMission getMission(int missionId) {
		// IMission mission =
		// int curMissionId = missionId;
		// if (curMissionId != 0) {
		// int nodeIndex = missionDto.getNodeIndex();
		// curMission = new Mission(curMissionId, user, nodeIndex, false,
		// missionDto.getMissionId());
		// }
		for (UserMission um : runningmission) {
			if (um.getMissionId() == missionId) {
				return um;
			}

		}
		return null;

	}

	@Override
	public IMission enter(int missionId) {

//		if (user.getTeam().getPlayer().isDeath()) {
//			throw new SureIllegalOperationException("主角已经死亡，无法闯关");
//		}
		if (missionId < 0 || getMaxMissionId() < missionId - 1) {
			System.err.println( user.getId() + "闯关卡id有问题，id:" + missionId);
			user.getUserCounterHistory().set(CounterKey.MISSION_CURRENT_ID, 0);// 清空玩家的当前关卡
			return null;
		}
		
		MissionMapTemplet templet = MissionMapTempletConfig.get(missionId);
		if( templet == null ){
			user.getUserCounterHistory().set(CounterKey.MISSION_CURRENT_ID, 0);// 清空玩家的当前关卡
			System.err.println( user.getId() + "关卡模板不存在:" + missionId);
			return null;
		}
		
		if( ServerConfig.getServerId() != 59001){//遗留问题，仅仅针对盘古开天才无视此限制
			if (user.getLevel() < templet.getPlayGrade() ) {
				//2014-8-22增加等级限制，先屏蔽，以防影响以前的玩家
				curMission = null;
				//System.err.println("等级不足 ");
				//return null;
				user.getUserCounterHistory().set(CounterKey.MISSION_CURRENT_ID, 0);// 清空玩家的当前关卡
				throw new OperationFaildException(S.S10085);
			}
			
		}
		
		UserMission um = getMission(missionId);
		if (um == null) {
			if (runningmission.size() >= MAX_CROSS_MISSION_COUNT) {
				for (UserMission remove : runningmission) {
					if (remove.getMissionId() == getMaxMissionId() - 1) {// 当前的最大关卡不被删除
						continue;
					}
					runningmission.remove(remove);
//					System.out.println( "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
					new Mission(remove.getMissionId(), user, 0, false, getMaxMissionId()).end();
					DaoFactory.getUserMissionDao().delete(remove.getMissionId(), user.getId());
//					System.out.println( "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
					break;
				}
			}
			UserMission mission = createDto(missionId);
			runningmission.add(mission);

			curMission = new Mission(missionId, user, 0, true, getMaxMissionId());

		} else {
			try{
				curMission = new Mission(missionId, user, um.getNodeIndex(), false, getMaxMissionId());
			}catch( Exception exception){
				Debuger.debug( D.NOT_HAPPEN_EXCEPTION + user.getId() + "请求第" + missionId + "关时，只有关卡记录(user_mission)，没有关卡内容(mission_map)");
				curMission = new Mission(missionId, user, 0, true, getMaxMissionId());
			}
		}

		dispatchEvent(new EnterEvent(user));
		Events.getInstance().dispatch(new MissionEnterEvent(user));
		user.getUserCounterHistory().set(CounterKey.MISSION_CURRENT_ID, curMission.getMissionId());// 保存玩家当前关卡
//		System.out.println( "正在运行的关卡有:");
//		for( UserMission m : runningmission){
//			System.out.print( m.getMissionId() +",");	
//		}
//		System.out.println();

		
		return curMission;
	}

	/**
	 * 闯关结束之后的通用操作无论闯关成功失败（包括玩家主动放弃）
	 */
	private void endMission() {

		if (curMission == null) {
			return;
		}
		UserMission remove = getMission(curMission.getMissionId());
		runningmission.remove(remove);
		DaoFactory.getUserMissionDao().delete(remove.getMissionId(), user.getId());
		curMission.end();
		curMission = null;
		user.getUserCounterHistory().set(CounterKey.MISSION_CURRENT_ID, 0);// 清空玩家的当前关卡
	}

	@Override
	public void giveUp( int missionId ) {

		UserMission um = getMission(missionId);

		if (um != null) {
			runningmission.remove(um);
			DaoFactory.getUserMissionDao().delete(missionId, user.getId());
			new Mission( missionId, user, 0, false, getMaxMissionId()).end();
			user.getUserCounterHistory().set(CounterKey.MISSION_CURRENT_ID, 0);// 清空玩家的当前关卡
		}

	}

	@Override
	public Object run(int path, EventType type) {
		if (curMission == null) {
			throw new SureIllegalOperationException("当前还没开始闯关");
		}

//		if (user.getTeam().getPlayer().isDeath()) {
//			throw new OperationFaildException(S.S10192);
//		}

		UserMission um = getMission(curMission.getMissionId());

		MissionNode nextNode = curMission.getNextRunNode(path);
		if( nextNode == null ){
			throw new UnImportentException("下一个节点为空，当前节点： " + curMission.getMissionId() + "|" + um.getNodeIndex() + " 请求类型:" + type + " 玩家：" + user.getId() );// 后端自己看，不要通知客户端
		}
		if (nextNode.getEvent().getType() != type) {
			throw new UnImportentException("请求的节点类型为" + type + "实际节点类型为" + nextNode.getEvent().getType());// 后端自己看，不要通知客户端
		}

		user.getPlayer().reduce(PlayerProperty.PHYSICAL, 1);// 必须放在这里，否则可能出现mission_map和user_mission不同步的情况

		int curNodeId = curMission.getCurrentNode().getTemplet().getId();
		Object result = curMission.run(path);
		
//		System.out.println( curMission.getCurrentNode().toString() );
		System.out.println( "当前节点在分叉节点以" + (curMission.getCurrentNode().getTemplet().beforeCrossNode()? "前" : "后"));
		

		if (curMission.getCurrentNode().getTemplet().getId() != curNodeId) {
			MissionNode currentNode = curMission.getCurrentNode();
			um.setNodeIndex(currentNode.getTemplet().getId());
			if(branchIsCross()){
				boolean isBranch = true;
				mcManager.setCross( curMission.getMissionId(), isBranch );
			}
			if( curMission.bossIsCross() ){
				mcManager.setCross( curMission.getMissionId(), false );
			}
			// user.getPlayer().reduce(PlayerProperty.PHYSICAL, 1
			// );放这里会出问题，如果里面的run方法执行完毕，
			// mission_map的数据发生改变，而user_mission不会变化，导致两边不匹配
		} else {
			int dieCount = um.getDieCount() + 1;
			um.setDieCount(dieCount);
			user.getPlayer().add(PlayerProperty.PHYSICAL, 1);// 打输了，返回1点体力
		}
		DaoFactory.getUserMissionDao().update(um);
		return result;
	}

	@Override
	public int[] getDemonCamp(int path) {
		MissionNode nextNode = curMission.getNextRunNode(path);
		IEvent event = nextNode.getEvent();
		if (event.getType() != EventType.BATTLE) {
			// throw new OperationFaildException(S.S10182);
			throw new RuntimeException("下一节点非战斗节点，无法请求阵容");
		}
		String camp = event.getMissionArg().split("\\|")[0];
		return Array.toIntegerArray(camp);
	}

	@Override
	public IMission getCurMission() {
		return curMission;
	}

	/**
	 * 在数据库中创建一条新记录
	 */
	private UserMission createDto(int missionId) {
		UserMission um = new UserMissionImpl();
		// um.setCurrentMission(0);
		um.setDieCount(0);
		um.setMissionId(missionId);//
		um.setNodeIndex(0);
		um.setUname(user.getId());
		um.setCreateDate( (int) (System.currentTimeMillis() / 1000));
		DaoFactory.getUserMissionDao().add(um);
		
		return um;
	}

	@Override
	public MissionStarManager getStarManager() {
		if (starManager == null) {
			starManager = new MissionStarManagerImpl(user);
		}
		return starManager;
	}

	@Override
	public int getCurMissionId() {
		if (curMission == null) {
			return -1;
		}
		return curMission.getMissionId();
	}

	@Override
	public int getMaxMissionId() {
		return user.getUserCounterHistory().get(CounterKey.MAX_MISSION_ID);
	}

	public void setMaxMissionId(int maxMissionId) {
		user.getUserCounterHistory().set(CounterKey.MAX_MISSION_ID, maxMissionId);
	}

	@Override
	public void clearDieCount() {
		// missionList.setDieCount(0);
		// DaoFactory.getUserMissionDao().update(missionList);
	}

	@Override
	public int getDieCount() {
		return 0;
		// getM
		// return missionList.getDieCount();
	}

	@Override
	public List<Prize> successCrossMission() {
		if (curMission == null || !curMission.isDone()) {
			if( curMission == null ){
				System.out.println( "curMission == null ");
			}
			else{
				System.out.println( "curMission.isDone() " + curMission.isDone() );
			}
			throw new SureIllegalOperationException("闯关尚未结束");
		}
		List<Prize> e = Lists.newArrayList();
		List<Integer> prizeList = curMission.getPrizeBox();
		MapBoxes boxes = new MapBoxes(prizeList, user);
		e.addAll(boxes.open());

		Events.getInstance().dispatch(new MissionEndEvent());
		
		if (curMission.getMissionId() > getMaxMissionId()) {
			setMaxMissionId(curMission.getMissionId());
		}// 更新最大关卡

		boolean isBranch = false;
		mcManager.setCross( curMission.getMissionId(), isBranch );

		user.getUserCounter().mark(CounterKey.HAS_PASS_MISSION_TODAY);

		Debuger.debug("MissionManagerImpl.successCrossMission()");

		xianShiChongGuan();
		endMission();
		
		
		
		return e;
	}

	/**
	 * 台湾版专用功能
	 */
	private void xianShiChongGuan() {
		if(curMission == null ){
			return;
		}
		if(D.LANGUAGE != 2 ){
			return;
		}
		//ActivityTemplet temp = ActivityTempletConfig.get(ActivityIds.xian);
		
		if (!Util.Time.isIn(D.XIAN_SHI_TONG_GUAN_SHI_JIAN)) {
			return;
		}
		
		if( curMission.getMissionId() == D.XIAN_SHI_TONG_GUAN_GUAN_KA && !user.getUserCounterHistory().isMark( CounterKey.XIAN_SHI_TONG_GUAN)){
			user.getPrizeCenter().addPrize(6, D.XIAN_SHI_TONG_GUAN_JIANG_LI, S.STR10346, S.STR10345);
			user.getUserCounterHistory().mark( CounterKey.XIAN_SHI_TONG_GUAN);
		}
		
	}

	@Override
	public void backMainBranch() {
		if (curMission == null) {
			throw new OperationFaildException(S.S10240);
		}

		UserMission um = getMission(curMission.getMissionId());
		int index = curMission.backMainBranch();
		if( index == -1 ){
			throw new RuntimeException( "此节点不是路口节点不允许返回 " );
		}
		um.setNodeIndex(index);
		DaoFactory.getUserMissionDao().update(um);
	}

	@Override
	public Boolean branchIsCross() {

		// 2014年3月5日 11:50:14 林岑加
		// 因为客户端走到这里, 有时会报空指针
		if (curMission == null) {

			// java.lang.NullPointerException
			// at
			// cn.mxz.mission.MissionManagerImpl.branchIsCross(MissionManagerImpl.java:236)
			// at
			// cn.mxz.mission.MissionServiceImpl.branchIsCross(MissionServiceImpl.java:283)
			// at
			// cn.mxz.util.interceptor.ServiceInterceptor.cashLog(ServiceInterceptor.java:233)
			// at
			// cn.mxz.util.interceptor.ServiceInterceptor.bagChanged(ServiceInterceptor.java:174)
			// at
			// cn.mxz.util.interceptor.ServiceInterceptor.serviceLog(ServiceInterceptor.java:84)
			// at $Proxy25.branchIsCross(Unknown Source)
			// at
			// cn.mxz.handler.DataHandlerImpl.dispatch(DataHandlerImpl.java:5417)
			// at cn.mxz.handler.DataHandlerImpl.onData(DataHandlerImpl.java:21)
			// at
			// cn.mxz.base.handler.AbstractDataHandler.onData(AbstractDataHandler.java:111)
			// at
			// cn.mxz.base.handler.DataHandlerMain.onData(DataHandlerMain.java:43)

			return false;
		}
		// ////////////END ///////////////

		return curMission.branchIsCross();
	}

	/**
	 * 根据关卡id，判断支线是否已经通过
	 */
	@Override
	public Boolean branchIsCross(int missionId) {
		return mcManager.isCross(missionId, true);
	}
	public boolean mainIsCross( int missionId ){
		return mcManager.isCross(missionId, false);
	}

	@Override
	public void refreshChallenge(int missionId, Boolean isBranch) {
		mcManager.resetTodayChallengeRound(missionId, isBranch);
	}

	@Override
	public MissionBattle challenge(int missionId, Boolean isBranch) {
		return mcManager.challenge(missionId, isBranch);

	}

	@Override
	public int[] getBossCamp(int missionId, Boolean isBranch) {
		MissionMapTemplet templet = MissionMapTempletConfig.get(missionId);
		return mcManager.getEnemy(templet, isBranch);

	}

	@Override
	public int[] getChallengeData(int missionId) {
		int[] data = new int[4];
		data[0] = mcManager.getTodayChallengeCount(missionId, true);
		data[1] = mcManager.getTodayChallengeRound(missionId, true);
		data[2] = mcManager.getTodayChallengeCount(missionId, false);
		data[3] = mcManager.getTodayChallengeRound(missionId, false);
		return data;

	}

	/**
	 * @return moppingUp
	 */
	public MoppingUp getMoppingUp() {
		return moppingUp;
	}

	@Override
	public boolean isPerfect(int missionId) {
		return mcManager.isCross(missionId, true) && mcManager.isCross(missionId, false);

	}

	@Override
	public int[] getCompletion(int chapterId) {
		return mcManager.getCompletion(chapterId);
	}

	@Override
	public List<MissionBox> getBoxes(int chapterId) {

		CopyTemplet cp = CopyTempletConfig.get(chapterId);

		if(cp == null) {
			throw new SureIllegalOperationException("副本不存在" + chapterId);
		}

		ArrayList<MissionBox> ls = Lists.newArrayList();

		add(ls, cp.getAward1(), chapterId, 30);
		add(ls, cp.getAward2(), chapterId, 60);
		add(ls, cp.getAward3(), chapterId, 100);

		return ls;
	}

	private void add(ArrayList<MissionBox> ls, String a, int chapterId, int percentNeed) {
		a = a.trim();
		if (a.isEmpty()) {
			return;
		}

		Completeness c = buildCompletion(getCompletion(chapterId));

		ls.add(new MissionBoxImpl(user, a, c, chapterId, percentNeed));
	}

	private Completeness buildCompletion(int[] completion) {
		return new CompletenessImpl(completion[1], completion[0]);
	}

	@Override
	public List<UserMission> getRunningMission(){
		return runningmission;
	}

	@Override
	public void directJumpBranch() {
		if( curMission == null ){
			throw new OperationFaildException(S.S10240);
		}
		if( curMission.getCurrentNode().getTemplet().getId() != 0 ){
			throw new RuntimeException( "只有站在起点，可以直接跳到支线");
		}
		//手工设置分叉点以前的节点为通过状态，但是不能获取奖励,主要要更新两个地方，一个是mission_map的数据，一个是user_mission数据
		
		UserMission um = getMission(curMission.getMissionId());
		int index = curMission.directJumpBranch();
		if( index == -1 ){
			throw new RuntimeException( "没找到分叉点 " );
		}
		um.setNodeIndex(index);
		DaoFactory.getUserMissionDao().update(um);
		
				
	}
}
