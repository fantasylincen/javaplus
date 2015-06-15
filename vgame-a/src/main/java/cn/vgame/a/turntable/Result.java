package cn.vgame.a.turntable;

import java.util.List;

import cn.javaplus.excel.Row;
import cn.javaplus.random.Fetcher;
import cn.javaplus.util.Util;

public class Result {
	private List<Row> result;
	private long generateTime;

	public Result() {
		this.generateTime = System.currentTimeMillis();
	}

	/**
	 * 转盘结果
	 * 
	 * @return
	 */
	public List<Row> getResult() {
		return result;
	}

	public void setResult(List<Row> result) {
		this.result = result;
	}

	/**
	 * 生成这个奖励的时间
	 * 
	 * @return
	 */
	public long getGenerateTime() {
		return generateTime;
	}

	@Override
	public String toString() {
		Fetcher<Row, Object> ss = new Fetcher<Row, Object>() {

			@Override
			public Object get(Row t) {
				return /*Util.Chinese.getPinYinHump(*/t.get("dsc")/*)*/;
			}
		};
		return Util.Collection.linkWith(",", result, ss);
	}

}