package cn.javaplus.stock.moni;

/**
 * 单项 盈利
 */
public class ProfitOfSingleStock implements Comparable<ProfitOfSingleStock> {

	private double profitPercent;
	private double profitRmb;
	private String id;

	public ProfitOfSingleStock(String id) {
		this.id = id;
	}

	public void add(double profitPercent, double profitRmb) {
		this.profitPercent += profitPercent;
		this.profitRmb += profitRmb;
	}

	public String getId() {
		return id;
	}

	public double getProfitPercent() {
		return profitPercent;
	}

	public long getProfitRmb() {
		return (long) profitRmb;
	}

	@Override
	public int compareTo(ProfitOfSingleStock o) {
		long v = o.getProfitRmb() - getProfitRmb();
		if (v > 0)
			return 1;
		if (v == 0)
			return 0;
		return -1;
	}

	@Override
	public String toString() {
		return getId() + ":" + getProfitRmb();
	}
}
