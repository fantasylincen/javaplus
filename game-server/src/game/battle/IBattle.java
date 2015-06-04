package game.battle;




public interface IBattle {

	/**
	 * 运行战斗
	 * @return
	 */
	void run( boolean isMovesSequence );
	
	
	
	/**
	 * 攻击方是否获胜
	 * @return
	 */
	boolean getAttackerIsWin();
}
