package org.javaplus.game.common.util;

public class NumberUtil {

	public static String sub(float score) {
		if (score <= 0) {
			return "0.00";
		}
		return String.format("%.2f", score);
	}

}
