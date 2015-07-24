package org.hhhhhh.guess.gmanager;

import java.io.File;
import java.util.List;

import org.hhhhhh.guess.exception.GuessException;
import org.hhhhhh.guess.hibernate.dao.DbUtil;
import org.hhhhhh.guess.hibernate.dto.ImageDto;
import org.hhhhhh.guess.hibernate.dto.QuestionDto;
import org.hhhhhh.guess.util.ParameterUtil;

import com.opensymphony.xwork2.ActionSupport;

public class SetQuestionAction extends ActionSupport implements QuestionOption {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1879233316110030622L;

	@Override
	public final String execute() throws Exception {

		String questionId = ParameterUtil.getParameter("questionId");

		QuestionDto dto = DbUtil.get(QuestionDto.class, questionId);

		if (image != null)
			updateImageData(dto);

		dto.setContent(content.trim());
		dto.setDsc(dsc.trim());

		DbUtil.save(dto);
		
		OptionSetter setter = new OptionSetter();
		setter.setOptions(this, questionId);
		
		
		return SUCCESS;
	}

	private void updateImageData(QuestionDto dto) {
		String imageId = dto.getImageId();
		ImageDto idto = DbUtil.get(ImageDto.class, imageId);
		idto.setImage(new ImageReader().read(image.get(0)));
		DbUtil.save(idto);
		
		
		
	}

	private String content;
	private String dsc;
	private List<File> image;
	String optionA;
	String optionB;
	String optionC;
	String optionD;
	String optionE;
	String optionF;

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public String getOptionE() {
		return optionE;
	}

	public void setOptionE(String optionE) {
		this.optionE = optionE;
	}

	public String getOptionF() {
		return optionF;
	}

	public void setOptionF(String optionF) {
		this.optionF = optionF;
	}

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
		if (image == null) {
			throw new GuessException("图片不能为空");
		}
		this.image = image;
	}

}