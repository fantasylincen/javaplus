package cn.mxz.battle.skill.attacktype;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.mxz.battle.BattleCamp;
import cn.mxz.battle.skill.AttackMode;
import cn.mxz.battle.skill.FighterBeAttack;
import cn.mxz.fighter.Fighter;

import com.google.common.collect.Lists;

@Component("attackType5")

public class A5 implements AttackType {



//攻击类型
//
//1(扣血)单体攻击
//2(扣血)小贯穿（攻击直线2人）
//3(扣血)大贯穿（攻击直线3人）
//4(扣血)横排
//5(扣血)溅射（相邻目标衰减50%）
//6(扣血)全体
//7(扣血)随机攻击一个单体目标
//8(扣血)随机攻击两个目标
//9(扣血)随机攻击三个目标
//
//
//21(加血)自己
//22(加血)气血最少
//23(加血)全体
//24(加血)对己方随机1个损血目标
//25(加血)对己方随机2个损血目标
//
//31（不扣血）自己
//32（不扣血）气血最少
//33（不扣血）前排目标
//34（不扣血）中排目标
//35（不扣血）后排目标

	@Override
	public List<FighterBeAttack> getFighters(BattleCamp ans, int position) {

		List<FighterBeAttack> ls = new ArrayList<FighterBeAttack>();

		Fighter target = AttackTypeUtil.getTarget(ans, position);

		String define = "5:1,4,7|4:1,3,7,5|7:5,3,4|1:5,4,3|3:1,4,7";

		int t = ans.getPosition(target);

		//		  1       1
		//		3 4 5   5 4 3
		//		  7       7

		define = getDefine(t, define);

		ls.add(AttackTypeUtil.build(target, 1));
		ls.addAll(AttackTypeUtil.buildList(getByDefine(ans, define)));

		return ls;
	}

	private List<Fighter> getByDefine(BattleCamp ans, String define) {

		List<Fighter> ls = Lists.newArrayList();
		define = define.replaceAll("[0-9]:", "");
		String[] split = define.split(",");
		for (String string : split) {
			if(string.isEmpty()) {
				continue;
			}
			Integer i = new Integer(string);
			Fighter fighter = ans.get(i);
			if(fighter != null && !fighter.isDeath()) {
				ls.add(fighter);
			}
		}
		return ls;
	}

	private String getDefine(int t, String define) {
		String[] split = define.split("\\|");
		for (String string : split) {
			if(string.startsWith("" + t)) {
				return string;
			}
		}
		throw new IllegalArgumentException(t + " ----- " + define);
	}

	@Override
	public AttackMode getAttakMode() {
		return AttackMode.REDUCE;
	}

	@Override
	public boolean isReleaseToFriend() {
		return false;
	}

}
