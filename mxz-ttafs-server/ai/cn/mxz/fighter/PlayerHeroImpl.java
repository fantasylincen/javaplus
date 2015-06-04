package cn.mxz.fighter;

import java.util.List;

import cn.javaplus.util.Util;
import cn.mxz.AdditionMultiplier;
import cn.mxz.Attribute;
import cn.mxz.SkillTemplet;
import cn.mxz.base.TalentEmpty;
import cn.mxz.battle.Battle;
import cn.mxz.battle.MissionBattle;
import cn.mxz.events.Events;
import cn.mxz.events.PlayerHeroQualityUpEvent;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounterSetter;
import cn.mxz.util.debuger.Debuger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import define.D;

public class PlayerHeroImpl extends HeroImpl implements PlayerHero {

	public PlayerHeroImpl() {

		super();
	}

	public PlayerHeroImpl(HeroImpl hero) {

		super(hero);
	}

	@Override
	public Attribute getAttribute() {

		Attribute add = super.getAttribute();

		Attribute charmAddition = getCharmAddition();

		add = AttributeCalculator.adding(add, charmAddition); // 魅力加成

		return add;
	}

	@Override
	public Attribute getCharmAddition() {

		Attribute sp = super.getAttribute();

		Attribute m = AttributeCalculator.multiply(sp, getCharmAdditions()); // 魅力加成

		return m;
	}

	@Override
	public int getMaxLevel() {

		return D.USER_MAX_LEVEL;
	}

	@Override
	public float getAbilityAddition() {

//		int count = PracticeCenterImpl.getInstance().getCareCount(city);
//
//		Integer maxKey = LotTempletConfig.getMaxKey();
//
//		if (count > maxKey) {
//
//			count = maxKey;
//		}
//
//		LotTemplet temp = LotTempletConfig.get(count);
//
//		if (temp == null) {
//
//			return 0;
//		}
//
//		return temp.getAddition();
		return 0;
	}

	@Override
	public String getUname() {

		return city.getId();
	}

	@Override
	public AdditionMultiplier getCharmAdditions() {

//		List<Integer> list = new ArrayList<Integer>(HeartTempletConfig.getKeys());
//
//		Collections.reverse(list);
//
//		Iterator<Integer> it = list.iterator();
//
//		int charm = city.getPlayer().get(PlayerProperty.CHARM);
//
//		while (it.hasNext()) {
//
//			Integer id = it.next();
//
//			HeartTemplet temp = HeartTempletConfig.get(id);
//
//			if (charm >= temp.getValue()) {
//
//				return temp;
//			}
//		}

		return new TalentEmpty();
	}

//	@Override
//	public int getReviseRemainSec() {
//
//		if (super.getHpNow() > 0) {
//
//			return 0;
//		}
//
//		IMissionManager ms = city.getMission();
//
//		int dieCount = ms.getDieCount();
//
//		int time = ReviseRemainFetcher.getInstance().getRemainSec(dieCount) * 1000;// 玩家复活时间
//
//		long deathTime = new Long(getDeathTime());
//
//		long end = time + deathTime;
//
//		long remain = end - System.currentTimeMillis();
//
//		if (remain <= 0) {
//
//			// Debuger.debug("PlayerHeroImpl.getReviseRemainSec() 死亡时间:" +
//			// deathTime + " 剩余:" + remain);
//
//			return 0;
//
//		} else {
//
//			// Debuger.debug("PlayerHeroImpl.getReviseRemainSec() 复活剩余时间:" +
//			// remain);
//
//			return (int) (remain / 1000);
//		}
//	}
//
//	private String getDeathTime() {
//
//		UserCounter his = city.getUserCounterHistory();
//
//		long longTime = his.get(CounterKey.PLAYER_DEATH_TIME);
//
//		longTime *= 1000;
//
//		return longTime + "";
//	}

	@Override
	public void reduceHp(int reduce) {

		super.reduceHp(reduce);

		if (super.getHpNow() <= 0) {

			Debuger.debug("PlayerHeroImpl.reduceHp() 战士洗白了!");

			UserCounterSetter his = city.getUserCounterHistory();

			his.set(CounterKey.PLAYER_DEATH_TIME, (int) (System.currentTimeMillis() / 1000)); // 设置死亡时间
		}
	}

//	@Override
//	public int getHpNow() {
//
//		int hpNow = super.getHpNow();
//
//		if (hpNow <= 0 && getReviseRemainSec() <= 2) { // 自动复活
//
//			Debuger.debug("PlayerHeroImpl.getHpNow() 复活了:" + this);
//
//			addHp(Integer.MAX_VALUE / 2);
//
//			commit();
//		}
//
//		return super.getHpNow();
//	}

	/**
	 * 触发了伪概率的回合数
	 */
	private int	roundWeiGaiLv	= -1;

	private void initWeiGaiLv(int id) {
		JSONObject o = JSON.parseObject(D.SKILL_WEI_GAI_LV);

		int mapMin = o.getInteger("mapMin");
		int mapMax = o.getInteger("mapMax");
		int round = o.getInteger("round");

		if (id >= mapMin && id <= mapMax) {
			roundWeiGaiLv = Util.Random.get(1, round);
		}
	}

	@Override
	protected boolean isSkillHappen(List<SkillTemplet> temps, Battle battle) {

		if (battle instanceof MissionBattle) {
			MissionBattle b = (MissionBattle) battle;
			if (b.isBoss()) {
				int r = battle.getRound();
				if (r == 1) {
					initWeiGaiLv(b.getMapId());
				}
				if(roundWeiGaiLv == r) {
					return true;
				}
//				return roundWeiGaiLv == r;
			}
		}

		return super.isSkillHappen(temps, battle);
	}


	@Override
	public void qualityLevelUp() {
		super.qualityLevelUp();
		Events.getInstance().dispatch(new PlayerHeroQualityUpEvent(getCity(), this));
	}
	
	@Override
	public String getName() {
		return city.getPlayer().getNick();
	}
}
