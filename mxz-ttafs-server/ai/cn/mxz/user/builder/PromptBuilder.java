package cn.mxz.user.builder;

import cn.mxz.bossbattle.BossBattleActivity;
import cn.mxz.city.City;
import cn.mxz.prompt.PromptManager;
import cn.mxz.protocols.user.UserP.PromptPro;

class PromptBuilder {
//	public static void main(String[] args) {
//
//		String t = "hpksx,hpkzm,ssksj,ysyktzcs,jjjlwlq,kdb,ymsktz,ymsjlklq,ydbxxx,ydzxxx,yxtxxx,yhyly,sjyxxx,lmyxxx,slyxxx,mbklq,cjklq,kzm,zmhd";
//		String[] split = t.split(",");
//		int i = 0;
//		for (String s : split) {remainSecFromStart
//			String f = Util.Str.firstToUpperCase(s);
////			System.out.println("b.set" + f + "(pm.get" + f + "());");
//			System.out.println("boolean get" + f + "();");
////			System.out.println("required bool " + s + " = " + ++i + ";");
//		}
//	}
	public PromptPro build(City city) {
		PromptPro.Builder b = PromptPro.newBuilder();
		PromptManager pm = city.getPromptManager();

		b.setHpksx(pm.getHpksx());
		b.setHpkzm(pm.getHpkzm());
		b.setSsksj(pm.getSsksj());
		b.setYsyktzcs(pm.getYsyktzcs());
		b.setJjjlwlq(pm.getJjjlwlq());

		b.setKdb(pm.getKdb());

		b.setYmsktz(pm.getYmsktz());

		b.setYmsjlklq(pm.getYmsjlklq());

		b.setYdbxxx(pm.getYdbxxx());

		b.setYdzxxx(pm.getYdzxxx());

		b.setYxtxxx(pm.getYxtxxx());

		b.setYhyly(pm.getYhyly());

		b.setSjyxxx(pm.getSjyxxx());

		b.setLmyxxx(pm.getLmyxxx());

		b.setSlyxxx(pm.getSlyxxx());

		b.setMbklq(pm.getMbklq());

		b.setCjklq(pm.getCjklq());
		
		b.setMmbxklq(pm.getMmbxklq());

		b.setKzm(pm.getKzm());

		b.setZmhd(pm.getZmhd());

		b.setBbybx(pm.getBbybx());


		b.setYxhb(pm.getYxhb());

		//奇遇按钮提示
		boolean qy = pm.getQiYuButtons();

		b.setSx(qy);

		b.setKfyxxx(pm.getKfyxxx());
		
		b.setKflb(pm.getKflb());
		b.setKflbOpen(pm.getKflbOpen());
		b.setRemainSecFromStart( BossBattleActivity.INSTANCE.getStartRemainSec() );
		
		return b.build();
	}

	public PromptPro buildEmpty() {
		PromptPro.Builder b = PromptPro.newBuilder();
		b.setHpksx(false);
		b.setHpkzm(false);
		b.setKfyxxx(false);
		b.setSsksj(false);
		b.setYsyktzcs(false);
		b.setJjjlwlq(false);
		b.setKdb(false);
		b.setYmsktz(false);
		b.setYmsjlklq(false);
		b.setYdbxxx(false);
		b.setYdzxxx(false);
		b.setYxtxxx(false);
		b.setYhyly(false);
		b.setSjyxxx(false);
		b.setLmyxxx(false);
		b.setMmbxklq(false);
		b.setSlyxxx(false);
		b.setMbklq(false);
		b.setCjklq(false);
		b.setKzm(false);
		b.setZmhd(false);
		b.setBbybx(false);
		b.setYxhb(false);
		b.setSx(false);
		b.setKflb(false);
		b.setKflbOpen(false);
		
		b.setRemainSecFromStart( BossBattleActivity.INSTANCE.getStartRemainSec() );
		
		return b.build();
	}
}
