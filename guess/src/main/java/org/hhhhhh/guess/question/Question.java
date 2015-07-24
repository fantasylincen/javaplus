package org.hhhhhh.guess.question;

import java.util.ArrayList;
import java.util.List;

import org.hhhhhh.guess.hibernate.dao.DbUtil;
import org.hhhhhh.guess.hibernate.dto.QuestionDto;
import org.hhhhhh.guess.hibernate.dto.QuestionOptionDto;
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

	@SuppressWarnings("unchecked")
	public List<Option> getOptions() {
		ArrayList<Option> ls = Lists.newArrayList();
		List<QuestionOptionDto> find = DbUtil.find("QuestionOptionDto", "questionId", dto.getId());
		for (QuestionOptionDto dto : find) {
			ls.add(new Option(this.dto, dto, user));
		}
		return ls;
	}
	
}
