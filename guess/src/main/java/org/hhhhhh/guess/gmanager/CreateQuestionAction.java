package org.hhhhhh.guess.gmanager;

import java.io.File;
import java.util.List;

import org.hhhhhh.guess.exception.GuessException;
import org.hhhhhh.guess.hibernate.dao.DbUtil;
import org.hhhhhh.guess.hibernate.dto.ImageDto;
import org.hhhhhh.guess.hibernate.dto.QuestionDto;
import org.hhhhhh.guess.util.ParameterUtil;

import cn.javaplus.util.Util;

import com.opensymphony.xwork2.ActionSupport;

public class CreateQuestionAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public final String execute() throws Exception {

		String roundId = ParameterUtil.getParameter("roundId");

		String imageId = saveImage();

		QuestionDto dto = new QuestionDto();
		dto.setId(Util.ID.createId());
		dto.setContent(content.trim());
		dto.setDsc(dsc.trim());
		dto.setCreateTime(Util.Time.getCurrentFormatTime());
		dto.setRoundId(roundId);
		dto.setImageId(imageId);
		DbUtil.save(dto);

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