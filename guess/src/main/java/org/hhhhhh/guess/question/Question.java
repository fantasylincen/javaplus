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

	public Question(QuestionDto dto) {
		this.dto = dto;
	}

	public String getAnswerOptionHead() {
		return dto.getAnswerOptionHead();
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
	
	public boolean isAnswered(User user) {
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
			ls.add(new Option(this.dto, dto));
		}
		return ls;
	}

	public int getJiFen() {
		return dto.getJiFen();
	}
	
}
