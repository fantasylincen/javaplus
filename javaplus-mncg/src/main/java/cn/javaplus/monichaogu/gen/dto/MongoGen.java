



	static byte copy(byte value) {
		public static StockDao getStockDao() {

	
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

	

	




			put("date", o, date);




	
		private String value = "";
		private boolean isClientVisible = false;
		private String dsc = "";





		public String getValue() {
		public boolean getIsClientVisible() {
		public String getDsc() {

		public void setValue(String value) {
		public void setIsClientVisible(boolean isClientVisible) {
		public void setDsc(String dsc) {

			put("key", o, key);
			put("value", o, value);
			put("isClientVisible", o, isClientVisible);
			put("dsc", o, dsc);

			value = getString(o, "value");
			isClientVisible = getBoolean(o, "isClientVisible");
			dsc = getString(o, "dsc");









	
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

