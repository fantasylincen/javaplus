package cn.javaplus.chatrobot.extractor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import cn.javaplus.collections.counter.Counter;
import cn.javaplus.log.Debuger;

import com.google.common.collect.Lists;

public class MessageExtractor2 {

	private List<Format> formats;

	public static class Format {

		private boolean isArg;
		private String content;
		private int index;
		private int witch;

		public Format(String content, boolean isArg, int index, int witch) {
			this.content = content;
			this.isArg = isArg;
			this.index = index;
			this.witch = witch;
		}

		public String getContent() {
			return content;
		}

		/**
		 * 比如: [server]的[userId]的[type]设置为[count] 其中一共有2个 "的" 第一个 "的" witch = 0
		 * 第二个 "的" witch = 1
		 * 
		 * @return
		 */
		public int getWitch() {
			return witch;
		}

		public int getIndex() {
			return index;
		}

		public boolean isArg() {
			return isArg;
		}

		@Override
		public String toString() {
			return content;
		}
	}

	private final class ComparatorImplementation implements Comparator<Index> {
		public int compare(Index o1, Index o2) {
			return o1.getStart() - o2.getStart();
		}
	}

	public class Index {
		int start;
		int end;
		boolean isArg;

		public int getStart() {
			return start;
		}

		public int getEnd() {
			return end;
		}

		public Index(int start, int end, boolean isArg) {
			this.start = start;
			this.end = end;
			this.isArg = isArg;
		}

		public boolean isArg() {
			return isArg;
		}
	}

	/**
	 * 定义格式: 比如 "[serverId]给[userId]加[count][type]"
	 * 
	 * @param format
	 *            该方法执行后, 会将这段话分解为:
	 * 
	 *            是否是参数 内容 true [serverId] fasle 给 true [userId] fasle 加上 true
	 *            [count] true [type]
	 */
	public void defineFormat(String format) {
		List<Index> argIndexes = findArgIndexes(format);
		List<Index> unArgIndexes = findUnArgs(argIndexes, format.length());
		ArrayList<Index> indexes = Lists.newArrayList(argIndexes);
		indexes.addAll(unArgIndexes);
		sortByStart(indexes);
		formats = Lists.newArrayList();
		int i = 0;
		Counter<String> counter = new Counter<String>();
		for (Index index : indexes) {
			String ss = format.substring(index.getStart(), index.getEnd());
			formats.add(new Format(ss, index.isArg(), i++, counter.get(ss)));
			counter.add(ss);
		}
	}

	/**
	 * 根据define的格式, 将输入的文本转换为如下:
	 * 
	 * 比如输入: "lc私服给lc32加3金币"
	 * 
	 * 返回:
	 * 
	 * 是否是参数 参数名 内容 true serverId lc私服 fasle 给 给 true userId lc32 fasle 加上 加上
	 * true count 3 true type 金币
	 * 
	 * @param input
	 * @return
	 */
	public List<Result> getResult(String input) {
		// 分解为: [在/p, lc/en, 私/ag, 服/v, 给/p, lc/en, 1/m, 加/v, 上, 3/m, 金币/n]
		List<Term> parse = ToAnalysis.parse(input);

//		Debuger.debug("MessageExtractor2.getResult() ANSJ 解析结果:" + parse);

		// 提取为: [在, lc, 私, 服, 给, lc, 1, 加, 上, 3, 金币]
		List<String> textSplit = getTexts(parse);

		// 根据: 在[serverId]给[userId]加[count][type]
		// 合并为: [在, lc私服, 给, lc1, 加上, 3, 金币]
		merge(textSplit);

		check(textSplit);

		ArrayList<Result> ls = Lists.newArrayList();

		for (int i = 0; i < formats.size(); i++) {
			check(formats.get(i), textSplit.get(i));
			ls.add(buildResult(formats.get(i), textSplit.get(i)));
		}

		return ls;
	}

	private void check(Format format, String string) {
		if (format.isArg()) {
			return;
		}
		if (!format.getContent().equals(string)) {
			throw new RuntimeException("非参数的内容必须要相同   " + format.getContent()
					+ " --- " + string);
		}
	}

	private void check(List<String> textSplit) {

		if (textSplit.size() != formats.size()) {
			throw new RuntimeException("无法匹配:" + textSplit + " ---------- "
					+ formats);
		}

		if (!containsAllUnArg(textSplit)) {
			throw new RuntimeException(formats + "    和    " + textSplit
					+ "    不匹配!");
		}

	}

	/**
	 * 包含了说有非参数
	 * 
	 * @param textSplit
	 * @return
	 */
	private boolean containsAllUnArg(List<String> textSplit) {
		List<Format> as = getUnArgs();
		for (Format format : as) {
			String content = format.getContent();
			if (!textSplit.contains(content)) {
				return false;
			}
		}
		return true;
	}

	// *
	// * 是否是参数 参数名 内容
	// * true serverId lc私服
	// * fasle 给 给
	// * true userId lc32
	// * fasle 加上 加上
	// * true count 3
	// * true type 金币
	private Result buildResult(Format format, String content) {
		boolean isArg = format.isArg();
		String c = format.getContent();
		if (isArg) {
			c = c.substring(1, c.length() - 1);
		}
		return new Result(isArg, c, content);
	}

	private List<String> getTexts(List<Term> parse) {
		ArrayList<String> ls = Lists.newArrayList();
		for (Term term : parse) {
			ls.add(term.getName());
		}
		return ls;
	}

	private void merge(List<String> textSplit) {
		// 提取为: [在, lc, 私, 服, 给, lc, 1, 加, 上, 3, 金币]
		// 根据: 在[serverId]给[userId]加[count][type]
		// 合并为: [在, lc私服, 给, lc1, 加上, 3, 金币]

		mergeUnArgs(textSplit);// 合并非参数
		List<Format> unArgs = getUnArgs();
		for (int i = 0; i < unArgs.size() - 1; i++) {
			Format before = unArgs.get(i);
			Format after = unArgs.get(i + 1);

			mergeBetween(textSplit, before, after);
		}
		mergeHead(textSplit, unArgs.get(0));
		mergeTail(textSplit, unArgs.get(unArgs.size() - 1));

		// 1 合并 在 和 给 之间的
		// 2 合并 给 和 加上之间的
		// 3 ...
		// if(textSplit.size() < formats.size()) {
		// throw new RuntimeException("无法匹配:" + textSplit + " ---------- " +
		// formats);
		// }
		// while(textSplit.size() > formats.size()) {
		// mergeFirst(textSplit);
		// }
	}

	private void mergeBetween(List<String> textSplit, Format before,
			Format after) {

		// String afterContent = after.getContent();
		// String beforeContent = before.getContent();
		//
		// int indexStart = textSplit.indexOf(beforeContent);
		// int indexEnd = textSplit.indexOf(afterContent);

		int indexStart = indexOf(textSplit, before);
		int indexEnd = indexOf(textSplit, after);

		if (indexEnd == -1 || indexStart == -1) {
			return;
		}

		int minElementSize = after.getIndex() - before.getIndex();

		int size = indexEnd - indexStart;
		if (size <= minElementSize) {
			return;
		}

		merge(textSplit, indexStart + 1, indexEnd);

	}

	private int indexOf(List<String> textSplit, Format after) {
		int witch = after.getWitch();
		int index = 0;
		int i = 0;
		for (String string : textSplit) {
			if (string.equals(after.getContent())) {
				if (index == witch) {
					return i;
				}
				index++;
			}
			i++;
		}
		return -1;
	}

	private void mergeTail(List<String> textSplit, Format format) {
		int indexOf = textSplit.lastIndexOf(format.getContent());
		if (indexOf == -1) {
			return;
		}
		int minElementSize = formats.size() - format.getIndex() - 1;
		int size = textSplit.size() - indexOf - 1;
		if (size <= minElementSize) {
			return;
		}
		merge(textSplit, indexOf + 1, textSplit.size());
	}

	private void mergeHead(List<String> textSplit, Format format) {
		int indexOf = textSplit.indexOf(format.getContent());
		if (indexOf == -1 || indexOf == 0) {
			return;
		}
		int minElementSize = format.getIndex();
		int size = indexOf;
		if (size <= minElementSize) {
			return;
		}
		merge(textSplit, 0, indexOf);
	}

	// 根据: [在, 给, 加上]
	// 把:[在, lc, 私, 服, 给, lc, 1, 加, 上, 3, 金币]
	// 合并为: [在, lc,私, 服, 给, lc, 1, 加上, 3, 金币]
	private void mergeUnArgs(List<String> textSplit) {
		List<Format> unArgs = getUnArgs();
		for (Format arg : unArgs) {
			mergeNextTo(textSplit, arg);
		}
	}

	/**
	 * 所有非参数
	 * 
	 * @return
	 */
	private List<Format> getUnArgs() {
		ArrayList<Format> ls = Lists.newArrayList();
		for (Format f : formats) {
			if (!f.isArg()) {
				ls.add(f);
			}
		}
		return ls;
	}

	/**
	 * 合并textSplit中, 紧挨的2个字
	 * 
	 * 比如 textSplit = [加, 上, 上, 加, 上, 个] arg = 加上 结果: [加上, 上, 加上, 个]
	 * 
	 * @param textSplit
	 * @param arg
	 */
	private void mergeNextTo(List<String> textSplit, Format format) {
		String arg = format.getContent();
		int length = arg.length();

		if (length <= 1) {
			return;
		}

		for (int len = 2; len <= length; len++) {
			for (int i = 0; i <= textSplit.size() - len; i++) {
				String text = sub(textSplit, i, i + len);
				if (text.equals(arg)) {
					merge(textSplit, i, i + len);
					mergeNextTo(textSplit, format);
					return;
				}
			}
		}
	}

	private void merge(List<String> textSplit, int start, int end) {
		if (end <= start) {
			return;
		}
		List<String> ls = Lists.newArrayList();
		ls.addAll(textSplit.subList(0, start));
		ls.add(sub(textSplit, start, end));
		ls.addAll(textSplit.subList(end, textSplit.size()));
		textSplit.clear();
		textSplit.addAll(ls);
	}

	private String sub(List<String> textSplit, int start, int end) {
		StringBuilder sb = new StringBuilder();
		for (int i = start; i < end; i++) {
			sb.append(textSplit.get(i));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		// ArrayList<String> ls = Lists.newArrayList("0", "1", "2", "3", "4",
		// "5", "6", "7", "8");
		// String a = "012345678";
		// Debuger.debug("MessageExtractor2.main()" + a.substring(0, 8));
		// new MessageExtractor2().merge(ls, 1, 4); //[ 0, 123, 4, 5, 6, 7]
		// Debuger.debug("MessageExtractor2.main()   " + ls );

		// ArrayList<String> ls = Lists.newArrayList("003", "2", "2", "233",
		// "4", "2", "2", "4", "4");
		// String a = "012345678";
		// Debuger.debug("MessageExtractor2.main()" + a.substring(0, 8));
		// Format format = new Format("22", false, 0);
		// new MessageExtractor2().mergeNextTo(ls, format); //[ 0, 123, 4, 5, 6,
		// 7]
		// Debuger.debug("MessageExtractor2.main()   " + ls );

		// ArrayList<String> ls = Lists.newArrayList("003", "2", "2", "233",
		// "4", "2", "2", "4", "4");
		// String a = "012345678";
		// Debuger.debug("MessageExtractor2.main()" + a.substring(0, 8));
		// Format format = new Format("4", false, 0, 12);
		// int indexOf = new MessageExtractor2().indexOf(ls, format);
		// Debuger.debug("MessageExtractor2.main()   " + indexOf );

		// MessageExtractor2 m = new MessageExtractor2();
		// m.defineFormat(Messages.getString("format"));
		// List<Result> result = m.getResult(Messages.getString("input"));
		// for (Result result2 : result) {
		// System.out.println(result2);
		// }
	}

	private void sortByStart(ArrayList<Index> all) {
		Comparator<Index> c = new ComparatorImplementation();
		Collections.sort(all, c);
	}

	private List<Index> findUnArgs(List<Index> argIndexes, int length) {
		ArrayList<Integer> indexes = Lists.newArrayList();
		indexes.add(0);
		for (Index p : argIndexes) {
			indexes.add(p.getStart());
			indexes.add(p.getEnd());
		}
		indexes.add(length);

		ArrayList<Index> ls = Lists.newArrayList();
		for (int i = 0; i < indexes.size(); i += 2) {
			ls.add(new Index(indexes.get(i), indexes.get(i + 1), false));
		}
		filter(ls);
		return ls;
	}

	private void filter(ArrayList<Index> ls) {
		Iterator<Index> it = ls.iterator();
		while (it.hasNext()) {
			Index n = it.next();
			if (n.getStart() == n.getEnd()) {
				it.remove();
			}
		}
	}

	/**
	 * 查找参数位置
	 * 
	 * @param format
	 * @return
	 */
	private List<Index> findArgIndexes(String format) {

		Pattern p = Pattern.compile(Messages.getString("MessageExtractor2.1"));
		Matcher m = p.matcher(format);

		List<Index> ls = Lists.newArrayList();

		while (m.find()) {
			int start = m.start();
			int end = m.end();
			ls.add(new Index(start, end, true));
		}
		return ls;
	}

	/**
	 * 是否匹配
	 * 
	 * @param input
	 * @return
	 */
	public boolean matches(String input) {
		try {
			getResult(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 匹配相似度
	 * 
	 * @param heSay
	 * @return
	 */
	public int getSimilar(String heSay) {
		List<Format> as = getUnArgs();
		float i = 0;
		for (Format f : as) {
			String content = f.getContent();
			if (isSimilar(content, heSay)) {
				i++;
			}
		}
		return (int) (i / as.size() * 1000);
	}

	private boolean isSimilar(String content, String heSay) {
		return heSay.contains(content);
	}
}
