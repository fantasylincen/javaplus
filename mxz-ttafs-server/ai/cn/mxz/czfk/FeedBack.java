package cn.mxz.czfk;

import java.util.ArrayList;
import java.util.List;

import message.S;
import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.FeedBackDto;
import mongo.gen.MongoGen.ReceivedBoxDto;
import cn.mxz.FeedBackTemplet;
import cn.mxz.StuffTemplet;
import cn.mxz.StuffTempletConfig;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class FeedBack {

	private FeedBackTemplet temp;
	private City user;
	private FeedBackDto dto;

	public FeedBack(FeedBackTemplet temp, City user, FeedBackDto dto) {
		this.temp = temp;
		this.user = user;
		this.dto = dto;
	}

	public int getId() {
		return temp.getId();
	}

	public int getBagId() {
		return temp.getBagid();
	}

	public String getName() {
		int bagid = temp.getBagid();
		StuffTemplet stuffTemplet = StuffTempletConfig.get(bagid);
		return stuffTemplet.getName();
	}

	public int getRechargeNeed() {
		return temp.getMoney();
	}

	public boolean getCanReceive() {
		int remainSec = user.getFeedBackManager().getRemainSec();
		boolean a = getHasReceive();
		boolean b = monnyEnouph();
		boolean c = remainSec >= 0;
		return !a && b && c;
	}

	private boolean monnyEnouph() {
		FeedBackManager fm = user.getFeedBackManager();
		int all = fm.getRechargeAll();
		return all >= getRechargeNeed();
	}

	public boolean getHasReceive() {
		List<Integer> ids = getReceivedIds();
		return ids.contains(getId());
	}

	private List<Integer> getReceivedIds() {

		List<ReceivedBoxDto> ids = dto.getReceivedIds();
		if (ids == null || ids.isEmpty()) {
			return Lists.newArrayList();
		}
		ArrayList<Integer> ls = Lists.newArrayList();
		for (ReceivedBoxDto id : ids) {
			ls.add(id.getBoxId());
		}
		return ls;
	}

	public void receive() {
		check();
		user.getBagAuto().addProp(getBagId(), 1);
		markReceive();
	}

	private void markReceive() {
		List<Integer> ids = getReceivedIds();
		ids.add(getId());
		save(ids);
	}

	private void save(List<Integer> ids) {
		dto.setReceivedIds(build(ids));
		Daos.getFeedBackDao().save(dto);
	}

	private List<ReceivedBoxDto> build(List<Integer> ids) {
		ArrayList<ReceivedBoxDto> ls = Lists.newArrayList();
		for (Integer dd : Sets.newHashSet(ids)) {
			ls.add(build(dd));
		}
		return ls;
	}

	private ReceivedBoxDto build(Integer dd) {
		ReceivedBoxDto dto = new ReceivedBoxDto();
		dto.setBoxId(dd);
		return dto;
	}

	private void check() {
		if (!getCanReceive()) {
			throw new OperationFaildException(S.S10326);
		}
	}

}
