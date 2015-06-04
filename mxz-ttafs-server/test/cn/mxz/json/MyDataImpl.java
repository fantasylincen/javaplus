package cn.mxz.json;

import java.util.List;

import com.google.common.collect.Lists;

public class MyDataImpl implements MyData {

	@Override
	public int getId() {
		return 1;
	}

	@Override
	public String getName() {
		return "aaa";
	}

	@Override
	public List<Fighter> getFighters() {
		List<Fighter> ls = Lists.newArrayList();
		ls.add(new FighterImpl());
		return ls;
	}

}
