package org.hhhhhh.guess.hibernate.dto;


public class AnswersDto  {
	String username_question_id;
	String option_id;
	private int round;
	String date;

	public String getUsername_question_id() {
		return username_question_id;
	}

	public void setUsername_question_id(String username_question_id) {
		this.username_question_id = username_question_id;
	}

	public String getOption_id() {
		return option_id;
	}

	public void setOption_id(String option_id) {
		this.option_id = option_id;
	}


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}
}
