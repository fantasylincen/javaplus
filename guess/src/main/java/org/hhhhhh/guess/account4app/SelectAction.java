package org.hhhhhh.guess.account4app;

import org.hhhhhh.guess.Server;
import org.hhhhhh.guess.action.JsonActionAfterRoleEnterGame;
import org.hhhhhh.guess.exception.GuessException;
import org.hhhhhh.guess.hibernate.dao.AnswersDao;
import org.hhhhhh.guess.hibernate.dao.Daos;
import org.hhhhhh.guess.hibernate.dto.AnswersDto;

import cn.javaplus.util.Util;

public class SelectAction extends JsonActionAfterRoleEnterGame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6542712148722350752L;

	String questionId;
	String optionId;

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getOptionId() {
		return optionId;
	}

	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}

	@Override
	protected Object run() {
		AnswersDao dao = Daos.getAnswersDao();
		if (dao.get(key()) != null) {
			throw new GuessException("不可修改答案");
		}
		
		AnswersDto dto = new AnswersDto();
		dto.setDate(Util.Time.getCurrentFormatTime());
		dto.setOption_id(getOptionId());
		dto.setRound(Server.getManager().getRound());
		dto.setUsername_question_id(key());
		dao.save(dto);
		
		return new SuccessResult();
	}

	private String key() {
		return user.getUsername() + ":" + getQuestionId();
	}

}
