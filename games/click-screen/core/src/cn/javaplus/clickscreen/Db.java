package cn.javaplus.clickscreen;

import java.util.List;
import java.util.Map;

import org.javaplus.clickscreen.excel.Row;
import org.javaplus.clickscreen.excel.Sheet;
import org.javaplus.game.common.IPreferences;
import org.javaplus.game.common.log.Log;
import org.javaplus.game.common.util.Lists;
import org.javaplus.game.common.util.Util;

import cn.javaplus.clickscreen.record.Dto;
import cn.javaplus.clickscreen.tank.TankDto;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

public class Db {

	public class CommitThread extends Thread {

		private Integer space;

		@Override
		public void run() {
			while (true) {
				try {
					commit();
					Log.d("commit over");
				} catch (Exception e) {
					e.printStackTrace();
				}
				Util.Thread.sleep(getSpace());
			}
		}

		private long getSpace() {
			if (space == null) {
				Sheet sheet = App.getStaticData().get("systemConfig");
				Row row = sheet.get("DB_WRITE_SPACE");
				space = row.getInt("value");
			}
			return space;
		}
	}

	private static final int BOSS_MISSION = 10;
	private Dto dto;

	public void addCoin(double c) {
		dto.setCoin(dto.getCoin() + c);
	}

	public void commit() {
		String r = JSON.toJSONString(dto);
		IPreferences p = App.getPreferences();
		p.put("click-screen", r);
	}

	public double getCoin() {
		return dto.getCoin();
	}

	public Db() {
		dto = loadDto();
		CommitThread t = new CommitThread();
		t.setPriority(Thread.MIN_PRIORITY);
		t.start();
	}

	private Dto loadDto() {
		IPreferences p = App.getPreferences();
		String js = p.getString("click-screen");
		if (js.trim().isEmpty())
			return new Dto();
		return JSON.parseObject(js, Dto.class);
	}

	public int getEnemyTankLevel() {
		return 10;
	}

	public List<TankDto> getTanks() {
		Map<Integer, TankDto> tanks = getTanksMap();
		return Lists.newArrayList(tanks.values());
	}

	private Map<Integer, TankDto> getTanksMap() {
		Map<Integer, TankDto> tanks = dto.getTanks();
		if (tanks == null)
			tanks = initTanks();
		return tanks;
	}

	private Map<Integer, TankDto> initTanks() {
		Sheet tanksSheet = App.getStaticData().get("tanks");
		List<Row> rows = tanksSheet.getAll();
		Map<Integer, TankDto> tanks = Maps.newHashMap();
		for (Row row : rows) {
			TankDto r = new TankDto();

			int id = row.getInt("id");

			r.setTankId(id);
			r.setBulletId(row.getInt("bulletId"));

			r.setOpen(row.getBool("isOpen"));

			r.setFireByTap(row.getBool("isFireByTap"));
			r.setAttack(row.getDouble("attack"));
			r.setFireSpace(row.getDouble("fireSpace"));
			r.setSpeed(row.getDouble("bulletSpeedOnOpen"));
			Log.d("Init tank:" + JSON.toJSONString(r));

			tanks.put(r.getTankId(), r);
		}
		dto.setTanks(tanks);
		return dto.getTanks();
	}

	public void reduceCoin(double c) {
		addCoin(-c);
	}

	public int getSelectedMission() {
		int a = App.getPreferences().getInt("selected-mission");
		if (a == 0) {
			a = 1;
			App.getPreferences().put("selected-mission", a);
		}
		return a;
	}

	public int getSecondaryMission() {
		if (dto.getSecondaryMission() == 0) {
			dto.setSecondaryMission(1);
		}
		return dto.getSecondaryMission();
	}

	public void passMission() {
		dto.setSecondaryMission(dto.getSecondaryMission() + 1);
		if (dto.getSecondaryMission() >= BOSS_MISSION) {
			App.getPreferences().put("selected-mission", getSelectedMission() + 1);
			dto.setSecondaryMission(1);
		}
	}

	public boolean isBoss() {
		int sm = getSecondaryMission();
		return sm == BOSS_MISSION;
	}

	public int getBossMission() {
		return BOSS_MISSION;
	}

	public TankDto getTank(int id) {
		Map<Integer, TankDto> tanks = getTanksMap();
		return tanks.get(id);
	}
}
