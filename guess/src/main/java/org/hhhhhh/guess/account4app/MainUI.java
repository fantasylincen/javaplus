package org.hhhhhh.guess.account4app;

import java.util.ArrayList;
import java.util.List;

import org.hhhhhh.guess.Server;
import org.hhhhhh.guess.ad.Ad;
import org.hhhhhh.guess.question.Option;
import org.hhhhhh.guess.question.Question;
import org.hhhhhh.guess.rankinglist.RankingList;
import org.hhhhhh.guess.user.User;

import com.google.common.collect.Lists;

public class MainUI {

	public List<RankListItem> getRankingList() {
		ArrayList<RankListItem> ls = Lists.newArrayList();
		RankingList list = Server.getManager().getRankingList();
		List<User> users = list.getUsers();
		for (User user : users) {
			ls.add(new RankListItem(user));
		}
		return ls;
	}


	public class QuestionItem {

		public class OptionItem {

			private final Option option;

			public OptionItem(Option option) {
				this.option = option;
			}

			public String getId() {
				return option.getId();
			}

			public String getTitle() {
				return option.getTitle();
			}

			public String getDsc() {
				return option.getDsc();
			}

			public double getScale() {
				return option.getScale();
			}
			
			public String getScaleValue() {
				return option.getScaleText();
			}

			public boolean isSelected() {
				boolean selected = option.isSelected(user);
//				Log.d(selected, question.getDsc());
				return selected;
			}

		}
		private final Question question;

		public QuestionItem(Question question) {
			this.question = question;
		}

		public String getAnswerOptionHead() {
			String head = question.getAnswerOptionHead();
			if (head == null || head.isEmpty())
				return null;
			return head;
		}

		public int getJiFen() {
			return question.getJiFen();
		}

		public String getId() {
			return question.getId();
		}

		public String getDsc() {
			return question.getDsc();
		}
		public String getTitle() {
			return question.getTitle();
		}

		public String getImg() {
			return /*"http://113.204.112.162/guess/gmanager/" + */question.getImg();
		}

		public boolean isAnswered(User user) {
			return question.isAnswered(user);
		}

		public int getCount() {
			return question.getCount();
		}

		public List<OptionItem> getOptions() {
			ArrayList<OptionItem> ls = Lists.newArrayList();

			List<Option> options = question.getOptions();
			for (Option option : options) {
				ls.add(new OptionItem(option));
			}
			return ls;
		}
	}

	public static class AdItem {

		private final Ad ad;

		public AdItem(Ad ad) {
			this.ad = ad;
		}

		public String getImg() {
			return ad.getImg();
		}

		public String getUrl() {
			return ad.getUrl();
		}

		public String getText() {
			return ad.getText();
		}

	}

	public static class RankListItem {

		private final User u;

		public RankListItem(User user) {
			u = user;
		}

		public String getNick() {
			return u.getNick();
		}

		public int getJiFen() {
			return u.getJiFen();
		}

		/**
		 * 获得奖金
		 */
		public double getJiangJin() {
			return Server.getManager().getJiangJin(u);
		}
	}

	private final User user;

	public MainUI(User user) {
		this.user = user;
	}

	public RankListItem getFirst() {
		return new RankListItem(Server.getManager().getFirstOne());
	}

	public AdItem getAd2() {
		return new AdItem(Server.getManager().getAd2());
	}

	public AdItem getAd() {
		return new AdItem(Server.getManager().getAd());
	}

	public int getRank() {
		return Server.getManager().getRankingList().getRank(user);
	}

	public double getJiangChi() {
		return Server.getManager().getJiangChi();
	}

	public int getRound() {
		return Server.getManager().getRound();
	}

	public String getDay() {
		return Server.getManager().getDayA() + "/"
				+ Server.getManager().getDayB();
	}

	public int getRemainSec() {
		return Server.getManager().getRemainSec();
	}

	public int getYiYuCe() {
		return Server.getManager().getYiYuCe();
	}

	public int getJiFen() {
		return user.getJiFen();
	}

	public List<QuestionItem> getQuestions() {
		ArrayList<QuestionItem> ls = Lists.newArrayList();
		List<Question> questions = Server.getManager().getQuestions();

		for (Question question : questions) {
			ls.add(new QuestionItem(question));
		}
		return ls;
	}

}
