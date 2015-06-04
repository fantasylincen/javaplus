package cn.mxz.script;

import java.util.List;

import com.google.common.collect.Lists;

public class ScriptBase {


	public static List<Double> list(double... objs) {
		List<Double> ls = Lists.newArrayList();
		for (double i : objs) {
			ls.add(i);
		}
		return ls;
	}
}
