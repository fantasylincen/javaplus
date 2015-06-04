package cn.mxz.skill;

import cn.mxz.SkillTemplet;
import cn.mxz.SkillTempletConfig;
import cn.mxz.fighter.IdLevel;
import cn.mxz.team.Skill;

/**
 * 技能伤害值计算器
 *
 * @author 林岑
 *
 */
public class SkillDamageCalc {

	/**
	 * 加成
	 * @author 林岑
	 *
	 */
	public static class Addition {
		private float value;
		private boolean isPercent;

		public Addition(float value, boolean isPercent) {
			this.value = value;
			this.isPercent = isPercent;
		}
		public float getValue() {
			return value;
		}
		public boolean isPercent() {
			return isPercent;
		}
	}

	public Addition calc(IdLevel s) {

		SkillTemplet temp = SkillTempletConfig.get(s.getId());

		int rt = temp.getReleaseType();

		if(rt == 1) {
			float v = temp.getHarmGrowPar() * (s.getLevel() - 1) + temp.getHarmPar();
			return new Addition(v, false);
		}

		if(rt == 3) {
			int f = temp.getPasvFront();
			if(f < 6) {
				float g = temp.getFrontGrowPar() * (s.getLevel() - 1) + temp.getFrontPar();
				return new Addition(g, true);
			} else {//固定值
				float g = temp.getFrontGrowFixed() * (s.getLevel() - 1) + temp.getFrontFixed();
				return new Addition(g, false);
			}
		}

		throw new RuntimeException("无法识别的技能类型!");
	}

	public Addition calcNext(Skill s) {
		return calc(buildNext(s));
	}

	private IdLevel buildNext(Skill s) {
		return new SkillNextLevel(s);
	}

}
