






	static byte copy(byte value) {
		public static PriceDao getPriceDao() {
		public static StockDao getStockDao() {
		public static UserDao getUserDao() {

	
		public KeyValueDtoCursor findByValue(String value) {

	
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

	

	
		public UserDtoCursor findByRmb(double rmb) {
		/**

	




			put("date", o, date);




	
		private int count = 0;



		public int getCount() {

		public void setCount(int count) {

			put("id", o, id);
			put("count", o, count);

			count = getInteger(o, "count");





	
		private String value = "";



		public String getValue() {

		public void setValue(String value) {

			put("key", o, key);
			put("value", o, value);

			value = getString(o, "value");





	
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

			put("date", o, date);
			put("open", o, open);
			put("close", o, close);
			put("high", o, high);
			put("low", o, low);
			put("up", o, up);
			put("volume", o, volume);
			put("avg5", o, avg5);
			put("avg10", o, avg10);
			put("avg15", o, avg15);
			put("avg20", o, avg20);
			put("avg30", o, avg30);
			put("avg45", o, avg45);
			put("avg60", o, avg60);
			put("avg100", o, avg100);
			put("avg120", o, avg120);
			put("max30", o, max30);
			put("max60", o, max60);
			put("max90", o, max90);

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







































	




			put("id", o, id);




	
		private MongoMap<PriceDto> pricesNormal = Maps.newMongoMap();
		private MongoMap<PriceDto> pricesFuQuan = Maps.newMongoMap();
		private List<ChuQuanDateDto> chuQuanDates = Lists.newArrayList();





		public MongoMap<PriceDto> getPricesNormal() {
		public MongoMap<PriceDto> getPricesFuQuan() {
		public List<ChuQuanDateDto> getChuQuanDates() {

		public void setPricesNormal(MongoMap<PriceDto> pricesNormal) {
		public void setPricesFuQuan(MongoMap<PriceDto> pricesFuQuan) {
		public void setChuQuanDates(List<ChuQuanDateDto> chuQuanDates) {

			put("id", o, id);
			put("pricesNormal", o, pricesNormal);
			put("pricesFuQuan", o, pricesFuQuan);
			put("chuQuanDates", o, chuQuanDates);

			pricesNormal = loadPricesNormal(o);
			pricesFuQuan = loadPricesFuQuan(o);
			chuQuanDates = loadChuQuanDates(o);

		MongoMap<PriceDto> loadPricesNormal(DBObject o) {
		MongoMap<PriceDto> loadPricesFuQuan(DBObject o) {




		List<ChuQuanDateDto> loadChuQuanDates(DBObject o) {

	
		private double rmb = 0;
		private List<GuPiaoDto> gupiaos = Lists.newArrayList();
		private List<SelectDto> selects = Lists.newArrayList();





		public double getRmb() {
		public List<GuPiaoDto> getGupiaos() {
		public List<SelectDto> getSelects() {

		public void setRmb(double rmb) {
		public void setGupiaos(List<GuPiaoDto> gupiaos) {
		public void setSelects(List<SelectDto> selects) {

			put("id", o, id);
			put("rmb", o, rmb);
			put("gupiaos", o, gupiaos);
			put("selects", o, selects);

			rmb = getDouble(o, "rmb");
			gupiaos = loadGupiaos(o);
			selects = loadSelects(o);






		List<GuPiaoDto> loadGupiaos(DBObject o) {
		List<SelectDto> loadSelects(DBObject o) {

