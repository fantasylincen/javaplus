package cn.javaplus.smonitor;

import java.util.Iterator;
import java.util.List;

import cn.javaplus.log.Log;
import cn.javaplus.smonitor.gen.dto.MongoGen;
import cn.javaplus.smonitor.gen.dto.MongoGen.Daos;
import cn.javaplus.smonitor.gen.dto.MongoGen.GuPiaoDto;
import cn.javaplus.smonitor.gen.dto.MongoGen.KeyValueDao;
import cn.javaplus.smonitor.gen.dto.MongoGen.KeyValueDto;
import cn.javaplus.smonitor.gen.dto.MongoGen.SelectDto;
import cn.javaplus.smonitor.gen.dto.MongoGen.UserDto;

public class User {
	private final UserDto dto;

	public User(UserDto dto) {
		this.dto = dto;
	}

	public String getId() {
		return dto.getId();
	}

	public double getRmb() {
		return dto.getRmb();
	}

	public List<GuPiaoDto> getGupiaos() {
		return dto.getGupiaos();
	}

	public List<SelectDto> getSelects() {
		
		List<SelectDto> selects = dto.getSelects();
		Iterator<SelectDto> it = selects.iterator();
		while (it.hasNext()) {
			MongoGen.SelectDto selectDto = (MongoGen.SelectDto) it.next();
			if(selectDto.getId().trim().isEmpty())
				it.remove();
		}
		return selects;
	}

	public double getZiChan() {
		return getRmb() + getGupiaoPrice();
	}

	private double getGupiaoPrice() {
		List<GuPiaoDto> all = getGupiaos();
		double a = 0;
		for (GuPiaoDto gp : all) {
			a += ShiChang.getPrice(gp) * gp.getCount();
		}
		return a;
	}

	public String getLastSelectCode() {
		KeyValueDao dao = Daos.getKeyValueDao();
		KeyValueDto dto = dao.get("LAST_SELECT_CODE");
		if(dto == null) {
			return null;
		}
		return dto.getValue();
	}

	public void setLastSelectCode(String code) {
		KeyValueDto dto = new KeyValueDto();
		dto.setKey("LAST_SELECT_CODE");
		dto.setValue(code);

		Daos.getKeyValueDao().save(dto);
	}

	public void buy(String code, int count) {
		double price = ShiChang.getPrice(code);
		double need = count * price;
		double re = getRmb() - need;
		if (re < 0) {
			Log.e("购买失败 金钱不够");
			return;
		}

		dto.setRmb(re);

		addGuPiaoDto(code, count);

		Daos.getUserDao().save(dto);
	}

	private void addGuPiaoDto(String code, int count) {
		GuPiaoDto dto = getDto(code);
		dto.setCount(dto.getCount() + count);
	}

	private GuPiaoDto getDto(String code) {
		for (GuPiaoDto g : getGupiaos()) {
			if (g.getId().equals(code))
				return g;
		}
		GuPiaoDto gg = new GuPiaoDto();
		gg.setId(code);
		getGupiaos().add(gg);
		return gg;
	}

	public void sell(String code, int count) {
		List<GuPiaoDto> gupiaos = getGupiaos();
		Iterator<GuPiaoDto> it = gupiaos.iterator();
		while (it.hasNext()) {
			MongoGen.GuPiaoDto gp = (MongoGen.GuPiaoDto) it.next();
			if (gp.getId().equals(code)) {
				sell(gp, it, count);
			}
		}
		Daos.getUserDao().save(dto);
	}

	private void sell(GuPiaoDto gp, Iterator<GuPiaoDto> it, int count) {
		int now = gp.getCount();
		int re = now - count;

		double price = ShiChang.getPrice(gp.getId());

		if (re < 0) {
			return;
		} else if (re == 0) {
			addRmb(count * price);
			it.remove();
		} else {
			addRmb(count * price);
			gp.setCount(re);
		}
	}

	private void addRmb(double d) {
		dto.setRmb(dto.getRmb() + d);
	}
}
