package cn.mxz.battle;

import java.util.List;

import cn.mxz.dogz.Dogz;
import cn.mxz.equipment.SnatchRobotCamp;
import cn.mxz.fighter.Fighter;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.newpvp.PvpRobotCamp;

import com.google.common.collect.Lists;

class BattleCampImpl implements BattleCamp {

	private Camp<? extends Fighter> camp;
	private Dogz dogz;

	BattleCampImpl(Camp<? extends Fighter> camp, boolean isUpHp) {
		this.camp = camp;
		if (isUpHp) {
			for (Fighter f : camp.getFighters()) {
				if (f.getHpNow() < f.getHpMax()) {
					f.addHp(f.getHpMax() - f.getHpNow());
					// if(Debuger.isDevelop())
					// f.reduceHp(f.getHpMax() - 60);
				}
			}
		}
		if (camp instanceof PlayerCamp) {
			this.dogz = camp.getDogz();
		}
	}

	@Override
	public boolean isAllDeath() {

		return getLivesCount() <= 0;
	}

	@Override
	public int getLivesCount() {

		return getLives().size();
	}

	@Override
	public boolean contains(Fighter f) {

		if (f.equals(dogz)) {
			return true;
		}

		List<? extends Fighter> fighters = camp.getFighters();
		boolean contains = fighters.contains(f);
		return contains;
	}

	@Override
	public void init() {

		for (Fighter t : camp.getFighters()) {

			t.getBufferManager().clear();
		}
	}

	@Override
	public final List<Fighter> getLives() {

		final List<Fighter> all = Lists.newArrayList();

		List<? extends Fighter> fightersFront = camp.getFighters();

		for (Fighter f : fightersFront) {

			if (!f.isDeath()) {

				all.add(f);
			}
		}

		return all;
	}

	@Override
	public Fighter getLeft(Fighter target) {

		List<Fighter> ls = getLives();

		for (Fighter t : ls) {

			if (isLeft(t, target)) {

				return t;
			}
		}

		return null;
	}

	@Override
	public Fighter getRight(Fighter target) {

		List<Fighter> ls = getLives();

		for (Fighter t : ls) {

			if (isRight(t, target)) {

				return t;
			}
		}

		return null;
	}

	@Override
	public boolean isRight(Fighter f1, Fighter f2) {

		int position2 = camp.getPosition(f2);

		int position1 = camp.getPosition(f1);

		return isSameRow(f1, f2) && position1 - position2 == 1;
	}

	@Override
	public boolean isLeft(Fighter t, Fighter target) {

		int positionTarget = camp.getPosition(target);

		int positionT = camp.getPosition(t);

		return isSameRow(t, target) && positionTarget - positionT == 1;
	}

	@Override
	public boolean isSameRow(Fighter f1, Fighter f2) {

		int rowT = getRow(f1);

		int rowTarget = getRow(f2);

		return rowT == rowTarget;
	}

	@Override
	public boolean isColNear(Fighter f1, Fighter f2) {

		int col1 = getCol(f1);

		int col2 = getCol(f2);

		isRowNear(f1, f2);

		return Math.abs(col1 - col2) == 1;
	}

	@Override
	public boolean isRowNear(Fighter f1, Fighter f2) {

		final int row1 = getRow(f1);

		final int row2 = getRow(f2);

		return Math.abs(row2 - row1) == 1;
	}

	@Override
	public int getRow(Fighter f) {

		int position = camp.getPosition(f);

		return position / 5;
	}

	@Override
	public int getCol(Fighter f1) {

		int position = camp.getPosition(f1);

		return position % 5;
	}

	@Override
	public List<Fighter> getFighters() {
		return Lists.newArrayList(camp.getFighters());
	}

	@Override
	public int getPosition(Fighter f) {

		if (f.equals(dogz)) {
			return -1;
		}

		return camp.getPosition(f);
	}

	@Override
	public Fighter get(int position) {
		return camp.get(position);
	}

	@Override
	public int getShenJia() {
		if (camp instanceof PvpRobotCamp) {
			return camp.getShenJia();
		}
		if (camp instanceof SnatchRobotCamp) {
			return camp.getShenJia();
		}
		if (camp instanceof PlayerCamp) {
			PlayerCamp c = (PlayerCamp) camp;
			return c.getCity().getFormation().getShenJia();
		}
		return camp.getShenJia();
	}

	@Override
	public Dogz getDogz() {
		return dogz;
	}

	@Override
	public Fighter getMainFighter() {
		return camp.getMainFighter();
	}

	@Override
	public String getUserId() {
		return camp.getUserId();
	}

}
