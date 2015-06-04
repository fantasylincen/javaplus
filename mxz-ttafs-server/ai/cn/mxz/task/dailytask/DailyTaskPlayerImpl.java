package cn.mxz.task.dailytask;

import java.util.Collection;

import mongo.gen.MongoGen.DailyTaskDto;
import cn.mxz.base.task.Task;
import cn.mxz.city.City;
import cn.mxz.shangxiang.ShangXiangPlayer;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

public class DailyTaskPlayerImpl implements DailyTaskPlayer {

	private City city;

	public DailyTaskPlayerImpl(City city) {
		this.city = city;
	}

	@Override
	public int getCornucopiaTimes() {

		return get(CounterKey.CORNUCOPIA_RUN_TIMES);
	}

	private int get(CounterKey d, Object... args) {
		UserCounter c = getTodayCounter();
		return c.get(d, args);
	}

	@Override
	public int getCorona() {
		return get(CounterKey.CORONA_RUN_TIMES);
	}

	@Override
	public int getDogzDunWuTimes() {
		return get(CounterKey.DOGZ_DUNWU_TIMES);
	}

	@Override
	public int getProtectedDaJiTimes() {
		int i = get(CounterKey.PROTECT_DAJI_TIMES);
		return i;
	}

	@Override
	public int getPracticeTimes() {
		return get(CounterKey.PRACTICE_FINISH_TIMES);
	}

	@Override
	public int getFishTimes() {
		return get(CounterKey.FISH_TIMES);
	}

	@Override
	public int getFindGodTimes() {
		return getR(1) + getR(2) + getR(3);
	}

	private int getR(int i) {
		UserCounter his = city.getUserCounterHistory();
		return his.get(CounterKey.RECRUIT_TIMES, i);
	}

	@Override
	public int getEquipmentLevelUpTimes() {
		return get(CounterKey.EQUIPMENT_LEVEL_UP_TIMES);
	}

	@Override
	public int getDogzFeedTimes() {
		return get(CounterKey.DOGZ_FEED_TIMES);
	}

	@Override
	public int getSnatchTimes() {
		return get(CounterKey.SNATCH_SUCCESS_TIMES);
	}

	@Override
	public int getSacrificeTimes() {
		return get(CounterKey.FIGHTER_SACRIFICE_TIMES);
	}

	@Override
	public int getRechargeGold() {
		return get(CounterKey.TOTAL_RECHARGE_GOLD_COUNT);
	}

	@Override
	public boolean isFinishNightAndNoonShangXiang() {
		ShangXiangPlayer player = city.getShangXiangPlayer();
		return player.hasReceiveNoon() && player.hasReceiveNight();
	}

	@Override
	public boolean hadPassMissionToday() {
		return getTodayCounter().isMark(CounterKey.HAS_PASS_MISSION_TODAY);
	}

	@Override
	public int getTaskFinishCountToday() {

		Collection<Task<DailyTaskDto>> all = city.getDailyTaskManager()
				.getAll();

		int count = 0;
		for (Task<DailyTaskDto> task : all) {
			if (task.isFinishAll()) {
				count++;
			}
		}
		return count;
	}

	@Override
	public int getPvpWinningStreak() {
		return get(CounterKey.PVP_FIGNTING_TIMES);
	}

	private UserCounter getTodayCounter() {
		return city.getUserCounter();
	}

	@Override
	public int getAttackShenMoTimesQLYJ() {

		UserCounter uc = getTodayCounter();
		return uc.get(CounterKey.SHEN_MO_QLYJ_FIGNTING_TIMES);
	}

	@Override
	public int getYuanShenLevelUpTimes() {

		UserCounter c = getTodayCounter();
		int i = c.get(CounterKey.YUAN_SHEN_LEVEL_UP_TIMES);
		return i;
	}

	@Override
	public int getBLTYTimes() {
		UserCounter c = getTodayCounter();
		return c.get(CounterKey.RECRUIT_TIMES, 2);
	}

	@Override
	public int getWLTYTimes() {

		UserCounter c = getTodayCounter();
		return c.get(CounterKey.RECRUIT_TIMES, 3);
	}

	@Override
	public int getDuJieTimes() {
		UserCounter c = getTodayCounter();
		return c.get(CounterKey.CHUANG_ZHEN_TIMES);
	}

	@Override
	public boolean hasDianZan() {
		return city.getZanManager().getTodayIsClick();
	}

	@Override
	public int getTiaoZhanBossTimes() {
		UserCounter c = getTodayCounter();
		return c.get(CounterKey.BOSS_BATTLE_TIMES);
	}

	@Override
	public int getHeiShiTimes() {
		UserCounter c = getTodayCounter();
		int i = c.get(CounterKey.HEI_SHI_EXCHANGE_TIMES);
		return i;
	}

	@Override
	public short getShangXiangTimes() {
		UserCounter c = getTodayCounter();
		int i = c.get(CounterKey.SHANG_XIANG_TIMES);
		return (short) i;
	}

}
