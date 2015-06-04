package robot;

import config.robot.MateRobotTemplet;
import config.robot.MateRobotTempletCfg;

import game.pvp.DanGrad;
import game.pvp.MatchingType;
import game.pvp.PvpMateInfo;
import game.util.fighting.FightingFormula;
import user.UserInfo;

/**
 * 匹配 机器人 管理中心
 * @author DXF
 */
public class MateRobotManager {

	/**
	 * 根据玩家信息获取 机器人
	 * @param user
	 * @param type
	 * @param round
	 * @return
	 */
	public static PvpMateInfo get( UserInfo user, MatchingType type, boolean round ) {
		
		// 获取当前玩家战斗力 根据战斗力寻找对应机器人
		int fighting		= FightingFormula.run( user, type );
		
		MateRobotTemplet r	= MateRobotTempletCfg.get( fighting, type, round );
		
		PvpMateInfo info 	= new PvpMateInfo();
		UserInfo robot		= new UserInfo( null, -1, r.name );
		robot.setLevel( r.level );
		
		info.setUser( robot );
		info.setList( r.getList() );
		info.setFighting( FightingFormula.formula( r.list ) );
		info.setDanGrad( DanGrad.HEROIC_BRASS );
		info.setTalents( null );
		info.setIsRobot( true );
		
		return info;
	}

	
}
