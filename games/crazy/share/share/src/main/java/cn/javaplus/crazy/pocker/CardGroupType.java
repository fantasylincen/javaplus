package cn.javaplus.crazy.pocker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import cn.javaplus.crazy.collections.Lists;

public enum CardGroupType {

	A {
		@Override
		public boolean matches(List<Card> cs) {
			return cs.size() == 1;
		}

		@Override
		public boolean isBigger(List<Card> willSend, List<Card> lastCards) {
			return compareFirst(willSend, lastCards);
		}

	},
	NAA {
		@Override
		public boolean matches(List<Card> cs) {
			if (!isLianXu(filterByCount(4, cs))) {
				return false;
			}
			Set<String> set = newHashSet();
			set.add("2");
			set.add("222");
			set.add("2222");
			set.add("22222");
			set.add("222222");
			set.add("2222222");
			set.add("22222222");
			set.add("222222222");
			set.add("2222222222");
			set.add("22222222222");
			set.add("222222222222");
			return set.contains(getCountList(cs));
		}

		@Override
		public boolean isBigger(List<Card> willSend, List<Card> lastCards) {
			return sum(willSend) > sum(lastCards);
		}
	},
	NAAA {
		@Override
		public boolean matches(List<Card> cs) {
			if (!isLianXu(filterByCount(3, cs))) {
				return false;
			}

			Set<String> set = newHashSet();
			set.add("3");
			set.add("33");
			set.add("333");
			set.add("3333");
			set.add("33333");
			set.add("333333");
			set.add("3333333");

			return set.contains(getCountList(cs));
		}

		@Override
		public boolean isBigger(List<Card> willSend, List<Card> lastCards) {
			return sum(willSend) > sum(lastCards);
		}
	},
	AAAA_OR_SS {

		@Override
		public boolean matches(List<Card> cs) {
			return isWangZa(cs) || isAAAA(cs);
		}

		private boolean isAAAA(List<Card> cs) {
			return "4".equals(getCountList(cs));
		}

		private boolean isWang(Card card) {
			int value = card.getId();
			return value == 53 || value == 54;
		}

		public boolean isWangZa(List<Card> cs) {
			if (cs.size() != 2) {
				return false;
			}
			return isWang(cs.get(0)) && isWang(cs.get(1));
		}

		@Override
		public boolean isBigger(List<Card> willSend, List<Card> lastCards) {
			if (isWangZa(willSend)) {
				return true;
			}
			if (isWangZa(lastCards)) {
				return false;
			}
			return compareFirst(willSend, lastCards);
		}
	},
	NAAAX {
		@Override
		public boolean matches(List<Card> cs) {
			if (!isLianXu(filterByCount(3, cs))) {
				return false;
			}

			Set<String> set = newHashSet();
			set.add("31");
			set.add("3311");
			set.add("333111");
			set.add("33331111");
			set.add("3333311111");
			set.add("333333111111");
			set.add("332");
			set.add("33321");
			set.add("3333211");
			set.add("333322");
			set.add("333332111");
			set.add("33333221");
			set.add("33333321111");
			set.add("3333332211");
			set.add("333333222");

			set.add("32");
			set.add("3322");
			set.add("333222");
			set.add("33332222");

			return set.contains(getCountList(cs));
		}

		@Override
		public boolean isBigger(List<Card> willSend, List<Card> lastCards) {
			return getSumOf(3, willSend) > getSumOf(3, lastCards);
		}
	},

	NAAAAXY {

		@Override
		public boolean matches(List<Card> cs) {
			if (!isLianXu(filterByCount(4, cs))) {
				return false;
			}
			Set<String> set = newHashSet();
			set.add("411");
			set.add("441111");
			set.add("444111111");
			set.add("42");
			set.add("44211");
			set.add("44421111");
			set.add("4422");
			set.add("4442211");
			set.add("444222");

			set.add("422");
			set.add("442222");

			return set.contains(getCountList(cs));
		}

		@Override
		public boolean isBigger(List<Card> willSend, List<Card> lastCards) {
			return getSumOf(4, willSend) > getSumOf(4, lastCards);
		}
	},

	ABCDE {
		@Override
		public boolean matches(List<Card> cs) {
			if (cs.size() < 5) {
				return false;
			}
			ArrayList<Integer> ls = Lists.newArrayList();
			for (Card card : cs) {
				ls.add(card.getValue());
			}
			Collections.sort(ls);
			for (int i = 0; i < ls.size() - 1; i++) {
				Integer v1 = ls.get(i);
				Integer v2 = ls.get(i + 1);
				if (v2 != v1 + 1) {
					return false;
				}
			}

			return true;
		}

		@Override
		public boolean isBigger(List<Card> willSend, List<Card> lastCards) {
			return sum(willSend) > sum(lastCards);
		}
	};

	protected boolean isCountMatches(Counter<Integer> counter, int... counts) {
		Set<Integer> set = counter.keySet();
		if (set.size() != counts.length) {
			return false;
		}
		List<Integer> countsInCounter = getCountsSorted(counter); // 升序排列
		for (int i = 0; i < counts.length; i++) {
			int v1 = counts[i];
			int v2 = countsInCounter.get(i);
			if (v1 != v2) {
				return false;
			}
		}
		return true;
	}

	protected int sum(List<Card> lastCards) {
		int sum = 0;
		for (Card card : lastCards) {
			sum += card.getValue();
		}
		return sum;
	}

	/**
	 * 列表中 所有card的value是否是连续的
	 * 
	 * 如果列表为空 或者只有1个元素 返回true
	 * 
	 * @param f
	 * @return
	 */
	protected boolean isLianXu(List<Card> f) {
		Set<Integer> set = newHashSet();
		for (Card card : f) {
			set.add(card.getValue());
		}
		if (set.size() == 1 || set.isEmpty()) {
			return true;
		}
		ArrayList<Integer> ls = Lists.newArrayList(set);
		Collections.sort(ls);
		for (int i = 0; i < ls.size() - 1; i++) {
			Integer v1 = ls.get(i);
			Integer v2 = ls.get(i + 1);
			if (v2 != v1 + 1) {
				return false;
			}
		}
		return true;
	}

	private static <T> Set<T> newHashSet() {
		return new HashSet<T>();
	}

	/**
	 * 把列表中 数量为 count个的 card 全部取出来放到一个新的列表中
	 * 
	 * 
	 * 比如 filterByCount(3, 111773799239) 返回 111777999 card.getValue()
	 * 
	 * @param count
	 * @param cs
	 * @return
	 */
	protected List<Card> filterByCount(int count, List<Card> cs) {
		Counter<Integer> c = new Counter<Integer>();
		for (Card card : cs) {
			c.add(card.getValue());
		}

		ArrayList<Card> ls = Lists.newArrayList(cs);
		Iterator<Card> it = ls.iterator();
		while (it.hasNext()) {
			Card card = it.next();
			if (c.get(card.getValue()) != count) {
				it.remove();
			}
		}
		return ls;
	}

	protected int getSumOf(int count, List<Card> cs) {
		Counter<Integer> c = new Counter<Integer>();
		for (Card card : cs) {
			c.add(card.getValue());
		}

		for (Entry<Integer, Integer> e : c.entrySet()) {
			if (e.getValue().equals(count)) {
				return e.getKey();
			}
		}
		return 0;
	}

	protected boolean compareFirst(List<Card> willSend, List<Card> lastCards) {
		return willSend.get(0).getComparator() > lastCards.get(0)
				.getComparator();
	}

	/**
	 * 各种值的个数序列 排了序的
	 * 
	 * 比如 3, 3, 3, 4, 4 返回 32
	 * 
	 * 比如 3, 3, 3, 4 返回 31
	 * 
	 * 比如 5, 5, 5, 5, 4, 6 返回 411
	 * 
	 * 比如 3, 4, 5, 6, 7 返回 11111
	 * 
	 * 比如 2, 2 返回 11
	 * 
	 * @param cs
	 * @return
	 */
	protected String getCountList(List<Card> cs) {
		Counter<Integer> counter = new Counter<Integer>();
		for (Card card : cs) {
			counter.add(card.getValue());
		}
		List<Integer> countsSorted = getCountsSorted(counter);
		StringBuilder sb = new StringBuilder();
		for (Integer i : countsSorted) {
			sb.append(i);
		}
		return sb.toString();
	}

	/**
	 * 计数器中所有值， 升序排列
	 * 
	 * @param counter
	 * @return
	 */
	private List<Integer> getCountsSorted(Counter<Integer> counter) {
		ArrayList<Integer> ls = Lists.newArrayList();
		Set<Entry<Integer, Integer>> e = counter.entrySet();
		for (Entry<Integer, Integer> entry : e) {
			Integer v = entry.getValue();
			ls.add(v);
		}
		Collections.sort(ls);
		Collections.reverse(ls);
		return ls;
	}

	public abstract boolean matches(List<Card> cs);

	protected void removeAllByValue(ArrayList<Card> all, int value) {
		Iterator<Card> it = all.iterator();
		while (it.hasNext()) {
			Card next = it.next();
			if (next.getValue() == value) {
				it.remove();
			}
		}
	}

	protected int removeFeij0(ArrayList<Card> all) {
		Counter<Integer> c = new Counter<Integer>();
		for (Card card : all) {
			c.add(card.getValue());
		}

		int count = 0;
		for (Integer key : c.keySet()) {
			int value = c.get(key);
			if (value == 3) {
				removeAllByValue(all, key);
				count++;
			}
		}
		return count;
	}

	/**
	 * 合并两个列表
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static <T> java.util.List<T> merge(java.util.List<T> a,
			java.util.List<T> b) {

		java.util.List<T> ls = new ArrayList<T>();

		ls.addAll(a);

		ls.addAll(b);

		return ls;
	}

	public abstract boolean isBigger(List<Card> willSend, List<Card> lastCards);

}
