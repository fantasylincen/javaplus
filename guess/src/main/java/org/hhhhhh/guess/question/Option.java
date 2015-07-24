package org.hhhhhh.guess.question;

import org.hhhhhh.guess.exception.GuessException;
import org.hhhhhh.guess.hibernate.dto.QuestionDto;
import org.hhhhhh.guess.hibernate.dto.QuestionOptionDto;
import org.hhhhhh.guess.user.User;
import org.hhhhhh.guess.util.KeyValue;

public class Option {

	private final QuestionOptionDto dto;
	private final User user;
	private final QuestionDto questionDto;

	public Option(QuestionDto questionDto, QuestionOptionDto dto, User user) {
		this.questionDto = questionDto;
		this.dto = dto;
		this.user = user;
	}

	public String getId() {
		return dto.getHead();
	}

	public String getTitle() {
		return dto.getDsc();
	}

	public String getDsc() {
		return dto.getDsc();
	}

	public String getScale() {
		int count = questionDto.getCount();
		double percent;
		if (count == 0)
			percent = 0;
		else
			percent = (dto.getCount() + 0f / count);
		String format = String.format("%.0f", percent * 100);
		return format + "%";
	}

	
	/**
	 * 是否选定某个结果
	 */
	public boolean isSelected() {

		KeyValue kv = user.getKeyValueForever();
		String key = "SELECTED:" + questionDto.getId();
		String head = kv.getString(key);

		return dto.getHead().equals(head);
	}
}
