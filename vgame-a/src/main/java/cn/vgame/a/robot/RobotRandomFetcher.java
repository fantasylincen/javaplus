package cn.vgame.a.robot;

import cn.javaplus.excel.Row;
import cn.javaplus.random.WeightFetcher;

public class RobotRandomFetcher implements WeightFetcher<Row> {

	@Override
	public Integer get(Row t) {
		return t.getInt("robotWeight");
	}

}