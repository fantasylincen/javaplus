package cn.mxz.base.servertask;

import java.util.Collection;
import java.util.List;

import message.S;
import cn.mxz.CopterRanklistTemplet;
import cn.mxz.CopterRanklistTempletConfig;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.chuangzhen.ChuangZhenPlayer;
import cn.mxz.chuangzhen.ChuangZhenRankingList;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.city.Messages;
import cn.mxz.util.debuger.Debuger;
import db.dao.impl.DaoFactory;

public class ChuangZhenZeroTask extends TaskSafetyLogToFile {

	@Override
	public void runSafty() {

		sendRankingReward();
		
		clearRankingList();
		
	}

	private void sendRankingReward() {
		ChuangZhenRankingList list = ChuangZhenRankingList.getInstance();
		
		String title = Messages.getText(S.S60215);
		String desc = Messages.getText(S.S60216);
		
		int i =1;
		List<ChuangZhenPlayer> all2 = list.getAll(4);
		for( ChuangZhenPlayer player : all2 ){
			CopterRanklistTemplet templet = CopterRanklistTempletConfig.get(i++);
			if(templet == null) {
				break;
			}
			int count = templet.getSycee3();
			
			String str;
			
			if(templet.getRewardType() == 1) {
				str = "110022," + count;
			} else {
				str = "110009," + count;
			}
			
			CityFactory.getCity(player.getId()).getPrizeCenter().addPrize(3, str, desc, title);
		}
		i=1;
		List<ChuangZhenPlayer> all3 = list.getAll(5);
		for( ChuangZhenPlayer player : all3 ){
			CopterRanklistTemplet templet = CopterRanklistTempletConfig.get(i++);
			if(templet == null) {
				break;
			}
			int count = templet.getSycee4();
			String str;
			
			if(templet.getRewardType() == 1) {
				str = "110022," + count;
			} else {
				str = "110009," + count;
			}
			CityFactory.getCity(player.getId()).getPrizeCenter().addPrize(3, str, desc, title);
		}
		i=1;
		List<ChuangZhenPlayer> all4 = list.getAll(6);
		for( ChuangZhenPlayer player : all4 ){
			CopterRanklistTemplet templet = CopterRanklistTempletConfig.get(i++);
			if(templet == null) {
				break;
			}
			int count = templet.getSycee5();
			String str;
			
			if(templet.getRewardType() == 1) {
				str = "110022," + count;
			} else {
				str = "110009," + count;
			}
			CityFactory.getCity(player.getId()).getPrizeCenter().addPrize(3, str, desc, title);
		}
	}

	private void clearRankingList() {
		DaoFactory.getChuangZhenDao().clear();
		
		Debuger.debug("ChuangZhenZeroTask.clearRankingList() 清空闯阵排行榜");

		Collection<City> all = WorldFactory.getWorld().getOnlineAll();
		for (City city : all) {
			city.freeMemory();
		}
	}

}
