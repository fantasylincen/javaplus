package org.hhhhhh.guess.hibernate.dto;

public class QuestionDto {

	String id;
	String roundId;
	String content;
	String createTime;
	private String imageId;
	private String dsc;
	private int jiFen;
	/**
	 * 参与回答的人数
	 */
	private int count;

	/**
	 * 正确答案的head A/B/C/D/E/F
	 */
	private String answerOptionHead;

	public String getId() {
		return id;
	}

	public String getRoundId() {
		return roundId;
	}

	public void setRoundId(String roundId) {
		this.roundId = roundId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDsc() {
		return dsc;
	}

	public void setDsc(String dsc) {
		this.dsc = dsc;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getJiFen() {
		return jiFen;
	}

	public void setJiFen(int jiFen) {
		this.jiFen = jiFen;
	}

	public String getAnswerOptionHead() {
		if (answerOptionHead == null || answerOptionHead.equals("null"))
			answerOptionHead = "";
		return answerOptionHead;
	}

	public void setAnswerOptionHead(String answerOptionHead) {
		this.answerOptionHead = answerOptionHead;
	}

}
