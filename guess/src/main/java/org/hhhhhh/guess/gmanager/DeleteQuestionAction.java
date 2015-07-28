package org.hhhhhh.guess.gmanager;

import org.hhhhhh.guess.hibernate.dao.DbUtil;
import org.hhhhhh.guess.hibernate.dto.QuestionDto;
import org.hhhhhh.guess.util.ParameterUtil;

import com.opensymphony.xwork2.ActionSupport;

public class DeleteQuestionAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7446080789453466076L;

	@Override
	public final String execute() throws Exception {

		String questionId = ParameterUtil.getParameter("questionId");

		QuestionDto dto = DbUtil.get(QuestionDto.class, questionId);

		DbUtil.delete(dto);
		
		return SUCCESS;
	}

}