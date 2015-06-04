package cn.vgame.a.turntable;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.excel.Row;

public class PlayResult {

	public class Result {

		private final Row row;

		public Result(Row row) {
			this.row = row;
		}

		public String getType() {
			return row.get("type");
		}

		public int getId() {
			return row.getInt("id");
		}

	}

	private final List<Row> results;
	private final long coin;
	private long caiJin;
	private String caiJinNotice;
	
	
	public String getCaiJinNotice() {
		
		return caiJinNotice;
	}

	public PlayResult(List<Row> results, long coin, String caiJinNotice, long caiJin) {
		this.results = results;
		this.coin = coin;
		this.caiJinNotice = caiJinNotice;
		this.caiJin = caiJin;
	}
	
	public long getCoin() {
		return coin;
	}

	public List<Result> getResults() {
		ArrayList<Result> ls = Lists.newArrayList();
		for (Row r : results) {
			ls.add(new Result(r));
		}
		return ls;
	}

	public long getCaiJin() {
		return caiJin;
	}

	public void setCaiJin(long caiJin) {
		this.caiJin = caiJin;
	}
}
