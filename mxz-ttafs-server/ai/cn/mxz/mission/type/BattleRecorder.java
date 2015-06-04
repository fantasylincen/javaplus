package cn.mxz.mission.type;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import cn.mxz.battle.BattleCamp;
import cn.mxz.battle.MissionBattle;
import cn.mxz.fighter.Fighter;
import cn.mxz.formation.PlayerCamp;

public class BattleRecorder {

	private List<Fighter>	uds;
	private List<Fighter>	ups;
	private PlayerCamp		city;

	public BattleRecorder(MissionBattle battle) {
		BattleCamp under = battle.getUnder();
		BattleCamp upper = battle.getUpper();

		uds = under.getFighters();
		ups = upper.getFighters();

		city = battle.getUnderPlayerCamp();
	}

	public Object save() {

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		StringBuilder sb = new StringBuilder();
		pw.println();
		pw.println("----------");
		print(pw, uds);
		pw.println("----------");
		print(pw, ups);

		pw.println("----------");

		pw.println(city.getCity().getId());

		return sw.toString();
	}

	private void print(PrintWriter pw, List<Fighter> u) {
		for (Fighter f : u) {
			pw.println(f.getTypeId());
			pw.println(f.getLevel());
			pw.println(f.getHpNow());
			pw.println();
		}
	}

}
