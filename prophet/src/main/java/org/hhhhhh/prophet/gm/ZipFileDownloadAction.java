package org.hhhhhh.prophet.gm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.hhhhhh.prophet.Server;

import com.opensymphony.xwork2.ActionSupport;

//文件下载  
public class ZipFileDownloadAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4257083460855721752L;

	private String projectId;
	private String zoneId;

	// 返回一个输入流，作为一个客户端来说是一个输入流，但对于服务器端是一个 输出流
	public InputStream getDownloadFile() throws Exception {
		byte[] data = getData();
		data = compress(data);
		return new ByteArrayInputStream(data);
	}

	private byte[] compress(byte[] data) {
		byte[] b = null;

		try {

			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			ZipOutputStream zip = new ZipOutputStream(bos);

			ZipEntry entry = new ZipEntry("game.xml");

			entry.setSize(data.length);

			zip.putNextEntry(entry);

			zip.write(data);

			zip.closeEntry();

			zip.close();

			b = bos.toByteArray();

			bos.close();

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		return b;
	}

	private byte[] getData() {
		Zone zone = Server.getProjectManager().getZone(projectId, zoneId);
		byte[] data = zone.getGameXml().getData();
		return data;
	}

	@Override
	public String execute() throws Exception {

		return SUCCESS;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

}