package cn.mxz.util;

import cn.javaplus.math.Fraction;
import cn.mxz.protocols.user.FractionP.FractionPro;

/**
 * 分数协议
 */
public class FractionBuilder{

	public FractionPro build(Fraction v) {

		FractionPro.Builder b = FractionPro.newBuilder();

		int n = v.getNumerator();
		b.setNumerator(n);

		int d = v.getDenominator();
		b.setDenominator(d);

		return b.build();
	}

	
	public FractionPro build(int n, int d) {
		return build(new Fraction(n, d));
	}

}
