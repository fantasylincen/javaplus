package org.hhhhhh.guess.hibernate.dto;

public class QuestionOptionDto {

	String questionIdAndHead;
	String questionId;
	String head;
	String dsc;
	String createTime;
	int count;

	public String getQuestionIdAndHead() {
		return questionIdAndHead;
	}

	public void setQuestionIdAndHead(String questionIdAndHead) {
		this.questionIdAndHead = questionIdAndHead;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getDsc() {
		return dsc;
	}

	public void setDsc(String dsc) {
		this.dsc = dsc;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
