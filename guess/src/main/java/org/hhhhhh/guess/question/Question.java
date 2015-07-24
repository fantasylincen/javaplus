package org.hhhhhh.guess.question;

import java.util.ArrayList;
import java.util.List;

import org.hhhhhh.guess.hibernate.dto.QuestionDto;
import org.hhhhhh.guess.user.User;

import com.google.common.collect.Lists;

public class Question {

	private final QuestionDto dto;
	private final User user;

	public Question(User user, QuestionDto dto) {
		this.user = user;
		this.dto = dto;
	}

	public String getId() {
		return dto.getId();
	}
	
	public String getDsc() {

		return dto.getDsc();
	}
	
	public String getImg() {
		return "gmanager/getImage?id=" + dto.getId();
	}
	
	public boolean isAnswered() {
		return user.getKeyValueForever().getBoolean("IS_ANSWERED:" + getId());
	}
	
	public int getCount() {
		return dto.getCount();
	}

	public List<Option> getOptions() {
		ArrayList<Option> ls = Lists.newArrayList();
		ls.add(new Option());
		ls.add(new Option());
		return ls;
	}
	
}
