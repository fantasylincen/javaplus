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
import org.hhhhhh.guess.rankinglist.RankingList;
import org.hhhhhh.guess.user.User;
import org.hhhhhh.guess.util.KeyValue;

import cn.javaplus.log.Log;
import cn.javaplus.time.Time;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;

public class Manager {

	private List<RoundDto> rounds;
	private RoundDto current;

	public RankingList getRankingList() {
		return new RankingList();
	}

	public User getFirstOne() {

		UserDto dto = new UserDto();
		dto.setUsername("aa");
		dto.setPassword("");
		dto.setJiFen(1000000000);
		dto.setNick("张三疯");
		return new User(dto);
	}

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
					current = dto;
					break;
				}
			}
		}
		return current;
	}

	private boolean isNowTimeInTimeScope(RoundDto dto) {
		String startTime = dto.getStartTime();
		String endTime = dto.getEndTime();
		String scope = startTime + "|00:00 to " + endTime + "|24:00";
		return Util.Time.isIn(scope);
	}

	public Ad getAd() {
		return new Ad();
	}

	public Ad getAd2() {
		return new Ad();
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

	@SuppressWarnings("unchecked")
	public List<Question> getQuestions() {

		RoundDto current = Server.getManager().getCurrent();

		String roundId = current.getId();

		List<QuestionDto> find = DbUtil.find("QuestionDto", "roundId", roundId);
		ArrayList<Question> ls = Lists.newArrayList();

		for (QuestionDto dto : find) {
			Log.d(dto.getImageId());
			ls.add(new Question(dto));
		}
		return ls;
	}

	public Question getQuestion(String questionId) {
		QuestionDto find = DbUtil.get(QuestionDto.class, questionId);
		return new Question(find);
	}

}
