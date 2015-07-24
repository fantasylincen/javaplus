package org.hhhhhh.guess.gmanager;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hhhhhh.guess.hibernate.dao.DbUtil;
import org.hhhhhh.guess.hibernate.dto.ImageDto;

import com.opensymphony.xwork2.ActionSupport;

public class GetImageAction extends ActionSupport {

	private static final long serialVersionUID = -2182544777695553627L;
	protected HttpServletResponse response;
	protected HttpServletRequest request;
	protected HttpSession session;

	private String id;

	public InputStream getDownloadFile() throws Exception {
		response = ServletActionContext.getResponse();
		request = ServletActionContext.getRequest();
		session = request.getSession();

		byte[] data = getData();
		return new ByteArrayInputStream(data);
	}

	private byte[] getData() {
		ImageDto dto = DbUtil.get(ImageDto.class, id);
		return dto.getImage();
	}

	@Override
	public String execute() throws Exception {

		return SUCCESS;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}