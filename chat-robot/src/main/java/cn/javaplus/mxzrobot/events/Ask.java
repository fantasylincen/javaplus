package cn.javaplus.mxzrobot.events;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Element;

import cn.javaplus.chatrobot.extractor.MessageExtractor2;
import cn.javaplus.chatrobot.extractor.Result;
import cn.javaplus.log.Debuger;
import cn.javaplus.mxzrobot.actions.Action;
import cn.javaplus.mxzrobot.actions.Args;
import cn.javaplus.mxzrobot.actions.SendToServerAction;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Ask {

	private final class ComparatorImplementation implements Comparator<String> {
		public int compare(String o1, String o2) {
			return o2.length() - o1.length();
		}
	}

	public class AskWay {

		public class ArgsImpl2 implements Args {

			private Map<String, String> map;

			public ArgsImpl2() {
				map = Maps.newHashMap();
				if (AskWay.this.matches()) {
					List<Result> result = extractor.getResult(heAsk);
					for (Result r : result) {
						if (r.isArg()) {
							String content = r.getContent();

							Translator translator = new Translator();
							content = translator.translate(content);
							map.put(r.getName(), content);
						}
					}
				}
			}

			public int getInt(Object key) {
				return new Integer(map.get(key));
			}

			public String getString(Object key) {
				return map.get(key);
			}

			public double getDouble(Object key) {
				return new Double(map.get(key));
			}

			public long getLong(Object key) {
				return new Long(map.get(key));
			}

			public boolean getBoolean(Object key) {
				return "true".equals(map.get(key));
			}

			public Set<String> getKeys() {
				return map.keySet();
			}

		}

		private String ask;
		private MessageExtractor2 extractor;

		@Override
		public String toString() {
			return ask;
		}

		public AskWay(String ask) {
			this.ask = ask;
			initExtractor();
		}

		private void initExtractor() {
			extractor = new MessageExtractor2();
			extractor.defineFormat(ask);
		}

		public boolean matches() {
			return extractor.matches(heAsk);
		}

		public Args getArgs() {
			return new ArgsImpl2();
		}

		/**
		 * 与这句话的相似度
		 * 
		 * @param heSay
		 * @return
		 */
		public int getSimilar(String heSay) {
			return extractor.getSimilar(heSay);
		}

	}

	public static void main(String[] args) {
		String a = "[acc][sdfg][dbd]";
		Pattern c = Pattern.compile("(\\[[a-zA-Z0-9]+\\])");
		Matcher m = c.matcher(a);
		while (m.find()) {
			String g = m.group();
			System.out.println(g.replaceAll("\\[", "").replaceAll("\\]", ""));
		}
	}

	List<AskWay> askWays = Lists.newArrayList();

	private String heAsk;

	private Element answerNode;

	private AskWay matchesWay;

	private String actionClassName;

	private Action action;

	public List<AskWay> getAskWays() {
		return askWays;
	}

	public Ask(List<String> asks, Element answerNode, String heAsk) {
		this.answerNode = answerNode;
		this.heAsk = heAsk;
		sortByLenth(asks);
		for (String a : asks) {
			AskWay aw = new AskWay(a);
			askWays.add(aw);
			if (aw.matches()) {
				matchesWay = aw;
				Debuger.debug("Ask.Ask() ---------- " + this);
				break;
			}
		}
		this.action = initAction();
	}

	private void sortByLenth(List<String> asks) {
		Comparator<String> c = new ComparatorImplementation();
		Collections.sort(asks, c);
	}

	public Args getArgs() {
		if (matchesWay == null) {
			throw new RuntimeException("未找到匹配项!");
		}
		return matchesWay.getArgs();
	}

	public Action getAction() {

		return action;
	}

	private Action initAction() {
		Element sendToServer = answerNode.element("sendToServer");
		Element actionClass = answerNode.element("actionClass");

		boolean serverEmpty = sendToServer != null;
		boolean actionClassEmpty = actionClass != null;
		if (serverEmpty == actionClassEmpty) {
			throw new RuntimeException(
					"answer 节点有且仅由一个 sendToServer 或 actionClass");
		}

		if (serverEmpty) {
			return new SendToServerAction(sendToServer);
		}

		if (actionClassEmpty) {
			try {
				actionClassName = actionClass.getTextTrim();
				Class<?> c = Class.forName("cn.javaplus.mxzrobot.actions."
						+ actionClassName);
				return (Action) c.newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		throw new RuntimeException("answer 节点有且仅由一个 sendToServer 或 actionClass");
	}

	public boolean matches() {
		return matchesWay != null;
	}
	
	@Override
	public String toString() {
		return "HeAsk: " + heAsk + "   MatchesWay:" + matchesWay;
	}

}
