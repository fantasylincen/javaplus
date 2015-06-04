package cn.mxz.dogz;

/**
 * 神兽详细数据
 * @author 林岑
 *
 */
public interface DogzUI {

	/**
	 * 状态  1:已经拥有了,   2: 可以开启   3:不可以开启的
	 * @return
	 */
	int getStatus();

	/**
	 * 神兽ID
	 * @return
	 */
	int getTempletId();

	/**
	 * 是否出战
	 * @return
	 */
	boolean isFighting();

	/**
	 * 神兽品阶
	 * @return
	 */
	int getStep();

	/**
	 * 神兽技能ID
	 * @return
	 */
	int getSkillId();
}
