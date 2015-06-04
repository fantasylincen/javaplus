package cn.mxz.mission.type;

import java.util.Arrays;
import java.util.List;

import cn.javaplus.util.Util;
import cn.javaplus.util.Util.Collection;
import cn.mxz.MapTemplet;
import cn.mxz.MissionMapTemplet;
import cn.mxz.MissionMapTempletConfig;
import cn.mxz.battle.MissionBattle;
import cn.mxz.city.City;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;

public class BattleEvent implements IEvent {

	private final int[] camp;

	private MapTemplet templet;

	private List<Integer> positions = Lists.newArrayList(1, 3, 4, 5, 7);

	private final boolean isBranch;

	private final boolean isBoss;

	/**
	 * 此构造函数仅仅处理boss情况
	 * 
	 * @param templet
	 * @param isBranch
	 * @param isBoss
	 */
	public BattleEvent(MissionMapTemplet templet, boolean isBranch,
			boolean isBoss) {
		this.isBoss = isBoss;
		this.isBranch = isBranch;

		int[] monsterIdS;
		this.templet = templet;

		if (isBoss) {// 最后一个怪
			int size = templet.getMonsterNumber();
			monsterIdS = new int[size];
			if (isBranch) {
				monsterIdS[0] = Integer.parseInt(templet.getLineBossId());
			} else {// 主线
				monsterIdS[0] = Integer.parseInt(templet.getBossId());
			}
			int[] enemys = buildEnemys(templet, size - 1);// 随机生成小怪
			System.arraycopy(enemys, 0, monsterIdS, 1, enemys.length);
		} else {
			int size = templet.getMonsterNumber();
			monsterIdS = buildEnemys(templet, size);
			// monsterIdS = new int[size];
			// String[] monsterStr = templet.getMonsterId().split(",");
			// for (int i = 0; i < size; i++) {
			// monsterIdS[i] =
			// cn.javaplus.common.util.Util.Collection.getRandomOne(Collection.getIntegers(templet.getMonsterId()));
			// }
		}

		cn.javaplus.util.Util.Collection.upset(positions);
		camp = new int[monsterIdS.length * 2];
		for (int i = 0; i < monsterIdS.length; i++) {
			camp[i * 2] = monsterIdS[i];
			camp[i * 2 + 1] = positions.get(i);
		}
	}

	/**
	 * 处理小怪的情况
	 * 
	 * @param templet
	 */
	public BattleEvent(MapTemplet templet) {
		this.templet = templet;
		isBoss = false;
		isBranch = false;

		int size = templet.getMonsterNumber();
		int[] monsters = buildEnemys(templet, size);
		camp = setPosition(monsters);
	}

	private int[] buildEnemys(MapTemplet templet, int size) {
		int[] monsters = new int[size];
		for (int i = 0; i < size; i++) {
			String monsterId = templet.getMonsterId();
			List<Integer> integers = Collection.getIntegers(monsterId);
			monsters[i] = Util.Collection.getRandomOne(integers);
		}
		return monsters;
	}

	/**
	 * arg的格式为： 100004,1|false|false 怪,位置,怪,位置|是否boss|是否支线
	 * 
	 * @param arg
	 * @param missionId
	 */
	public BattleEvent(String arg, int missionId) {

		this.templet = MissionMapTempletConfig.get(missionId);
		// 通过arg来初始化内容MissionMapTemplet 以及 jsonTemplet
		String[] allArgs = arg.split("\\|");
		String[] arr = allArgs[0].split(",");
		camp = new int[arr.length];
		int i = 0;
		for (String s : arr) {
			camp[i++] = Integer.parseInt(s);
		}
		isBoss = Boolean.parseBoolean(allArgs[1]);
		isBranch = Boolean.parseBoolean(allArgs[2]);
		// jsonTemplet = MissionPathCfg.getMapTemplet(missionId).getById( jsonId
		// );
	}

	@Override
	public EventType getType() {
		return EventType.BATTLE;
	}

	@Override
	public int getBrief() {
		return camp[0];
	}

	@Override
	public Object run(City user) {

		MissionBattle battle = new MissionBattleImpl(camp, user,
				templet.getId(), !isBranch, isBoss, false);

		battle.fighting();

		Debuger.debug("RandomEvent.run() MissionBattle 事件");
		return battle;
	}

	// private void save(MissionBattle battle) {
	// ObjectOutputStream os = null;
	// try {
	// os = new ObjectOutputStream(new FileOutputStream("logs/battles/" +
	// System.currentTimeMillis() + ".battle"));
	// os.writeObject(new BattleRecorder(battle).save());
	// os.flush();
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// } finally {
	// Closer.close(os);
	// }
	// }

	public static void main(String[] args) {
		int[] a = new int[] { 4, 5, 6 };
		System.out.println(Arrays.toString(a));
	}

	@Override
	public String getMissionArg() {
		StringBuilder sb = new StringBuilder();
		for (int i : camp) {
			sb.append(i + ",");
		}
		sb.deleteCharAt(sb.length() - 1);// 去掉最后一个逗号

		sb.append("|").append(isBoss);
		sb.append("|").append(isBranch);

		return sb.toString();
	}

	/**
	 * 设置小怪的位置，用一个数组返回 怪物id,position,怪物id,position
	 * 
	 * @param monsters
	 */
	private int[] setPosition(int[] monsters) {
		int[] camp = new int[monsters.length * 2];
		Util.Collection.upset(positions);
		for (int i = 0; i < monsters.length; i++) {
			camp[i * 2] = monsters[i];
			camp[i * 2 + 1] = positions.get(i);
		}
		return camp;
	}

}
