package cn.javaplus.stock.quotes;

public interface IStockRecord {

	public abstract String getCode();

	public abstract String getDate();

	public abstract String getName();

	public abstract String getCloseYestoday();

	public abstract String getPriceNow();

	public abstract String getHighToday();

	public abstract String getLowToday();

	public abstract boolean isDieTing();

	public abstract boolean isZhangTing();

	public abstract double getUp();

}