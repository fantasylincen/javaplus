package org.hhhhhh.guess.gmanager;

import java.io.File;
import java.util.List;

import org.hhhhhh.guess.exception.GuessException;
import org.hhhhhh.guess.hibernate.dao.DbUtil;
import org.hhhhhh.guess.hibernate.dto.ImageDto;
import org.hhhhhh.guess.hibernate.dto.QuestionDto;
import org.hhhhhh.guess.util.ParameterUtil;

import cn.javaplus.log.Log;

import com.opensymphony.xwork2.ActionSupport;

public class SetQuestionAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1879233316110030622L;

	@Override
	public final String execute() throws Exception {
		if(image == null) {
			throw new GuessException("图片不能为空");
		}
		String questionId = ParameterUtil.getParameter("questionId");

		QuestionDto dto = DbUtil.get(QuestionDto.class, questionId);

		updateImageData(dto);

		dto.setContent(content.trim());
		dto.setDsc(dsc.trim());

		DbUtil.save(dto);
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
		if(image == null) {
			throw new GuessException("图片不能为空");
		}
		this.image = image;
	}

}