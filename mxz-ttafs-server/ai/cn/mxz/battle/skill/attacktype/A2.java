package cn.mxz.battle.skill.attacktype;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.battle.BattleCamp;
import cn.mxz.battle.skill.AttackMode;
import cn.mxz.battle.skill.FighterBeAttack;
import cn.mxz.fighter.Fighter;

@Component("attackType2")
@Scope("prototype")

public class A2 implements AttackType {

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
	//2（不扣血）前排目标
	//34（不扣血）中排目标
	//35（不扣血）后排目标

	@Override
	public List<FighterBeAttack> getFighters(BattleCamp ans, int position) {

		Fighter target = AttackTypeUtil.getTarget(ans, position);

		Fighter target2 = AttackTypeUtil.getBehind(ans, target);

		return AttackTypeUtil.buildList(

				AttackTypeUtil.build(target, 1),

				AttackTypeUtil.build(target2, 1)
				);
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
