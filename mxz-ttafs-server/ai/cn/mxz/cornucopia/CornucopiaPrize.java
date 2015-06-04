package cn.mxz.cornucopia;

import cn.javaplus.util.Util;
import cn.mxz.CornucopiaTemplet;
import cn.mxz.PrizeInExcel;
import cn.mxz.user.Player;
import define.D;

/**
 * 聚宝盆奖励, 该奖励可以通过PrizeSender.send()发送给指定玩家
 * 
 * @author 林岑
 * 
 */
class CornucopiaPrize implements PrizeInExcel {

	private CornucopiaTemplet temp;
	private Player player;
	private int cashAdd;
	private int cash;
	private String type;

	CornucopiaPrize(CornucopiaTemplet temp, Player player) {
		this.temp = temp;
		this.player = player;
		calcCashType();
		calcHengCai();// 横财
	}

	public int getCash() {
		return cash;
	}

	private void calcCashType() {
		String[] split = temp.getCash().split(",");
		type = split[0];
		String string = split[1];
		cash = new Integer(string);

		cash = (int) (((player.getLevel() - D.YAO_QIAN_SHU_OPEN_LEVEL) * D.YAO_QIAN_SHU_LEVEL_FACTOR + 1) * cash);
	}

	public int getCashAdd() {
		return cashAdd;
	}

	private void calcHengCai() {
		Cornucopia c = CornucopiaFactory.getCornucopia(player.getId());
		float yunShi = c.getYunShi();
		if (Util.Random.isHappen(yunShi)) {
			String odds = D.YAO_QIAN_SHU_HENG_CAI_ODDS;
			String[] split = odds.split("\\-");
			double min = new Double(split[0]);
			double max = new Double(split[1]);
			double percent = Util.Random.get(min, max);
			cashAdd = (int) (percent * cash);
		}
	}

	@Override
	public String getAwards() {
		return type + "," + (cash + cashAdd);
	}

}
