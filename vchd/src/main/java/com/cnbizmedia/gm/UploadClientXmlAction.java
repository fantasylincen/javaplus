package com.cnbizmedia.gm;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.javaplus.util.Util;

import com.cnbizmedia.Server;
import com.cnbizmedia.gen.dto.MongoGen.GameXmlFileDto;
import com.opensymphony.xwork2.ActionSupport;

public class UploadClientXmlAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3922288835914912063L;
	private List<File> image; // 上传的文件
	private List<String> imageFileName; // 文件名称
	private List<String> imageContentType; // 文件类型

	@Override
	public String execute() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();

		HttpSession session = request.getSession();

		String pId = (String) session.getAttribute("projectId");
		String zId = (String) session.getAttribute("zoneId");
		Util.Exception.checkNull(pId, "projectId is null");
		Util.Exception.checkNull(zId, "zId is null");

		Zone zone = Server.getProjectManager().getZone(session);

		ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
		List<File> files = getImage();
		if (files != null && files.size() > 0) {
			for (int i = 0; i < files.size(); i++) {

				FileInputStream fis = new FileInputStream(files.get(i));

				ByteBuffer bf = ByteBuffer.allocate(10240000);

				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					
					bf.put(Arrays.copyOf(buffer, len));
				}
				bf.flip();
				
				GameXmlFileDto dto = new GameXmlFileDto();
				dto.setData(Arrays.copyOf(bf.array(), bf.limit()));
				dto.setUpdateTime(System.currentTimeMillis());
				dto.setVersion(dto.getVersion() + 1);
				
				zone.saveClientXmlFile(dto);

				fis.close();

			}
		}
		zone.save();
		return SUCCESS;
	}


	public List<File> getImage() {
		return image;
	}

	public void setImage(List<File> image) {
		this.image = image;
	}

	public List<String> getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(List<String> imageFileName) {
		this.imageFileName = imageFileName;
	}

	public List<String> getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(List<String> imageContentType) {
		this.imageContentType = imageContentType;
	}
}