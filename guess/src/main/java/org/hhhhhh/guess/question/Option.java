package org.hhhhhh.guess.question;

import org.hhhhhh.guess.hibernate.dao.DbUtil;
import org.hhhhhh.guess.hibernate.dto.AnswerDto;
import org.hhhhhh.guess.hibernate.dto.QuestionDto;
import org.hhhhhh.guess.hibernate.dto.QuestionOptionDto;
import org.hhhhhh.guess.user.User;

public class Option {

	private final QuestionOptionDto dto;
	private final QuestionDto questionDto;

	public Option(QuestionDto questionDto, QuestionOptionDto dto) {
		this.questionDto = questionDto;
		this.dto = dto;
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

	public String getScaleText() {
		
		double percent = getScale();
		String format = String.format("%.0f", percent * 100);
		return format + "%";
	}

	
	/**
	 * 是否选定某个结果
	 */
	public boolean isSelected(User user) {
		
		AnswerDto as = DbUtil.get(AnswerDto.class, user.getUsername() + ":" + questionDto.getId());
		if (as == null)
			return false;

		return as.getOptionHead().equals(dto.getHead());
	}

	public double getScale() {

		int count = questionDto.getCount();
		double percent;
		if (count == 0)
			percent = 0;
		else
			percent = (dto.getCount() + 0f / count);
		return percent;
	}
}
