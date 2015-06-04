package com.cnbizmedia.user;

import java.util.ArrayList;
import java.util.List;

import com.cnbizmedia.exception.VGameException;
import com.cnbizmedia.gen.dto.MongoGen.Daos;
import com.cnbizmedia.gen.dto.MongoGen.OrderDao;
import com.cnbizmedia.gen.dto.MongoGen.OrderDao.OrderDtoCursor;
import com.cnbizmedia.gen.dto.MongoGen.OrderFinishDao;
import com.cnbizmedia.gen.dto.MongoGen.OrderFinishDao.OrderFinishDtoCursor;
import com.cnbizmedia.gen.dto.MongoGen.UserDao;
import com.cnbizmedia.gen.dto.MongoGen.UserDto;
import com.cnbizmedia.recharge.IOrderDto;
import com.cnbizmedia.recharge.Order;
import com.google.common.collect.Lists;

public class User {

	private final UserDto dto;

	public User(UserDto dto) {
		this.dto = dto;
	}

	public String getEmail() {
		return dto.getEmail();
	}

	public String getPwdMD5() {
		return dto.getPwdMD5();
	}

	public String getNick() {

		if (!isNullOrEmpty(dto.getNick())) {
			return dto.getNick();
		}
		if (!isNullOrEmpty(dto.getEmail())) {
			return dto.getEmail();
		}
		return dto.getQQOpenId();
	}

	private boolean isNullOrEmpty(String s) {
		return s == null || s.isEmpty();
	}

	/**
	 * V币
	 * 
	 * @return
	 */
	public int getVb() {
		return dto.getVb();
	}

	public UserDto getDto() {
		return dto;
	}

	public List<Order> getOrders() {
		OrderDao dao1 = Daos.getOrderDao();
		OrderFinishDao dao2 = Daos.getOrderFinishDao();

		ArrayList<Order> ls = Lists.newArrayList();
		ls.addAll(getLastTen(dao1));
		ls.addAll(getLastTen(dao2));
		return ls;
	}

	private List<Order> getLastTen(OrderDao dao) {

		OrderDtoCursor find = dao.findByRechargeUserId(getDto().getId());

		int count = find.getCount();

		ArrayList<Order> ls = Lists.newArrayList();
		for (IOrderDto dto : find) {
			count--;
			if (count <= 10) {
				ls.add(new Order(dto, false));
			}
		}
		return ls;
	}

	private List<Order> getLastTen(OrderFinishDao dao) {

		OrderFinishDtoCursor find = dao.findByRechargeUserId(getDto().getId());

		int count = find.getCount();

		ArrayList<Order> ls = Lists.newArrayList();
		for (IOrderDto dto : find) {
			count--;
			if (count <= 10) {
				ls.add(new Order(dto, true));
			}
		}
		return ls;
	}

	public String getId() {
		return dto.getId();
	}

	public void reduceVb(int vbNeed) {
		if (vbNeed < 0)
			throw new IllegalArgumentException(vbNeed + "");
		
		checkEnouph(vbNeed);
		dto.setVb(dto.getVb() - vbNeed);
		Daos.getUserDao().save(dto);
	}

	private void checkEnouph(int vbNeed) {
		if (dto.getVb() < vbNeed)
			throw new VGameException("V币不够");
	}

	public void addVb(int vbAdd) {
		if (vbAdd <= 0)
			throw new IllegalArgumentException(vbAdd + "");
		dto.setVb(dto.getVb() + vbAdd);
		UserDao dao = Daos.getUserDao();
		dao.save(dto);
	}
}