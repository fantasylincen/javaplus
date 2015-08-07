package cn.vgame.b.gm;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.vgame.share.KeyValue;

import com.opensymphony.xwork2.ActionSupport;

public class UploadGameXmlAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4837211053124173756L;
	/**
	 * 
	 */
	private List<File> image; // 上传的文件
	private List<String> imageFileName; // 文件名称
	private List<String> imageContentType; // 文件类型

	@Override
	public String execute() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();

		HttpSession session = request.getSession();

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
				
				byte[] data = Arrays.copyOf(bf.array(), bf.limit());

				saveGameXml(data);
				
				fis.close();
			}
		}
		return SUCCESS;
	}


	private void saveGameXml(byte[] data) {
		KeyValue kv = cn.vgame.b.Server.getKeyValueForever();
		kv.set("GAME_XML", new String(data));
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