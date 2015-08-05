package org.hhhhhh.fqzs.result;

import java.util.List;

public class PlayResult {

	public class Result {

		public String type;

		public int id;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

	}

	private long coin;
	private long caiJin;
	private String caiJinNotice;

	public String getCaiJinNotice() {
		return caiJinNotice;
	}

	public long getCoin() {
		return coin;
	}

	private List<Result> results;

	public long getCaiJin() {
		return caiJin;
	}

	public void setCoin(long coin) {
		this.coin = coin;
	}

	public void setCaiJinNotice(String caiJinNotice) {
		this.caiJinNotice = caiJinNotice;
	}

	public void setCaiJin(long caiJin) {
		this.caiJin = caiJin;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}
}
