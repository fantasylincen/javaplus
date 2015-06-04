
package cn.mxz.mission;

import java.util.List;

import message.S;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.base.exception.UnImportentException;
import cn.mxz.battle.Battle;
import cn.mxz.city.City;
import cn.mxz.mission.templet.MissionPathCfg;
import cn.mxz.mission.templet.MissionPathTemplet;
import cn.mxz.mission.type.EventType;
import cn.mxz.mission.type.IEvent;



/**
 * 用户当前正在闯关的地图，如果没有则创建
 * @author Administrator
 *
 */
public class Mission implements IMission{

	private final City						user;
	private MissionData						data;

	private final int						missionId;

	/**
	 * 已经完成run方法的当前节点,也就是角色当前所站立的位置坐标
	 */
	private MissionNode						currentNode;


	private final MissionPathTemplet		jsonTemplet;//json路径配置文件

	public Mission( int curMissionId, City user, int nodeIndex, boolean isCreate, int maxMissionId ) {
		jsonTemplet = MissionPathCfg.getMapTemplet( curMissionId );
		if( jsonTemplet == null ){
			throw new SureIllegalOperationException( "非法操作,地图json模板不存在：" + curMissionId );
		}
		missionId = curMissionId;
		this.user = user;
		data = new MissionData( user, curMissionId, isCreate, maxMissionId );
		currentNode = data.getNode(nodeIndex);
	}

	/* （非 Javadoc）
	 * @see cn.mxz.mission.IMissions#run(int)
	 */
	@Override
	public Object run( int path ){
//		path = 2;

		if( !currentNode.isDone ){
			throw new SureIllegalOperationException( "当前节点未完成，不能移动到下一个节点" );
		}

		MissionNode nextNode = getNextRunNode( path );

		if( nextNode == null ){
			throw new SureIllegalOperationException( "非法移动,当前节点无下一个节点:" + currentNode.getTemplet().getId() );
		}

//		if( nextNode.getEvent().getType() != type ){
//			throw new SureIllegalOperationException( "期待的节点类型为" + type +  );
//		}

		//System.out.println( "下一个节点的详细信息：路径" + path +"\n," + nextNode.getEvent().getType() + ":" + nextNode.getEvent().getMissionArg() + "," + nextNode.getTemplet().getId() );
		IEvent event = nextNode.getEvent();
		Object result = event.run(user);
		if( event.getType() == EventType.BATTLE ){//检测是否胜利，如果失败，什么都不做，返回结果,成功则移动到下一个节点
			Battle s = (Battle) result;
			if( !s.isWin() ){//战斗失败
				return result;
			}
		}

		moveToNext( nextNode );
		return result;
	}

	/* （非 Javadoc）
	 * @see cn.mxz.mission.IMissions#getNextRunNode(int)
	 */
	@Override
	public MissionNode getNextRunNode( int path ) {
		MissionNode nextNode = null;
		if( currentNode.getTemplet().isLast() ){
			return null;
		}
		if( currentNode.getTemplet().getPath() != path ){//主线移动到支线第一个节点
			if( currentNode.getTemplet().getBranch() == null || currentNode.getTemplet().getBranch().size() == 0 ){
				throw new UnImportentException("不是分叉点，不能跳转支线");
				//System.err.println( "不能乱跑");
				//return null;
			}
			nextNode = data.getBranchFirstNode( path );
    		//if( nextNode.isDone ){
			if( branchIsCross() ){
    			throw new OperationFaildException(S.S10184);
    		}
		}
		else{
			nextNode = data.getNode( getCurrentNode().getTemplet().getId() + 1 );
		}

		return nextNode;
	}

	/**
	 * 移动到指定节点
	 */
	private void moveToNext( MissionNode nextNode ) {
		nextNode.setDone(true);
		data.update( nextNode );
		currentNode = nextNode;
	}


	/**
	 * 新功能，直接跳到支线,主要就是设定分岔路口以前的节点为通过状态，同时修改两张数据表
	 */
	@Override
	public int directJumpBranch(){
		int index = returnBifurcation();
		for (MissionNode node : getAllNodeS() ) {
			if( node.getTemplet().getId() > index ){
				break;
			}
			node.setDone(true);
			data.update( node );
		}
		
		
		return index;
		
	}
	/* 

	 * 返回分叉点，新改进允许客户不用走完支线即可返回主线
	 */
	@Override
	public int backMainBranch(){
		if( /*currentNode.getTemplet().isLast() &&*/ currentNode.getTemplet().getPath() != 1 ){
			return returnBifurcation();
		}
		return -1;
	}

	/**
	 * 支线完毕后回到主线分叉点，设置好相应的currentNode
	 */
	private int returnBifurcation(){

		int path = 1;
		int index = jsonTemplet.getBifurcation(path);
		if( index == -1 ){
			throw new SureIllegalOperationException( "支线回到主线出问题了,missionId" + missionId + "支线id" + path );
		}
		currentNode = data.getNode(index);
		return index;
	}

	/* （非 Javadoc）
	 * @see cn.mxz.mission.IMissions#getMissionId()
	 */
	@Override
	public int getMissionId() {
		return missionId;
	}

	/* （非 Javadoc）
	 * @see cn.mxz.mission.IMissions#getAllNodeS()
	 */
	@Override
	public List<MissionNode> getAllNodeS(){
		return data.getNodeList();
	}


	/* （非 Javadoc）
	 * @see cn.mxz.mission.IMissions#end()
	 */
	@Override
	public void end() {
		data.end();
	}

	/* （非 Javadoc）
	 * @see cn.mxz.mission.IMissions#isDone()
	 */
	@Override
	public boolean isDone(){
		return data.isDone();
	}

	/* （非 Javadoc）
	 * @see cn.mxz.mission.IMissions#getPrizeBox()
	 */
	@Override
	public List<Integer> getPrizeBox(){
		return data.getPrizeBox();
	}

	/* （非 Javadoc）
	 * @see cn.mxz.mission.IMissions#getCurrentNode()
	 */
	@Override
	public MissionNode getCurrentNode() {
		return currentNode;
	}



	/* （非 Javadoc）
	 * @see cn.mxz.mission.IMissions#branchIsCross()
	 */
	@Override
	public Boolean branchIsCross() {
		return data.branchIsCross();
	}
	
	@Override
	public Boolean bossIsCross() {
		return data.bossIsCross();
	}

	@Override
	public Boolean canJumpBranch() {
		return data.canJumpBranch();
	}

}

