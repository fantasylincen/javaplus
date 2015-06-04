package cn.mxz.battle;

import java.util.List;

import cn.mxz.dogz.Dogz;
import cn.mxz.fighter.Fighter;

/**
 * 在战场中的某方阵营
 * @author 林岑
 *
 */
public interface BattleCamp extends Camp<Fighter> {

	boolean isAllDeath();

	int getLivesCount();

	boolean contains(Fighter f);

	void init();

	List<Fighter> getLives();

	
	Fighter getLeft(Fighter target);

	Fighter getRight(Fighter target);

	boolean isRight(Fighter f1, Fighter f2);

	boolean isLeft(Fighter t, Fighter target);

	boolean isSameRow(Fighter f1, Fighter f2);

	int getRow(Fighter f);

	int getCol(Fighter f1);

	boolean isRowNear(Fighter f1, Fighter f2);

	boolean isColNear(Fighter f1, Fighter f2);

	/**
	 * 该阵营中的神兽
	 * @return
	 */
	Dogz getDogz();

}
