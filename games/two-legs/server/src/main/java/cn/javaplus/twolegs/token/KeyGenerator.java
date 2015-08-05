package cn.javaplus.twolegs.token;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 随机码生成器
 * 
 * @author 林岑
 * @time 2012年9月4日 17:21:24
 */
public class KeyGenerator {

	private static Random r = new Random();

	private static final List<Character> cs = new ArrayList<Character>();

	static {
		for (char c = 0; c <= 127; c++)
			if (Character.isLetterOrDigit(c))
				cs.add(c);
	}

	/**
	 * 获得长度为length的随机字符串.
	 * 
	 * @param length
	 *            长度
	 */
	public final static String getRandomString(int length) {
		StringBuilder s = new StringBuilder();
		for (char i = 0; i < length; i++) {
			s.append(getRandomChar());
		}
		return s.toString();
	}

	/**
	 * 获得一个随机字符
	 */
	private static char getRandomChar() {
		return cs.get(r.nextInt(cs.size()));
	}
}
