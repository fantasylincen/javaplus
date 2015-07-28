package org.hhhhhh.guess.hibernate.dto;

import java.io.Serializable;

public class AnswerDto  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1621271378870765414L;
	String usernameQuestionId;
	String username;
	String questionId;
	String optionHead;
	public String getUsernameQuestionId() {
		return usernameQuestionId;
	}
	public void setUsernameQuestionId(String usernameQuestionId) {
		this.usernameQuestionId = usernameQuestionId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getOptionHead() {
		return optionHead;
	}
	public void setOptionHead(String optionHead) {
		this.optionHead = optionHead;
	}
}
