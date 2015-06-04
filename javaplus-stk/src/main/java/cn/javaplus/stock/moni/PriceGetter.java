package cn.javaplus.stock.moni;

public interface PriceGetter {

	public abstract String getPrice(String code, String date, String clock);

}