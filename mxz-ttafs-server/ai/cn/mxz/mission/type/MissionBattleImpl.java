package cn.mxz.mission.type;

import java.util.ArrayList;
import java.util.List;

import scala.Serializable;
import cn.mxz.FighterExpPrize;
import cn.mxz.MissionMapTemplet;
import cn.mxz.MissionMapTempletConfig;
import cn.mxz.battle.AbstractBattle;
import cn.mxz.battle.BattleExpPrize;
import cn.mxz.battle.Camp;
import cn.mxz.battle.MissionBattle;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;
import cn.mxz.fighter.Fighter;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.mission.old.PropPrize;
import cn.mxz.mission.old.demon.Demon;
import cn.mxz.pvp.SuperCamp;
import cn.mxz.user.Player;

import com.google.common.collect.Lists;

import define.D;

public class MissionBattleImpl extends AbstractBattle implements MissionBattle,
		Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8200270327471111696L;

	public static final class MyPrize implements PropPrize, Serializable {

		/**
		 *
		 */
		private static final long serialVersionUID = 6950498511916625425L;
		private Prize prize;

		public MyPrize(Prize prize) {
			this.prize = prize;
		}

		@Override
		public void award(Player player) {
			prize.award(player);
		}

		public void award(City city) {
			award(city.getPlayer());
		}

		@Override
		public int getCount() {
			return prize.getCount();
		}

		@Override
		public int getId() {
			return prize.getId();
		}

		@Override
		public String toString() {
			return prize.getId() + ":" + prize.getCount();
		}
	}

	protected final List<BattleExpPrize> fighterPrize = new ArrayList<BattleExpPrize>();

	private MissionMapTemplet temp;
	boolean isMain;
	boolean isBoss;

	List<PropPrize> propPrize = Lists.newArrayList();

	private int star;

	private int hpStart;

	/**
	 * @param camp
	 *            怪兽,位置,怪兽,位置.....
	 * @param user
	 * @param mapId
	 * @param isMain
	 *            是否是主线
	 * @param isBoss
	 * @param isGuide
	 */
	public MissionBattleImpl(int[] camp, City user, int mapId, boolean isMain,
			boolean isBoss, boolean needCopy) {

		super(buildCamp(user, needCopy), buildDemonCamp(camp, mapId, isBoss,
				isMain, user));

		this.isMain = isMain;
		this.isBoss = isBoss;
		temp = MissionMapTempletConfig.get(mapId);
		hpStart = getHpAll();
	}

	private int getHpAll() {
		int count = 0;
		for (Fighter f : getUnder().getFighters()) {
			int h = f.getHpNow();
			count += h;
		}
		return count;
	}

	private static PlayerCamp buildCamp(City user, boolean needCopy) {
		return new SuperCamp(user.getFormation().getSelected(), 1);
	}

	@Override
	public boolean isMain() {
		return isMain;
	}

	@Override
	public boolean isBoss() {
		return isBoss;
	}

	private static Camp<Demon> buildDemonCamp(int[] monsterId, int mapId,
			boolean isBoss, boolean isMain, City city) {
		DemonInCampResolver resolver = new DemonInCampResolver();
		List<DemonInCamp> ls = resolver.resolve(monsterId, mapId, isBoss,
				isMain, city);
		return new DemonCamp2(ls);
	}

	@Override
	protected FighterExpPrize getMapTemplet() {
		if (containsBoss()) {
			return new BossPrize(temp);// Boss关卡经验 * 2
		}
		return temp;
	}

	@Override
	protected boolean containsBoss() {
		Camp<? extends Fighter> up = getUpper();
		for (Fighter f : up.getFighters()) {
			if ((isMain ? temp.getBossId() : temp.getLineBossId()).contains(f
					.getTypeId() + "")) {
				return true;
			}
		}
		return false;
	}

	public class BossPrize implements FighterExpPrize, Serializable {

		/**
		 *
		 */
		private static final long serialVersionUID = -6176506838317330359L;
		private MissionMapTemplet temp;

		public BossPrize(MissionMapTemplet temp) {
			this.temp = temp;
		}

		@Override
		public String getBossId() {
			return isMain ? temp.getBossId() : temp.getLineBossId();
		}

		@Override
		public String getBossDropOut() {
			return temp.getBossDropOut();
		}

		@Override
		public int getSingleCoins() {
			return temp.getSingleCoins() * D.BOSS_SINGLE_COINS_X;
		}

		@Override
		public String getLineBossDropOut() {
			return temp.getLineBossDropOut();
		}

		@Override
		public String getMonsterDropOut() {
			return temp.getMonsterDropOut();
		}

		@Override
		public float getLineWilsonParam() {
			return temp.getLineWilsonParam();
		}

		@Override
		public String getLineMonsterDropOut() {
			return getLineMonsterDropOut();
		}

	}

	@Override
	public void fighting() {
		super.fighting();

		if (isWin()) {
			List<PropPrize> ps = getPropPrize();
//			Debuger.debug("MissionBattleImpl.fighting() 副本掉落 ----------");
			for (PropPrize p : ps) {
//				Debuger.debug("MissionBattleImpl.fighting() 副本掉落 " + p.getId() + ", " + p.getCount());
				p.award(getUnderPlayerCamp().getCity());
			}
		}
	}

	@Override
	public int getStar() {
		return star;
	}

	@Override
	public List<BattleExpPrize> getExpPrize() {

		return fighterPrize;
	}

	@Override
	public List<PropPrize> getPropPrize() {
		return propPrize;
	}

	@Override
	public int getMapId() {
		return temp.getId();
	}

	@Override
	public List<BattleExpPrize> getFighterPrize() {
		return fighterPrize;
	}

	public int getHpStart() {
		return hpStart;
	}

}
