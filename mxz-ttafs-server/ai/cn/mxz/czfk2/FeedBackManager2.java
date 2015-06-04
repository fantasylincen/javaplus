package cn.mxz.czfk2;

import java.util.ArrayList;
import java.util.List;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.FeedBack2Dao;
import mongo.gen.MongoGen.FeedBack2Dto;
import mongo.gen.MongoGen.ReceivedBox2Dto;
import cn.javaplus.log.Debuger;
import cn.mxz.FeedBackTwoTemplet;
import cn.mxz.FeedBackTwoTempletConfig;
import cn.mxz.city.City;

import com.google.common.collect.Lists;

public class FeedBackManager2 {

	private City city;
	private List<FeedBack2> boxes;
	private FeedBack2Dto dto;

	public FeedBackManager2(City city) {
		this.city = city;
		initDto();
		initBoxes();
	}
	/**
	 * 活动开启时, 增加充值数量
	 * 
	 * @param count
	 */
	public void addOnActivityOpen(int count) {
		boolean isActivityOpen = city.getFeedBackManager().isActivityOpen();
		Debuger.debug("FeedBackManager.addOnActivityOpen()" + city.getId() + "|" + city.getPlayer().getNick() + "|" + count);
		if (isActivityOpen) {
			dto.setRechargeGold(dto.getRechargeGold() + count);
			Daos.getFeedBack2Dao().save(dto);
			Debuger.debug("FeedBackManager2.addOnActivityOpen success " + city.getId() + "|" + city.getPlayer().getNick() + "|" + count);
		}
	}
	private void initDto() {
		FeedBack2Dao dao = Daos.getFeedBack2Dao();
		dto = dao.get(city.getId());
		if (dto == null) {
			dto = dao.createDTO();
			dto.setUname(city.getId());
			dto.setRechargeGold(0);
			ArrayList<ReceivedBox2Dto> newArrayList = Lists.newArrayList();
			dto.setReceivedIds(newArrayList);
			dao.save(dto);
		}
	}

	private void initBoxes() {
		boxes = Lists.newArrayList();
		List<FeedBackTwoTemplet> all = FeedBackTwoTempletConfig.getAll();
		for (FeedBackTwoTemplet temp : all) {
			boxes.add(new FeedBack2(temp, city, dto));
		}
	}
	
	public City getCity() {
		return city;
	}

	public void receiveById(int id) {
		FeedBack2 back = get(id);
		back.receive();
	}

	private FeedBack2 get(int id) {
		List<FeedBack2> all = getBoxes();
		for (FeedBack2 feedBack : all) {
			if (feedBack.getId() == id) {
				return feedBack;
			}
		}
		return null;
	}

	public List<FeedBack2> getBoxes() {
		return boxes;
	}

	public int getRechargeAll() {
		return dto.getRechargeGold();
	}

	public int getRemainSec() {
		return city.getFeedBackManager().getRemainSec();
	}
	
}
