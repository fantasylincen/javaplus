





























	
		public static MoniPlayerDao getMoniPlayerDao() {
		public static PriceDao getPriceDao() {
		public static StockDao getStockDao() {
		public static StockIdDao getStockIdDao() {
		public static StockNameDao getStockNameDao() {
		public static StockPriceDao getStockPriceDao() {

	
		/**
		public DayDtoCursor findByDate(String date) {
		/**
		public DayDtoCursor findByOpen(double open) {
		/**
		public DayDtoCursor findByClose(double close) {
		/**
		public DayDtoCursor findByHigh(double high) {
		/**
		public DayDtoCursor findByLow(double low) {
		/**
		public DayDtoCursor findByUp(double up) {
		/**
		public DayDtoCursor findByVolume(long volume) {
		/**
		public DayDtoCursor findByAvg5(double avg5) {
		/**
		public DayDtoCursor findByAvg10(double avg10) {
		/**
		public DayDtoCursor findByAvg15(double avg15) {
		/**
		public DayDtoCursor findByAvg20(double avg20) {
		/**
		public DayDtoCursor findByAvg30(double avg30) {
		/**
		public DayDtoCursor findByAvg45(double avg45) {
		/**
		public DayDtoCursor findByAvg60(double avg60) {
		/**
		public DayDtoCursor findByAvg100(double avg100) {
		/**
		public DayDtoCursor findByAvg120(double avg120) {
		/**
		public DayDtoCursor findByMax2(double max2) {
		/**
		public DayDtoCursor findByMax3(double max3) {
		/**
		public DayDtoCursor findByMax4(double max4) {
		/**
		public DayDtoCursor findByMax5(double max5) {
		/**
		public DayDtoCursor findByMax6(double max6) {
		/**
		public DayDtoCursor findByMax10(double max10) {
		/**
		public DayDtoCursor findByMax15(double max15) {
		/**
		public DayDtoCursor findByMax20(double max20) {
		/**
		public DayDtoCursor findByMax30(double max30) {
		/**
		public DayDtoCursor findByMax60(double max60) {
		/**
		public DayDtoCursor findByMax90(double max90) {
		/**
		public DayDtoCursor findByDea(double dea) {
		/**
		public DayDtoCursor findByMacd(double macd) {
		/**
		public DayDtoCursor findByEmaShort(double emaShort) {
		/**
		public DayDtoCursor findByEmaLong(double emaLong) {
		/**
		public DayDtoCursor findByDiff(double diff) {
		/**
		public DayDtoCursor findByPercentOfFuQuan(double percentOfFuQuan) {
		/**
		public DayDtoCursor findByHighLowZhenFu(double highLowZhenFu) {
		/**
		public DayDtoCursor findByOpenCloseZhenFu(double openCloseZhenFu) {
		/**

	
		/**
		public MoniPlayerDtoCursor findByRmb(double rmb) {
		/**

	
		/**
		public PriceDtoCursor findByOpen(double open) {
		/**
		public PriceDtoCursor findByClose(double close) {
		/**
		public PriceDtoCursor findByHigh(double high) {
		/**
		public PriceDtoCursor findByLow(double low) {
		/**
		public PriceDtoCursor findByUp(double up) {
		/**
		public PriceDtoCursor findByVolume(int volume) {
		/**
		public PriceDtoCursor findByAvg5(double avg5) {
		/**
		public PriceDtoCursor findByAvg10(double avg10) {
		/**
		public PriceDtoCursor findByAvg15(double avg15) {
		/**
		public PriceDtoCursor findByAvg20(double avg20) {
		/**
		public PriceDtoCursor findByAvg30(double avg30) {
		/**
		public PriceDtoCursor findByAvg45(double avg45) {
		/**
		public PriceDtoCursor findByAvg60(double avg60) {
		/**
		public PriceDtoCursor findByAvg100(double avg100) {
		/**
		public PriceDtoCursor findByAvg120(double avg120) {
		/**
		public PriceDtoCursor findByMax30(double max30) {
		/**
		public PriceDtoCursor findByMax60(double max60) {
		/**
		public PriceDtoCursor findByMax90(double max90) {
		/**

	
		/**

	
		/**

	
		/**
		public StockNameDtoCursor findByName(String name) {
		/**

	
		/**
		public StockPriceDtoCursor findByTime(String time) {
		/**
		public StockPriceDtoCursor findByPrice(String price) {
		/**
		public StockPriceDtoCursor findByCloseYestoday(String closeYestoday) {
		/**
		public StockPriceDtoCursor findByHighToday(String highToday) {
		/**
		public StockPriceDtoCursor findByLowToday(String lowToday) {
		/**

	




			o.put("date", MongoGen.toObject(date));




	
		private String date = "";
		private double open = 0;
		private double close = 0;
		private double high = 0;
		private double low = 0;
		private double up = 0;
		private long volume = 0;
		private double avg5 = 0;
		private double avg10 = 0;
		private double avg15 = 0;
		private double avg20 = 0;
		private double avg30 = 0;
		private double avg45 = 0;
		private double avg60 = 0;
		private double avg100 = 0;
		private double avg120 = 0;
		private double max2 = 0;
		private double max3 = 0;
		private double max4 = 0;
		private double max5 = 0;
		private double max6 = 0;
		private double max10 = 0;
		private double max15 = 0;
		private double max20 = 0;
		private double max30 = 0;
		private double max60 = 0;
		private double max90 = 0;
		private double dea = 0;
		private double macd = 0;
		private double emaShort = 0;
		private double emaLong = 0;
		private double diff = 0;
		private double percentOfFuQuan = 0;
		private double highLowZhenFu = 0;
		private double openCloseZhenFu = 0;





































		public String getDate() {
		public double getOpen() {
		public double getClose() {
		public double getHigh() {
		public double getLow() {
		public double getUp() {
		public long getVolume() {
		public double getAvg5() {
		public double getAvg10() {
		public double getAvg15() {
		public double getAvg20() {
		public double getAvg30() {
		public double getAvg45() {
		public double getAvg60() {
		public double getAvg100() {
		public double getAvg120() {
		public double getMax2() {
		public double getMax3() {
		public double getMax4() {
		public double getMax5() {
		public double getMax6() {
		public double getMax10() {
		public double getMax15() {
		public double getMax20() {
		public double getMax30() {
		public double getMax60() {
		public double getMax90() {
		public double getDea() {
		public double getMacd() {
		public double getEmaShort() {
		public double getEmaLong() {
		public double getDiff() {
		public double getPercentOfFuQuan() {
		public double getHighLowZhenFu() {
		public double getOpenCloseZhenFu() {

		public void setDate(String date) {
		public void setOpen(double open) {
		public void setClose(double close) {
		public void setHigh(double high) {
		public void setLow(double low) {
		public void setUp(double up) {
		public void setVolume(long volume) {
		public void setAvg5(double avg5) {
		public void setAvg10(double avg10) {
		public void setAvg15(double avg15) {
		public void setAvg20(double avg20) {
		public void setAvg30(double avg30) {
		public void setAvg45(double avg45) {
		public void setAvg60(double avg60) {
		public void setAvg100(double avg100) {
		public void setAvg120(double avg120) {
		public void setMax2(double max2) {
		public void setMax3(double max3) {
		public void setMax4(double max4) {
		public void setMax5(double max5) {
		public void setMax6(double max6) {
		public void setMax10(double max10) {
		public void setMax15(double max15) {
		public void setMax20(double max20) {
		public void setMax30(double max30) {
		public void setMax60(double max60) {
		public void setMax90(double max90) {
		public void setDea(double dea) {
		public void setMacd(double macd) {
		public void setEmaShort(double emaShort) {
		public void setEmaLong(double emaLong) {
		public void setDiff(double diff) {
		public void setPercentOfFuQuan(double percentOfFuQuan) {
		public void setHighLowZhenFu(double highLowZhenFu) {
		public void setOpenCloseZhenFu(double openCloseZhenFu) {

			o.put("id", MongoGen.toObject(id));
			o.put("date", MongoGen.toObject(date));
			o.put("open", MongoGen.toObject(open));
			o.put("close", MongoGen.toObject(close));
			o.put("high", MongoGen.toObject(high));
			o.put("low", MongoGen.toObject(low));
			o.put("up", MongoGen.toObject(up));
			o.put("volume", MongoGen.toObject(volume));
			o.put("avg5", MongoGen.toObject(avg5));
			o.put("avg10", MongoGen.toObject(avg10));
			o.put("avg15", MongoGen.toObject(avg15));
			o.put("avg20", MongoGen.toObject(avg20));
			o.put("avg30", MongoGen.toObject(avg30));
			o.put("avg45", MongoGen.toObject(avg45));
			o.put("avg60", MongoGen.toObject(avg60));
			o.put("avg100", MongoGen.toObject(avg100));
			o.put("avg120", MongoGen.toObject(avg120));
			o.put("max2", MongoGen.toObject(max2));
			o.put("max3", MongoGen.toObject(max3));
			o.put("max4", MongoGen.toObject(max4));
			o.put("max5", MongoGen.toObject(max5));
			o.put("max6", MongoGen.toObject(max6));
			o.put("max10", MongoGen.toObject(max10));
			o.put("max15", MongoGen.toObject(max15));
			o.put("max20", MongoGen.toObject(max20));
			o.put("max30", MongoGen.toObject(max30));
			o.put("max60", MongoGen.toObject(max60));
			o.put("max90", MongoGen.toObject(max90));
			o.put("dea", MongoGen.toObject(dea));
			o.put("macd", MongoGen.toObject(macd));
			o.put("emaShort", MongoGen.toObject(emaShort));
			o.put("emaLong", MongoGen.toObject(emaLong));
			o.put("diff", MongoGen.toObject(diff));
			o.put("percentOfFuQuan", MongoGen.toObject(percentOfFuQuan));
			o.put("highLowZhenFu", MongoGen.toObject(highLowZhenFu));
			o.put("openCloseZhenFu", MongoGen.toObject(openCloseZhenFu));

			date = getString(o, "date");
			open = getDouble(o, "open");
			close = getDouble(o, "close");
			high = getDouble(o, "high");
			low = getDouble(o, "low");
			up = getDouble(o, "up");
			volume = getLong(o, "volume");
			avg5 = getDouble(o, "avg5");
			avg10 = getDouble(o, "avg10");
			avg15 = getDouble(o, "avg15");
			avg20 = getDouble(o, "avg20");
			avg30 = getDouble(o, "avg30");
			avg45 = getDouble(o, "avg45");
			avg60 = getDouble(o, "avg60");
			avg100 = getDouble(o, "avg100");
			avg120 = getDouble(o, "avg120");
			max2 = getDouble(o, "max2");
			max3 = getDouble(o, "max3");
			max4 = getDouble(o, "max4");
			max5 = getDouble(o, "max5");
			max6 = getDouble(o, "max6");
			max10 = getDouble(o, "max10");
			max15 = getDouble(o, "max15");
			max20 = getDouble(o, "max20");
			max30 = getDouble(o, "max30");
			max60 = getDouble(o, "max60");
			max90 = getDouble(o, "max90");
			dea = getDouble(o, "dea");
			macd = getDouble(o, "macd");
			emaShort = getDouble(o, "emaShort");
			emaLong = getDouble(o, "emaLong");
			diff = getDouble(o, "diff");
			percentOfFuQuan = getDouble(o, "percentOfFuQuan");
			highLowZhenFu = getDouble(o, "highLowZhenFu");
			openCloseZhenFu = getDouble(o, "openCloseZhenFu");









































































	
		private String value = "";
		private boolean isClientVisible = false;
		private String dsc = "";





		public String getValue() {
		public boolean getIsClientVisible() {
		public String getDsc() {

		public void setValue(String value) {
		public void setIsClientVisible(boolean isClientVisible) {
		public void setDsc(String dsc) {

			o.put("key", MongoGen.toObject(key));
			o.put("value", MongoGen.toObject(value));
			o.put("isClientVisible", MongoGen.toObject(isClientVisible));
			o.put("dsc", MongoGen.toObject(dsc));

			value = getString(o, "value");
			isClientVisible = getBoolean(o, "isClientVisible");
			dsc = getString(o, "dsc");









	
		private List<MyStockDto> stocks = Lists.newArrayList();
		private double rmb = 0;
		private List<String> tradeLogs = Lists.newArrayList();





		public List<MyStockDto> getStocks() {
		public double getRmb() {
		public List<String> getTradeLogs() {

		public void setStocks(List<MyStockDto> stocks) {
		public void setRmb(double rmb) {
		public void setTradeLogs(List<String> tradeLogs) {

			o.put("id", MongoGen.toObject(id));
			o.put("stocks", MongoGen.toObjectMyStockDto(stocks));
			o.put("rmb", MongoGen.toObject(rmb));
			o.put("tradeLogs", MongoGen.toObjectString(tradeLogs));

			stocks = loadStocks(o);
			rmb = getDouble(o, "rmb");
			tradeLogs = loadTradeLogs(o);





		List<MyStockDto> loadStocks(DBObject o) {

		List<String> loadTradeLogs(DBObject o) {

	
		private int count = 0;
		private double buyPrice = 0;
		private String buyDate = "";





		public int getCount() {
		public double getBuyPrice() {
		public String getBuyDate() {

		public void setCount(int count) {
		public void setBuyPrice(double buyPrice) {
		public void setBuyDate(String buyDate) {

			o.put("id", MongoGen.toObject(id));
			o.put("count", MongoGen.toObject(count));
			o.put("buyPrice", MongoGen.toObject(buyPrice));
			o.put("buyDate", MongoGen.toObject(buyDate));

			count = getInteger(o, "count");
			buyPrice = getDouble(o, "buyPrice");
			buyDate = getString(o, "buyDate");









	
		private double open = 0;
		private double close = 0;
		private double high = 0;
		private double low = 0;
		private double up = 0;
		private int volume = 0;
		private double avg5 = 0;
		private double avg10 = 0;
		private double avg15 = 0;
		private double avg20 = 0;
		private double avg30 = 0;
		private double avg45 = 0;
		private double avg60 = 0;
		private double avg100 = 0;
		private double avg120 = 0;
		private double max30 = 0;
		private double max60 = 0;
		private double max90 = 0;




















		public double getOpen() {
		public double getClose() {
		public double getHigh() {
		public double getLow() {
		public double getUp() {
		public int getVolume() {
		public double getAvg5() {
		public double getAvg10() {
		public double getAvg15() {
		public double getAvg20() {
		public double getAvg30() {
		public double getAvg45() {
		public double getAvg60() {
		public double getAvg100() {
		public double getAvg120() {
		public double getMax30() {
		public double getMax60() {
		public double getMax90() {

		public void setOpen(double open) {
		public void setClose(double close) {
		public void setHigh(double high) {
		public void setLow(double low) {
		public void setUp(double up) {
		public void setVolume(int volume) {
		public void setAvg5(double avg5) {
		public void setAvg10(double avg10) {
		public void setAvg15(double avg15) {
		public void setAvg20(double avg20) {
		public void setAvg30(double avg30) {
		public void setAvg45(double avg45) {
		public void setAvg60(double avg60) {
		public void setAvg100(double avg100) {
		public void setAvg120(double avg120) {
		public void setMax30(double max30) {
		public void setMax60(double max60) {
		public void setMax90(double max90) {

			o.put("date", MongoGen.toObject(date));
			o.put("open", MongoGen.toObject(open));
			o.put("close", MongoGen.toObject(close));
			o.put("high", MongoGen.toObject(high));
			o.put("low", MongoGen.toObject(low));
			o.put("up", MongoGen.toObject(up));
			o.put("volume", MongoGen.toObject(volume));
			o.put("avg5", MongoGen.toObject(avg5));
			o.put("avg10", MongoGen.toObject(avg10));
			o.put("avg15", MongoGen.toObject(avg15));
			o.put("avg20", MongoGen.toObject(avg20));
			o.put("avg30", MongoGen.toObject(avg30));
			o.put("avg45", MongoGen.toObject(avg45));
			o.put("avg60", MongoGen.toObject(avg60));
			o.put("avg100", MongoGen.toObject(avg100));
			o.put("avg120", MongoGen.toObject(avg120));
			o.put("max30", MongoGen.toObject(max30));
			o.put("max60", MongoGen.toObject(max60));
			o.put("max90", MongoGen.toObject(max90));

			open = getDouble(o, "open");
			close = getDouble(o, "close");
			high = getDouble(o, "high");
			low = getDouble(o, "low");
			up = getDouble(o, "up");
			volume = getInteger(o, "volume");
			avg5 = getDouble(o, "avg5");
			avg10 = getDouble(o, "avg10");
			avg15 = getDouble(o, "avg15");
			avg20 = getDouble(o, "avg20");
			avg30 = getDouble(o, "avg30");
			avg45 = getDouble(o, "avg45");
			avg60 = getDouble(o, "avg60");
			avg100 = getDouble(o, "avg100");
			avg120 = getDouble(o, "avg120");
			max30 = getDouble(o, "max30");
			max60 = getDouble(o, "max60");
			max90 = getDouble(o, "max90");







































	
		private MongoMap<DayDto> days = Maps.newMongoMap();
		private List<ChuQuanDateDto> chuQuanDates = Lists.newArrayList();




		public MongoMap<DayDto> getDays() {
		public List<ChuQuanDateDto> getChuQuanDates() {

		public void setDays(MongoMap<DayDto> days) {
		public void setChuQuanDates(List<ChuQuanDateDto> chuQuanDates) {

			o.put("id", MongoGen.toObject(id));
			o.put("days", MongoGen.toObject(days));
			o.put("chuQuanDates", MongoGen.toObjectChuQuanDateDto(chuQuanDates));

			days = loadDays(o);
			chuQuanDates = loadChuQuanDates(o);

		MongoMap<DayDto> loadDays(DBObject o) {



		List<ChuQuanDateDto> loadChuQuanDates(DBObject o) {

	




			o.put("id", MongoGen.toObject(id));




	
		private String name = "";



		public String getName() {

		public void setName(String name) {

			o.put("id", MongoGen.toObject(id));
			o.put("name", MongoGen.toObject(name));

			name = getString(o, "name");





	
		private String time = "";
		private String price = "";
		private String closeYestoday = "";
		private String highToday = "";
		private String lowToday = "";







		public String getTime() {
		public String getPrice() {
		public String getCloseYestoday() {
		public String getHighToday() {
		public String getLowToday() {

		public void setTime(String time) {
		public void setPrice(String price) {
		public void setCloseYestoday(String closeYestoday) {
		public void setHighToday(String highToday) {
		public void setLowToday(String lowToday) {

			o.put("id", MongoGen.toObject(id));
			o.put("time", MongoGen.toObject(time));
			o.put("price", MongoGen.toObject(price));
			o.put("closeYestoday", MongoGen.toObject(closeYestoday));
			o.put("highToday", MongoGen.toObject(highToday));
			o.put("lowToday", MongoGen.toObject(lowToday));

			time = getString(o, "time");
			price = getString(o, "price");
			closeYestoday = getString(o, "closeYestoday");
			highToday = getString(o, "highToday");
			lowToday = getString(o, "lowToday");













