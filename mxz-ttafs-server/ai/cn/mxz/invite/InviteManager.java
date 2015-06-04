package cn.mxz.invite;

import java.util.List;

import message.S;
import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.InviteCodeDao;
import mongo.gen.MongoGen.InviteCodeDao.InviteCodeDtoCursor;
import mongo.gen.MongoGen.InviteCodeDto;
import mongo.gen.MongoGen.InviteUsersDao;
import mongo.gen.MongoGen.InviteUsersDao.InviteUsersDtoCursor;
import mongo.gen.MongoGen.InviteUsersDto;
import mongo.gen.MongoGen.Lists;
import cn.javaplus.log.Debuger;
import cn.javaplus.log.Log;
import cn.mxz.FriendsFeedbackTemplet;
import cn.mxz.FriendsFeedbackTempletConfig;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.system.SystemCounter;
import cn.mxz.system.SystemCounterKey;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import define.D;

public class InviteManager {

	private City city;
	private int count;
	private long lastUpdateTime;
	private long lastUpdateTime2;
	private List<City> users;

	public InviteManager(City city) {
		this.city = city;
		lastUpdateTime = 0;
		lastUpdateTime2 = 0;
	}

	/**
	 * 我的邀请码
	 */
	public String getMyCode() {
		InviteCodeDto dto = getDto();
		return dto.getCode();
	}

	public List<InviteReward> getRewards() {
		List<FriendsFeedbackTemplet> all = FriendsFeedbackTempletConfig
				.getAll();
		List<InviteReward> ls = Lists.newArrayList();
		for (FriendsFeedbackTemplet t : all) {
			ls.add(new InviteReward(city, t));
		}
		return ls;
	}

	/**
	 * 已经邀请了多少人
	 */
	public int getNumber() {
		if (isTimeUp()) {
			updateCount();
			lastUpdateTime = System.currentTimeMillis();
		}
		return count;
	}
	
	public List<City> getInvites() {
		if (isTimeUp2()) {
			updateUsers();
			lastUpdateTime2 = System.currentTimeMillis();
		}
		return users;
	}

	private void updateUsers() {
		InviteUsersDao dao = Daos.getInviteUsersDao();
		InviteUsersDtoCursor c = dao.findByB(city.getId());
		users = Lists.newArrayList();
		for (InviteUsersDto dto : c) {
			users.add(CityFactory.getCity(dto.getA()));
		}
	}

	private boolean isTimeUp() {
		long l = System.currentTimeMillis() - lastUpdateTime;
		Debuger.debug("time:" + l);
		return l > 30000;
	}

	private boolean isTimeUp2() {
		long l = System.currentTimeMillis() - lastUpdateTime2;
		Debuger.debug("time:" + l);
		return l > 30000;
	}

	private void updateCount() {
		InviteUsersDao dao = Daos.getInviteUsersDao();
		InviteUsersDtoCursor c = dao.findByB(city.getId());
		count = c.getCount();
		Debuger.debug("更新数量：" + city.getId());
	}

	public boolean commitCode(String code) {
		if (hasBeanInvited()) {
			return true;
		}
		InviteUsersDao dao = Daos.getInviteUsersDao();
		InviteUsersDto dto = dao.createDTO();

		InviteCodeDto d = findUserByCode(code);

		if (d == null) {
			Log.e("没有找到用户 " + code);
			return true;
		}

		if(d.getUname().equals(city.getId())) {
			Log.e("不能输入自己的邀请码 " + code);
			return true;
		}
		
		Log.d("找到用户:" + d.getUname());
		
		dto.setA(city.getId());
		dto.setB(d.getUname());
		dao.save(dto);

		int prizeId = city.getPrizeCenter().addPrize(3, D.INVITE_CODE_REWARD,
				S.STR10342, S.STR10341);

		city.getUserCounterHistory().set(CounterKey.INVITE_PRIZE_ID, prizeId);

		markHasBeanInvited();
		
		return false;
	}

	private void markHasBeanInvited() {
		UserCounter his = city.getUserCounterHistory();
		his.mark(CounterKey.HAS_BEAN_INVITED);
	}

	private InviteCodeDto findUserByCode(String code) {
		InviteCodeDao dao2 = Daos.getInviteCodeDao();
		InviteCodeDtoCursor c = dao2.findByCode(code);
		for (InviteCodeDto d : c) {
			return d;
		}
		return null;
	}

	private InviteCodeDto getDto() {
		InviteCodeDao dao = Daos.getInviteCodeDao();
		InviteCodeDto dto = dao.get(city.getId());
		if (dto == null) {
			dto = dao.createDTO();
			dto.setUname(city.getId());
			dto.setCode(generateCode());
			dao.save(dto);
		}
		return dto;
	}

	/**
	 * 生成一个邀请码
	 * 
	 * @return
	 */
	private String generateCode() {
		SystemCounter ins = SystemCounter.getInstance();
		int code = ins.get(SystemCounterKey.MAX_INVATE_CODE);
		if (code < 100000) {
			code = 100000;
		}
		code++;
		ins.set(SystemCounterKey.MAX_INVATE_CODE, code);
		return code + "";
	}

	public boolean hasBeanInvited() {
		UserCounter his = city.getUserCounterHistory();
		return his.isMark(CounterKey.HAS_BEAN_INVITED);
	}

	public boolean hasReceive() {
		int prizeId = city.getUserCounterHistory().get(
				CounterKey.INVITE_PRIZE_ID);
		if (prizeId == 0) {
			return false;
		}
		boolean gotPrize = city.getPrizeCenter().isGotPrize(prizeId);
		return gotPrize;
	}
}
