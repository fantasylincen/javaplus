package cn.mxz.mission.old;

import java.util.ArrayList;
import java.util.List;

import cn.mxz.EquipmentTempletConfig;
import cn.mxz.FormationTempletConfig;
import cn.mxz.SkillTempletConfig;
import cn.mxz.StuffTemplet;
import cn.mxz.StuffTempletConfig;
import cn.mxz.activity.heishi.HeishiManager;
import cn.mxz.bag.BagRouter;
import cn.mxz.bag.BagRouterImpl;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.BaseRewards.BaseRewardId;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.city.PlayerProperty;
import cn.mxz.prizecenter.IPrize;
import cn.mxz.prop.PropTempletFactory;
import cn.mxz.protocols.user.mission.BoxP.BoxPro.PrizePro;
import cn.mxz.skill.SkillManager;
import cn.mxz.user.Player;
import cn.mxz.user.team.Formation;
import cn.mxz.user.team.Team;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounterSetter;
import cn.mxz.util.debuger.Debuger;
import cn.mxz.util.needs.INeeds;
import cn.mxz.util.needs.NeedsChecker;
import cn.mxz.util.needs.NeedsFactory;
import define.D;

public class PrizeImpl implements Prize, IPrize {

	public PrizeImpl() {

	}

	private int id;

	private int count;

	public PrizeImpl(int id, int count) {

		this.setId(id);

		this.setCount(count);
	}

	@Override
	public int getId() {

		return id;
	}

	@Override
	public int getCount() {

		return count;
	}

	private void addGiftGold(Player player, int count2) {
		City city = CityFactory.getCity(player.getId());
		UserCounterSetter c = city.getUserCounterAuto();
		c.add(CounterKey.GIFT_GOLD_COUNT, getCount());
		player.addGiftGold(getCount());
	}

	private void addSkill(City city) {

		SkillManager sm = city.getSkillManager();

		int c = getCount();
		for (int i = 0; i < c; i++) {
			sm.add(getId());
		}
	}

	private void addTactical(City city) {

		Formation f = city.getFormation();

		for (int i = 0; i < getCount(); i++) {

			f.addNewTactical(getId());
		}

	}

	private boolean isSkill() {
		return SkillTempletConfig.getKeys().contains(getId());
	}

	private boolean isTactical() {
		return FormationTempletConfig.getKeys().contains(getId());
	}

	private void addProp(City city) {

		// 加入碎片背包
		StuffTemplet t = StuffTempletConfig.get(getId());
		if (t != null) {

			int type = t.getType();

			BagRouter rotour = new BagRouterImpl();
			if (rotour.isSuiPian(type)) {

				city.getPiecesBag().addProp(getId(), getCount());

				return;
			}
		}

		// 创建新装备
		if (EquipmentTempletConfig.get(getId()) != null) {

			for (int i = 0; i < getCount(); i++) {
				city.getEquipmentManager().createNewEquipment(getId());
			}

			return;
		}

		// 加入到普通背包
		city.getBagAuto().addProp(getId(), getCount());
	}

	private boolean isProp() {

		return PropTempletFactory.get(getId()) != null;
	}

	@Override
	public String toString() {
		return "PrizeImpl [id=" + getId() + ", count=" + getCount() + "]";
	}

	/**
	 * @param count
	 *            要设置的 count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @param id
	 *            要设置的 id
	 */
	public void setId(int id) {
		this.id = id;
	}

	public void award(City city) {
		award(city.getPlayer());
	}

	@Override
	public void award(Player player) {

		if (getId() == D.ID_CASH) { // 金币

			player.add(PlayerProperty.CASH, getCount());

		}

		else if (getId() == D.ID_GOLD) { // 元宝

			addGiftGold(player, getCount());

		}

		else if (getId() == D.ID_DI_XIAN_LING) { // 地仙令

			player.add(PlayerProperty.DI_XIAN_LING, getCount());

		}

		else if (getId() == D.ID_TIAN_XIAN_LING) { // 天仙令

			player.add(PlayerProperty.TIAN_XIAN_LING, getCount());

		}

		else if (getId() == D.ID_JIN_XIAN_LING) { // 金仙令

			player.add(PlayerProperty.JIN_XIAN_LING, getCount());

		}

		else if (getId() == D.ID_EXP) { // 经验值

			player.addExp(getCount());

		}

		else if (getId() == D.ID_PHYSICAL) { // 体力

			player.add(PlayerProperty.PHYSICAL, getCount());

		}

		else if (getId() == D.ID_POWER) { // 精力

			player.add(PlayerProperty.POWER, getCount());

		}

		else if (getId() == D.ID_GIFT_GOLD) { // 礼券

			addGiftGold(player, getCount());

		}

		else if (getId() == D.ID_CULTIVATION) { // 修行

			player.add(PlayerProperty.CULTIVATION, getCount());

		}

		else if (getId() == D.ID_POINTS) { // 积分

			player.add(PlayerProperty.POINTS, getCount());

		}

		else if (getId() == D.ID_REPUTATION) { // 声望

			player.add(PlayerProperty.REPUTATION, getCount());

		}

		else if (getId() == D.ID_LOT) { // 魅力值

			player.add(PlayerProperty.CHARM, getCount());

		}

		else if (getId() == D.ID_CASH_BALL) { // 金币球

			new CashBall(player, getCount()).award();

		}

		else if (getId() == D.ID_ACTIVITY1) { // 活动币1

			player.add(PlayerProperty.ACTIVITY1, getCount());

		}

		else if (getId() == D.ID_ACTIVITY2) { // 活动币2

			player.add(PlayerProperty.ACTIVITY2, getCount());

		}

		else if (getId() == D.ID_ACTIVITY3) { // 活动币3

			player.add(PlayerProperty.ACTIVITY3, getCount());

		}

		else if (getId() == D.ID_ACTIVITY4) { // 活动币4

			player.add(PlayerProperty.ACTIVITY4, getCount());

		}

		else if (getId() == D.ID_ACTIVITY5) { // 活动币5

			player.add(PlayerProperty.ACTIVITY5, getCount());

		}

		else if (getId() == D.ID_SHOU_HUN) { // 兽魂
			player.add(PlayerProperty.SHOU_HUN, getCount());

		}

		else if (getId() == D.ID_BAI_WAN_TONG_QIAN) { // 金币

			player.add(PlayerProperty.CASH, getCount());

		}

		else if (getId() == D.ID_NEW_GOLD) {

			player.add(PlayerProperty.NEW_GOLD, getCount());

		}

		else if (getId() == BaseRewardId.QiSeJingShi_110023) {

			HeishiManager heishiManager = player.getCity().getHeishiManager();
			heishiManager.addQsjs(getCount());

			// Debuger.debug("PrizeImpl.award() 七色晶石奖励 " + getCount());

		}
		
		else if (getId() == 110025) {//110025白水晶

			HeishiManager heishiManager = player.getCity().getHeishiManager();
			heishiManager.addBsj(getCount());

			// Debuger.debug("PrizeImpl.award() 七色晶石奖励 " + getCount());

		}
		else if (getId() == 110026) {//  110026黄水晶

			HeishiManager heishiManager = player.getCity().getHeishiManager();
			heishiManager.addHsj(getCount());

			// Debuger.debug("PrizeImpl.award() 七色晶石奖励 " + getCount());

		}

		else if (getId() == BaseRewardId.RongYu_110024) {

			player.add(PlayerProperty.RONG_YU, getCount());

		}

		else {// 道具奖励

			final World w = WorldFactory.getWorld();

			final City city = w.get(player.getId());

			if (isProp()) {

				addProp(city);

			} else if (isTactical()) {

				addTactical(city);

			} else if (isSkill()) {

				addSkill(city);

			} else {

				Team team = city.getTeam();
				for (int i = 0; i < getCount(); i++) {
					team.createNewHeroAuto(getId());
				}
			}

		}
		
		try {
			Debuger.debug("PrizeImpl " + player.getId() + "," + player.getNick() + "," + getId() + "," + getCount());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 用于封装Excel物品需要好多好多 的检查器
	 * 
	 * @author 林岑
	 * 
	 */
	public static class NeedsCheckerImpl2 implements NeedsChecker {

		private INeeds build(Integer id, Integer count) {

			INeeds playerNeed;

			if (id == D.ID_CASH) {

				playerNeed = NeedsFactory.getPlayerNeed(PlayerProperty.CASH,
						count);

			}

			else if (id == D.ID_GOLD) {

				playerNeed = new GoldNeed(count);

			}

			else if (id == D.ID_DI_XIAN_LING) {

				playerNeed = NeedsFactory.getPlayerNeed(
						PlayerProperty.DI_XIAN_LING, count);

			}

			else if (id == D.ID_TIAN_XIAN_LING) {

				playerNeed = NeedsFactory.getPlayerNeed(
						PlayerProperty.TIAN_XIAN_LING, count);

			}

			else if (id == D.ID_JIN_XIAN_LING) {

				playerNeed = NeedsFactory.getPlayerNeed(
						PlayerProperty.JIN_XIAN_LING, count);

			}

			else if (id == D.ID_EXP) {

				playerNeed = NeedsFactory.getExpNeed(count);

			}

			else if (id == D.ID_PHYSICAL) {

				playerNeed = NeedsFactory.getPlayerNeed(
						PlayerProperty.PHYSICAL, count);

			}

			else if (id == D.ID_POWER) {

				playerNeed = NeedsFactory.getPlayerNeed(PlayerProperty.POWER,
						count);

			}

			else if (id == D.ID_GIFT_GOLD) {

				playerNeed = new GoldNeed(count);

			}

			else if (id == D.ID_CULTIVATION) {

				playerNeed = NeedsFactory.getPlayerNeed(
						PlayerProperty.CULTIVATION, count);

			}

			else if (id == D.ID_POINTS) {

				playerNeed = NeedsFactory.getPlayerNeed(PlayerProperty.POINTS,
						count);

			}

			else if (id == D.ID_REPUTATION) {

				playerNeed = NeedsFactory.getPlayerNeed(
						PlayerProperty.REPUTATION, count);

			}

			else if (id == D.ID_LOT) {

				playerNeed = NeedsFactory.getLotNeed(count);

			}

			else if (id == D.ID_CASH_BALL) {

				playerNeed = NeedsFactory.getCashBallNeed(count);

			}

			else if (id == D.ID_ACTIVITY1) { // 活动币1

				playerNeed = NeedsFactory.getPlayerNeed(
						PlayerProperty.ACTIVITY1, count);

			}

			else if (id == D.ID_ACTIVITY2) { // 活动币2

				playerNeed = NeedsFactory.getPlayerNeed(
						PlayerProperty.ACTIVITY2, count);

			}

			else if (id == D.ID_ACTIVITY3) { // 活动币3

				playerNeed = NeedsFactory.getPlayerNeed(
						PlayerProperty.ACTIVITY3, count);

			}

			else if (id == D.ID_ACTIVITY4) { // 活动币4

				playerNeed = NeedsFactory.getPlayerNeed(
						PlayerProperty.ACTIVITY4, count);

			}

			else if (id == D.ID_ACTIVITY5) { // 活动币5

				playerNeed = NeedsFactory.getPlayerNeed(
						PlayerProperty.ACTIVITY5, count);

			}

			else if (id == D.ID_SHOU_HUN) { // 兽魂

				playerNeed = NeedsFactory.getPlayerNeed(
						PlayerProperty.SHOU_HUN, count);

			}

			else if (id == D.ID_BAI_WAN_TONG_QIAN) { // 金币

				playerNeed = NeedsFactory.getPlayerNeed(PlayerProperty.CASH,
						count);

			}

			else if (id == D.ID_NEW_GOLD) {

				playerNeed = NeedsFactory.getPlayerNeed(
						PlayerProperty.NEW_GOLD, count);

			}

			else if (id == BaseRewardId.QiSeJingShi_110023) {

				playerNeed = new JingShiNeed(BaseRewardId.QiSeJingShi_110023, count);

			} 
			
			

			else if (id == 110025) {//110025白水晶

				playerNeed = new JingShiNeed(110025, count);

			}
			else if (id == 110026) {//  110026黄水晶

				playerNeed = new JingShiNeed(110026, count);

			}
			
			
			
			else if (id == BaseRewardId.RongYu_110024) {

				playerNeed = NeedsFactory.getPlayerNeed(PlayerProperty.RONG_YU,
						count);

			}

			else { // 道具奖励

				playerNeed = NeedsFactory.getPropNeed(id, count);

			}

			playerNeed.discount(discount);

			return playerNeed;
		}

		private float discount = 1.0f;

		private String[] needs;

		public NeedsCheckerImpl2(String... needs) {
			this.needs = needs;
		}

		@Override
		public void deduct(Player player) {

			final List<INeeds> all = getNeeds();

			for (INeeds n : all) {

				n.discount(discount);

				n.deduct(player);
			}
		}

		@Override
		public void check(Player player) {

			List<INeeds> buildNeeds = getNeeds();

			for (INeeds iNeeds : buildNeeds) {

				iNeeds.discount(discount);

				iNeeds.checkEnouph(player);
			}
		}

		@Override
		public List<INeeds> getNeeds() {

			final List<INeeds> all = new ArrayList<INeeds>();

			for (String n : this.needs) {

				addNeeds(all, n);

			}

			return all;
		}

		private void addNeeds(final List<INeeds> all, String n) {

			final String[] split = n.split("\\|");

			for (String need : split) {

				need = need.trim();

				if (!need.isEmpty()) {

					INeeds buildNeed = buildNeed(need);

					buildNeed.discount(discount);

					all.add(buildNeed);
				}
			}
		}

		private INeeds buildNeed(String text) {

			final String[] s = text.split(",");

			final Integer id = new Integer(s[0].trim());

			final Integer count = new Integer(s[1].trim());

			INeeds build = build(id, count);

			build.discount(discount);

			return build;
		}

		@Override
		public void discount(float discount) {

			this.discount = discount;
		}

		@Override
		public int getDeductTimesMax(City city) {

			List<INeeds> buildNeeds = getNeeds();

			int c = 0;

			for (INeeds iNeeds : buildNeeds) {

				int checkTimes = iNeeds.getDeductTimesMax(city);

				c += checkTimes;
			}

			return c;
		}

		@Override
		public void check(City city) {
			check(city.getPlayer());
		}

		@Override
		public void deduct(City user) {
			deduct(user.getPlayer());
		}
	}

	public static class PrizeBuilder {

		/**
		 * 构造一个物品
		 * 
		 * @param player
		 * @param goods
		 * @return
		 */
		public PrizePro build(Player player, Prize goods) {

			PrizePro.Builder b = PrizePro.newBuilder();

			int id2 = goods.getId();

			b.setId(id2);

			int count2 = goods.getCount();
			if (count2 == 0) {
				new Exception().printStackTrace();
			}

			b.setCount(count2);

			return b.build();
		}

	}
}
