package cn.mxz.battle;

import cn.javaplus.math.Fraction;
import cn.mxz.protocols.user.god.FighterP.YuanShenPro;
import cn.mxz.util.FractionBuilder;

public class YuanShenBuilder {

	public YuanShenPro build(YuanShen yuanShen) {
		YuanShenPro.Builder b = YuanShenPro.newBuilder();
		b.setType(yuanShen.getType());
		b.setLevel(yuanShen.getLevel());
		Fraction exp = yuanShen.getExp();
		
//		Debuger.debug("YuanShenBuilder.build() " + yuanShen.getType() + ":" + exp.getNumerator() + "/" + exp.getDenominator());
		
		b.setExp(new FractionBuilder().build(exp));
		b.setExpAll(yuanShen.getExpAll());
		b.setExpPar(yuanShen.getExpPar());
		b.setStep(yuanShen.getStep());
		return b.build();
	}

}
