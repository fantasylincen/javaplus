package cn.javaplus.tb;

import cn.javaplus.tb.gen.dto.MongoGen.RecordDto;

public class RecordAdaptor {

	private final RecordDto dto;

	public RecordAdaptor(RecordDto dto) {
		this.dto = dto;
	}

	public RecordDto getDto() {
		return dto;
	}

	public String getSourceId() {
		return dto.getSourceId();
	}

	public String getMineId() {
		return dto.getMineId();
	}

	public String getSourceUrl() {
		return "http://item.taobao.com/item.htm?id=" + getSourceId();
//		return "http://item.taobao.com/item.htm?id=36026436361";
	}

	public String getUrl() {
		return "http://item.taobao.com/item.htm?id=" + getMineId();
//		return "http://item.taobao.com/item.htm?id=36026436361";
	}

	public boolean isTimeUp() {
		return getCd() <= 0;
	}

	public String getSourcePrice() {
		if (dto.getSource() == null)
			return null;
		return dto.getSource().getPrice();
	}

	public String getPrice() {
		if (dto.getMine() == null)
			return null;
		return dto.getMine().getPrice();
	}
	

	public String getCount() {
		if (dto.getMine() == null)
			return null;
		return dto.getMine().getCount();
	}
	

	public String getSourceCount() {
		if (dto.getSource() == null)
			return null;
		return dto.getSource().getCount();
	}
	

	private long getCd() {

		long t = System.currentTimeMillis();
		long last = dto.getLastUpdateTime();

		long cd = t - last;
		cd = 30000 - cd;
		if (cd < 0)
			cd = 0;
		return cd;
	}

}
