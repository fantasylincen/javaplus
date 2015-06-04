package cn.mxz.other;

import java.util.Random;

public class Main {

	public static int random(int min, int max) {

		return new Random().nextInt(max - min + 1) + min;
	}

}
