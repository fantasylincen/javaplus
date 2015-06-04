
package config.skill.accord;


import game.fighter.FighterBase;

/**
 * 存放技能效果的数据，包含受影响的属性，公式，相关参数
 * @author DXF
 * 2013-1-16 上午10:14:01
 */
	
public class SkillEffect {
	private Formula						formula;
	private float						arguments;
	
	
	public Formula getFormula() {
		return formula;
	}
	public void setFormula( Formula formula) {
		this.formula = formula;
	}
	public float getArguments() {
		return arguments;
	}
	public void setArgument(float arguments) {
		this.arguments = arguments;
	}
	
	/**
	 * 开始执行 技能效果
	 * @param defender 
	 * @param attacker 
	 * @param level 
	 */
	public float run( FighterBase attacker, FighterBase defender, byte level ){
		return formula.run( attacker, defender, level, arguments );
	}
	
	@Override
	public String toString() {
		return "SkillEffect [formula=" + formula
				+ ", arguments=" + arguments + "]";
	}
	
	
}
