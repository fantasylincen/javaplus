package org.hhhhhh.guess.hibernate.dao;

import org.hhhhhh.guess.hibernate.dto.AnswerDto;

public class AnswersDao {

	public AnswerDto get(String key) {
		return DbUtil.get(AnswerDto.class, key);
	}

}
