package cn.mxz.mission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.mxz.DemonGroupTemplet;
import cn.mxz.DemonGroupTempletConfig;
import cn.mxz.MapTemplet;
import cn.mxz.MissionMapTemplet;
import cn.mxz.MissionMapTempletConfig;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.city.City;
import cn.mxz.mission.templet.MissionNodeTemplet;
import cn.mxz.mission.templet.MissionPathCfg;
import cn.mxz.mission.templet.MissionPathTemplet;
import cn.mxz.mission.type.BattleEvent;
import cn.mxz.mission.type.EmptyEvent;
import cn.mxz.mission.type.EventType;
import cn.mxz.mission.type.IEvent;
import cn.mxz.mission.type.PrizeEvent;
import cn.mxz.mission.type.RandomEventBuilder;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Lists;

import db.dao.impl.DaoFactory;
import db.domain.MissionMap;
import db.domain.MissionMapImpl;
import define.D;

/**
 * 任务地图的数据
 * @author Administrator
 *
 */
public class MissionData {

	private int											missionId;

	private LinkedListMultimap<Integer, MissionNode> 	pathMap = LinkedListMultimap.create();//核心的关卡数据
	private final MissionPathTemplet					jsonTemplet;//json出来的路径配置文件
	private final City									user;

	private List<MissionMap> 							listDto;

	/**
	 * 本关卡是否完结，主要用于判定支线任务完成后，不允许重复闯支线关
	 */
	private boolean										isDone;

	private boolean										isGuide;

	/**
	 * 目前的数据库自动生成架构不支持三个key的结构，因此，把username和missionid整合到ids字段中，规则为uname|missionId
	 */
	private final String								idsFieldValue;

//	private int											maxMissionId;

	/**
	 * 创建一个新的MissionData
	 * @param missionId
	 */
	public MissionData( City user, int missionId, boolean isCreate, int maxMissionId ) {
//		maxMissionId = user.getMission().getMaxMissionId();
		if( maxMissionId == 1 && missionId == 1 ){
			throw new SureIllegalOperationException("打完第二关以前不能打第一关" );
		}
		if( maxMissionId >= D.MAX_GUIDE_MISSION_ID ){
			isGuide = false;
		}else{
			isGuide = true;
		}

		jsonTemplet = MissionPathCfg.getMapTemplet(missionId);
		this.missionId = missionId;
		this.user = user;
		idsFieldValue = user.getId() + "|" + missionId;
		if( isCreate ){
			createNewMission();
			Debuger.debug("第" + missionId +"关的模板节点数为" + jsonTemplet.getPathMap().size() + "，实际上是 :" + pathMap.size());
			if( missionId > 2 ){
				if( pathMap.size() != jsonTemplet.getPathMap().size() ){
					throw new RuntimeException( "后端自己查看的异常， 第" + missionId +"关的模板节点数为" + jsonTemplet.getPathMap().size() + "，实际上是 :" + pathMap.size() + "用户名：" + user.getId() );
				}
			}
		}
		else{
			listDto = DaoFactory.getMissionMapDao().findByIds(idsFieldValue );
			//listDto = DaoFactory.getMissionMapDao().findBy( "uname", user.getId() );修改前
			Collections.sort(listDto, new ComparatorMission() );

			loadFromDB( );
		}
	}

	private void createNewMission() {
		
		if( isGuide ){
			for( Integer path : jsonTemplet.getPathSet() ){
				createGuide(path);
			}
		}
		else{
			for( Integer path : jsonTemplet.getPathSet() ){
				randomAssign(path);
			}
		}

		
		createDB();
	}

	private void createGuide(Integer path) {
		int allSize = jsonTemplet.getPath( path ).size();

		int i = 1;
		for( MissionNodeTemplet jsonNode : jsonTemplet.getPath(path) ){
			IEvent e;
			int type = jsonNode.getGuideType();
			if( type == 4 ){//战斗模块

				String arg = buildGuideBattle( jsonNode );
				arg += "|";
				arg += (i == allSize);//是否boss
				arg += "|";
				arg += (path!=1);//是否支线
				e = new BattleEvent( arg, missionId );
			}
			else if( type == 2 || type == 1 ){
				e =  new PrizeEvent( jsonNode.getGuideArg() + "" );
			}
			else{
				e = new EmptyEvent( );
			}
			boolean isDone = false;
			if( i == 1 && path == 1 ){//主线并且是第一个节点默认已经通关
				isDone = true;
			}
			MissionNode node = new MissionNode( jsonNode, e, isDone );//第一个节点默认为通过状态
			pathMap.put( path, node );
			i++;
		}

	}

	private String buildGuideBattle(MissionNodeTemplet jsonNode ){
		int campId = jsonNode.getGuideArg();
		DemonGroupTemplet t = DemonGroupTempletConfig.get( campId );
		return t.getGroup();

	}

	/**
	 * 本节点是否已经完结
	 * @return
	 */
	public boolean isEnd(){
		return isDone;
	}

	/**
	 * 在数据库新建一个关卡
	 */
	private void createDB(){
		listDto = Lists.newArrayList();
		for( MissionNode node : pathMap.values() ){
			MissionMap m = new MissionMapImpl();
			m.setArgs( node.getEvent().getMissionArg() );
			m.setIsFinish( node.isDone );
			m.setTempletId( node.getTemplet().getId() );
			m.setIds( idsFieldValue );
			m.setType( node.getEvent().getType().toNum() );
			m.setIndexs( node.getTemplet().getIndex() );
			m.setPaths( node.getTemplet().getPath() );
			listDto.add( m );
		}
		DaoFactory.getMissionMapDao().addAll(listDto);
	}

	List<MissionNode> getNodeList(){
		List<MissionNode> list = new ArrayList<MissionNode>( pathMap.values() );
		return list;
	}

	private void loadFromDB(){
		for( MissionMap mm : listDto ){

			MissionNodeTemplet templet = MissionPathCfg.getMapTemplet(missionId).getById( mm.getTempletId() );
			IEvent e = EventType.fromNum( mm.getType() ).create( mm.getArgs(), missionId );
			MissionNode node = new MissionNode(templet , e, mm.getIsFinish() );
			pathMap.put( templet.getPath(), node );

		}
	}


	/**
	 * 随机指派各种事件
	 */
	private void randomAssign( int path ){

		int allSize = jsonTemplet.getPath( path ).size();
		int randomSize = getRandomSize(path, allSize);

		List<IEvent> events = buildOnePath( randomSize, path );//
		MissionNode node = null;

		int i = 1;
//		if( path == 1 ){
//			System.out.println( "json文件 中主线的节点长度为:" + jsonTemplet.getPath(path).size()  );
//		}
//		else{
//			System.out.println( "json文件 中支线的节点长度为:" + jsonTemplet.getPath(path).size()  );
//		}
		for( MissionNodeTemplet jsonNode : jsonTemplet.getPath(path) ){

			if( i == 1 && path == 1){//如果是主线，并且是第一块石头，则放置一个已经完成的空事件
				IEvent e = new EmptyEvent();
				node = new MissionNode( jsonNode, e, true );

			}else if( i == allSize ){//最后一块石头放boss
				node = buildBossMission(jsonNode, jsonNode.isBranch(), true );

			} else{
				IEvent e = cn.javaplus.util.Util.Collection.getRandomOne( events );
				node = new MissionNode( jsonNode, e, false );
				events.remove( e );
			}
			pathMap.put( path, node );
			i++;
		}

	}

	/**
	 * 获取需要随机生成的节点
	 * @param path
	 * @param allSize
	 * @return
	 */
	private int getRandomSize(int path, int allSize) {
		int randomeSize = allSize;
		if( path == 1 ){
			randomeSize -= 2;//去除首尾节点
		}
		else{
			randomeSize -= 1;//支线去除尾节点
		}
		return randomeSize;
	}

	private MissionNode buildBossMission(MissionNodeTemplet jsonNode, boolean b, boolean c) {
		MissionMapTemplet templet = MissionMapTempletConfig.get( missionId );
		IEvent e = new BattleEvent(templet, jsonNode.isBranch(), true );
		MissionNode node = new MissionNode( jsonNode, e, false );
		return node;
	}


	/**
	 * 生成一条线路上的随机节点，不包括首(空节点)，尾（boss）节点<br/>
	 *
	 * @param allSize
	 * @return
	 */
	private List<IEvent> buildOnePath( int allSize, int path ){

		List<IEvent> eventList = Lists.newArrayList();

		MapTemplet excelTemplet = MissionMapTempletConfig.get( missionId );//这里不会为null

		int remainSize = allSize;//防止线路节点数目 < 4,而4个项目概率又均为0.25，会导致不会生成任何节点内容

		int size = (int) (allSize * excelTemplet.getDemonScale());//战斗关卡
//		System.out.println( user.getId() + " 战斗关卡的数量为:" + size );
		eventList.addAll( buildEventS( EventType.BATTLE, size) );
		remainSize -= size;

		size = (int) (allSize * excelTemplet.getBoxScale());//宝箱关卡

//		if(user.isTester()) {
//			size = 10;
//		}

//		System.out.println( user.getId() + " 宝箱关卡的数量为:" + size );
		eventList.addAll( buildEventS( EventType.PRIZE, size) );
		remainSize -= size;

		size = (int) (allSize * excelTemplet.getAskScale());//随机事件关卡
		eventList.addAll( buildRandomEvents( size ) );
		remainSize -= size;


		eventList.addAll( buildEventS( EventType.EMPTY, remainSize ) );//把空节点放在最前面


		return eventList;
	}
	private List<IEvent> buildEventS( EventType type, int size ){
		List<IEvent> list = Lists.newArrayList();
		for( int i = 0; i < size; i++ ){
			IEvent e = type.create( missionId );
			list.add( e );
		}
		return list;
	}
	/**
	 * 构建随机事件，这个比较特殊，因为云游事件一个关卡只允许出现一个，神魔事件则在上一个神魔未结束的时候不能产生新的神魔事件
	 * @param size
	 * @return
	 */

	private List<IEvent> buildRandomEvents( int size ){
		return new RandomEventBuilder( user ).build(size);
	}

	/**
	 * 结算时获取所有的宝箱
	 * @return
	 */
	public List<Integer> getPrizeBox(){
		List<Integer> prizes = Lists.newArrayList();
		for( MissionNode node : pathMap.values() ){
			IEvent e = node.getEvent();
			if( e.getType()  == EventType.PRIZE && node.isDone && e.getBrief() == D.ZJBX_ID || e.getBrief() == D.ZJBX_ID1 ){
				prizes.add( e.getBrief() );
			}
		}
		return prizes;
	}

	public void end() {
		for( MissionMap m : listDto ){
			DaoFactory.getMissionMapDao().delete( idsFieldValue, m.getTempletId() );
		}
	}

	class ComparatorMission implements Comparator<MissionMap>{

		 @Override
		public int compare(MissionMap arg0, MissionMap arg1) {
			 return arg0.getTempletId() - arg1.getTempletId();
		 }
	}

	public void update(MissionNode nextNode) {
		for( MissionMap mm : listDto ){
			if( mm.getTempletId() == nextNode.getTemplet().getId() ){
				mm.setIsFinish( true );
				DaoFactory.getMissionMapDao().update( mm );
			}

		}

		//TODO待优化
	}

	/**
	 * 获取支线的首个节点
	 * @return
	 */
	public MissionNode getBranchFirstNode( int path) {
		for( MissionNode node : pathMap.get( path ) ){
			return node;
		}
		return null;
	}

	public MissionNode getNode( int index ){
		return getNodeList().get(index);
	}

	/**
	 * 是否通关
	 * @return
	 */
	public boolean isDone(){
		List<MissionNode> list = pathMap.get( 1 );
		MissionNode last = list.get( list.size() - 1 );
		return last.isDone();
	}

	public Boolean branchIsCross() {
		List<MissionNode> list = pathMap.get( 2 );
		if( list != null && list.size() != 0 ){
			MissionNode node = list.get( list.size() - 1 );
			return node.isDone;
		}
		return false;
	}
	
	/**
	 * 主线boss是否死亡，boss死亡，意味着可以扫荡，但是不能算作通关
	 * @return
	 */
	public Boolean bossIsCross() {
		List<MissionNode> list = pathMap.get( 1 );
		if( list != null && list.size() != 0 ){
			MissionNode node = list.get( list.size() - 1 );
			return node.isDone;
		}
		return false;
	}

	/**
	 * 支线在完全没有走过的情况下，才能进入
	 * @return
	 */
	public Boolean canJumpBranch() {
		List<MissionNode> list = pathMap.get( 2 );
		if( list == null ){
			return false;
		}
		
		for( MissionNode node : list ){
			if( node.isDone() ){
				return false;
			}
		}
		return true;
	}


}
