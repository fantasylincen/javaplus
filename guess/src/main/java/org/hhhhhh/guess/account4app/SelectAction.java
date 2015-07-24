package org.hhhhhh.guess.account4app;

import org.hhhhhh.guess.action.JsonActionAfterRoleEnterGame;
import org.hhhhhh.guess.exception.GuessException;
import org.hhhhhh.guess.hibernate.dao.DbUtil;
import org.hhhhhh.guess.hibernate.dto.QuestionDto;
import org.hhhhhh.guess.hibernate.dto.QuestionOptionDto;
import org.hhhhhh.guess.util.KeyValue;

public class SelectAction extends JsonActionAfterRoleEnterGame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6542712148722350752L;

	String questionId;
	String optionId;

	private QuestionDto dto;

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
		
		dto = DbUtil.get(QuestionDto.class, questionId);
		
		if(dto == null)
			throw new GuessException("问题不存在");
		
		KeyValue kv = user.getKeyValueForever();
		String key = "SELECTED:" + questionId;
		if (kv.getString(key) != null)
			throw new GuessException("不可修改答案");
		kv.set(key, optionId);
		
		addCount();
		
		return new SuccessResult();
	}

	private void addCount() {

		
		dto.setCount(dto.getCount() + 1);
		DbUtil.save(dto);		

		QuestionOptionDto d = DbUtil.get(QuestionOptionDto.class, questionId + ":" + optionId);
		d.setCount(d.getCount() + 1);
		DbUtil.save(d);
	}

}
