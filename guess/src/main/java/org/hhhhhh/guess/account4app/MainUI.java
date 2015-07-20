package org.hhhhhh.guess.account4app;

import java.util.ArrayList;
import java.util.List;

import org.hhhhhh.guess.Server;
import org.hhhhhh.guess.user.User;

import com.google.common.collect.Lists;

public class MainUI {

	public static class OptionItem {
		
		public String getId() {
			
		}
		
		public String getTitle() {
			
		}
		
		public String getDsc() {
			
		}
		public String getScale() {
			
		}
		public boolean isSelected() {
			
		}
	}

	public static class QuestionItem {
		
		public String getId() {
			
		}
		
		public String getDsc() {
			
		}
		public String getImg() {
			
		}
		
		public boolean isAnswered() {
			
		}
		
		public int getCount() {
			
		}
		
		public List<OptionItem> getOptions() {
			ArrayList<OptionItem> ls = Lists.newArrayList();
			ls.add(new OptionItem());
			//TODO LC
			return ls;
		}

	}

	public static class AdItem {
		public String getImg() {
			//TODO LC
			return "https://www.baidu.com/img/bd_logo1.png";
		}
		
		public String getUrl() {
			//TODO LC
			return "http://www.baidu.com";
		}
		
		public String getText() {
			//TODO LC
			return "我是呆萌的jjy";
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
			return 1;//TODO LC
		}
	}

	private final User user;

	public MainUI(User user) {
		this.user = user;
	}

	public RankListItem getFirst() {
		return new RankListItem(Server.getFirstOne());
	}

	public AdItem getAd2() {
		return new AdItem();//TODO LC
	}

	public AdItem getAd() {
		return new AdItem();//TODO LC
	}
	
	public int getRank() {
		
	}
	
	public double getJiangChi() {
		
	}
	
	public int getRound() {
		
	}
	
	public String getDay() {
		
	}
	
	public int getRemainSec() {
		
	}
	
	public int getYiYuCe() {
		
	}
	
	public int getJiFen() {
		return user.getJiFen();
	}
	
	public List<QuestionItem> getQuestions() {
		ArrayList<QuestionItem> ls = Lists.newArrayList();
		ls.add(new QuestionItem());
		//TODO LC
		return ls;
	}

}
