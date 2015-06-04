package cn.mxz.cornucopia;

import java.util.List;

import cn.javaplus.util.Util;
import cn.mxz.CornucopiaTemplet;
import cn.mxz.CornucopiaTempletConfig;
import cn.mxz.protocols.cornucopia.CornucopiaP.CornucopiaPro;
import cn.mxz.user.Player;
import define.D;

class CornucopiaBuilder {

	public CornucopiaPro build(Cornucopia c) {
		CornucopiaPro.Builder b = CornucopiaPro.newBuilder();
		b.setTimes(c.getRunTimesToday());
		b.setAll(c.getAll());
		int v = (int) (c.getYunShi() * 100);
		b.setYunShi(v);

//		Debuger.debug("CornucopiaBuilder.build() 运势:" + v);
		int type = getType(v);

		b.setMaxTimes(c.getMaxTime());
//		Debuger.debug("CornucopiaBuilder.build()" + type + " " + v);
		b.setType(type);

		CornucopiaTemplet t = CornucopiaTempletConfig.get(c
				.getRunTimesToday() + 1);
		
		
		if (t == null) {
			b.setCash("110001,0");
			b.setCouponsNeed(100000000);
		} else {
			Player player = c.getCity().getPlayer();
			CornucopiaPrize p = new CornucopiaPrize(t, player);
			
			b.setCash("110001," + p.getCash());
			b.setCouponsNeed(t.getCouponsNeed());
		}
		return b.build();
	}

	private int getType(int v) {
		String[] split = D.YAO_QIAN_SHU_BIAO_QING.split(",");
		for (String string : split) {
			if (contains(string, v)) {
				return new Integer(string.split(":")[1]);
			}
		}
		throw new RuntimeException(v + "");
	}

	private boolean contains(String string, int v) {
		String[] split = string.split(":");

		String scope = split[0];
		List<Integer> all = Util.Collection.getIntegers(scope);
		int min = all.get(0);
		int max = all.get(1);

		return v >= min && v <= max;
	}

}
