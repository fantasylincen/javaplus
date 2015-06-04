package cn.mxz.bossbattle;


/**
 * 击杀者
 * @author Administrator
 *
 */
public interface ISimpleChallenger {

	String getUserId();
	
	/** 
	 * 昵称
	 * @return
	 */
	String getNick();
	
	/**
	 * 声望
	 * @return
	 */
	int getReputation();
	
	/**
	 * 总伤害
	 * @return
	 */
	int getAllDamage();
	
	/**
	 * 名次
	 * @return
	 */
	int getRank();

}
