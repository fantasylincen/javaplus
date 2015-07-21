package org.hhhhhh.guess.question;

import java.util.List;

import org.hhhhhh.guess.Server;
import org.hhhhhh.guess.ad.Ad;
import org.hhhhhh.guess.user.User;

import com.google.common.collect.Lists;

public class Manager {

	public User getFirstOne() {
		return Server.getUser("aaa");
	}

	public int getRound() {
		return 0;//TODO LC
	}

	public Ad getAd() {
		return new Ad();//TODO LC
	}

	public Ad getAd2() {
		return new Ad();//TODO LC
	}

	/**
	 * 以预测人数
	 * @return
	 */
	public int getYiYuCe() {
		return 0;//TODO LC
	}

	public int getRemainSec() {
		return 0;//TODO LC
	}

	public double getJiangChi() {
		return 0;//TODO LC
	}

	public int getRank(User user) {
		return 1;//TODO LC
	}

	public List<Question> getQuestions() {
		return Lists.newArrayList();//TODO LC
	}

	public int getDayB() {
		return 0;//TODO LC
	}

	public int getDayA() {
		return 0;//TODO LC
	}

	public double getJiangJin(User u) {
		return 0;//TODO LC
	}
}
