package org.hhhhhh.guess.gmanager;

import java.io.File;
import java.util.List;

import org.hhhhhh.guess.exception.GuessException;
import org.hhhhhh.guess.hibernate.dao.DbUtil;
import org.hhhhhh.guess.hibernate.dto.ImageDto;
import org.hhhhhh.guess.hibernate.dto.QuestionOptionDto;
import org.hhhhhh.guess.hibernate.dto.QuestionDto;
import org.hhhhhh.guess.util.ParameterUtil;

import cn.javaplus.util.Util;

import com.opensymphony.xwork2.ActionSupport;

public class CreateQuestionAction extends ActionSupport implements
		QuestionOption {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String optionA;
	String optionB;
	String optionC;
	String optionD;
	String optionE;
	String optionF;

	public String getOptionA() {
		return optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public String getOptionE() {
		return optionE;
	}

	public String getOptionF() {
		return optionF;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public void setOptionE(String optionE) {
		this.optionE = optionE;
	}

	public void setOptionF(String optionF) {
		this.optionF = optionF;
	}

	@Override
	public final String execute() throws Exception {

		String roundId = ParameterUtil.getParameter("roundId");

		String imageId = saveImage();

		QuestionDto dto = new QuestionDto();
		String questionId = Util.ID.createId();

		dto.setId(questionId);
		dto.setContent(content.trim());
		dto.setDsc(dsc.trim());
		dto.setCreateTime(Util.Time.getCurrentFormatTime());
		dto.setRoundId(roundId);
		dto.setImageId(imageId);
		DbUtil.save(dto);

		OptionSetter setter = new OptionSetter();
		setter.setOptions(this, questionId);

		return SUCCESS;
	}

	private String saveImage() {
		byte[] read = new ImageReader().read(getImage().get(0));
		ImageDto dto = new ImageDto();
		dto.setId(Util.ID.createId());
		dto.setImage(read);
		DbUtil.save(dto);
		return dto.getId();
	}

	private String content;
	private String dsc;
	private List<File> image;


	public String getDsc() {
		return dsc;
	}

	public void setDsc(String dsc) {
		if (dsc == null)
			throw new GuessException("说明不能为空");
		this.dsc = dsc;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		if (content == null)
			throw new GuessException("内容不能为空");
		this.content = content;
	}

	public List<File> getImage() {
		return image;
	}

	public void setImage(List<File> image) {
		this.image = image;
	}

}