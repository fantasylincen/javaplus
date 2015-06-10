package game.growup;

import java.util.ArrayList;
import java.util.List;

import util.ErrorCode;

/**
 * 英雄进化信息
 * @author DXF
 *
 */
public class EvolutionInfo {

	
	/** 错误码  */
	public ErrorCode 		code;
	
	/** 进化后的品质 */
	public Quality 			quality;

	/** 进化后的最高等级 */
	public short 			maxLevel;

	/** 进化后HP */
	public int 				hp;

	/** 进化后的攻击力 */
	public int 				attack;
	
	/** 消耗金币 */
	public int				needGold;
	
	/** 需要奖杯数 */
	public int				needTrophy = 0;
	
	/** 携带等级 */
	public short			carryLv = 1;

	/** 材料 **/
	public List<Material>	material = new ArrayList<Material>();
}

