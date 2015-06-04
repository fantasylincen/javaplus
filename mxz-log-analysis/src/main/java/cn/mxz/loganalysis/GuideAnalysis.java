package cn.mxz.loganalysis;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.javaplus.collections.counter.Counter;
import cn.mxz.AppMaskTemplet;
import cn.mxz.AppMaskTempletConfig;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class GuideAnalysis extends Thread {

	private static final String HEAD = "KeyValueTransformImpl\\.put\\(appId,";
	private static final String T = "KeyValueTransformImpl.put(appId,";

	@Override
	public void run() {
		AppMaskTempletConfig.load();
		Iterator<LogData> it = new LogDataIterator();
		Counter<Integer> counter = new Counter<Integer>();
		while (it.hasNext()) {
			LogData logData = (LogData) it.next();
			String log = logData.getLog();
			if (log.contains(T)) {
				Pattern c = Pattern.compile(HEAD + "[0-9]{1,3}");
				Matcher m = c.matcher(log);
				m.find();
				String g = m.group();
				g = g.replaceAll(HEAD, "");
				counter.add(new Integer(g));
			}
		}

		TreeMap<Integer, Integer> tm = Maps.newTreeMap();

		for (Integer key : counter.keySet()) {
			int count = counter.get(key);
			tm.put(key, count);
		}

		List<Entry<Integer, Integer>> ss = Lists.newArrayList(tm.entrySet());

		removeError(ss);
		sortById(ss);

		printResult(counter, ss);
	}

	private void printResult(Counter<Integer> counter,
			List<Entry<Integer, Integer>> ss) {
		for (int i = 0; i < ss.size(); i++) {

			Entry<Integer, Integer> a = ss.get(i);

			Entry<Integer, Integer> b = null;

			if (i < ss.size() - 1) {
				b = ss.get(i + 1);
			}

			print(counter, a, b);
		}
	}

	private void print(Counter<Integer> counter, Entry<Integer, Integer> a,
			Entry<Integer, Integer> b) {

		int nodeId = a.getKey();

		List<AppMaskTemplet> nodes = AppMaskTempletConfig.findByNodeId(nodeId);
		AppMaskTemplet temp = nodes.get(0);
		float liuShiLv = 0;
		int liuShi = 0;

		if (b != null) {
//			System.out.println(a.getValue() + "," + b.getValue());
			liuShi = a.getValue() - b.getValue();
			
			liuShiLv = (liuShi + 0f) / a.getValue() * 100;
		}

		java.text.DecimalFormat   df=new   java.text.DecimalFormat("#.##");   
		Debuger.debug("达到 [" + temp.getId() + "]" + "[" + nodeId + "]"
				+ "[达到此引导人次: 	" + counter.get(nodeId) + "]" + "流失量:" + liuShi
				+ " 流失率: " + df.format(liuShiLv) + "%	[" + temp.getDes() + "]");

	}

	private void sortById(List<Entry<Integer, Integer>> ss) {
		Collections.sort(ss, new Comparator<Entry<Integer, Integer>>() {

			public int compare(Entry<Integer, Integer> a,
					Entry<Integer, Integer> b) {
				Integer nodeId1 = a.getKey();
				Integer nodeId2 = b.getKey();
				return AppMaskTempletConfig.findByNodeId(nodeId1).get(0)
						.getId()
						- AppMaskTempletConfig.findByNodeId(nodeId2).get(0)
								.getId();
			}
		});
	}

	/**
	 * 移除错误的引导节点
	 * 
	 * @param ss
	 */
	private void removeError(List<Entry<Integer, Integer>> ss) {
		Iterator<Entry<Integer, Integer>> it = ss.iterator();
		while (it.hasNext()) {
			Entry<Integer, Integer> e = it.next();
			int nodeId = e.getKey();
			if (AppMaskTempletConfig.findByNodeId(nodeId).isEmpty()) {
				System.err.println("错误 nodeId:" + nodeId);
				it.remove();
			}
		}
	}
}
