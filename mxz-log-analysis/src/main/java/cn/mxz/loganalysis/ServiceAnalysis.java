package cn.mxz.loganalysis;

import java.util.Iterator;
import java.util.Set;

import com.google.common.collect.Sets;

public class ServiceAnalysis extends Thread {

	@Override
	public void run() {
		Iterator<LogData> it = new LogDataIterator();
		Set<String> set = Sets.newHashSet();
		while (it.hasNext()) {
			LogData logData = (LogData) it.next();
			String head = logData.getHead();
			String log = logData.getLog();
			if (head.equals("Service")) {
				String method = log.replaceAll("\\(.*", "");
				set.add(method);
			}
		}
		
		for (String string : set) {
			System.out.println(string);
		}
	}
}
