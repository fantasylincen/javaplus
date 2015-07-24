package org.hhhhhh.guess.question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hhhhhh.guess.Server;
import org.hhhhhh.guess.ad.Ad;
import org.hhhhhh.guess.hibernate.dao.DbUtil;
import org.hhhhhh.guess.hibernate.dto.QuestionDto;
import org.hhhhhh.guess.hibernate.dto.RoundDto;
import org.hhhhhh.guess.hibernate.dto.UserDto;
import org.hhhhhh.guess.user.User;
import org.hhhhhh.guess.util.KeyValue;

import cn.javaplus.time.Time;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;

public class Manager {

	private List<RoundDto> rounds;
	private RoundDto current;

	public User getFirstOne() {

		UserDto dto = new UserDto();
		dto.setUsername("aa");
		dto.setPassword("");
		dto.setJiFen(1000000000);
		dto.setNick("张三疯");
		return new User(dto);
	}

	@SuppressWarnings("unchecked")
	public int getRound() {

		return getRounds().size();
	}

	private void sortRounds() {
		Collections.sort(getRounds(), new RoundsC());
	}

	public RoundDto getCurrent() {
		if (!getRounds().isEmpty()) {
			ArrayList<RoundDto> rs = Lists.newArrayList(getRounds());
			Collections.reverse(rs);
			for (RoundDto dto : rs) {
				if (isNowTimeInTimeScope(dto)) {
					current = getRounds().get(0);
					break;
				}
			}
		}
		return current;
	}

	private boolean isNowTimeInTimeScope(RoundDto dto) {
		return Util.Time.isIn(dto.getStartTime() + "|00:00 to " + dto.getEndTime()
				+ "|24:00");
	}

	public Ad getAd() {
		return new Ad();// TODO LC
	}

	public Ad getAd2() {
		return new Ad();// TODO LC
	}

	/**
	 * 以预测人数
	 * 
	 * @return
	 */
	public int getYiYuCe() {
		RoundDto c = getCurrent();
		if (c == null)
			return 0;
		return c.getYiYuCe();
	}

	public int getRemainSec() {
		return 0; // 新版本没有了倒计时
	}

	public double getJiangChi() {
		KeyValue kv = Server.getKeyValueForever();
		return kv.getDouble("JIANG_CHI");
	}

	public int getRank(User user) {
		return 1;// TODO LC
	}

	/**
	 * 本期活动总共有多少天
	 * 
	 * @return
	 */
	public int getDayB() {
		RoundDto c = getCurrent();
		if (c == null)
			return 0;

		String startTime = c.getStartTime();
		String endTime = c.getEndTime();

		Date dStart = Util.Time.parse("yyyy-MM-dd", startTime);
		Date dEnd = Util.Time.parse("yyyy-MM-dd", endTime);

		return (int) ((dEnd.getTime() - dStart.getTime()) / Time.MILES_ONE_DAY);
	}

	/**
	 * 本期活动已经进行到了第几天
	 */
	public int getDayA() {
		RoundDto c = getCurrent();
		if (c == null)
			return 0;

		String startTime = c.getStartTime();

		Date dStart = Util.Time.parse("yyyy-MM-dd", startTime);

		long now = System.currentTimeMillis();

		int day = (int) ((now - dStart.getTime()) / Time.MILES_ONE_DAY) + 1;
		int max = getDayB();

		day = Math.min(day, max);

		return day;
	}

	public double getJiangJin(User u) {
		return 0;// TODO LC
	}

	@SuppressWarnings("unchecked")
	public List<RoundDto> getRounds() {
		if (rounds == null) {
			setRounds(DbUtil.find("RoundDto"));
			sortRounds();
		}
		return rounds;
	}

	private void setRounds(List<RoundDto> rounds) {
		this.rounds = rounds;
	}
}
