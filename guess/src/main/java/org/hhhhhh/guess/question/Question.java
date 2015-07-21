package org.hhhhhh.guess.question;

import java.util.ArrayList;
import java.util.List;

import org.hhhhhh.guess.account4app.MainUI.OptionItem;

import com.google.common.collect.Lists;

public class Question {

	public String getId() {
		return "111111";
	}
	
	public String getDsc() {

		return "111111";
	}
	public String getImg() {

		return "111111";
	}
	
	public boolean isAnswered() {
		return false;
		
	}
	
	public int getCount() {
	return 1;	
	}

	public List<Option> getOptions() {
		ArrayList<Option> ls = Lists.newArrayList();
		ls.add(new Option());
		ls.add(new Option());
		ls.add(new Option());
		return ls;
	}
	
}
