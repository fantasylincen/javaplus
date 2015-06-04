package mongo.dtodefine;

interface SimpleChallenger {
	
	String getUserId();

	/**
	 * 昵称
	 * 
	 * @return
	 */
	String getNick();

	/**
	 * 声望
	 * 
	 * @return
	 */
	int getReputation();

	/**
	 * 总伤害
	 * 
	 * @return
	 */
	int getAllDamage();

	/**
	 * 名次
	 * 
	 * @return
	 */
	int getRank();
}
