

	
		public RecordDtoCursor findByMineId(String mineId) {
		public RecordDtoCursor findByLastUpdateTime(long lastUpdateTime) {

	
		private String count = "";

		public String getCount() {

		public void setCount(String count) {

		checkNull(count);

			o.put("price", price);
			o.put("count", count);

		count = getString(o, "count");

	
		private String mineId = "";
		private TbItemDto source = null;
		private TbItemDto mine = null;
		private long lastUpdateTime = 0;

		public String getMineId() {
		public TbItemDto getSource() {
		public TbItemDto getMine() {
		public long getLastUpdateTime() {

		public void setMineId(String mineId) {
		public void setSource(TbItemDto source) {
		public void setMine(TbItemDto mine) {
		public void setLastUpdateTime(long lastUpdateTime) {

		checkNull(mineId);
		o.put("_id", sourceId);
			o.put("sourceId", sourceId);
			o.put("mineId", mineId);
			if(source != null) {
			if(mine != null) {
			o.put("lastUpdateTime", lastUpdateTime);

		mineId = getString(o, "mineId");
			DBObject source_dto = (DBObject) o.get("source");
			DBObject mine_dto = (DBObject) o.get("mine");
		lastUpdateTime = getLong(o, "lastUpdateTime");

