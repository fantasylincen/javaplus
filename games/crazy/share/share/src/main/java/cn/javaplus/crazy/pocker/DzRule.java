package cn.javaplus.crazy.pocker;

import java.util.List;

public class DzRule {

	public static void check(List<Card> willSend, List<Card> lastCards) {
		if (lastCards.isEmpty()) {
			check(willSend);
		} else {
			checkBigger(willSend, lastCards);
		}
	}

	/**
	 * @param willSend
	 *            即将 不能为null 或者 empty
	 * @param lastCards
	 *            最后 不能为null 或者 empty
	 */
	private static void checkBigger(List<Card> willSend, List<Card> lastCards) {
		if (!isBigger(willSend, lastCards)) {
			throw new DzRuleException();
		}
	}

	public static boolean isBigger(List<Card> willSend, List<Card> lastCards) {

		CardGroupType will = createGroup(willSend);
		CardGroupType last = createGroup(lastCards);

		boolean a = will == CardGroupType.AAAA_OR_SS;
		boolean b = last != CardGroupType.AAAA_OR_SS;
		if (a && b) {
			return true;
		}
		if (will != last) {
			throw new DzRuleException();
		}
		if (willSend.size() != lastCards.size()) {
			throw new DzRuleException();
		}
		CardGroupType type = will;

		return type.isBigger(willSend, lastCards);
	}

	private static CardGroupType createGroup(List<Card> ss) {
		for (CardGroupType t : CardGroupType.values()) {
			if (t.matches(ss)) {
				return t;
			}
		}
		throw new DzRuleException();
	}

	private static void check(List<Card> willSend) {
		for (CardGroupType t : CardGroupType.values()) {
			if (t.matches(willSend)) {
				return;
			}
		}
		throw new DzRuleException();
	}
}
