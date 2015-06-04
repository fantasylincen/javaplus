//package cn.mxz.activity.boss;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import com.google.common.collect.Lists;
//
//import cn.javaplus.common.util.math.Fraction;
//import cn.mxz.BossEventMapTemplet;
//import cn.mxz.BossEventMapTempletConfig;
//import cn.mxz.base.world.WorldFactory;
//import cn.mxz.fighter.AbstractFighter;
//import cn.mxz.messagesender.MessageFactory;
//import cn.mxz.prop.equipment.Equipment;
//import cn.mxz.team.Skill;
//import cn.mxz.user.City;
//import db.dao.factory.CacheDaoFactory;
//import db.domain.BossData;
//import define.D;
//
//public class BossImpl extends AbstractFighter implements Boss {
//
//	private BossData	data;
//
//	BossImpl(BossData data) {
//
//		this.data = data;
//
//		this.addListener(new SaveAttackTimesListener());
//		this.addListener(new SaveKillerListener());
//	}
//
//	@Override
//	public int getTypeId() {
//		return data.getBossTempletId();
//	}
//
//	@Override
//	public int getLevel() {
//		return data.getLevel();
//	}
//
//	@Override
//	public void reduceHp(int reduce) {
//
//		data.addHp(-reduce);
//	}
//
//	@Override
//	public void commit() {
//
//		DaoFactory.getCacheBossDataDAO().update(data);
//	}
//
//	@Override
//	public void addHp(int i) {
//
//		data.addHp(i);
//	}
//
//	@Override
//	public int getId() {
//		return data.getBossId();
//	}
//
//	@Override
//	public int getTempletId() {
//		return data.getBossTempletId();
//	}
//
//	@Override
//	public City getFinder() {
//
//		return WorldFactory.getWorld().get(data.getUname());
//	}
//
//	@Override
//	public int getRemainSec() {
//
//		if (data.getChallengeTimes() >= D.BOSS_RUN_AWAY_ATTACK_TIMES) {
//
//			return 0;
//		}
//
//		if (isDeath()) {
//
//			return 0;
//		}
//
//		int runAwaySec = getRunAwaySec();
//
//		int now = (int) (System.currentTimeMillis() / 1000);
//
//		int foundTime = data.getFoundTime();
//
//		int still = now - foundTime;
//
//		return still > runAwaySec ? 0 : runAwaySec - still;
//	}
//
//	/**
//	 * 逃跑时间
//	 *
//	 * @return
//	 */
//	private int getRunAwaySec() {
//
//		if (isSuperBoss()) {
//
//			return D.SUPER_BOSS_RUN_AWAY;
//
//		} else {
//
//			return D.NORMAL_BOSS_RUN_AWAY;
//		}
//	}
//
//	@Override
//	public boolean isSuperBoss() {
//
//		// 遍历BossEventMapTempletConfig表的CoreBossId字段, 如果bossID在其中, 就视为终极Boss
//
//		List<String> listByCoreBossId = BossEventMapTempletConfig.getListByCoreBossId();
//
//		for (String string : listByCoreBossId) {
//
//			if (string.contains(data.getBossTempletId() + "")) {
//
//				return true;
//			}
//		}
//
//		return false;
//	}
//
//	@Override
//	public int getStatus(City city) {
//
//		// Boss 状态: 1: 我发现的Boss 2:请求我帮助的Boss 3:被击杀的BOss 4:逃跑掉的Boss
//
//		if (isDeath()) {
//
//			return 3;
//		}
//
//		if (isRunAway()) {
//
//			return 4;
//		}
//
//		for (int i = 0; i < BossData.HELPER_ID_LEN; i++) {
//
//			String helperId = data.getHelperId(i);
//
//			if (city.getId().equals(helperId)) {
//
//				return 2;
//			}
//		}
//
//		if (city.getId().equals(data.getUname())) {
//
//			return 1;
//		}
//
//		return 4;
//	}
//
//	@Override
//	public boolean isRunAway() {
//
//		if (isDeath()) {
//
//			return false;
//		}
//
//		int remainSec = getRemainSec();
//
//		return remainSec <= 0;
//	}
//
//	@Override
//	public Fraction getHp() {
//
//		return new Fraction(data.getHp(), getAttribute().getHp());
//	}
//
//	@Override
//	public int getMultiple() {
//		return D.BOSS_FULL_FIGHTING_ADDITION;
//	}
//
//	@Override
//	public List<BossChallenger> getBossChallengers() {
//
//		List<BossChallenger> list = new ArrayList<BossChallenger>();
//
//		list.add(new BossChallengerImpl(data.getUname())); // 加入发现者
//
//		for (int i = 0; i < BossData.HELPER_ID_LEN; i++) {
//
//			String helperId = data.getHelperId(i);
//
//			if (helperId == null || helperId.isEmpty()) {
//
//				continue;
//			}
//
//			list.add(new BossChallengerImpl(helperId));
//		}
//
//		return list;
//	}
//
//	@Override
//	protected int getActiveSkillLevel() {
//		return 1;
//	}
//
//	@Override
//	public BossChallenger getKiller() {
//
//		List<BossChallenger> bossChallengers = getBossChallengers();
//
//		for (BossChallenger c : bossChallengers) {
//
//			if (data.getKiller().equals(c.getId())) {
//
//				return c;
//			}
//		}
//
//		return null;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((data == null) ? 0 : data.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		BossImpl other = (BossImpl) obj;
//		if (data == null) {
//			if (other.data != null)
//				return false;
//		} else if (data.getBossId() != other.data.getBossId())
//			return false;
//		return true;
//	}
//
//	@Override
//	public void changeHelper(List<BossChallenger> sub) {
//
//		clearHelpers();
//
//		for (int i = 0; i < sub.size(); i++) {
//
//			City city2 = sub.get(i).getCity();
//
//			data.setHelperId(i, city2.getId());
//		}
//
//		commit();
//
//
//		for (BossChallenger bossChallenger : sub) {
//
//			BossPlayer p = BossObjects.getBossPlayer(bossChallenger.getId());
//
//			MessageFactory.getBoss().onReceiveBoss(bossChallenger.getCity().getSocket(), data.getBossId(), p.getBossCountInvitedMe());
//		}
//
//	}
//
//	private void clearHelpers() {
//
//		for (int i = 0; i < BossData.HELPER_ID_LEN; i++) {
//
//			data.setHelperId(i, "");
//		}
//	}
//
//	@Override
//	public BossChallenger getMvp() {
//
//		List<BossChallenger> cs = getBossChallengers();
//
//		Collections.sort(cs, new DamageComparetor(this));
//
//		if (cs.size() < 1) {
//
//			return null;
//		}
//
//		return cs.get(0);
//
//	}
//
//	@Override
//	public BossChallenger getJMvp() {
//
//		List<BossChallenger> cs = getBossChallengers();
//
//		Collections.sort(cs, new DamageComparetor(this));
//
//		if (cs.size() < 2) {
//
//			return null;
//		}
//
//		return cs.get(1);
//	}
//
//	@Override
//	protected Fraction getActiveSkillExp() {
//
//		return new Fraction(1, 1);
//	}
//
//	@Override
//	public int getHpNow() {
//		return data.getHp();
//	}
//
//	@Override
//	protected float getNanDuXiShu() {
//
//		BossEventMapTemplet map = BossEventMapTempletConfig.get(data.getMapId());
//
//		float bossParam = map.getBossParam();
//
//		return bossParam;
//	}
//
//	public BossData getDto() {
//		return data;
//	}
//
//	@Override
//	public List<Equipment> getEquipments() {
//		return Lists.newArrayList();
//	}
//
//	@Override
//	public int getHpMax() {
//		int hpMax = getAttribute().getHp();
//		return hpMax;
//	}
//
//	@Override
//	public List<Skill> getSkills() {
//		return Lists.newArrayList();
//	}
//}
