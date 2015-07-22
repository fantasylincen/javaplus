package cn.vgame.a.robot;

import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.javaplus.random.WeightFetcher;
import cn.javaplus.util.Util;
import cn.vgame.a.Server;
import cn.vgame.a.account.Role;
import cn.vgame.a.turntable.Turntable;
import cn.vgame.a.turntable.TurntableUtil;
import cn.vgame.a.turntable.swt.ISwitchs;
import cn.vgame.share.KeyValue;
import cn.vgame.share.Xml;

public class Robot {

	private RobotSwitch switchs = new RobotSwitch();;
	private final String roleId;

	public Robot(String roleId) {
		this.roleId = roleId;
	}

	public String getId() {
		return roleId;
	}

	public Role getRole() {
		return Server.getRole(roleId);
	}

	public long getCoin() {
		return getRole().getCoin();
	}

	private static List<String> nicks;
	
	public String getNick() {
		if(nicks == null) {
			List<String> nicks2 = RobotManager.getNicks();
			nicks = Lists.newArrayList(nicks2);
			nicks.remove(null);
		}
		
		return Util.Collection.getRandomOne(nicks);
		
//		return getRole().getNick();
	}

	public long getCommitAll() {

		if (switchs == null)
			return 0;
		return TurntableUtil.getCountAll(switchs);
	}

	/**
	 * 是否压注了金鲨
	 * 
	 * @return
	 */
	public boolean isCommitJinSha() {
		if (switchs == null)
			return false;
		return switchs.getC() > 0;
	}

	/**
	 * 今日金币输入
	 */
	public long getCoinInToday() {
		KeyValue keyValueDaily = getRole().getKeyValueDaily();
		return keyValueDaily.getLong("COIN_IN");
	}

	/**
	 * 今日金币输出
	 */
	public long getCoinOutToday() {
		return getRole().getKeyValueDaily().getLong("COIN_OUT");
	}

	/**
	 * 历史金币输入
	 */
	public long getCoinInHistory() {
		return getRole().getKeyValueForever().getLong("COIN_IN");
	}

	public long getCoinOutHistory() {
		return getRole().getKeyValueForever().getLong("COIN_OUT");
	}

	public void getResultFromTurnTable() {
		if (switchs != null)
			Turntable.getInstance().getPlayResult(getRole(), switchs);
	}

	public ISwitchs getSwitchs() {
		return switchs;
	}

	public void randomCommit() {
		Turntable ins = Turntable.getInstance();
		ISwitchs randomSwitchs = randomSwitchs();
		// Log.d(getId(), TurntableUtil.getCountAll(randomSwitchs));
		switchs.add(randomSwitchs);
		ins.commitUserSwitchs(getId(), switchs);
	}

	private ISwitchs randomSwitchs() {
		RobotSwitch switchs = new RobotSwitch();
		int count = getRandomCount();
		for (int i = 0; i < count; i++) {
			Row row = getRandomRow();
			int coin = getRandomCoin();
			long coinNow = getCoin();
			long need = TurntableUtil.getCountAll(this.switchs);

			if (coinNow < need + coin) {
				break;
			}
			switchs.add(row.get("type"), coin);
		}
		// Log.d("robot random switch", getId(), getNick(), switchs);
		return switchs;
	}

	private int getRandomCoin() {
		String value = "ROBOT_COMMIT_COIN";
		return getRandomConstValue(value) / 10 * 10;
	}

	private Row getRandomRow() {
		Xml xml = Server.getXml();
		Sheet sheet = xml.get("weights");
		List<Row> all = sheet.getAll();
		WeightFetcher<Row> f = new RobotRandomFetcher();
		return Util.Random.getRandomOneByWeight(all, f);
	}

	private int getRandomCount() {
		String value = "ROBOT_COMMIT_COUNT";
		return getRandomConstValue(value);
	}

	private int getRandomConstValue(String value) {
		String ss = Server.getConst().getString(value);
		List<Integer> is = Util.Collection.getIntegers(ss);
		return Util.Random.get(is.get(0), is.get(1));
	}

	public void clearSwitchs() {
		this.switchs = new RobotSwitch();
	}
}
