package cn.javaplus.monichaogu;

import java.util.List;
import java.util.Map;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.monichaogu.gen.dto.MongoGen.Daos;
import cn.javaplus.monichaogu.gen.dto.MongoGen.Maps;
import cn.javaplus.monichaogu.gen.dto.MongoGen.SelectDto;
import cn.javaplus.monichaogu.gen.dto.MongoGen.UserDao;
import cn.javaplus.monichaogu.gen.dto.MongoGen.UserDto;

import com.opensymphony.xwork2.ActionSupport;

public class AddZiXuanAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4895530478630873960L;
	private String addZiXuan;

	@Override
	public String execute() throws Exception {

		String[] split = getAddZiXuan().split("\r");

		UserDao d = Daos.getUserDao();
		UserDto user = d.get("user");

		user.getSelects().clear();
		for (String id : split) {
			id = id.trim();

			SelectDto e = new SelectDto();
			e.setId(id);

			user.getSelects().add(e);

		}

		List<SelectDto> selects = user.getSelects();
		user.setSelects(filter(selects));
		d.save(user);

		return super.execute();
	}

	private List<SelectDto> filter(List<SelectDto> selects) {
		Map<String, SelectDto> map = Maps.newHashMap();
		for (SelectDto s : selects) {

			String id = s.getId();
			if (!id.trim().isEmpty())
				map.put(id, s);
		}
		return Lists.newArrayList(map.values());
	}

	public String getAddZiXuan() {
		return addZiXuan;
	}

	public void setAddZiXuan(String addZiXuan) {
		this.addZiXuan = addZiXuan;
	}
}
