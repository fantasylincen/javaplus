package cn.mxz.czfk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.FeedBackDao;
import mongo.gen.MongoGen.FeedBackDto;
import cn.javaplus.log.Debuger;
import cn.javaplus.util.Util;
import cn.mxz.ActivityTemplet;
import cn.mxz.ActivityTempletConfig;
import cn.mxz.FeedBackTemplet;
import cn.mxz.FeedBackTempletConfig;
import cn.mxz.activity.ActivityIds;
import cn.mxz.city.City;

import com.google.common.collect.Lists;

public class FeedBackManager {

	private City city;
	private List<FeedBack> boxes;
	private FeedBackDto dto;

	public FeedBackManager(City city) {
		this.city = city;
		initDto();
		initBoxes();
	}

	private void initDto() {
		FeedBackDao dao = Daos.getFeedBackDao();
		dto = dao.get(city.getId());
		if (dto == null) {
			dto = dao.createDTO();
			dto.setUname(city.getId());
			dto.setRechargeGold(0);
			dao.save(dto);
		}
	}

	private void initBoxes() {
		boxes = Lists.newArrayList();
		List<FeedBackTemplet> all = FeedBackTempletConfig.getAll();
		for (FeedBackTemplet temp : all) {
			boxes.add(new FeedBack(temp, city, dto));
		}
	}

	/**
	 * 活动开启时, 增加充值数量
	 * 
	 * @param count
	 */
	public void addOnActivityOpen(int count) {
		boolean isActivityOpen = isActivityOpen();
		Debuger.debug("FeedBackManager.addOnActivityOpen()" + city.getId() + "|" + city.getPlayer().getNick() + "|" + count);
		if (isActivityOpen) {
			dto.setRechargeGold(dto.getRechargeGold() + count);
			Daos.getFeedBackDao().save(dto);
			Debuger.debug("FeedBackManager.addOnActivityOpen success " + city.getId() + "|" + city.getPlayer().getNick() + "|" + count);
		}
	}

	public boolean isActivityOpen() {
		int id = ActivityIds.ChongZhiHuiKui_20;
		ActivityTemplet temp = ActivityTempletConfig.get(id);
		boolean isActivityOpen = getRemainSec() > 0
				&& Util.Time.isIn(temp.getTime());
		return isActivityOpen;
	}

	public int getRechargeAll() {
		return dto.getRechargeGold();
	}

	public int getRemainSec() {
		int id = ActivityIds.ChongZhiHuiKui_20;
		ActivityTemplet temp = ActivityTempletConfig.get(id);
		String time = temp.getTime();
		String[] split = time.split(" to ");

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd|HH:mm");

		Date end;
		try {
			end = sf.parse(split[1]);
			long tend = end.getTime();
			long curr = System.currentTimeMillis();

			long ms = tend - curr;
			int remain = (int) (ms / 1000);

			if (remain < 0) {
				remain = 0;
			}
			return remain;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

	}

	public List<FeedBack> getBoxes() {
		return boxes;
	}

	public void receiveById(int id) {
		FeedBack back = get(id);
		back.receive();
	}

	private FeedBack get(int id) {
		List<FeedBack> all = getBoxes();
		for (FeedBack feedBack : all) {
			if (feedBack.getId() == id) {
				return feedBack;
			}
		}
		return null;
	}

}
