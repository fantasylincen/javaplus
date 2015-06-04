package cn.javaplus.smonitor.downloader;

/**
 * 单条记录
 */
public class StockRecord implements IStockRecord {

	private String fields;

	public StockRecord(String code, String fields) {
		this.code = code;
		this.fields = fields;

		String[] e = fields.split(",");

		setCode(code);

		setName(e[0]);// 0：”大秦铁路”，股票名字；
		setOpenToday(e[1]);// 1：”27.55″，今日开盘价；
		setCloseYestoday(e[2]);// 2：”27.25″，昨日收盘价；
		setPriceNow(e[3]);// 3：”26.91″，当前价格；
		setHighToday(e[4]);// 4：”27.55″，今日最高价；
		setLowToday(e[5]);// 5：”26.20″，今日最低价；
		setJingBuy(e[6]);// 6：”26.91″，竞买价，即“买一”报价；
		setJingSell(e[7]);// 7：”26.92″，竞卖价，即“卖一”报价；
		setChengJiaoLiang(e[8]);// 8：”22114263″，成交的股票数，由于股票交易以一百股为基本单位，所以在使用时，通常把该值除以一百；
		setChengJiaoJinE(e[9]);// 9：”589824680″，成交金额，单位为“元”，为了一目了然，通常以“万元”为成交金额的单位，所以通常把该值除以一万；
		setBuyCount1(e[10]);// 10：”4695″，“买一”申请4695股，即47手；
		setBuyPrice1(e[11]);// 11：”26.91″，“买一”报价；
		setBuyCount2(e[12]);// 12：”57590″，“买二”
		setBuyPrice2(e[13]);// 13：”26.90″，“买二”
		setBuyCount3(e[14]);// 14：”14700″，“买三”
		setBuyPrice3(e[15]);// 15：”26.89″，“买三”
		setBuyCount4(e[16]);// 16：”14300″，“买四”
		setBuyPrice4(e[17]);// 17：”26.88″，“买四”
		setBuyCount5(e[18]);// 18：”15100″，“买五”
		setBuyPrice5(e[19]);// 19：”26.87″，“买五”

		setSellCount1(e[20]);
		setSellPrice1(e[21]);
		setSellCount2(e[22]);
		setSellPrice2(e[23]);
		setSellCount3(e[24]);
		setSellPrice3(e[25]);
		setSellCount4(e[26]);
		setSellPrice4(e[27]);
		setSellCount5(e[28]);
		setSellPrice5(e[29]);

		setDate(e[30]);// 30：”2008-01-11″，日期；
		setClock(e[31]); // 31：”15:05:32″，时间；
	}

	public StockRecord(String line) {
		this(getCode(line), getFields(line));
	}

	private static String getFields(String line) {
		String fields = line.substring(9, line.length());
		return fields;
	}

	private static String getCode(String line) {
		String code;
		try {
			code = line.substring(0, 8);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return code;
	}

	private void setCode(String code) {
		this.code = code;
	}

	public String toFormatString() {
		return code + "," + fields;
	}

	private String code = "";
	private String clock = "";
	private String date = "";
	private String name = "";
	private String openToday = "";
	private String closeYestoday = "";
	private String priceNow = "";
	private String highToday = "";
	private String lowToday = "";
	private String jingBuy = "";
	private String jingSell = "";
	private String chengJiaoLiang = "";
	private String chengJiaoJinE = "";
	private String buyPrice1 = "";
	private String buyCount1 = "";
	private String buyPrice2 = "";
	private String buyCount2 = "";
	private String buyPrice3 = "";
	private String buyCount3 = "";
	private String buyPrice4 = "";
	private String buyCount4 = "";
	private String buyPrice5 = "";
	private String buyCount5 = "";
	private String sellCount1 = "";
	private String sellPrice1 = "";
	private String sellCount2 = "";
	private String sellPrice2 = "";
	private String sellCount3 = "";
	private String sellPrice3 = "";
	private String sellCount4 = "";
	private String sellPrice4 = "";
	private String sellCount5 = "";
	private String sellPrice5 = "";

	/* (non-Javadoc)
	 * @see cn.javaplus.stock.quotes.IStockRecord#getCode()
	 */
	@Override
	public String getCode() {
		return this.code;
	}

	public String getClock() {
		return this.clock;
	}

	/* (non-Javadoc)
	 * @see cn.javaplus.stock.quotes.IStockRecord#getDate()
	 */
	@Override
	public String getDate() {
		return date;
	}

	/* (non-Javadoc)
	 * @see cn.javaplus.stock.quotes.IStockRecord#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	public String getOpenToday() {
		return this.openToday;
	}

	/* (non-Javadoc)
	 * @see cn.javaplus.stock.quotes.IStockRecord#getCloseYestoday()
	 */
	@Override
	public String getCloseYestoday() {
		return this.closeYestoday;
	}

	/* (non-Javadoc)
	 * @see cn.javaplus.stock.quotes.IStockRecord#getPriceNow()
	 */
	@Override
	public String getPriceNow() {
		return this.priceNow;
	}

	/* (non-Javadoc)
	 * @see cn.javaplus.stock.quotes.IStockRecord#getHighToday()
	 */
	@Override
	public String getHighToday() {
		return this.highToday;
	}

	/* (non-Javadoc)
	 * @see cn.javaplus.stock.quotes.IStockRecord#getLowToday()
	 */
	@Override
	public String getLowToday() {
		return this.lowToday;
	}

	public String getJingBuy() {
		return this.jingBuy;
	}

	public String getJingSell() {
		return this.jingSell;
	}

	public String getChengJiaoLiang() {
		return this.chengJiaoLiang;
	}

	public String getChengJiaoJinE() {
		return this.chengJiaoJinE;
	}

	public String getBuyPrice1() {
		return this.buyPrice1;
	}

	public String getBuyCount1() {
		return this.buyCount1;
	}

	public String getBuyPrice2() {
		return this.buyPrice2;
	}

	public String getBuyCount2() {
		return this.buyCount2;
	}

	public String getBuyPrice3() {
		return this.buyPrice3;
	}

	public String getBuyCount3() {
		return this.buyCount3;
	}

	public String getBuyPrice4() {
		return this.buyPrice4;
	}

	public String getBuyCount4() {
		return this.buyCount4;
	}

	public String getBuyPrice5() {
		return this.buyPrice5;
	}

	public String getBuyCount5() {
		return this.buyCount5;
	}

	public String getSellCount1() {
		return this.sellCount1;
	}

	public String getSellPrice1() {
		return this.sellPrice1;
	}

	public String getSellCount2() {
		return this.sellCount2;
	}

	public String getSellPrice2() {
		return this.sellPrice2;
	}

	public String getSellCount3() {
		return this.sellCount3;
	}

	public String getSellPrice3() {
		return this.sellPrice3;
	}

	public String getSellCount4() {
		return this.sellCount4;
	}

	public String getSellPrice4() {
		return this.sellPrice4;
	}

	public String getSellCount5() {
		return this.sellCount5;
	}

	public String getSellPrice5() {
		return this.sellPrice5;
	}

	public void setId(String id) {
		this.code = id;
	}

	public void setClock(String clock) {
		this.clock = clock;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOpenToday(String openToday) {
		this.openToday = openToday;
	}

	public void setCloseYestoday(String closeYestoday) {
		this.closeYestoday = closeYestoday;
	}

	public void setPriceNow(String priceNow) {
		this.priceNow = priceNow;
	}

	public void setHighToday(String highToday) {
		this.highToday = highToday;
	}

	public void setLowToday(String lowToday) {
		this.lowToday = lowToday;
	}

	public void setJingBuy(String jingBuy) {
		this.jingBuy = jingBuy;
	}

	public void setJingSell(String jingSell) {
		this.jingSell = jingSell;
	}

	public void setChengJiaoLiang(String chengJiaoLiang) {
		this.chengJiaoLiang = chengJiaoLiang;
	}

	public void setChengJiaoJinE(String chengJiaoJinE) {
		this.chengJiaoJinE = chengJiaoJinE;
	}

	public void setBuyPrice1(String buyPrice1) {
		this.buyPrice1 = buyPrice1;
	}

	public void setBuyCount1(String buyCount1) {
		this.buyCount1 = buyCount1;
	}

	public void setBuyPrice2(String buyPrice2) {
		this.buyPrice2 = buyPrice2;
	}

	public void setBuyCount2(String buyCount2) {
		this.buyCount2 = buyCount2;
	}

	public void setBuyPrice3(String buyPrice3) {
		this.buyPrice3 = buyPrice3;
	}

	public void setBuyCount3(String buyCount3) {
		this.buyCount3 = buyCount3;
	}

	public void setBuyPrice4(String buyPrice4) {
		this.buyPrice4 = buyPrice4;
	}

	public void setBuyCount4(String buyCount4) {
		this.buyCount4 = buyCount4;
	}

	public void setBuyPrice5(String buyPrice5) {
		this.buyPrice5 = buyPrice5;
	}

	public void setBuyCount5(String buyCount5) {
		this.buyCount5 = buyCount5;
	}

	public void setSellCount1(String sellCount1) {
		this.sellCount1 = sellCount1;
	}

	public void setSellPrice1(String sellPrice1) {
		this.sellPrice1 = sellPrice1;
	}

	public void setSellCount2(String sellCount2) {
		this.sellCount2 = sellCount2;
	}

	public void setSellPrice2(String sellPrice2) {
		this.sellPrice2 = sellPrice2;
	}

	public void setSellCount3(String sellCount3) {
		this.sellCount3 = sellCount3;
	}

	public void setSellPrice3(String sellPrice3) {
		this.sellPrice3 = sellPrice3;
	}

	public void setSellCount4(String sellCount4) {
		this.sellCount4 = sellCount4;
	}

	public void setSellPrice4(String sellPrice4) {
		this.sellPrice4 = sellPrice4;
	}

	public void setSellCount5(String sellCount5) {
		this.sellCount5 = sellCount5;
	}

	public void setSellPrice5(String sellPrice5) {
		this.sellPrice5 = sellPrice5;
	}

	@Override
	public boolean isDieTing() {
		return getUp() < -0.097;
	}

	public double getUp() {
		double close = new Double(getCloseYestoday());
		double now = new Double(getPriceNow());
		return (now - close) / close;
	}

	@Override
	public boolean isZhangTing() {
		double up = getUp();
		if(up > 0.45) {
			throw new RuntimeException("data error must < 44%");
		}
		return up > 0.097;
	}

}
